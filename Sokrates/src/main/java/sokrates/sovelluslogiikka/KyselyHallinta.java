package sokrates.sovelluslogiikka;

import java.util.ArrayList;

/**
 * Luokka KyselyHallinta pitää sisällään listan kyselyistä, oletuskyselyn sekä esimerkkiasetuksen. KyselyHallinta
 * vastaa sovelluslogiikan ja muiden pakkausten yhteistyöstä.
 *
 * @author Teo
 */
public class KyselyHallinta {

    /**
     * KyselyHallintaan kapseloitu lista kyselyistä
     */
    private ArrayList<Kysely> kyselyt;
    /**
     * KyselyHallintaan on kulloinkin kapseloitu yksi erityinen oletuskysely,
     * joka on tavallaan kyselytysvuorossa. Oletuskysely voi olla myös null.
     */
    private Kysely oletuskysely;
    /**
     * KyselyHallintaan kapseloitu esimerkkiasetus, jonka mukaan joko näytetään
     * tai jätetään näyttämättä kysymykseen liittyvä esimerkkivastaus, kun
     * kysymyksiä tulostetaan käyttäjälle.
     *
     */
    private boolean examples;

    /**
     * Metodi luo uuden KyselyHallinnan, jolle luodaan ArrayList kyselyistä,
     * TreeMap kyselyiden nimistä, oletusarvoisesti null oletuskysely sekä
     * oletusarvoisesti true esimerkkiennäyttämisasetus.
     */
    public KyselyHallinta() {
        this.kyselyt = new ArrayList<>();
        this.oletuskysely = null;
        this.examples = true;
    }

    /**
     * Joss nimiTaulukosta ei löydy parametrina annettua nimeä, niin kyselyihin
     * luodaan uusi kysely sillä nimellä ja nimiTaulukkoon lisätään kyseinen
     * nimi avaimenaan kyselyiden koko (eli lukumäärä). Avain on siis
     * järjestysnumero.
     *
     * @param nimi Kyselylle annettava nimi.
     */
    public void lisaaKysely(String nimi) {
        if (!onkoTamanNiminenKyselyOlemassa(nimi)) {
            this.kyselyt.add(new Kysely(nimi));
        }
    }

    /**
     * Metodi poistaa kyselylistasta parametrina saadun kyselyn.
     *
     * Metodi käy sitten läpi nimiTaulukon avaimet ja poistaa nimiTaulukosta
     * sellaisen avaimen, jota vastaava kyselyn nimi on sama kuin parametrina
     * saatu poistettavan kyselyn nimi.
     *
     * @param poistettavaKysely Kysely joka halutaan poistaa
     * @param poistettavanKyselynNimi Nimi kyselyn joka halutaan poistaa
     */
    public void poistaKysely(Kysely poistettavaKysely) {
        this.kyselyt.remove(poistettavaKysely);
    }

    /**
     * Jos nimiTaulukko tuntee nimen, käydään läpi kyselyt ja palautetaan kysely
     * jolla on sama nimi.
     *
     * Jos nimiTaulukko ei tunne nimeä, palautetaan null.
     *
     * @param nimi Nimi jota vastaavaa kyselyä kaivataan.
     *
     * @return kysely jonka nimi on sama kuin annettu hakusana.
     *
     * @return null jos nimiTaulukko ei tunne nimeä.
     */
    public Kysely haeKyselyNimenPerusteella(String nimi) {
        Kysely loydettyKysely = null;
        
        for (Kysely kysely : this.kyselyt) {
            if (kysely.getNimi().equals(nimi)) {
                loydettyKysely = kysely;
            }
        }

        return loydettyKysely;
    }

    public Kysely getOletusKysely() {
        return this.oletuskysely;
    }

    public void setOletusKysely(Kysely kysely) {
        this.oletuskysely = kysely;
    }

    /**
     * @return kaikki KyselyHallinnan tuntemat Kyselyt ArrayListinä
     */
    public ArrayList<Kysely> getKyselyt() {
        return this.kyselyt;
    }

    /**
     * Metodi palauttaa true jos parametrina saadun merkkijonon niminen kysely
     * on olemassa, false jos ei.
     *
     * @param nimi jota vastaavan kyselyn olemassaoloa tutkitaan
     * @return
     */
    public boolean onkoTamanNiminenKyselyOlemassa(String nimi) {
        for (Kysely kysely : this.kyselyt) {
            if (kysely.getNimi().equals(nimi)) {
                return true;
            }
        }
        
        return false;
    }

    public boolean getExamples() {
        return this.examples;
    }

    public void setExamples(boolean examples) {
        this.examples = examples;
    }
}