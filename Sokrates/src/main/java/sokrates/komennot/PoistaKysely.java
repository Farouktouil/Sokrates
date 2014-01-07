package sokrates.komennot;

import sokrates.kayttoliittyma.Tulostamo;
import sokrates.sovelluslogiikka.Kysely;
import sokrates.sovelluslogiikka.KyselyHallinta;
import sokrates.util.Lukija;

/**
 * Komento PoistaKysely laittaa käyttäjän valitsemaan poistettavan kyselyn ja
 * poistaa sitten valitun kyselyn KyselyHallinnan muistista.
 *
 * @author Teo
 */
public class PoistaKysely extends Komento {

    public PoistaKysely(Lukija lukija, KyselyHallinta hallinta, String nimi, String seliteSuomeksi, String seliteEnglanniksi) {
        super(lukija, hallinta, nimi, seliteSuomeksi, seliteEnglanniksi);
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
        System.out.println(Tulostamo.valitsePoistettavaKysely());
        Kysely poistettavaKysely = super.kayttajanOsoittamaKysely();

        if (poistettavaKysely == null) {
            return true;
        } else {
            String poistettavanKyselynNimi = poistettavaKysely.getNimi();
            this.hallinta.poistaKysely(poistettavaKysely);
<<<<<<< HEAD
            System.out.println("\n    Poistettu kysely nimeltä "
                    + poistettavanKyselynNimi + ".");
=======
            System.out.println(Tulostamo.poistettuKyselyNimelta(poistettavanKyselynNimi));
>>>>>>> kaksikielistettiin tekstikäyttiskin

            if (poistettavaKysely.equals(this.hallinta.getOletusKysely())) {
                this.hallinta.setOletusKysely(null);
                System.out.println(Tulostamo.oletusKyselyaEiOleValittu());
            }

            System.out.println();
            return true;
        }
    }
}