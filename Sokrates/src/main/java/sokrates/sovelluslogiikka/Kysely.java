/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokrates.sovelluslogiikka;

import java.util.ArrayList;
import sokrates.util.Lukija;

/**
 *
 * @author tepi
 */
public class Kysely {

    private ArrayList<Kysymys> kysymykset = new ArrayList<>();

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
                System.out.println("  (esim. " + kysymys.getEsimerkkiVastaus() + ")");
            }

            System.out.print("  ");
            String kayttajanVastaus = lukija.lueMerkkijono();
            System.out.println("");
            kysymys.lisaaVastaus(kayttajanVastaus);
        }
    }
}
