package sokrates.kayttoliittyma;

import java.util.TreeMap;
import sokrates.komennot.*;
import sokrates.sovelluslogiikka.*;
import sokrates.tiedostonkasittely.TiedostonKirjoittaja;
import sokrates.tiedostonkasittely.TiedostonLukija;
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
        this.ohje = new Ohje(lukija, hallinta, null, "Valitse komento:", "Pick a command:", komennot.values());
        this.tk = new TiedostonKirjoittaja();
        this.tl = new TiedostonLukija();
        luoKomennot(this.hallinta);
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
    
    public void paivitaKyselyt() {
        this.hallinta.lisaaKyselyt(tl.lueKyselyTiedostojenNimet("src/inquiries/"));
    }

    /**
     * Metodi luo(duttaa) yksitellen jokaisen ohjelman käyttämän komennon
     * lukuunottamatta Ohjetta, joka luodaan Sovelluksen konstruktorissa.
     *
     * @param hallinta Parametrina saatu KyselyHallinta annetaan edelleen
     * jokaiselle komennolle tiedoksi
     */
    private void luoKomennot(KyselyHallinta hallinta) {
        luoKomento(new KyseleKysely(lukija, hallinta, tk, "0", "kysele kysely", "perform an inquiry"));
        luoKomento(new LisaaKysely(lukija, hallinta, tk, "1", "lisää uusi kysely", "add a new inquiry"));
        luoKomento(new PoistaKysely(lukija, hallinta, tk, tl, "2", "poista kysely", "remove an inquiry"));
        luoKomento(new LisaaKysymyksiaKyselyyn(lukija, hallinta, "3", "lisää kysymyksiä kyselyyn", "add questions to an inquiry"));
        luoKomento(new PoistaKysymyksiaKyselysta(lukija, hallinta, "4", "poista kysymyksiä kyselystä", "remove questions from an inquiry"));
        luoKomento(new VaihdaKieli(lukija, hallinta, "5", "vaihda kieleksi englanti", "change language to Finnish"));
        luoKomento(new EsimerkkiToggle(lukija, hallinta, "6", "aseta esimerkkivastaukset off/on", "toggle examples off/on"));
        luoKomento(new Lopeta(lukija, hallinta, "x", "lopeta", "quit"));
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
//    /**
//     * Metodi luo ohjelman mukana tulevan kyselyn nimeltä 'ongelmanratkaisu'
//     * KyselyHallintaan.
//     */
//    private void luoOngelmanratkaisuKysely() {
//        this.hallinta.lisaaKysely("ongelmanratkaisu");
//        Kysely ongelmanratkaisu = this.hallinta.haeKyselyNimenPerusteella("ongelmanratkaisu");
//
//        ongelmanratkaisu.lisaaKysymys(new Kysymys("nimeä ongelma (tämä tulee tekstitiedoston nimeksi):",
//                "name a problem (this will be the name of the file):",
//                "combining computing and ergonomy, exercise"));
//
//        ongelmanratkaisu.lisaaKysymys(new Kysymys("aiheuttajat, juuret, oletukset, mihin perustuu",
//                "causes, roots, assumptions, what is it based on ",
//                "computing tends to be done sitting still, moving and posture might drain focus"));
//
//        ongelmanratkaisu.lisaaKysymys(new Kysymys("muuttujia, aspekteja, konsideraatioita, ali-/yli-/analogisia ongelmia, uhrauksia/vaihtokauppoja",
//                "variables, aspects, considerations, sub-/super-/analogous problems, sacrifices/trade-offs",
//                "focus, effort, futuristic input devices, ergonomy, (aerobic) health, time"));
//
//        ongelmanratkaisu.lisaaKysymys(new Kysymys("ihmisiä, ongelmattomia, ratkaisseita, eksperttejä, apua",
//                "people, anyone without the problem, anyone who has solved it, experts, help",
//                "maybe those with working standing desks or treadmill desks; ergonomists; anyone who doesn't mind and manages both well"));
//
//        ongelmanratkaisu.lisaaKysymys(new Kysymys("kuvaile mahdollisia ratkaisuja",
//                "describe possible solutions",
//                "standing desk, treadmill desk, mobile computing (futuristic I/O), better time management"));
//
//        ongelmanratkaisu.lisaaKysymys(new Kysymys("tietolähteitä, hakusanoja, kysymyksiä, aloja/kategorioita",
//                "information sources, tags and keywords, questions, fields/categories",
//                "online: ergonomy while using a computer, finding time for diverse movement, standing/treadmill desks"));
//
//        ongelmanratkaisu.lisaaKysymys(new Kysymys("mahdollisia toimenpiteitä, pieniä askelia, mitä ainakin voisi tehdä seuraavaksi",
//                "possible actions, small steps, what at least could be done next",
//                "search, talk, ask, try to implement something for a while, experiment and see what happens"));
//
//        ongelmanratkaisu.lisaaKysymys(new Kysymys("mitä tahansa muuta",
//                "anything else",
//                "treadmill desks could cost a lot, drain too much focus, take space and/or be noisy"));
//    }
//
//    /**
//     * Metodi luo ohjelman mukana tulevan kyselyn nimeltä 'päiväkirja'
//     * KyselyHallintaan.
//     */
//    private void luoPaivakirjaKysely() {
//        this.hallinta.lisaaKysely("päiväkirja");
//        Kysely paivakirja = hallinta.haeKyselyNimenPerusteella("päiväkirja");
//
//        paivakirja.lisaaKysymys(new Kysymys("ajoita ja/tai nimeä päivä (tämä tulee tekstitiedoston nimeksi):",
//                "time and/or name a day (this will be the name of the file):",
//                "2014-1-1 post-new year"));
//
//        paivakirja.lisaaKysymys(new Kysymys("keitä tuttuja? keitä muita?",
//                "familiar beings? others?",
//                "met a new student with an old friend"));
//
//        paivakirja.lisaaKysymys(new Kysymys("missä olit?",
//                "where were you?",
//                "home, campus, friend, shop, movies, eating"));
//
//        paivakirja.lisaaKysymys(new Kysymys("mitä teit tänään?",
//                "what did you do today?",
//                "browse news, program, math exercises, read books, discuss limits on deep sea fish weirdness"));
//
//        paivakirja.lisaaKysymys(new Kysymys("mitä opit tänään?",
//                "what did you learn today?",
//                "limits on fish weirdness might be unknowable, programming can make one forget the time"));
//
//        paivakirja.lisaaKysymys(new Kysymys("toteutuiko tavoitteita? oliko hyvä päivä?",
//                "did you meet any aims? was it a good day?",
//                "met a programming deadline and a friend, nice!"));
//
//        paivakirja.lisaaKysymys(new Kysymys("huominen",
//                "tomorrow",
//                "visit city center during the day, maybe swimming in the evening. no dl :)"));
//
//        paivakirja.lisaaKysymys(new Kysymys("mitä tahansa muuta",
//                "anything else",
//                "forgot to mention that during lunch we met more new people as well"));
//    }
//
//    private void luoUnipaivakirjaKysely() {
//        this.hallinta.lisaaKysely("unipäiväkirja");
//        Kysely unipaivakirja = hallinta.haeKyselyNimenPerusteella("unipäiväkirja");
//
//        unipaivakirja.lisaaKysymys(new Kysymys("ajoita ja/tai nimeä uni (tämä tulee tekstitiedoston nimeksi):",
//                "time and/or name a dream (this will be the name of the file):",
//                "2014-1-1 moominvalley with a twist"));
//
//        unipaivakirja.lisaaKysymys(new Kysymys("keitä tuttuja? keitä muita?",
//                "familiar beings? others?",
//                "bro, old teacher, and a weird god-type with glasses"));
//
//        unipaivakirja.lisaaKysymys(new Kysymys("missä tapahtui?",
//                "where did stuff happen?",
//                "I was in a well, and the trees that were trying to get me out were on ISS"));
//
//        unipaivakirja.lisaaKysymys(new Kysymys("mitä tapahtui?",
//                "what happened?",
//                "more than what didn't >80"));
//
//        unipaivakirja.lisaaKysymys(new Kysymys("millaisia unilogiikan ilmentymiä?",
//                "what manifestations of dream logic?",
//                "shoes were kind of concerned. children felt formidable. math hat never laughs."));
//
//        unipaivakirja.lisaaKysymys(new Kysymys("tunnelmia?",
//                "moods?",
//                "everyone kept searching for something, until suddenly nomadic"));
//
//        unipaivakirja.lisaaKysymys(new Kysymys("vaikutteita?",
//                "influences?",
//                "books, movies, article read, discussion had, mood experienced, person or character"));
//
//        unipaivakirja.lisaaKysymys(new Kysymys("mitä tahansa muuta",
//                "anything else",
//                "right, the elephant was loyal but only to physicists"));
//    }
}