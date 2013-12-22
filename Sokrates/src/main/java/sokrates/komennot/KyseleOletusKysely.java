/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokrates.komennot;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sokrates.sovelluslogiikka.Kysely;
import sokrates.sovelluslogiikka.KyselyHallinta;
import sokrates.sovelluslogiikka.Kysymys;
import sokrates.util.Lukija;

/**
 *
 * @author Teo
 */
public class KyseleOletusKysely extends Komento {

    public KyseleOletusKysely(Lukija lukija, KyselyHallinta hallinta, String nimi, String selite) {
        super(lukija, hallinta, nimi, selite);
    }

    @Override
    public boolean suorita() {
        boolean examples = hallinta.getExamples();

        Kysely oletuskysely = hallinta.getOletusKysely();
        oletuskysely.kysele(lukija, examples);

        ArrayList<Kysymys> kysymykset = oletuskysely.getKysymykset();
        luoTiedosto(kysymykset);

        return true;
    }

    public void luoTiedosto(ArrayList<Kysymys> kysymykset) {
        PrintWriter writer = null;
        
        try {
            String ekanKysymyksenEkaVastaus = kysymykset.get(0).getVastaukset().get(0);
            writer = new PrintWriter(ekanKysymyksenEkaVastaus + ".txt", "UTF-8");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(KyseleOletusKysely.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(KyseleOletusKysely.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        kirjoitaKysymyksetVastauksineenLuotuunTiedostoon(writer, kysymykset);
    }
    
    public void kirjoitaKysymyksetVastauksineenLuotuunTiedostoon(PrintWriter writer, ArrayList<Kysymys> kysymykset) {
        for (Kysymys kysymys : kysymykset) {
            writer.println(kysymys.getKysymys());
            writer.println("    " + kysymys.getVastaukset().get(0));
            writer.println("");
        }
        
        writer.close();
    }
}
