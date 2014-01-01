package sokrates.komennot;

import sokrates.sovelluslogiikka.KyselyHallinta;
import sokrates.util.Lukija;

public class EsimerkkiToggle extends Komento {

    public EsimerkkiToggle(Lukija lukija, KyselyHallinta hallinta, String nimi, String selite) {
        super(lukija, hallinta, nimi, selite);
    }

    @Override
    public boolean suorita() {

        if (this.hallinta.getExamples() == true) {
            this.hallinta.setExamples(false);
            System.out.println("    Esimerkkivastaukset ovat nyt pois päältä.\n");
        } else if (this.hallinta.getExamples() == false) {
            this.hallinta.setExamples(true);
            System.out.println("    Esimerkkivastaukset ovat nyt päällä.\n");
        }

        return true;
    }
}
