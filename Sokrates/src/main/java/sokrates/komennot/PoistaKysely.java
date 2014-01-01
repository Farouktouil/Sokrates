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
        Kysely poistettavaKysely = super.kayttajaValitseeKyselyn();
        String poistettavanKyselynNimi = poistettavaKysely.getNimi();
        this.hallinta.poistaKysely(poistettavanKyselynNimi);
        System.out.println("    Poistettiin kysely " + poistettavanKyselynNimi + ".");
        return true;
    }
}
