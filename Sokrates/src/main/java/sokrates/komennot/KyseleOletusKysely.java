/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokrates.komennot;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sokrates.sovelluslogiikka.Kysely;
import sokrates.sovelluslogiikka.KyselyHallinta;
import sokrates.tiedostonkasittely.Tiedostonkasittelija;
import sokrates.util.Lukija;

/**
 *
 * @author Teo
 */
public class KyseleOletusKysely extends Komento {
    private Tiedostonkasittelija tk;
    
    public KyseleOletusKysely(Lukija lukija, KyselyHallinta hallinta, String nimi, String selite, Tiedostonkasittelija tk) {
        super(lukija, hallinta, nimi, selite);
        this.tk = tk;
    }

    @Override
    public boolean suorita() {
        boolean examples = hallinta.getExamples();
        
        Kysely oletuskysely = hallinta.getOletusKysely();
        oletuskysely.kysele(lukija, examples);
        
        kirjoitutaVastauksineen(oletuskysely);
        
        return true;
    }
    
    public void kirjoitutaVastauksineen(Kysely oletuskysely) {
        try {
            tk.kirjoitutaVastauksineen(oletuskysely.getKysymykset());
        } catch (IOException ex) {
            Logger.getLogger(KyseleOletusKysely.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

