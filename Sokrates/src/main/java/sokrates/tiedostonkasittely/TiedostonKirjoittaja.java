package sokrates.tiedostonkasittely;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sokrates.komennot.KyseleKysely;
import sokrates.sovelluslogiikka.Asetukset;
import sokrates.sovelluslogiikka.Kieli;
import sokrates.sovelluslogiikka.Kysely;
import sokrates.sovelluslogiikka.Kysymys;

/**
 * TiedostonKirjoittaja vastaa tekstitiedostojen luomisesta, niihin
 * kirjoittamisesta ja niiden poistamisesta. Se luo kyselytiedostot, sekä
 * vastauskoosteet kyselytysten jälkeen. Se myös kirjoittaa käyttäjän lisäämät
 * kysymykset kyselytiedostoihin, jotta ne säilyvät seuraavaankin
 * käynnistyskertaan.
 *
 * @author Teo
 */
public class TiedostonKirjoittaja {

    /**
     * Metodi luo inquiries-kansioon kyselytiedoston parametrinaan saamalla
     * nimellä.
     *
     * @param nimi jolla kyselytiedosto luodaan, .txt päätteenään.
     * @throws IOException
     */
    public void luoKyselyTiedostoNimelta(String nimi) throws IOException {
        if (new File("inquiries/").exists()) {
            try {
                File file = new File("inquiries/", nimi + ".txt");
                FileWriter writer = new FileWriter(file);
            } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                Logger.getLogger(KyseleKysely.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("HUOM. kansiota 'inquiries' ei ole olemassa."
                    + "Se pitää luoda juurikansioon jotta kyselyt tallentuvat.");
        }
    }

    /**
     * Metodi poistaa parametrinaan saamansa kyselytiedoston.
     *
     * @param kysely Poistettava kyselytiedosto
     */
    public void poistaKyselyTiedosto(File kysely) {
        if (kysely.exists()) {
//            String poistettiin = kysely.getAbsolutePath();
//             tyhjennaKyselyTiedosto(kysely);
            boolean onnistuiko = kysely.delete();
//            System.out.println("Poistettiin " + poistettiin + " " + onnistuiko);
        }
    }

    /**
     * Tyhjentää kyselytiedoston
     *
     * @param kysely Tyhjennettävä kyselytiedosto
     */
    private void tyhjennaKyselyTiedosto(File kysely) {
        try {
            try (FileWriter writer = new FileWriter(kysely)) {
                writer.write("");
            }
        } catch (Exception ex) {
            System.out.println("Kyselyä ei ole");
        }
    }

    /**
     * Metodi lisää käyttäjän luoman kysymyksen parametrinaan saamaan
     * kyselytiedostoon. Kysymyksen tiedot kirjoitetaan tiedostoon entisen
     * sisällön jatkoksi.
     *
     * @param kysely Kyselytiedosto johon kysymys lisätään
     * @param s1 Käyttäjän antama kysymys suomeksi
     * @param s2 Käyttäjän antama kysymys englanniksi
     * @param s3 Käyttäjän antama esimerkkivastaus kysymykseen
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    public void kirjoitaTiedostoonRivit(File kysely, ArrayList<String> rivit) throws FileNotFoundException, UnsupportedEncodingException {
        if (kysely.exists()) {
            try {
                try (BufferedWriter output = new BufferedWriter(new FileWriter(kysely, true))) {
                    for (String rivi : rivit) {
                        output.newLine();
                        output.append(rivi);
                    }
                    output.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(TiedostonKirjoittaja.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Metodi antaa tekstitiedoston nimeksi aina käyttäjän ensimmäisen
     * vastauksen ensimmäiseen kysymykseen, mikä selvennetään myös kysymys
     * käyttäjälle esitettäessä.
     *
     * Tiedoston luotuaan metodi kirjoituttaa sinne kysymykset vastauksineen
     * kutsumalla alempaa toista metodia.
     *
     * @param kysymykset Lista kysymyksistä joiden pohjalta tekstitiedosto
     * luodaan
     */
    public void luoTiedosto(ArrayList<Kysymys> kysymykset) {
        PrintWriter writer = null;

        if (new File("completed/").exists()) {
            try {
                String ekanKysymyksenVastaus = kysymykset.get(0).getVastaus();
                String tekstiTiedostonNimi = ekanKysymyksenVastaus;
                writer = new PrintWriter("completed/" + tekstiTiedostonNimi + ".txt", "UTF-8");
            } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                Logger.getLogger(KyseleKysely.class.getName()).log(Level.SEVERE, null, ex);
            }

            kirjoitaKysymyksetVastauksineenLuotuunTiedostoon(writer, kysymykset);
        } else {
            System.out.println("HUOM. kansiota 'completed' ei ole olemassa."
                    + "Se pitää luoda juurikansioon jotta kyselytykset tallentuvat.");
        }
    }

    /**
     * Metodi kirjoittaa luotuun tekstitiedostoon jokaisen kysymyksen ja sen
     * ensimmäisen vastauksen sopivin rivinvaihdoin.
     *
     * @param writer Kirjoittaja
     * @param kysymykset Lista kirjoitettavista kysymyksistä, jotka muistavat
     * vastauksensa.
     */
    private void kirjoitaKysymyksetVastauksineenLuotuunTiedostoon(PrintWriter writer, ArrayList<Kysymys> kysymykset) {
        for (Kysymys kysymys : kysymykset) {
            writer.println(kysymys.getKysymysKielella(Asetukset.getKieli()));
            writer.println();
            writer.println("    " + kysymys.getVastaus());
            writer.println();
            writer.println();
        }

        writer.close();
    }

    /**
     * Metodi poistaa parametrina saamastaan (parametrina saamansa tiedoston)
     * rivilistata sellaisen rivin ja kaksi seuraavaa, jotka vastaavat
     * kysymyksen tietoja.
     *
     * Sitten metodi tyhjennyttää kyselytiedoston ja kirjoituttaa sinne
     * kysymykset uudestaan lukuunottamatta poistettuja.
     *
     * @param kohdeKyselyTiedosto Kyselytiedosto, josta kysymyksiä poistetaan.
     * @param riviLista Rivit jotka kyselystä poistetaan.
     * @param kysymys Kysymys jonka tietoja vastaavat rivit poistetaan.
     */
    public void poistaKyselystaKysymys(File kohdeKyselyTiedosto, ArrayList<String> riviLista, Kysymys kysymys) {
        try {
            ArrayList<String> poistettavatRivit = new ArrayList<>();
            ArrayList<String> uusiRiviLista = riviLista;
            String poistettavaSuomi = kysymys.getKysymysKielella(Kieli.SUOMI);
            String poistettavaEnglanti = kysymys.getKysymysKielella(Kieli.ENGLANTI);
            String poistettavaEsim = kysymys.getEsimerkkiVastaus();

            int i = 0;

            while (i < riviLista.size() - 2) {
                if (riviLista.get(i).equals(poistettavaSuomi)
                        && riviLista.get(i + 1).equals(poistettavaEnglanti)
                        && riviLista.get(i + 2).equals(poistettavaEsim)) {
                    poistettavatRivit.add(riviLista.get(i));
                    poistettavatRivit.add(riviLista.get(i + 1));
                    poistettavatRivit.add(riviLista.get(i + 2));
                }

                i++;
            }

            for (String rivi : poistettavatRivit) {
                uusiRiviLista.remove(rivi);
            }

            tyhjennaKyselyTiedosto(kohdeKyselyTiedosto);

            kirjoitaTiedostoonRivit(kohdeKyselyTiedosto, uusiRiviLista);
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(TiedostonKirjoittaja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}