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

    private ArrayList<Kysely> kyselyt;
    private TreeMap<Integer, String> nimiTaulukko;
    private Kysely oletuskysely;
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
            this.nimiTaulukko.put(this.kyselyt.size(), nimi);
        }
    }

    public void poistaKysely(Kysely poistettavaKysely, String poistettavanKyselynNimi) {
        this.kyselyt.remove(poistettavaKysely);

        int poistettavanKyselynNimenAvainNumero = -1;

        for (int avainNumero : this.nimiTaulukko.keySet()) {
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

    public boolean tamanNiminenKyselyOnOlemassa(String nimi) {
        if (this.nimiTaulukko.containsValue(nimi)) {
            return true;
        }

        return false;
    }

    /**
     * @return kaikkien KyselyHallinnan tuntemien kyselyiden nimet avaiminaan
     * kyselyiden kulloisestakin lkm:stä saatu (järjestys)numero
     */
    public TreeMap<Integer, String> getNimiTaulukko() {
        return this.nimiTaulukko;
    }

    public boolean getExamples() {
        return this.examples;
    }

    public void setExamples(boolean examples) {
        this.examples = examples;
    }
}