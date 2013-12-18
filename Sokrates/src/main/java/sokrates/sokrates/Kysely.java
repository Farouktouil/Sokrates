/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokrates.sokrates;

import java.util.ArrayList;

/**
 *
 * @author tepi
 */
public class Kysely {

    private ArrayList<Kysymys> kysymykset;
    private boolean examples = true;

    public Kysely() {
        this.kysymykset = new ArrayList<Kysymys>();
    }

    public void lisaaKysymys(Kysymys kysymys) {
        if (!this.kysymykset.contains(kysymys)) {
            this.kysymykset.add(kysymys);
        }
    }

    public ArrayList<Kysymys> getKysymykset() {
        return this.kysymykset;
    }

    public void esimerkkiToggle() {
        if (this.examples = true) {
            this.examples = false;
        } else {
            this.examples = true;
        }
    }

    public void kysele() {
        for (Kysymys kysymys : this.kysymykset) {
            kysymys.kysy(examples);
            kysymys.lisaaVastaus("Tähän käyttäjän vapaasti kirjoittama vastaus.");
        }
    }
}
