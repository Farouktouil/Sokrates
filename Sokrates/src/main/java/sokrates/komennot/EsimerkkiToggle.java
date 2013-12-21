/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokrates.komennot;

import sokrates.sovelluslogiikka.KyselyHallinta;
import sokrates.util.Lukija;

/**
 *
 * @author Teo
 */
public class EsimerkkiToggle extends Komento {

    public EsimerkkiToggle(Lukija lukija, KyselyHallinta hallinta, String nimi, String selite) {
        super(lukija, hallinta, nimi, selite);
    }

    @Override
    public boolean suorita() {
        hallinta.esimerkkiToggle();
        return true;
    }
}
