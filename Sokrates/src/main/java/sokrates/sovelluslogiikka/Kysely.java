package sokrates.sovelluslogiikka;

import java.util.ArrayList;

/**
 * Luokan Kysely olio on yksittäinen kysely, joka sisältää listan kysymyksiä.
 * Kyselyllä on aina jonkinlainen merkkijono nimenä. Kyselyyn voi lisätä
 * kysymyksiä.
 *
 * @author Teo
 */
public class Kysely {

    /**
     * Kyselyyn kapseloitu lista kysymyksiä
     */
    private ArrayList<Kysymys> kysymykset;
    /**
     * Kyselyn nimi
     */
    private String nimi;

    /**
     * Konstruktori luo kyselyn ja asettaa tämän muistamaksi nimekseen
     * parametrina saadun merkkijonon.
     *
     * @param nimi Kyselylle annettava nimi
     */
    public Kysely(String nimi) {
        this.kysymykset = new ArrayList<>();
        this.nimi = nimi;
    }

    /**
     * @return Kyselyn kysymykset listana
     */
    public ArrayList<Kysymys> getKysymykset() {
        return this.kysymykset;
    }

    /**
     * @return Kyselylle annettu nimi
     */
    public String getNimi() {
        return this.nimi;
    }

    /**
     * Metodi lisää parametrina saadun kysymyksen kysymyksiin joss kysymystä ei
     * löydy listasta jo ennestään
     *
     * @param kysymys Kyselyyn lisättävä kysymys
     */
    public void lisaaKysymys(Kysymys kysymys) {
        if (!this.kysymykset.contains(kysymys)) {
            this.kysymykset.add(kysymys);
        }
    }

    /**
     * Metodi poistaa parametrina saadun kysymyksen kyselystä.
     *
     * @param kysymys Kyselyyn lisättävä kysymys
     */
    public void poistaKysymys(Kysymys kysymys) {
        this.kysymykset.remove(kysymys);
    }

    /**
     * Metodi auttaa estämään ohjelman kaatumista kun käyttäjä yrittää syöttää
     * olemassaolevaa indeksiä valitakseen kysymyksen.
     *
     * @return Kyselyn sisältämien kysymysten indeksit Integer-listana
     * (kysymysten järjestysnumerot siis, nollasta alkaen).
     */
    public ArrayList<Integer> getKysymystenIndeksiLista() {
        ArrayList<Integer> kysymystenIndeksiLista = new ArrayList<>();

        for (int i = 0; i < this.kysymykset.size(); i++) {
            kysymystenIndeksiLista.add(i);
        }

        return kysymystenIndeksiLista;
    }
}