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
import sokrates.sovelluslogiikka.Kysymys;

/**
 * Vastaa tekstitiedoston luomisesta ja siihen kirjoittelusta kyselytyksen
 * jälkeen.
 *
 * @author Teo
 */
public class TiedostonKirjoittaja {

    public void luoKyselyTiedostoNimelta(String nimi) throws IOException {
        try {
            File file = new File("src/inquiries/", nimi + ".txt");
            try (FileWriter writer = new FileWriter(file)) {
            }
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(KyseleKysely.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void poistaKyselyTiedosto(File kysely) {
        if (kysely.exists()) {
            String poistettiin = kysely.getAbsolutePath();
//             tyhjennaKyselyTiedosto(kysely);
            boolean onnistuiko = kysely.delete();
            System.out.println("Poistettiin " + poistettiin + " " + onnistuiko);
        }
    }

//    private void tyhjennaKyselyTiedosto(File kysely) {
//        try {
//            FileWriter writer = new FileWriter(kysely);
//            writer.write("");
//            writer.close();
//        } catch (Exception ex) {
//            System.out.println("Kyselyä ei ole");
//        }
//    }

    public void kirjoitaTiedostoonRivit(File kysely, String s1, String s2, String s3) throws FileNotFoundException, UnsupportedEncodingException {
        if (kysely.exists()) {
            try {
                try (BufferedWriter output = new BufferedWriter(new FileWriter(kysely, true))) {
                    output.newLine();
                    output.append(s1);
                    output.newLine();
                    output.append(s2);
                    output.newLine();
                    output.append(s3);
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

        try {
            String ekanKysymyksenVastaus = kysymykset.get(0).getVastaus();
            String tekstiTiedostonNimi = ekanKysymyksenVastaus;
            writer = new PrintWriter(tekstiTiedostonNimi + ".txt", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(KyseleKysely.class.getName()).log(Level.SEVERE, null, ex);
        }

        kirjoitaKysymyksetVastauksineenLuotuunTiedostoon(writer, kysymykset);
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
            writer.println(kysymys.getKysymysNykyisellaKielella());
            writer.println();
            writer.println("    " + kysymys.getVastaus());
            writer.println();
            writer.println();
        }
        writer.close();
    }
}