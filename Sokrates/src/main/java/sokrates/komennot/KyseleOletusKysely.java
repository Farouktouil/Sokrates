package sokrates.komennot;

import java.util.ArrayList;
import sokrates.sovelluslogiikka.KyselyHallinta;
import sokrates.sovelluslogiikka.Kysymys;
import sokrates.tiedostonkasittely.TiedostonKirjoittaja;
import sokrates.util.Lukija;

/**
 * Komento KyseleOletusKysely tulostaa käyttäjälle KyselyHallinnassa
 * oletuskyselynä olevan kyselyn sisältämät kysymykset yksi kerrallaan, ja
 * välissä jokaiseen kysymykseen liitetään käyttäjän antama vastaus.
 *
 * Lopuksi käsketään komennon (konstruktorikutsun yhteydessään luomaa ja)
 * muistamaa TiedostonKirjoittajaa luomaan tekstitiedosto äsken kysytyistä
 * kysymyksistä.
 *
 * @author Teo
 */
public class KyseleOletusKysely extends Komento {

    private TiedostonKirjoittaja tk;

    public KyseleOletusKysely(Lukija lukija, KyselyHallinta hallinta, String nimi, String selite) {
        super(lukija, hallinta, nimi, selite);
        this.tk = new TiedostonKirjoittaja();
    }

    /**
     * Kun KyseleOletusKysely suoritetaan, poimitaan hallinnasta nykyisen
     * oletuskyselyn kysymykset ArrayListiksi. Jos lista on epätyhjä,
     * kysellään() kysymykset nykyisen esimerkkiasetuksen kanssa.
     *
     * @return false Joka tapauksessa, eli kyselytyksen jälkeen ohjelma päättyy
     * oli kyselyssä kysymyksiä tai ei.
     */
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

    private void kysele(Lukija lukija, boolean examples, ArrayList<Kysymys> kysymykset) {

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

        this.tk.luoTiedosto(kysymykset);
    }
}