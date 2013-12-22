/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokrates.sovelluslogiikka;

/**
 *
 * @author tepi
 */
public class Asetukset {

    static Kieli kieli = Kieli.SUOMI;

    public static Kieli getKieli() {
        return kieli;
    }

    public void setKieli(Kieli kieli) {
        Asetukset.kieli = kieli;
    }
}
