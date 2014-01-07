package sokrates.sovelluslogiikka;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Luokka KyselyHallinta pitää sisällään listan kyselyistä, TreeMapin kyselyiden
 * järjestetyistä nimistä, oletuskyselyn sekä esimerkkiasetuksen. KyselyHallinta
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
     * KyselyHallintaan kapseloitu taulukko kyselyiden nimistä, joista
     * jokaisella on avaimenaan merkkijono (käytännössä luku).
     */
    private TreeMap<String, String> nimiTaulukko;
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
        this.nimiTaulukko = new TreeMap<>();
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
        if (!this.nimiTaulukko.containsValue(nimi)) {
            this.kyselyt.add(new Kysely(nimi));
            this.nimiTaulukko.put("" + this.kyselyt.size(), nimi);
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
    public void poistaKysely(Kysely poistettavaKysely, String poistettavanKyselynNimi) {
        this.kyselyt.remove(poistettavaKysely);

        String poistettavanKyselynNimenAvainNumero = "" + -1;

        for (String avainNumero : this.nimiTaulukko.keySet()) {
            if (this.nimiTaulukko.get(avainNumero).equals(poistettavanKyselynNimi)) {
                poistettavanKyselynNimenAvainNumero = avainNumero;
            }
        }

        this.nimiTaulukko.remove(poistettavanKyselynNimenAvainNumero);
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
        if (this.nimiTaulukko.containsValue(nimi)) {
            for (Kysely kysely : this.kyselyt) {
                if (kysely.toString().equals(nimi)) {
                    return kysely;
                }
            }
        }

        return null;
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
     * on olemassa (nimiTaulukon arvona), false jos ei.
     *
     * @param nimi jolla kyselyn olemassaoloa tiedustellaan
     * @return
     */
    public boolean onkoTamanNiminenKyselyOlemassa(String nimi) {
        if (this.nimiTaulukko.containsValue(nimi)) {
            return true;
        }

        return false;
    }

    /**
     * @return kaikkien KyselyHallinnan tuntemien kyselyiden nimet avaiminaan
     * kyselyiden kulloisestakin lkm:stä saatu (järjestys)numero
     */
    public TreeMap<String, String> getNimiTaulukko() {
        return this.nimiTaulukko;
    }

    public boolean getExamples() {
        return this.examples;
    }

    public void setExamples(boolean examples) {
        this.examples = examples;
    }
}