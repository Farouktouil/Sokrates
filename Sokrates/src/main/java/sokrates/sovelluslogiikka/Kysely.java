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
     * Metodi palauttaa nimen joka kyselylle tätä luodessa annettiin.
     *
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

    public ArrayList<Kysymys> getKysymykset() {
        return this.kysymykset;
    }
}