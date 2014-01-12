package sokrates.tiedostonkasittely;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import sokrates.sovelluslogiikka.Kysely;
import sokrates.sovelluslogiikka.KyselyHallinta;
import sokrates.sovelluslogiikka.Kysymys;

/**
 * TiedostonLukija vastaa tekstitiedostojen (kyselytiedostojen) lukemisesta ja
 * tulkitsemisesta kyselyiksi ja kysymyksiksi.
 *
 * @author Teo
 */
public class TiedostonLukija {

    /**
     * Metodi lukee parametrina saamansa kansion sisältämien tekstitiedostojen
     * nimet ja palauttaa ne sanalistana.
     *
     * @param directory Kansio jonka sisältämien tekstitiedostojen nimet
     * halutaan kyselyiden nimiksi.
     * @return Sanalista kansion sisältämien tekstitiedostojen nimistä.
     */
    public List<String> lueKyselyTiedostojenNimet(String directory) {
        List<String> nimet = new ArrayList<>();
        File dir = new File(directory);

        if (dir.isDirectory() && dir.canRead()) {
            for (File file : dir.listFiles()) {
                String kyselyTiedostonNimi = file.getName();

                if (kyselyTiedostonNimi.endsWith((".txt"))) {
                    nimet.add(kyselyTiedostonNimi.substring(0, kyselyTiedostonNimi.length() - 4));
                }
            }
        }

//      System.out.println(nimet);
        return nimet;
    }

    /**
     * Metodi lisää parametrina saamansa kyselytiedostojen sisällöt hallintaan
     * kysymyksiksi tulkittuina alempi metodi apunaan. Tämä metodi kerää kunkin
     * tiedoston sisältämät rivit listaksi jonka syöttää sitten alemmalle
     * metodille.
     *
     * @param kyselyTiedostot Lista tiedostoista jotka on tarkoitus tulkita
     * hallintaan.
     * @param hallinta jonne tiedostot on tarkoitus tulkita.
     * @throws FileNotFoundException
     */
    public void lisaaKyselyTiedostojenSisallotKysymyksiksi(List<File> kyselyTiedostot, KyselyHallinta hallinta) throws FileNotFoundException {
        for (File kyselyTiedosto : kyselyTiedostot) {
            ArrayList<String> riviLista = lueKyselyTiedostoRiviListaksi(kyselyTiedosto);
            tulkitseKysymyksiksi(riviLista, kyselyTiedosto, hallinta);
        }
    }

    public ArrayList<String> lueKyselyTiedostoRiviListaksi(File kyselyTiedosto) {
        ArrayList<String> riviLista = new ArrayList<>();

        try {
            Scanner lukija = new Scanner(new FileInputStream(kyselyTiedosto), "UTF-8");

            if (lukija.hasNextLine()) {
                lukija.nextLine();
            } else {
                return null;
            }

            while (lukija.hasNextLine()) {
                riviLista.add(lukija.nextLine());
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TiedostonLukija.class.getName()).log(Level.SEVERE, null, ex);
        }

        return riviLista;
    }

    /**
     * Metodi tulkitsee listana saamistaan riveistä joka ensimmäisen suomi-,
     * joka toisen englanti- ja joka kolmannen esimerkkivastausosaksi kysymystä,
     * ja lisää kysymykset yksi kerrallaan kohdekyselyyn KyselyHallinnassa.
     *
     * Metodi varautuu myös epätarkoituksenmukaisesti rakentuneisiin
     * kyselytiedostoihin tulkiten tarvittaessa puuttuviin kohtiin tyhjää (""),
     * ja saattaa siten luoda myös puutteellisia kysymyksiä, mutta parempi se
     * kuin kaatua.
     *
     * @param riviLista Kumppanimetodin tuottama lista riveistä joita tiedoston
     * sisältä löytyi.
     * @param kyselyTiedosto Tiedosto jota vastaavaa kyselyä ollaan tässä
     * tiedostosta hallintaan kääntämässä.
     * @param hallinta jonne ollaan vääntämässä.
     */
    public void tulkitseKysymyksiksi(ArrayList<String> riviLista, File kyselyTiedosto, KyselyHallinta hallinta) {
        int i = 0;

        while (i < riviLista.size()) {
            String kyselyTiedostonNimi = kyselyTiedosto.getName();
            String kyselynNimi = kyselyTiedostonNimi.substring(0, kyselyTiedostonNimi.length() - 4);

            String kysymysSuomeksi = riviLista.get(i);
            String kysymysEnglanniksi = "";
            String esimerkkiVastaus = "";

            if (i < riviLista.size() - 1) {
                kysymysEnglanniksi = riviLista.get(i + 1);
                if (i < riviLista.size() - 2) {
                    esimerkkiVastaus = riviLista.get(i + 2);
                }
            }

            Kysely kohdeKysely = hallinta.getKyselyNimenPerusteella(kyselynNimi);
            kohdeKysely.lisaaKysymys(new Kysymys(kysymysSuomeksi, kysymysEnglanniksi, esimerkkiVastaus));

            if (i < riviLista.size() - 2) {
                i += 3;
            } else if (i < riviLista.size() - 1) {
                i += 2;
            } else {
                i += 1;
            }
        }
    }

    /**
     * Metodi palauttaa parametrina saamaansa nimeä vastaavan (kansion
     * inquiries) kyselytiedoston.
     *
     * @param nimi Nimi jota vastaava kyselytiedosto halutaan.
     * @return Kyselytiedosto (File), tai null jos mikään ei täsmää.
     */
    public File getNimeaVastaavaKyselyTiedosto(String nimi) {
        File dir = new File("inquiries/");

        for (File file : dir.listFiles()) {
            String kyselyTiedostonNimi = file.getName();

            if (kyselyTiedostonNimi.equals(nimi + ".txt")) {
                return file;
            }
        }

        return null;
    }

    /**
     * Metodi palauttaa parametrina saamansa sanalistan sisältämiä nimiä
     * vastaavat (kansion inquiries) kyselytiedostot tiedostolistana.
     *
     * @param nimet Lista nimistä joita vastaavat kyselytiedostot halutaan.
     * @return Lista nimiä vastaavista tiedostoista.
     */
    public List<File> getNimiaVastaavatKyselyTiedostot(List<String> nimet) {
        List<File> kyselyTiedostot = new ArrayList<>();

        for (String nimi : nimet) {
            File file = getNimeaVastaavaKyselyTiedosto(nimi);
            kyselyTiedostot.add(file);
        }

        return kyselyTiedostot;
    }
}