package sokrates.kayttoliittyma;

import java.io.FileNotFoundException;

/**
 * Luokka App tapahtuu ohjelma käynnistettäessä
 *
 * @author Teo
 */
public class App {

    /**
     * Metodi luo ja suorituttaa() Sovelluksen
     *
     * @param args
     */
    public static void main(String[] args) throws FileNotFoundException {
        new Sovellus().suorita();
    }
}