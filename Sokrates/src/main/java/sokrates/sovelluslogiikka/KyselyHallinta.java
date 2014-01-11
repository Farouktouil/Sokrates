package sokrates.sovelluslogiikka;

import java.util.ArrayList;
import java.util.List;

/**
 * Luokka KyselyHallinta pitää sisällään listan kyselyistä sekä
 * esimerkkiasetuksen. KyselyHallinta vastaa sovelluslogiikan ja muiden
 * pakkausten yhteistyöstä.
 *
 * @author Teo
 */
public class KyselyHallinta {

    /**
     * KyselyHallintaan kapseloitu lista kyselyistä
     */
    private ArrayList<Kysely> kyselyt;
    /**
     * KyselyHallintaan kapseloitu esimerkkiasetus, jonka mukaan joko näytetään
     * tai jätetään näyttämättä kysymykseen liittyvä esimerkkivastaus, kun
     * kysymyksiä tulostetaan käyttäjälle.
     */
    private boolean examples;

    /**
     * Metodi luo uuden KyselyHallinnan, jolle luodaan ArrayList kyselyistä sekä
     * oletusarvoisesti true esimerkkiennäyttämisasetus.
     */
    public KyselyHallinta() {
        this.kyselyt = new ArrayList<>();
        this.examples = true;
    }

    /**
     * KyselyHallinnan muistamiin kyselyihin lisätään parametrina annetun
     * niminen uusi kysely, joss sellaista ei löydy sieltä jo ennestään.
     *
     * @param nimi Kyselylle annettava nimi.
     */
    public void lisaaKysely(String nimi) {
        if (getKyselyNimenPerusteella(nimi) == null) {
            this.kyselyt.add(new Kysely(nimi));
        }
    }

    /**
     * Metodi yrittää lisätä KyselyHallintaan parametrina saamansa nimilistan
     * nimet.
     *
     * @param nimet Lista kyselyiden nimistä jotka halutaan lisätä hallintaan.
     */
    public void lisaaKyselyt(List<String> nimet) {
        for (String nimi : nimet) {
            lisaaKysely(nimi);
        }
    }

    /**
     * Metodi poistaa kyselylistasta parametrina saadun kyselyn.
     *
     * @param poistettavaKysely Kysely joka halutaan poistaa
     */
    public void poistaKysely(Kysely poistettavaKysely) {
        this.kyselyt.remove(poistettavaKysely);
    }

    /**
     * Metodi palauttaa null jos parametrina saadun nimistä kyselyä ei löydy, ja
     * itse kyselyn jos löytyy.
     *
     * @param nimi Nimi jonka perusteella kyselyä haetaan.
     * @return
     */
    public Kysely getKyselyNimenPerusteella(String nimi) {
        Kysely loydettyKysely = null;
        for (Kysely kysely : this.kyselyt) {
            if (kysely.getNimi().equals(nimi)) {
                loydettyKysely = kysely;
            }
        }
        return loydettyKysely;
    }

    /**
     * @return kaikki KyselyHallinnan tuntemat Kyselyt ArrayListinä
     */
    public ArrayList<Kysely> getKyselyt() {
        return this.kyselyt;
    }

    public boolean getExamples() {
        return this.examples;
    }

    public void setExamples(boolean examples) {
        this.examples = examples;
    }

    /**
     * Metodi auttaa estämään ohjelman kaatumista kun käyttäjä yrittää syöttää
     * olemassaolevaa indeksiä valitakseen kyselyn.
     *
     * @return Hallinnan sisältämien kyselyiden indeksit Integer-listana
     * (kyselyiden järjestysnumerot siis, nollasta alkaen).
     */
    public ArrayList<Integer> getKyselyidenIndeksiLista() {
        ArrayList<Integer> kyselyidenIndeksiLista = new ArrayList<>();

        for (int i = 0; i < this.kyselyt.size(); i++) {
            kyselyidenIndeksiLista.add(i);
        }

        return kyselyidenIndeksiLista;
    }
}