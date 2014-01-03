package sokrates.sovelluslogiikka;

import java.util.ArrayList;

/**
 * Luokan Kysely olio on yksittäinen kysely, joka sisältää listan kysymyksiä.
 * Kyselyllä on aina jonkinlainen merkkijono nimenä. Kyselyyn voi lisätä
 * kysymyksiä. TODO: Mahdollisuus poistaa kysymyksiä.
 *
 * @author Teo
 */
public class Kysely {

    private ArrayList<Kysymys> kysymykset;
    private String nimi;

    /**
     * Metodi luo kyselyn ja asettaa tämän nimeksi parametrina saadun
     * merkkijonon
     *
     * @param nimi Kyselylle annettava nimi
     */
    public Kysely(String nimi) {
        this.kysymykset = new ArrayList<>();
        this.nimi = nimi;
    }

    /**
     * Metodi lisää parametrina saadun kysymyksen kysymyksiin jos ja vain jos
     * kysymystä ei löydy listasta jo ennestään
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

    /**
     * Metodi palauttaa nimen joka kyselylle tätä luodessa annettiin
     *
     * @return Kyselylle annettu nimi
     */
    @Override
    public String toString() {
        return this.nimi;
    }
}