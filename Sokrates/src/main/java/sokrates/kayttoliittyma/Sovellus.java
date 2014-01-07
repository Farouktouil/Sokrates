package sokrates.kayttoliittyma;

import java.util.TreeMap;
import sokrates.komennot.*;
import sokrates.sovelluslogiikka.*;
import sokrates.util.Lukija;

/**
 * Sovellus toimii ohjelman käyttöliittymänä ja rakennuspaikkana.
 *
 * Sovellus luo ja muistaa Lukijan, komennot, ohjeen ja KyselyHallinnan sekä luo
 * oletuskysely(n/t).
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
     * Lisäksi Sovellus luo kyselyn joka asetetaan KyselyHallinnan
     * oletuskyselyksi (ongelmanratkaisu) ja (ainakin toistaiseksi) toisenkin
     * kyselyn (päiväkirja).
     */
    public Sovellus() {
        this.lukija = new Lukija();
        this.komennot = new TreeMap<>();
        this.hallinta = new KyselyHallinta();
        this.ohje = new Ohje(lukija, hallinta, null, "Valitse komento:", "Pick a command:", komennot.values());

        luoKomennot(this.hallinta);
        this.hallinta.setOletusKysely(luoOletusKysely());
        luoPaivaKirjaKysely();
    }

    /**
     * Metodi tulostaa käyttäjälle ohjelman käyttöohjeet eli valittavissa olevat
     * komennot ja kuuntelee käyttäjän syötettä kunnes syöte vastaa jotakin
     * tunnettua komentoa, jolloin poiketaan suorittamaan kyseinen komento.
     * Komennon ollessa tyhjä (eli kai pakostikin pelkkä enterin painallus)
     * tulostetaan uudelleen sallitut komennot.
     *
     * Kuuntelulooppi jatkuu jos ja vain jos komento palauttaa true. false
     * palautuu vain kun on suoritettu komento Lopeta.
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
<<<<<<< HEAD
        luoKomento(new KyseleOletusKysely(lukija, hallinta, "1", "kysele oletuskysely"));
        luoKomento(new VaihdaOletusKysely(lukija, hallinta, "2", "vaihda oletuskysely"));
        luoKomento(new LisaaKysely(lukija, hallinta, "3", "lisää uusi kysely"));
        luoKomento(new PoistaKysely(lukija, hallinta, "4", "poista kysely"));
        luoKomento(new VaihdaKieli(lukija, hallinta, "5", "vaihda kieleksi englanti/suomi"));
        luoKomento(new EsimerkkiToggle(lukija, hallinta, "6", "esimerkkivastaukset off/on"));
        luoKomento(new Lopeta(lukija, hallinta, "x", "lopeta"));
=======
        luoKomento(new KyseleOletusKysely(lukija, hallinta, "1", "kysele oletuskysely", "perform default inquiry"));
        luoKomento(new VaihdaOletusKysely(lukija, hallinta, "2", "vaihda oletuskysely", "change default inquiry"));
        luoKomento(new LisaaKysely(lukija, hallinta, "3", "lisää uusi kysely", "add new inquiry"));
        luoKomento(new PoistaKysely(lukija, hallinta, "4", "poista kysely", "remove inquiry"));
        luoKomento(new VaihdaKieli(lukija, hallinta, "5", "vaihda kieleksi englanti", "change language to Finnish"));
        luoKomento(new EsimerkkiToggle(lukija, hallinta, "6", "aseta esimerkkivastaukset off/on", "toggle examples off/on"));
        luoKomento(new Lopeta(lukija, hallinta, "x", "lopeta", "quit"));
>>>>>>> kaksikielistettiin tekstikäyttiskin
    }

    /**
     * Metodi laittaa Sovelluksen komentotaulukkoon kunkin luodun komennon,
     * avaimenaan kyseisen komennon muistama nimi (käytännössä usein tai aina
     * yksittäinen numero).
     *
     * @param komento Parametrina saatu komento joka taulukkoon nimineen
     * lisätään.
     */
    private void luoKomento(Komento komento) {
        this.komennot.put(komento.getNimi(), komento);
    }

    private Kysely luoOletusKysely() {
        this.hallinta.lisaaKysely("ongelmanratkaisu");
        Kysely oletuskysely = this.hallinta.haeKyselyNimenPerusteella("ongelmanratkaisu");

        Kysymys nimeaOngelma = new Kysymys(Kieli.SUOMI, "Anna nimi ongelmalle (tämä tulee tekstitiedoston nimeksi):");
        nimeaOngelma.lisaaKysymysEriKielella(Kieli.ENGLANTI, "Name a problem (this will be the name of the file):");
        nimeaOngelma.setEsimerkkiVastaus("PDF-tiedoston generointi");

        Kysymys tunnetkoOngelmattomia = new Kysymys(Kieli.SUOMI, "Keillä tätä ongelmaa ei ole?");
        tunnetkoOngelmattomia.lisaaKysymysEriKielella(Kieli.ENGLANTI, "Who've solved or don't have the problem?");
        tunnetkoOngelmattomia.setEsimerkkiVastaus("Varmaankin vastaavaa koodanneet");

        Kysymys negaationKuvailu = new Kysymys(Kieli.SUOMI, "Kuvaile maailmaa tai tilannetta, jossa ongelmaa ei ole tai se on ratkaistu:");
        negaationKuvailu.lisaaKysymysEriKielella(Kieli.ENGLANTI, "Describe a world or situation where the problem has been solved or doesn't exist:");
        negaationKuvailu.setEsimerkkiVastaus("Ratkaisun jälkeen tämä ohjelma generoi kyselyt vastauksineen PDF-muodossa =)");

        Kysymys tietoLahteita = new Kysymys(Kieli.SUOMI, "Kenelle voisi tai kannattaisi puhua? Mistä voisi löytää tietoa?");
        tietoLahteita.lisaaKysymysEriKielella(Kieli.ENGLANTI, "Who could or should you talk to? Where could you find information?");
        tietoLahteita.setEsimerkkiVastaus("iText-kirjasto, Stack Overflow; ehkä iTextiä käyttäneitä jos löytäisi.");

        Kysymys hakuTaitoja = new Kysymys(Kieli.SUOMI, "Mitä kysymyksiä tai hakusanoja ongelmaan liittyy?");
        hakuTaitoja.lisaaKysymysEriKielella(Kieli.ENGLANTI, "What questions or keywords are relevant to the problem?");
        hakuTaitoja.setEsimerkkiVastaus("iText, Java PDF generate, How to create and write to a PDF file in java");

        Kysymys muuttujiaVaihtokauppoja = new Kysymys(Kieli.SUOMI, "Mitä muuttujia ongelmaan liittyy? Huomaatko vaihtokauppoja (tasapainottelua, 'trade-offs')?");
        muuttujiaVaihtokauppoja.lisaaKysymysEriKielella(Kieli.ENGLANTI, "What variables are there? Are there trade-offs, can it be described as an optimization or balancing problem?");
        muuttujiaVaihtokauppoja.setEsimerkkiVastaus("PDF olisi kiva, mutta voi osoittautua työlääksi. Lassi sanoi että korvaisi graafisen UI:n :^D");

        Kysymys relevanttejaAloja = new Kysymys(Kieli.SUOMI, "Mitä (erityis/tutkimus)aloja ongelmaan liittyy?");
        relevanttejaAloja.lisaaKysymysEriKielella(Kieli.ENGLANTI, "What fields of expertise are relevant to the problem?");
        relevanttejaAloja.setEsimerkkiVastaus("Java-ohjelmointikielen ongelmahan tämä lähinnä on");

        Kysymys heikkojaOletuksia = new Kysymys(Kieli.SUOMI, "Mihin ongelma perustuu? Mitä oletuksia voisi kyseenalaistaa?");
        heikkojaOletuksia.lisaaKysymysEriKielella(Kieli.ENGLANTI, "What is the problem based on? What assumptions could be questioned?");
        heikkojaOletuksia.setEsimerkkiVastaus("En vielä tiedä miten saisin ohjelmani generoimaan PDF-tiedostoja, ja luulen että se saattaisi olla hankalaa. Valmiilla kirjastolla näin yksinkertaisen tulostamisen saattaa kuitenkin voida tehdä yllättävän helposti. Sikäli ainakin ongelman vaikeus on atm tuntematon.");

        Kysymys toimintaSuunnitelmaa = new Kysymys(Kieli.SUOMI, "Mitä voisit yrittää ainakin ensimmäiseksi tehdä?");
        toimintaSuunnitelmaa.lisaaKysymysEriKielella(Kieli.ENGLANTI, "What are some first small steps that could be taken?");
        toimintaSuunnitelmaa.setEsimerkkiVastaus("Googlettelua, lukemista, iText-kirjaston installaamista ja esimerkin kopiointia.");

        oletuskysely.lisaaKysymys(nimeaOngelma);
        oletuskysely.lisaaKysymys(tunnetkoOngelmattomia);
        oletuskysely.lisaaKysymys(negaationKuvailu);
        oletuskysely.lisaaKysymys(tietoLahteita);
        oletuskysely.lisaaKysymys(hakuTaitoja);
        oletuskysely.lisaaKysymys(muuttujiaVaihtokauppoja);
        oletuskysely.lisaaKysymys(relevanttejaAloja);
        oletuskysely.lisaaKysymys(heikkojaOletuksia);
        oletuskysely.lisaaKysymys(toimintaSuunnitelmaa);

        return oletuskysely;
    }

    private void luoPaivaKirjaKysely() {
        hallinta.lisaaKysely("päiväkirja");
        Kysely paivakirja = hallinta.haeKyselyNimenPerusteella("päiväkirja");

        Kysymys nimeaPaiva = new Kysymys(Kieli.SUOMI, "Anna nimi päivälle (tämä tulee tekstitiedoston nimeksi):");
        nimeaPaiva.lisaaKysymysEriKielella(Kieli.ENGLANTI, "Name a day (this will be the name of the file):");
        nimeaPaiva.setEsimerkkiVastaus("2014-1-1");

        Kysymys miltaTuntuu = new Kysymys(Kieli.SUOMI, "Miltä tuntuu?");
        miltaTuntuu.lisaaKysymysEriKielella(Kieli.ENGLANTI, "How are you feeling?");
        miltaTuntuu.setEsimerkkiVastaus("Could be worse!");

        paivakirja.lisaaKysymys(nimeaPaiva);
        paivakirja.lisaaKysymys(miltaTuntuu);
    }
}