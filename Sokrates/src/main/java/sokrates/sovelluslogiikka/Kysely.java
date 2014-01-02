package sokrates.sovelluslogiikka;

import java.util.ArrayList;

/**
 * Luokan Kysely olio on yksittäinen kysely, joka sisältää listan kysymyksiä.
 * Kyselyllä on aina jonkinlainen merkkijono nimenä.
 * Kyselyyn voi lisätä kysymyksiä.
 * TODO: Mahdollisuus poistaa kysymyksiä.
 *
 * @author Teo
 */
public class Kysely {

    private ArrayList<Kysymys> kysymykset = new ArrayList<>();
    private String nimi;

    /**
     * Metodi luo kyselyn ja asettaa tämän nimeksi parametrina
     * saadun merkkijonon
     *
     * @param nimi Kyselylle annettava nimi
     */
    public Kysely(String nimi) {
        this.nimi = nimi;
    }

    /**
     * Metodi lisää parametrina saadun kysymyksen kysymyksiin
     * jos ja vain jos kysymystä ei löydy listasta jo ennestään
     * 
     * @param kysymys Kyselyyn lisättävä kysymys
     */
    public void lisaaKysymys(Kysymys kysymys) {
        if (!this.kysymykset.contains(kysymys)) {
            this.kysymykset.add(kysymys);
        }
    }

    public ArrayList<Kysymys> getKysymykset() {
        return this.kysymykset;
    }

    @Override
    public String toString() {
        return this.nimi;
    }
}