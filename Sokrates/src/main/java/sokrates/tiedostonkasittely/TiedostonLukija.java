package sokrates.tiedostonkasittely;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import sokrates.sovelluslogiikka.KyselyHallinta;
import sokrates.sovelluslogiikka.Kysymys;

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

        System.out.println(nimet); // TOIMII @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        return nimet;
    }

    public void lisaaKyselyTiedostojenSisallotKysymyksiksi(List<File> kyselyTiedostot, KyselyHallinta hallinta) throws FileNotFoundException {

        for (File kyselyTiedosto : kyselyTiedostot) { // seuraava tapahtuu PER kyselytiedosto
            Scanner lukija = new Scanner(kyselyTiedosto);
            lukija.nextLine();
            lukija.nextLine();

            ArrayList<String> riviLista = new ArrayList<>();
            while (lukija.hasNextLine()) {
                riviLista.add(lukija.nextLine()); // rivilistaan lisätään jokainen kysymys suomeksi, englanniksi ja esim.vastaus
            }

            int i = 0;

            while (i < riviLista.size()) {
                // System.out.println(riviLista.get(i));
                String kyselyTiedostonNimi = kyselyTiedosto.getName();
                String kyselynNimi = kyselyTiedostonNimi.substring(0, kyselyTiedostonNimi.length() - 4);
                hallinta.haeKyselyNimenPerusteella(kyselynNimi).lisaaKysymys(
                        new Kysymys(riviLista.get(i), riviLista.get(i + 1), riviLista.get(i + 2)));
                i += 3;
            }
        }
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

    public List<File> getNimiaVastaavatKyselyTiedostot(List<String> nimet) {
        List<File> kyselyTiedostot = new ArrayList<>();

        for (String nimi : nimet) {
            File file = getNimeaVastaavaKyselyTiedosto(nimi);
            kyselyTiedostot.add(file);
        }

        return kyselyTiedostot;
    }
}