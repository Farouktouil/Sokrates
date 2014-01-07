package sokrates.komennot;

import java.util.ArrayList;
import sokrates.kayttoliittyma.Tulostamo;
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

    public KyseleOletusKysely(Lukija lukija, KyselyHallinta hallinta, String nimi, String seliteSuomeksi, String seliteEnglanniksi) {
        super(lukija, hallinta, nimi, seliteSuomeksi, seliteEnglanniksi);
        this.tk = new TiedostonKirjoittaja();
    }

    /**
     * Kun KyseleOletusKysely suoritetaan, poimitaan hallinnasta nykyisen
     * oletuskyselyn kysymykset ArrayListiksi. Jos lista on epätyhjä,
     * kysellään() kysymykset nykyisen esimerkkiasetuksen kanssa.
     *
     * @return true Jotta Sovelluksen komentolooppi voisi jatkua
     */
    @Override
    public boolean suorita() {
        if (hallinta.getOletusKysely() == null) {
            System.out.println(Tulostamo.oletusKyselyaEiOleValittu());
            return true;
        }

        ArrayList<Kysymys> kysymykset = hallinta.getOletusKysely().getKysymykset();

        if (kysymykset.isEmpty()) {
            System.out.println(Tulostamo.kyselyssaEiOleYhtaanKysymysta());
            return true;
        } else {
            boolean examples = hallinta.getExamples();
            kysele(lukija, examples, kysymykset);
        }

        String tekstitiedostonNimi = kysymykset.get(0).getVastaukset().get(0);
        System.out.println(Tulostamo.kyselyOnValmis(tekstitiedostonNimi));
        return true;
    }

    private void kysele(Lukija lukija, boolean examples, ArrayList<Kysymys> kysymykset) {

        for (Kysymys kysymys : kysymykset) {
            System.out.println(kysymys.getKysymysNykyisellaKielella());
            if (examples) {
                String esimerkkiVastaus = kysymys.getEsimerkkiVastaus();
                System.out.println(Tulostamo.esimerkkiVastaus(esimerkkiVastaus));
            }
            System.out.print("\n    ");
            String kayttajanVastaus = lukija.lueMerkkijono();
            System.out.println();

            kysymys.lisaaVastaus(kayttajanVastaus);
        }

        this.tk.luoTiedosto(kysymykset);
    }
}