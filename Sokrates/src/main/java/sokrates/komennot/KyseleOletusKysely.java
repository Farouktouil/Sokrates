package sokrates.komennot;

import java.util.ArrayList;
import sokrates.sovelluslogiikka.KyselyHallinta;
import sokrates.sovelluslogiikka.Kysymys;
import sokrates.tiedostonkasittely.TiedostonKirjoittaja;
import sokrates.util.Lukija;

public class KyseleOletusKysely extends Komento {

    public KyseleOletusKysely(Lukija lukija, KyselyHallinta hallinta, String nimi, String selite) {
        super(lukija, hallinta, nimi, selite);
    }

    @Override
    public boolean suorita() {
        ArrayList<Kysymys> kysymykset = hallinta.getOletusKysely().getKysymykset();

        if (kysymykset.isEmpty()) {
            System.out.println("Kyselyssä ei ole yhtään kysymystä.");
        } else {
            boolean examples = hallinta.getExamples();
            kysele(lukija, examples, kysymykset);
        }

        return false; // lopettaa siis ohjelman joka tapauksessa
    }

    public void kysele(Lukija lukija, boolean examples, ArrayList<Kysymys> kysymykset) {

        for (Kysymys kysymys : kysymykset) {
            System.out.println(kysymys.getKysymysNykyisellaKielella());
            if (examples) {
                System.out.println("    (esim. " + kysymys.getEsimerkkiVastaus() + ")");
            }
            System.out.print("\n    ");
            String kayttajanVastaus = lukija.lueMerkkijono();
            System.out.println();

            kysymys.lisaaVastaus(kayttajanVastaus);
        }

        new TiedostonKirjoittaja().luoTiedosto(kysymykset);
    }
}