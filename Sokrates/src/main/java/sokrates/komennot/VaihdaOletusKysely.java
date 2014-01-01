package sokrates.komennot;

import sokrates.sovelluslogiikka.Kysely;
import sokrates.sovelluslogiikka.KyselyHallinta;
import sokrates.util.Lukija;

public class VaihdaOletusKysely extends Komento {

    public VaihdaOletusKysely(Lukija lukija, KyselyHallinta hallinta, String nimi, String selite) {
        super(lukija, hallinta, nimi, selite);
    }

    @Override
    public boolean suorita() {
        Kysely uusiOletusKysely = super.kayttajanOsoittamaKysely();
        this.hallinta.setOletusKysely(uusiOletusKysely);
        System.out.println("\n    Oletuskysely on nyt " + uusiOletusKysely + ".\n");

        return true;
    }
}