package sokrates.komennot;

import java.util.ArrayList;
import sokrates.kayttoliittyma.Tulostamo;
import sokrates.sovelluslogiikka.Asetukset;
import sokrates.sovelluslogiikka.Kysely;
import sokrates.sovelluslogiikka.KyselyHallinta;
import sokrates.sovelluslogiikka.Kysymys;
import sokrates.tiedostonkasittely.TiedostonKirjoittaja;
import sokrates.util.Lukija;

/**
 * Komento KyseleKysely tulostaa käyttäjälle tämän valitseman kyselyn kysymykset
 * yksi kerrallaan, niin että välissä jokaiseen kysymykseen liitetään käyttäjän
 * antama vastaus.
 *
 * Lopuksi käsketään komennon (konstruktorikutsun yhteydessään luomaa ja)
 * muistamaa TiedostonKirjoittajaa luomaan tekstitiedosto äsken kysytyistä
 * kysymyksistä.
 *
 * @author Teo
 */
public class KyseleKysely extends Komento {

    /**
     * Luokkaan KyseleKysely kapseloitu tiedostonkirjoittaja
     */
    private TiedostonKirjoittaja tk;

    public KyseleKysely(Lukija lukija, KyselyHallinta hallinta, TiedostonKirjoittaja tk,
            String nimi, String seliteSuomeksi, String seliteEnglanniksi) {
        super(lukija, hallinta, nimi, seliteSuomeksi, seliteEnglanniksi);
        this.tk = tk;
    }

    /**
     * Kun KyseleKysely suoritetaan, poimitaan käyttäjän valitseman kyselyn
     * kysymykset ArrayListiksi, joka jos epätyhjä niin kysellään toisen metodin
     * avulla. Lopuksi tulostetaan ilmoitus siitä, että kysely on valmis ja
     * mistä tekstitiedosto löytyy.
     *
     * @return true Jotta Sovelluksen komentolooppi voisi jatkua
     */
    @Override
    public boolean suorita() {
        System.out.println(Tulostamo.valitseKyseltavaKysely());
        Kysely kyseltavaKysely = super.kayttajanOsoittamaKysely();

        if (kyseltavaKysely == null) {
            return true;
        }

        ArrayList<Kysymys> kysymykset = kyseltavaKysely.getKysymykset();

        if (kysymykset.isEmpty()) {
            System.out.println(Tulostamo.kyselyssaEiOleYhtaanKysymysta());
            return true;
        }

        boolean examples = hallinta.getExamples();

        kysele(lukija, examples, kysymykset);

        String tekstitiedostonNimi = kysymykset.get(0).getVastaus();
        System.out.println(Tulostamo.kyselyOnValmis(tekstitiedostonNimi));
        return true;
    }

    /**
     * Metodi tulostaa kunkin kysymyksen Asetusten nykyisellä kielellä.
     * Esimerkkivastaus tulostetaan joss examples = true.
     *
     * Ensimmäiseen kysymykseen vaaditaan epätyhjä vastaus, sillä se tulee
     * tekstitiedoston nimeksi.
     *
     * Jokaiseen kysymykseen liitetään käyttäjän vastaus.
     *
     * Lopuksi kutstutaan TiedostonKirjoittajan metodia luoTiedosto parametrina
     * käytetty kysymyslista.
     *
     * @param lukija Lukija
     * @param examples Tulostetaanko esimerkkivastauksia kyllä/ei = true/false
     * @param kysymykset Lista kyseltävistä kysymyksistä
     */
    private void kysele(Lukija lukija, boolean examples, ArrayList<Kysymys> kysymykset) {
        Kysymys ekaKysymys = kysymykset.get(0);
        System.out.println(ekaKysymys.getKysymysKielella(Asetukset.getKieli()));
        if (examples) {
            String esimerkkiVastaus = ekaKysymys.getEsimerkkiVastaus();
            System.out.println(Tulostamo.esimerkkiVastaus(esimerkkiVastaus));
        }

        while (true) {
            System.out.print("\n    ");
            String kayttajanVastaus = lukija.lueMerkkijono("");
            if (kayttajanVastaus.isEmpty()) {
                continue;
            }
            System.out.println();
            ekaKysymys.setVastaus(kayttajanVastaus);
            break;

        }

        for (int i = 1; i < kysymykset.size(); i++) {
            Kysymys kysymys = kysymykset.get(i);

            System.out.println(kysymys.getKysymysKielella(Asetukset.getKieli()));
            if (examples) {
                String esimerkkiVastaus = kysymys.getEsimerkkiVastaus();
                System.out.println(Tulostamo.esimerkkiVastaus(esimerkkiVastaus));
            }
            System.out.print("\n    ");
            String kayttajanVastaus = lukija.lueMerkkijono("");
            System.out.println();
            kysymys.setVastaus(kayttajanVastaus);
        }

        this.tk.luoTiedosto(kysymykset);
    }
}