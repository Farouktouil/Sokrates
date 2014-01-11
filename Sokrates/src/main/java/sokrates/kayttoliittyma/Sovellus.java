package sokrates.kayttoliittyma;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import sokrates.komennot.*;
import sokrates.sovelluslogiikka.*;
import sokrates.tiedostonkasittely.*;
import sokrates.util.Lukija;

/**
 * Sovellus toimii ohjelman käyttöliittymänä ja rakennuspaikkana.
 *
 * Sovellus luo ja muistaa Lukijan, komennot, ohjeen ja KyselyHallinnan sekä luo
 * kyselyt.
 *
 * Sovellus myös lukee käyttäjän syötettä ja suorituttaa komentoja tämän
 * pohjalta. Ohjelma päättyy kun Sovelluksen suorita()-metodin
 * jatketaan-muuttuja saa arvon false.
 *
 * @author Teo
 */
public class Sovellus {

    /**
     * Sovellukseen kapseloitu lukija, joka lukee käyttäjän syötettä
     */
    private Lukija lukija;
    /**
     * Sovellukseen kapseloitu taulukko komennoista, joista kullakin on
     * avaimenaan merkkijono (käytännössä komennon nimi)
     */
    private TreeMap<String, Komento> komennot;
    /**
     * Sovellukseen kapseloitu KyselyHallinta
     */
    private KyselyHallinta hallinta;
    /**
     * Sovellukseen kapseloitu Ohje-komento
     */
    private Komento ohje;
    private TiedostonKirjoittaja tk;
    private TiedostonLukija tl;

    /**
     * Konstruktori luo uuden Sovelluksen, jolloin tälle luodaan Lukija, TreeMap
     * komennoilla nimineen, Ohje sekä KyselyHallinta.
     *
     * Sovellus myös luo kaikki komennot ja lisää ne muistamaansa
     * komennot-TreeMapiin.
     *
     * Luo myös kyselyt.
     */
    public Sovellus() {
        this.lukija = new Lukija();
        this.komennot = new TreeMap<>();
        this.hallinta = new KyselyHallinta();
        this.ohje = new Ohje(lukija, hallinta, komennot.values(), null, "Valitse komento:", "Pick a command:");
        this.tk = new TiedostonKirjoittaja();
        this.tl = new TiedostonLukija();
        luoKomennot();
    }

    /**
     * Metodi laittaa Sovelluksen komentotaulukkoon kunkin luodun komennon,
     * avaimenaan kyseisen komennon muistama nimi (käytännössä usein tai aina
     * yksittäinen numero tai kirjain).
     *
     * @param komento Parametrina saatu komento joka taulukkoon nimineen
     * lisätään.
     */
    private void luoKomento(Komento komento) {
        this.komennot.put(komento.getNimi(), komento);
    }

    /**
     * Metodi luo(duttaa) yksitellen jokaisen ohjelman käyttämän komennon
     * lukuunottamatta Ohjetta, joka luodaan Sovelluksen konstruktorissa.
     *
     * @param hallinta Parametrina saatu KyselyHallinta annetaan edelleen
     * jokaiselle komennolle tiedoksi
     */
    private void luoKomennot() {
        luoKomento(new KyseleKysely(lukija, hallinta, tk,
                "0", "kysele kysely", "perform an inquiry"));
        luoKomento(new LisaaKysely(lukija, hallinta, tk,
                "1", "lisää uusi kysely", "add a new inquiry"));
        luoKomento(new PoistaKysely(lukija, hallinta, tk, tl,
                "2", "poista kysely", "remove an inquiry"));
        luoKomento(new LisaaKysymyksiaKyselyyn(lukija, hallinta, tk, tl,
                "3", "lisää kysymyksiä kyselyyn", "add questions to an inquiry"));
        luoKomento(new PoistaKysymyksiaKyselysta(lukija, hallinta,
                "4", "poista kysymyksiä kyselystä", "remove questions from an inquiry"));
        luoKomento(new VaihdaKieli(lukija, hallinta,
                "5", "vaihda kieleksi englanti", "change language to Finnish"));
        luoKomento(new EsimerkkiToggle(lukija, hallinta,
                "6", "aseta esimerkkivastaukset off/on", "toggle examples off/on"));
        luoKomento(new Lopeta(lukija, hallinta,
                "x", "lopeta", "quit"));
    }

    public List<String> paivitaKyselyt() {
        List<String> kyselyTiedostojenNimet = tl.lueKyselyTiedostojenNimet("src/inquiries/");
        this.hallinta.lisaaKyselyt(kyselyTiedostojenNimet);
        return kyselyTiedostojenNimet;
    }

    public void paivitaKyselytKaynnistettaessa() {
        try {
            List<File> kyselyTiedostot = tl.getNimiaVastaavatKyselyTiedostot(paivitaKyselyt());
            tl.lisaaKyselyTiedostojenSisallotKysymyksiksi(kyselyTiedostot, hallinta);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Sovellus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodi tulostaa käyttäjälle ohjelman käyttöohjeet eli valittavissa olevat
     * komennot ja kuuntelee käyttäjän syötettä kunnes syöte vastaa jotakin
     * tunnettua komentoa, jolloin poiketaan suorittamaan kyseinen komento.
     * Komennon ollessa tyhjä (eli kai pakostikin pelkkä enterin painallus)
     * tulostetaan uudelleen sallitut komennot.
     *
     * Kuuntelulooppi jatkuu jos ja vain jos komento palauttaa true eli
     * käytännössä kunnes suoritetaan komento Lopeta.
     */
    public void suorita() {
        System.out.print("Tervetuloa kyselyohjelmaan. ");
        paivitaKyselytKaynnistettaessa();
        boolean jatketaan = true;

        while (jatketaan) {
            ohje.suorita();
            paivitaKyselyt();
            String syote = lukija.lueMerkkijono(Tulostamo.komento());
            System.out.println("");
            Komento komento = komennot.get(syote);

            if (komento == null) {
                komento = ohje;
            }

            jatketaan = komento.suorita();
        }
    }
}