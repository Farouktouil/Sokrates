package sokrates.util;

import java.util.Scanner;

/**
 * Lukija vastaa käyttäjän syötteen lukemista ja tulkitsemisesta haluttuun
 * muotoon.
 *
 * @author Teo
 */
public class Lukija {

    /**
     * Lukijaan kapseloitu Scanner-olio.
     */
    private Scanner lukija;

    /**
     * Konstruktori luo uuden Lukija-olion. Lukija muistaa täsmälleen yhden
     * Scanner-olion.
     */
    public Lukija() {
        this.lukija = new Scanner(System.in);
    }

    /**
     * Metodi tulostaa merkkijonon (kysymys tai lause), johon käyttäjän antama
     * vastaus toimii palautettavana merkkijonona.
     *
     * @param kysymys Tulostettava kysymys
     * @return Käyttäjän (tulostetun kysymyksen perään) kirjoittama rivi.
     */
    public String lueMerkkijono(String kysymys) {
        System.out.print(kysymys);
        return lukija.nextLine();
    }

    /**
     * Metodi tulkitsee kokonaisluvuksi parametrina saamansa merkkijonon.
     *
     * @param merkkiJono Kokonaisluvuksi tulkittava merkkijono
     * @return Kokonaisluvuksi tulkittu merkkijono
     */
    public int tulkitseKokonaisluvuksi(String merkkiJono) {
        int kokonaisLuku = -1;

        try {
            kokonaisLuku = Integer.parseInt(merkkiJono);
        } catch (Exception eiLukuunnuKivasti) {
        }

        return kokonaisLuku;
    }
}