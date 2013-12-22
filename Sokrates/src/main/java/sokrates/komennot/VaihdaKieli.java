/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokrates.komennot;

import sokrates.sovelluslogiikka.Asetukset;
import sokrates.sovelluslogiikka.Kieli;
import sokrates.sovelluslogiikka.KyselyHallinta;
import sokrates.util.Lukija;

/**
 *
 * @author Teo
 */
public class VaihdaKieli extends Komento {

    public VaihdaKieli(Lukija lukija, KyselyHallinta hallinta, String nimi, String selite) {
        super(lukija, hallinta, nimi, selite);
    }

    @Override
    public boolean suorita() {
        if (Asetukset.getKieli() == Kieli.SUOMI) {
            // Asetukset.setKieli() == Kieli.ENGLANTI);
            System.out.println("Ei voitu asettaa kieleksi englantia miksi.");
        } else if (Asetukset.getKieli() == Kieli.ENGLANTI) {
            // Asetukset.getKieli() == Kieli.SUOMI);
            System.out.println("Ei voitu asettaa kieleksi suomea miksi.");
        }
        
        return true;
    }
}
