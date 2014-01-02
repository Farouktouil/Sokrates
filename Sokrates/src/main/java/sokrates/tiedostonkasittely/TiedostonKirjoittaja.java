
package sokrates.tiedostonkasittely;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sokrates.komennot.KyseleOletusKysely;
import sokrates.sovelluslogiikka.Kysymys;

public class TiedostonKirjoittaja {
    
    public void luoTiedosto(ArrayList<Kysymys> kysymykset) {
        PrintWriter writer = null;

        try {
            String ekanKysymyksenEkaVastaus = kysymykset.get(0).getVastaukset().get(0);
            String tekstiTiedostonNimi = ekanKysymyksenEkaVastaus;
            writer = new PrintWriter(tekstiTiedostonNimi + ".txt", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(KyseleOletusKysely.class.getName()).log(Level.SEVERE, null, ex);
        }

        kirjoitaKysymyksetVastauksineenLuotuunTiedostoon(writer, kysymykset);
    }

    public void kirjoitaKysymyksetVastauksineenLuotuunTiedostoon(PrintWriter writer, ArrayList<Kysymys> kysymykset) {
        for (Kysymys kysymys : kysymykset) {
            writer.println(kysymys.getKysymysNykyisellaKielella());
            writer.println();
            writer.println("    " + kysymys.getVastaukset().get(0));
            writer.println();
            writer.println();
        }
        writer.close();
    }
}
