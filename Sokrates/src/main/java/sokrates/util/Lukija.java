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
     * Metodi lukee ja palauttaa merkkijonona seuraavan käyttäjän kirjoittaman
     * rivin.
     *
     * @return Käyttäjän kirjoittama rivi.
     */
    public String lueMerkkijono() {
        return lukija.nextLine();
    }

    /**
     * Metodi tulostaa merkkijonon (kysymys tai lause), johon käyttäjän antama
     * vastaus toimii edellisen metodin tapaan palautettavana merkkijonona.
     *
     * @param kysymys Tulostettava kysymys
     * @return Käyttäjän (tulostetun kysymyksen perään) kirjoittama rivi.
     */
    public String lueMerkkijono(String kysymys) {
        System.out.print(kysymys);
        return lukija.nextLine();
    }

    /**
     * Metodi tulkitsee käyttäjän kirjoittaman rivin kokonaisluvuksi ja
     * palauttaa tuon kokonaisluvun.
     *
     * @return Käyttäjän kirjoittamasta rivistä tulkittu kokonaisluku.
     */
    public int lueKokonaisluku() {
        return Integer.parseInt(lukija.nextLine());
    }

    /**
     * Metodi tulostaa kysymyksen, johon käyttäjän antama vastaus tulkitaan
     * kokonaisluvuksi ja palautetaan kokonaisluku.
     *
     * @param kysymys Käyttäjälle tulostettava kysymys.
     * @return Käyttäjän (tulostetun kysymyksen perään) kirjoittama rivi
     * tulkittuna kokonaisluvuksi.
     */
    public int lueKokonaisluku(String kysymys) {
        System.out.print(kysymys);

//        try {
//            Integer.parseInt(lukija.nextLine());
//        } catch (NumberFormatException ex) {
//            
//        }

        return Integer.parseInt(lukija.nextLine());
    }

    /**
     * Metodi tulkitsee kokonaisluvuksi parametrinaan saaman merkkijonon
     *
     * @param merkkiJono Kokonaisluvuksi tulkittava merkkijono
     * @return Kokonaisluvuksi tulkittu merkkijono
     */
    public int tulkitseKokonaisluvuksi(String merkkiJono) {
        return Integer.parseInt(merkkiJono);
    }
}