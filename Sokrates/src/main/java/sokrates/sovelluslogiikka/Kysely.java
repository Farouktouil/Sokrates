/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokrates.sovelluslogiikka;

import java.util.ArrayList;
import sokrates.tiedostonkasittely.Tiedostonkasittelija;
import sokrates.util.Lukija;

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

    public void kysele(Lukija lukija, boolean examples) {

        for (Kysymys kysymys : this.kysymykset) {

            System.out.println(kysymys.getKysymys());

            if (examples) {
                System.out.println("  (esim. " + kysymys.esimerkkivastaus() + ")");
            }

            System.out.print("  ");
            String kayttajanVastaus = lukija.lueMerkkijono();
            kysymys.lisaaVastaus(kayttajanVastaus);
        }
    }
}
