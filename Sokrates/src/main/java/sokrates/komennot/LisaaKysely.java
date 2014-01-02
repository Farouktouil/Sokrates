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
            String lisattavanKyselynNimi = lukija.lueMerkkijono("Anna kyselylle nimi: ");

            if (!lisattavanKyselynNimi.isEmpty()) {
                this.hallinta.lisaaKysely(lisattavanKyselynNimi);
                System.out.println("\n    Lisätty kysely nimeltä " + lisattavanKyselynNimi + ".\n");
                break;
            }
        }

        return true;
    }
}