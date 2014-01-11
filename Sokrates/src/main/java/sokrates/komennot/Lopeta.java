package sokrates.komennot;

import sokrates.sovelluslogiikka.KyselyHallinta;
import sokrates.util.Lukija;

/**
 * Yksinkertaisesti komento joka lopettaa ohjelman.
 *
 * @author Teo
 */
public class Lopeta extends Komento {

    public Lopeta(Lukija lukija, KyselyHallinta hallinta,
            String nimi, String seliteSuomeksi, String seliteEnglanniksi) {
        super(lukija, hallinta, nimi, seliteSuomeksi, seliteEnglanniksi);
    }

    /**
     * Palauttaa false jolloin Sovelluksen komentoutelulooppi ei enää jatku.
     *
     * @return false
     */
    @Override
    public boolean suorita() {
        return false; // lopettaa siis ohjelman
    }
}