/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokrates.sokrates;

/**
 *
 * @author tepi
 */
public class Asetukset {
    static Kieli kieli = Kieli.SUOMI;
    private Kysely oletuskysely;
    
    public static Kieli getKieli() {
        return kieli;
    }
    
    public void setKieli(Kieli kieli) {
        this.kieli = kieli;
    }
    
    public Kysely getOletusKysely() {
        return this.oletuskysely;
    }
    
    public void setOletusKysely(Kysely kysely) {
        this.oletuskysely = kysely;
    }
}
