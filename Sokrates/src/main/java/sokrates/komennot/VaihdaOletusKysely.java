package sokrates.komennot;

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

    public VaihdaOletusKysely(Lukija lukija, KyselyHallinta hallinta, String nimi, String selite) {
        super(lukija, hallinta, nimi, selite);
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
        Kysely uusiOletusKysely = super.kayttajanOsoittamaKysely();
        this.hallinta.setOletusKysely(uusiOletusKysely);
        System.out.println("\n    Oletuskysely on nyt " + uusiOletusKysely + ".\n");

        return true;
    }
}