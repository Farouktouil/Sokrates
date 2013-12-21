/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokrates.tiedostonkasittely;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import sokrates.sovelluslogiikka.Kysymys;

/**
 *
 * @author Teo
 */
public class Tiedostonkasittelija {
    
    public void kirjoitutaVastauksineen(ArrayList<Kysymys> kysymykset) throws IOException {
        String tiedostonNimi = kysymykset.get(0).vastaukset().get(0);
        File file = luoTiedosto(tiedostonNimi);
        kirjoitaKysymyksetVastauksineenTiedostoon(file, kysymykset);
    }
    
    public File luoTiedosto(String tiedostonNimi) {
          File file = new File(tiedostonNimi + ".txt");
          return file;
    }
    
    public void kirjoitaKysymyksetVastauksineenTiedostoon(File file, ArrayList<Kysymys> kysymykset) throws IOException {
        
          BufferedWriter output = new BufferedWriter(new FileWriter(file));
          
          for (Kysymys kysymys : kysymykset) {
              output.write(kysymys.getKysymys() + "\n    ");
              output.write(kysymys.vastaukset().get(0));
          }
          
          output.close();
    }

    public ArrayList<String> lue(String tiedosto) throws FileNotFoundException {
        Scanner lukija = new Scanner(new File(tiedosto));
        ArrayList<String> riviLista = new ArrayList<String>();

        while (lukija.hasNextLine()) {
            riviLista.add(lukija.nextLine());
        }

        return riviLista;
    }

    public void tallenna(String tiedosto, String teksti) throws FileNotFoundException, IOException {
        FileWriter kirjoittaja = new FileWriter(tiedosto);
        kirjoittaja.write(teksti);
        kirjoittaja.close();
    }

    public void tallenna(String tiedosto, ArrayList<String> tekstit) throws FileNotFoundException, IOException {
        FileWriter kirjoittaja = new FileWriter(tiedosto);
        for (String rivi : tekstit) {
            kirjoittaja.write(rivi + "\n");
        }
        kirjoittaja.close();
    }
}
