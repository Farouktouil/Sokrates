package sokrates.komennot;

import java.util.Collection;
import sokrates.sovelluslogiikka.KyselyHallinta;
import sokrates.util.Lukija;

public class Ohje extends Komento {

    private Collection<Komento> komennot;

    public Ohje(Lukija lukija, KyselyHallinta hallinta, String nimi, String selite, Collection<Komento> komennot) {
        super(lukija, hallinta, nimi, selite);
        this.komennot = komennot;
    }

    @Override
    public boolean suorita() {
        System.out.println("Tervetuloa kyselyohjelmaan. Käytettävissä on seuraavat komennot:");

        for (Komento komento : komennot) {
            System.out.println(komento.getNimi() + " " + komento.getSelite());
        }

        return true;
    }
}
