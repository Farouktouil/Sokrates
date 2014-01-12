package sokrates.komennot;

import java.io.File;
import sokrates.kayttoliittyma.Tulostamo;
import sokrates.sovelluslogiikka.Kysely;
import sokrates.sovelluslogiikka.KyselyHallinta;
import sokrates.tiedostonkasittely.*;
import sokrates.util.Lukija;

/**
 * Komento PoistaKysely laittaa käyttäjän valitsemaan poistettavan kyselyn ja
 * poistaa sitten valitun kyselyn KyselyHallinnan muistista sekä
 * kyselytiedostojen kansiosta.
 *
 * @author Teo
 */
public class PoistaKysely extends Komento {

    /**
     * Kapseloitu TiedostonKirjoittaja
     */
    private TiedostonKirjoittaja tk;
    /**
     * Kapseloitu TiedostonLukija
     */
    private TiedostonLukija tl;

    public PoistaKysely(Lukija lukija, KyselyHallinta hallinta, TiedostonKirjoittaja tk, TiedostonLukija tl,
            String nimi, String seliteSuomeksi, String seliteEnglanniksi) {
        super(lukija, hallinta, nimi, seliteSuomeksi, seliteEnglanniksi);
        this.tk = tk;
        this.tl = tl;
    }

    /**
     * Poistettavan kyselyn valitseminen tapahtuu kutsumalla Komento-luokan
     * kayttajanOsoittamaKysely()-metodia. Joss valinta ei ole null, poistetaan
     * kysely KyselyHallinnasta ja tulostetaan käyttäjälle tieto siitä, minkä
     * niminen kysely on poistettu.
     *
     * TiedostonLukijalta saadaan poistettavan kyselyn nimeä vastaava tiedosto,
     * joka TiedostonKirjoittajaa pyydetään poistamaan.
     *
     * @return true jotta Sovelluksen komentolooppi voi jatkua.
     */
    @Override
    public boolean suorita() {
        System.out.println(Tulostamo.valitsePoistettavaKysely());
        Kysely poistettavaKysely = super.kayttajanOsoittamaKysely();

        if (poistettavaKysely != null) {
            String poistettavanKyselynNimi = poistettavaKysely.getNimi();
            this.hallinta.poistaKysely(poistettavaKysely);

            if (new File("inquiries/").exists()) {
                File poistettava = tl.getNimeaVastaavaKyselyTiedosto(poistettavanKyselynNimi);
                tk.poistaKyselyTiedosto(poistettava);

                System.out.println(Tulostamo.poistettuKyselyNimelta(poistettavanKyselynNimi));
                System.out.println();
            }
        }
        
        return true;
    }
}