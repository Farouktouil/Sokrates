/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokrates.sovelluslogiikka;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Teo
 */
public class KyselyHallinta {

    private HashMap<String, Kysely> kyselyt;
    private Kysely oletuskysely;
    private boolean examples;

    public KyselyHallinta() {
        this.kyselyt = new HashMap<String, Kysely>();
        this.examples = true;
    }

    public void lisaaKysely(String nimi) {
        if (!this.kyselyt.containsKey(nimi)) {
            this.kyselyt.put(nimi, new Kysely());
        }
    }

    public Kysely haeKyselyNimenPerusteella(String nimi) {
        if (this.kyselyt.containsKey(nimi)) {
            return this.kyselyt.get(nimi);
        } else {
            return null;
        }
    }

    public Kysely getOletusKysely() {
        return this.oletuskysely;
    }

    public void setOletusKysely(Kysely kysely) {
        this.oletuskysely = kysely;
    }

    public boolean getExamples() {
        return this.examples;
    }
    
    public void setExamples(boolean examples) {
        this.examples = examples;
    }

    public void esimerkkiToggle() {
        if (this.examples = false) {
            setExamples(true);
            System.out.println("Esimerkkivastaukset ovat nyt päällä.");
            return;
        }
        
        if (this.examples = true) {
            setExamples(false);
            System.out.println("Esimerkkivastaukset ovat nyt pois päältä.");
            return;
        }
    }
}
