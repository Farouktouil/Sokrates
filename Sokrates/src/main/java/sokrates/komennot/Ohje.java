package sokrates.komennot;

import java.util.Collection;
import sokrates.sovelluslogiikka.KyselyHallinta;
import sokrates.util.Lukija;

/**
 * Ohje muistaa kokoelman komentoja ja tulostaa käyttäjälle ymmärrettävän listan
 * käytettävissä olevista komennoista.
 *
 * @author Teo
 */
public class Ohje extends Komento {

    /**
     * Sovelluksen luomat komennot
     */
    private Collection<Komento> komennot;

    public Ohje(Lukija lukija, KyselyHallinta hallinta, String nimi, String seliteSuomeksi, String seliteEnglanniksi, Collection<Komento> komennot) {
        super(lukija, hallinta, nimi, seliteSuomeksi, seliteEnglanniksi);
        this.komennot = komennot;
    }

    /**
     * Ohjeen suorittaminen tulostuttaa kehotuksen valita komento followed by
     * mahdolliset komennot kivasti numeroituina.
     *
     * @return true jotta Sovelluksen komentolooppi jatkuu.
     */
    @Override
    public boolean suorita() {
        System.out.println(this.getSelite());
        for (Komento komento : komennot) {
            System.out.println("  " + komento.getNimi() + " = " + komento.getSelite());
        }
        System.out.println();

        return true;
    }
}