/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokrates.sokrates;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author tepi
 */
public class Kysymys {

    private HashMap<Kieli, String> kysymysKaikillaKielilla = new HashMap();
    private ArrayList<String> vastaukset = new ArrayList();
    private String esimerkkivastaus;

    public Kysymys(Kieli kieli, String kysymys) {
        this.kysymysKaikillaKielilla.put(kieli, kysymys);
    }

    public void lisaaKysymysEriKielella(Kieli kieli, String kysymys) {
        if (!this.kysymysKaikillaKielilla.containsKey(kieli)) {
            this.kysymysKaikillaKielilla.put(kieli, kysymys);
        }
    }

    public void lisaaEsimerkkiVastaus(String esimerkkivastaus) {
        this.esimerkkivastaus = esimerkkivastaus;
    }

    public void lisaaVastaus(String vastaus) {
        this.vastaukset.add(vastaus);
    }

    public String esimerkkivastaus() {
        return this.esimerkkivastaus;
    }

    public ArrayList<String> vastaukset() {
        return this.vastaukset;
    }

    public HashMap<Kieli, String> kysymysKaikillaKielilla() {
        return this.kysymysKaikillaKielilla;
    }

    // Kannattanee muuttaa toStringiksi, jota kutsutaan käyttöliittymästä.
    public void kysy(boolean examples) {
        Kieli nykyinenKieli = Asetukset.getKieli();
        System.out.println(this.kysymysKaikillaKielilla.get(nykyinenKieli));

        if (examples = true) {
            tulostaEsimerkkiVastaus();
        }
    }

    // Kannattanee muuttaa toStringiksi, jota kutsutaan käyttöliittymästä.
    public void tulostaEsimerkkiVastaus() {
        if (!this.esimerkkivastaus.isEmpty()) {
            System.out.println("    Esim.: " + this.esimerkkivastaus);
        }
    }
}
