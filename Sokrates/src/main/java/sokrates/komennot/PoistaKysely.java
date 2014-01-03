package sokrates.komennot;

import sokrates.sovelluslogiikka.Kysely;
import sokrates.sovelluslogiikka.KyselyHallinta;
import sokrates.util.Lukija;

/**
 * Komennon PoistaKysely laittaa käyttäjän valitsemaan poistettavan kyselyn ja
 * poistaa sitten valitun kyselyn KyselyHallinnan muistista.
 *
 * @author Teo
 */
public class PoistaKysely extends Komento {

    public PoistaKysely(Lukija lukija, KyselyHallinta hallinta, String nimi, String selite) {
        super(lukija, hallinta, nimi, selite);
    }

    /**
     * Poistettavan kyselyn valitseminen tapahtuu kutsumalla Komento-luokan
     * kayttajanOsoittamaKysely()-metodia.
     *
     * TÄMÄ EI VIELÄ TOIMI NIIN KUIN PITÄISI
     *
     * @return true jotta Sovelluksen komentolooppi voi jatkua.
     */
    @Override
    public boolean suorita() {
        Kysely poistettavaKysely = super.kayttajanOsoittamaKysely();
        String poistettavanKyselynNimi = poistettavaKysely.toString();

        this.hallinta.poistaKysely(poistettavaKysely, poistettavanKyselynNimi);
        System.out.println("\n    Poistettu kysely nimeltä "
                + poistettavanKyselynNimi + ".\n");

        return true;
    }
}