/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokrates.komennot;

import sokrates.sovelluslogiikka.KyselyHallinta;
import sokrates.util.Lukija;

/**
 *
 * @author tepi
 */
public abstract class Komento {
    protected Lukija lukija;
    private String nimi;
    private String selite;
    protected KyselyHallinta hallinta;
    
    public Komento(Lukija lukija, KyselyHallinta hallinta, String nimi, String selite) {
        this.lukija = lukija;
        this.nimi = nimi;
        this.selite = selite;
        this.hallinta = hallinta;
    }
    
    public String getNimi() {
        return nimi;
    }
    
    public String getSelite() {
        return selite;
    }       

    public abstract boolean suorita();
}
