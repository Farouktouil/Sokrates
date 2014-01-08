package sokrates.kayttoliittyma;

import java.util.TreeMap;
import sokrates.komennot.*;
import sokrates.sovelluslogiikka.*;
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
        luoKomennot(this.hallinta);
        luoOngelmanRatkaisuKysely();
        luoPaivaKirjaKysely();
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
        ohje.suorita();

        boolean jatketaan = true;

        while (jatketaan) {
            String syote = lukija.lueMerkkijono(Tulostamo.komento());
            System.out.println("");

            Komento komento = komennot.get(syote);

            if (komento == null) {
                komento = ohje;
            }

            jatketaan = komento.suorita();
        }
    }

    /**
     * Metodi luo(duttaa) yksitellen jokaisen ohjelman käyttämän komennon
     * lukuunottamatta Ohjetta, joka luodaan Sovelluksen konstruktorissa.
     *
     * @param hallinta Parametrina saatu KyselyHallinta annetaan edelleen
     * jokaiselle komennolle tiedoksi
     */
    private void luoKomennot(KyselyHallinta hallinta) {
        luoKomento(new KyseleKysely(lukija, hallinta, ohje, "0", "kysele kysely", "perform an inquiry"));
        luoKomento(new LisaaKysely(lukija, hallinta, ohje, "1", "lisää uusi kysely", "add a new inquiry"));
        luoKomento(new PoistaKysely(lukija, hallinta, ohje, "2", "poista kysely", "remove an inquiry"));
        luoKomento(new LisaaKysymyksiaKyselyyn(lukija, hallinta, ohje, "3", "lisää kysymyksiä kyselyyn", "add questions to an inquiry"));
        luoKomento(new PoistaKysymyksiaKyselysta(lukija, hallinta, ohje, "4", "poista kysymyksiä kyselystä", "remove questions from an inquiry"));
        luoKomento(new VaihdaKieli(lukija, hallinta, ohje, "5", "vaihda kieleksi englanti", "change language to Finnish"));
        luoKomento(new EsimerkkiToggle(lukija, hallinta, ohje, "6", "aseta esimerkkivastaukset off/on", "toggle examples off/on"));
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

    /**
     * Metodi luo ohjelman mukana tulevan kyselyn nimeltä 'ongelmanratkaisu'
     * KyselyHallintaan.
     */
    private void luoOngelmanRatkaisuKysely() {
        this.hallinta.lisaaKysely("ongelmanratkaisu");
        Kysely ongelmanratkaisu = this.hallinta.haeKyselyNimenPerusteella("ongelmanratkaisu");

        ongelmanratkaisu.lisaaKysymys(new Kysymys("Anna nimi ongelmalle (tämä tulee tekstitiedoston nimeksi):",
                "Name a problem (this will be the name of the file):",
                "PDF-tiedoston generointi"));

        ongelmanratkaisu.lisaaKysymys(new Kysymys("Keillä tätä ongelmaa ei ole?",
                "Who've solved or don't have the problem?",
                "Varmaankin vastaavaa koodanneet"));

        ongelmanratkaisu.lisaaKysymys(new Kysymys("Kuvaile maailmaa tai tilannetta, jossa ongelmaa ei ole tai se on ratkaistu:",
                "Describe a world or situation where the problem has been solved or doesn't exist:",
                "Ratkaisun jälkeen tämä ohjelma generoi kyselyt vastauksineen PDF-muodossa =)"));

        ongelmanratkaisu.lisaaKysymys(new Kysymys("Kenelle voisi tai kannattaisi puhua? Mistä voisi löytää tietoa?",
                "Who could or should you talk to? Where could you find information?",
                "iText-kirjasto, Stack Overflow; ehkä iTextiä käyttäneitä jos löytäisi."));

        ongelmanratkaisu.lisaaKysymys(new Kysymys("Mitä kysymyksiä tai hakusanoja ongelmaan liittyy?",
                "What questions or keywords are relevant to the problem?",
                "iText, Java PDF generate, How to create and write to a PDF file in java"));

        ongelmanratkaisu.lisaaKysymys(new Kysymys("Mitä muuttujia ongelmaan liittyy? Huomaatko vaihtokauppoja (tasapainottelua, 'trade-offs')?",
                "What variables are there? Are there trade-offs, can it be described as an optimization or balancing problem?",
                "PDF olisi kiva, mutta voi osoittautua työlääksi. Lassi sanoi että korvaisi graafisen UI:n :^D"));

        ongelmanratkaisu.lisaaKysymys(new Kysymys("Mitä (erityis/tutkimus)aloja ongelmaan liittyy?",
                "What fields of expertise are relevant to the problem?",
                "What fields of expertise are relevant to the problem?"));

        ongelmanratkaisu.lisaaKysymys(new Kysymys("Mihin ongelma perustuu? Mitä oletuksia voisi kyseenalaistaa?",
                "What is the problem based on? What assumptions could be questioned?",
                "En vielä tiedä miten saisin ohjelmani generoimaan PDF-tiedostoja, ja luulen että se saattaisi olla hankalaa."
                + "Valmiilla kirjastolla näin yksinkertaisen tulostamisen saattaa kuitenkin voida tehdä yllättävän helposti."
                + "Sikäli ainakin ongelman vaikeus on atm tuntematon."));

        ongelmanratkaisu.lisaaKysymys(new Kysymys("Mitä voisit yrittää ainakin ensimmäiseksi tehdä?",
                "What are some first small steps that could be taken?",
                "Googlettelua, lukemista, iText-kirjaston installaamista ja esimerkin kopiointia."));
    }

    /**
     * Metodi luo ohjelman mukana tulevan kyselyn nimeltä 'päiväkirja'
     * KyselyHallintaan.
     */
    private void luoPaivaKirjaKysely() {
        this.hallinta.lisaaKysely("päiväkirja");
        Kysely paivakirja = hallinta.haeKyselyNimenPerusteella("päiväkirja");

        paivakirja.lisaaKysymys(new Kysymys("Anna nimi päivälle (tämä tulee tekstitiedoston nimeksi):",
                "Name a day (this will be the name of the file):",
                "2014-1-1"));

        paivakirja.lisaaKysymys(new Kysymys("Miltä tuntuu?",
                "How are you feeling?",
                "Could be worse!"));
    }
}