package sokrates.tiedostonkasittely;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Teo
 */
public class TiedostonLukija {

    public List<String> lueKyselyTiedostojenNimet(String directory) {
        List<String> nimet = new ArrayList<>();
        File dir = new File(directory);

        for (File file : dir.listFiles()) {
            String kyselyTiedostonNimi = file.getName();

            if (kyselyTiedostonNimi.endsWith((".txt"))) {
                nimet.add(kyselyTiedostonNimi.substring(0, kyselyTiedostonNimi.length() - 4));
            }
        }

        System.out.println(nimet); // [diary.txt, dream diary.txt, problem solving.txt]
        return nimet;
    }

    public File getNimeaVastaavaKyselyTiedosto(String nimi) {
        File dir = new File("src/inquiries/");

        for (File file : dir.listFiles()) {
            String kyselyTiedostonNimi = file.getName();

            if (kyselyTiedostonNimi.equals(nimi + ".txt")) {
                return file;
            }
        }

        return null;
    }
}