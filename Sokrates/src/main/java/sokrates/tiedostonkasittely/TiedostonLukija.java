package sokrates.tiedostonkasittely;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import sokrates.sovelluslogiikka.Kysely;
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

        System.out.println(nimet);
        return nimet;
    }

    public void lisaaKyselyTiedostojenSisallotKysymyksiksi(List<File> kyselyTiedostot, KyselyHallinta hallinta) throws FileNotFoundException {

        for (File kyselyTiedosto : kyselyTiedostot) { // seuraava tapahtuu PER kyselytiedosto
            Scanner lukija = new Scanner(kyselyTiedosto);

            ArrayList<String> riviLista = new ArrayList<>();
            while (lukija.hasNextLine()) {
                riviLista.add(lukija.nextLine()); // rivilistaan lisätään jokainen kysymys suomeksi, englanniksi ja esim.vastaus
            }

            int i = 0;

            while (i < riviLista.size()) {
                // System.out.println(riviLista.get(i));
                String kyselyTiedostonNimi = kyselyTiedosto.getName();
                String kyselynNimi = kyselyTiedostonNimi.substring(0, kyselyTiedostonNimi.length() - 4);

                String kysymysSuomeksi = riviLista.get(i);
                String kysymysEnglanniksi = "";
                String esimerkkiVastaus = "";

                if (i < riviLista.size() - 1) {
                    kysymysEnglanniksi = riviLista.get(i + 1);
                    if (i < riviLista.size() - 2) {
                        esimerkkiVastaus = riviLista.get(i + 2);
                    }
                }

                Kysely kohdeKysely = hallinta.haeKyselyNimenPerusteella(kyselynNimi);
                kohdeKysely.lisaaKysymys(new Kysymys(kysymysSuomeksi, kysymysEnglanniksi, esimerkkiVastaus));

                if (i < riviLista.size() - 2) {
                    i += 3;
                } else if (i < riviLista.size() - 1) {
                    i += 2;
                } else {
                    i += 1;
                }
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