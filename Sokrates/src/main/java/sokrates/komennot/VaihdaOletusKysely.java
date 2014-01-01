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
        Kysely uusiOletusKysely = super.kayttajaValitseeKyselyn();
        this.hallinta.setOletusKysely(uusiOletusKysely);
        System.out.println("    Oletuskysely on nyt " + uusiOletusKysely + ".");

        return true;
    }
}