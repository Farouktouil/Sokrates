package sokrates.komennot;

import sokrates.kayttoliittyma.Tulostamo;
import sokrates.sovelluslogiikka.Kysely;
import sokrates.sovelluslogiikka.KyselyHallinta;
import sokrates.util.Lukija;

/**
 * VaihdaOletusKysely vastaa KyselyHallinnan muistaman oletuskyselyn
 * vaihtamisesta.
 *
 * @author Teo
 */
public class VaihdaOletusKysely extends Komento {

    public VaihdaOletusKysely(Lukija lukija, KyselyHallinta hallinta, String nimi, String seliteSuomeksi, String seliteEnglanniksi) {
        super(lukija, hallinta, nimi, seliteSuomeksi, seliteEnglanniksi);
    }

    /**
     * Uuden oletuskyselyn valitseminen onnistuu kutsumalla Komento-luokan
     * metodia kayttajanOsoittamaKysely(). Valittu kysely asetetaan sitten
     * KyselyHallinnan oletuskyselyksi, mikä tulostetaan käyttäjällekin
     * tiedoksi.
     *
     * @return true jotta Sovelluksen komentolooppi voi jatkua.
     */
    @Override
    public boolean suorita() {
        System.out.println(Tulostamo.valitseOletusKysely());
        Kysely uusiOletusKysely = super.kayttajanOsoittamaKysely();

        if (uusiOletusKysely == null) {
            return true;
        } else {
            this.hallinta.setOletusKysely(uusiOletusKysely);
<<<<<<< HEAD
            System.out.println("\n    Oletuskysely on nyt " + uusiOletusKysely.getNimi() + ".\n");
=======
            System.out.println(Tulostamo.uusiOletusKyselyOnNyt(uusiOletusKysely.getNimi()));
>>>>>>> kaksikielistettiin tekstikäyttiskin
            return true;
        }
    }
}