package sokrates.komennot;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sokrates.sovelluslogiikka.Kysely;
import sokrates.sovelluslogiikka.KyselyHallinta;
import sokrates.sovelluslogiikka.Kysymys;
import sokrates.util.Lukija;

public class KyseleOletusKysely extends Komento {

    public KyseleOletusKysely(Lukija lukija, KyselyHallinta hallinta, String nimi, String selite) {
        super(lukija, hallinta, nimi, selite);
    }

    @Override
    public boolean suorita() {
        boolean examples = hallinta.getExamples();

        Kysely oletuskysely = hallinta.getOletusKysely();
        ArrayList<Kysymys> kysymykset = oletuskysely.getKysymykset();

        if (kysymykset.isEmpty()) {
            System.out.println("Kyselyssä ei ole yhtään kysymystä.");
        } else {
            kysele(lukija, examples, kysymykset);
        }

        return false;
    }

    public void kysele(Lukija lukija, boolean examples, ArrayList<Kysymys> kysymykset) {

        System.out.println();

        for (Kysymys kysymys : kysymykset) {
            System.out.println(kysymys.getKysymys());
            if (examples) {
                System.out.println("  (esim. " + kysymys.getEsimerkkiVastaus() + ")");
            }

            System.out.print("  ");
            String kayttajanVastaus = lukija.lueMerkkijono();
            System.out.println();
            kysymys.lisaaVastaus(kayttajanVastaus);
        }

        luoTiedosto(kysymykset);
    }

    public void luoTiedosto(ArrayList<Kysymys> kysymykset) {
        PrintWriter writer = null;

        try {
            String ekanKysymyksenEkaVastaus = kysymykset.get(0).getVastaukset().get(0);
            writer = new PrintWriter(ekanKysymyksenEkaVastaus + ".txt", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(KyseleOletusKysely.class.getName()).log(Level.SEVERE, null, ex);
        }

        kirjoitaKysymyksetVastauksineenLuotuunTiedostoon(writer, kysymykset);
    }

    public void kirjoitaKysymyksetVastauksineenLuotuunTiedostoon(PrintWriter writer, ArrayList<Kysymys> kysymykset) {
        for (Kysymys kysymys : kysymykset) {
            writer.println(kysymys.getKysymys());
            writer.println("");
            writer.println("    " + kysymys.getVastaukset().get(0));
            writer.println("");
            writer.println("");
        }

        writer.close();
    }
}