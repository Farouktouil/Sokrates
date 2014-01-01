package sokrates.komennot;

import sokrates.sovelluslogiikka.KyselyHallinta;
import sokrates.util.Lukija;

public class LisaaKysely extends Komento {

    public LisaaKysely(Lukija lukija, KyselyHallinta hallinta, String nimi, String selite) {
        super(lukija, hallinta, nimi, selite);
    }

    @Override
    public boolean suorita() {

        while (true) {
            String nimi = lukija.lueMerkkijono("Anna kyselylle nimi: ");

            if (!nimi.isEmpty()) {
                this.hallinta.lisaaKysely((nimi));
                System.out.println("\n    Luotu kysely nimeltä " + nimi + ".\n");
                break;
            }
        }

        return true;
    }
}
