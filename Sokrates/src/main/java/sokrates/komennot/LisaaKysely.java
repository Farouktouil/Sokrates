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
            String syote = lukija.lueMerkkijono("    Anna kyselylle nimi: ");

            if (!syote.isEmpty()) {
                this.hallinta.lisaaKysely((syote));
                System.out.println("    Luotu kysely nimeltä " + syote + ".");
                break;
            }
        }

        return true;
    }
}
