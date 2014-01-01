package sokrates.komennot;

import sokrates.sovelluslogiikka.Kysely;
import sokrates.sovelluslogiikka.KyselyHallinta;
import sokrates.util.Lukija;

public class PoistaKysely extends Komento {

    public PoistaKysely(Lukija lukija, KyselyHallinta hallinta, String nimi, String selite) {
        super(lukija, hallinta, nimi, selite);
    }

    @Override
    public boolean suorita() {
        Kysely poistettavaKysely = super.kayttajanOsoittamaKysely();
        String poistettavanKyselynNimi = poistettavaKysely.toString();
        this.hallinta.poistaKysely(poistettavanKyselynNimi);
        System.out.println("\n    Poistettu kysely nimeltä "
                + poistettavanKyselynNimi + ".\n");
        return true;
    }
}
