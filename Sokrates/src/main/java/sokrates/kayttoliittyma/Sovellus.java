/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokrates.kayttoliittyma;

import java.util.Map;
import java.util.TreeMap;
import sokrates.komennot.*;
import sokrates.sovelluslogiikka.Kieli;
import sokrates.sovelluslogiikka.Kysely;
import sokrates.sovelluslogiikka.KyselyHallinta;
import sokrates.sovelluslogiikka.Kysymys;
import sokrates.util.Lukija;

/**
 *
 * @author Teo
 */
public class Sovellus {
    
    private Lukija lukija;
    private Map<String, Komento> komennot;
    private Komento ohje;
    private KyselyHallinta hallinta;
    
    public Sovellus() {
        this.lukija = new Lukija();
        this.komennot = new TreeMap<>();
        this.hallinta = new KyselyHallinta();
        luoKomennot(hallinta);
        this.ohje = new Ohje(lukija, hallinta, null, null, komennot.values());
        hallinta.setOletusKysely(luoOletusKysely());
    }
    
    public void suorita() {
        ohje.suorita();
        
        boolean jatketaan = true;
        
        while (jatketaan) {
            String syote = lukija.lueMerkkijono("komento: ");

            Komento komento = komennot.get(syote);
            if (komento == null) {
                komento = ohje;
            }
            
            jatketaan = komento.suorita();
        }
    }
    
    private void luoKomennot(KyselyHallinta hallinta) {
        luoKomento(new KyseleOletusKysely(lukija, hallinta, "1", "kysele oletuskysely"));
        luoKomento(new EsimerkkiToggle(lukija, hallinta, "2", "esimerkkivastaukset off/on"));
        luoKomento(new VaihdaKieli(lukija, hallinta, "3", "vaihda kieleksi englanti/suomi"));
    }

    private void luoKomento(Komento komento) {
        komennot.put(komento.getNimi(), komento);
    }
    
    public Kysely luoOletusKysely() {
        hallinta.lisaaKysely("ongelmanratkaisu");
        Kysely oletuskysely = hallinta.haeKyselyNimenPerusteella("ongelmanratkaisu");
        
        Kysymys nimeaOngelma = new Kysymys(Kieli.SUOMI, "Anna nimi ongelmalle (tämä tulee tekstitiedoston nimeksi):");
        nimeaOngelma.lisaaKysymysEriKielella(Kieli.ENGLANTI, "Name a problem (this will be the name of the file):");
        nimeaOngelma.setEsimerkkiVastaus("PDF-tiedoston generointi");
        
        Kysymys tunnetkoOngelmattomia = new Kysymys(Kieli.SUOMI, "Tunnetko ongelmattomia?");
        tunnetkoOngelmattomia.lisaaKysymysEriKielella(Kieli.ENGLANTI, "Do you know people who have solved or don't have the problem?");
        tunnetkoOngelmattomia.setEsimerkkiVastaus("Varmaankin vastaavaa koodanneet");
        
        Kysymys negaationKuvittelu = new Kysymys(Kieli.SUOMI, "Voitko kuvitella tai kuvailla maailmaa tai tilannetta, jossa ongelmaa ei ole tai se on ratkaistu?");
        negaationKuvittelu.lisaaKysymysEriKielella(Kieli.ENGLANTI, "Can you imagine or describe a world or situation where the problem has been solved or doesn't exist?");
        negaationKuvittelu.setEsimerkkiVastaus("Ratkaisun jälkeen tämä ohjelma generoi kyselyt vastauksineen PDF-muodossa =)");
        
        Kysymys tietoLahteita = new Kysymys(Kieli.SUOMI, "Voisiko tai kannattaisiko puhua jollekulle? Mistä voisi löytää tietoa?");
        tietoLahteita.lisaaKysymysEriKielella(Kieli.ENGLANTI, "Could or should you talk to someone? Where could you find information?");
        tietoLahteita.setEsimerkkiVastaus("iText-kirjasto, Stack Overflow; ehkä iTextiä käyttäneitä jos löytäisi.");
        
        Kysymys hakuTaitoja = new Kysymys(Kieli.SUOMI, "Millaisia kysymyksiä tai hakusanoja ongelmaan liittyy?");
        hakuTaitoja.lisaaKysymysEriKielella(Kieli.ENGLANTI, "What questions or keywords are relevant to the problem?");
        hakuTaitoja.setEsimerkkiVastaus("iText, Java PDF generate, How to create and write to a PDF file in java");
        
        Kysymys muuttujiaVaihtokauppoja = new Kysymys(Kieli.SUOMI, "Mitä muuttujia ongelmaan liittyy? Huomaatko vaihtokauppoja?");
        muuttujiaVaihtokauppoja.lisaaKysymysEriKielella(Kieli.ENGLANTI, "What variables are there? Are there trade-offs, can it be described as an optimization or balancing problem?");
        muuttujiaVaihtokauppoja.setEsimerkkiVastaus("PDF olisi kiva, mutta voi osoittautua työlääksi. Lassi sanoi että korvaisi graafisen UI:n :^D");
        
        Kysymys relevanttejaAloja = new Kysymys(Kieli.SUOMI, "Mitä (erityis/tutkimus)aloja ongelmaan liittyy?");
        relevanttejaAloja.lisaaKysymysEriKielella(Kieli.ENGLANTI, "What fields of expertise are relevant to the problem?");
        relevanttejaAloja.setEsimerkkiVastaus("Java-ohjelmointikielen ongelmahan tämä lähinnä on");
        
        Kysymys heikkojaOletuksia = new Kysymys(Kieli.SUOMI, "Mihin ongelma perustuu? Voisiko joitakin oletuksia kyseenalaistaa?");
        heikkojaOletuksia.lisaaKysymysEriKielella(Kieli.ENGLANTI, "What is the problem based on? Could some assumptions be questioned?");
        heikkojaOletuksia.setEsimerkkiVastaus("En vielä tiedä miten saisin ohjelmani generoimaan PDF-tiedostoja, ja luulen että se saattaisi olla hankalaa. Valmiilla kirjastolla näin yksinkertaisen tulostamisen saattaa kuitenkin voida tehdä yllättävän helposti. Sikäli ainakin ongelman vaikeus on atm tuntematon.");
        
        Kysymys toimintaSuunnitelmaa = new Kysymys(Kieli.SUOMI, "Mitä voisit yrittää ainakin ensimmäiseksi tehdä?");
        toimintaSuunnitelmaa.lisaaKysymysEriKielella(Kieli.ENGLANTI, "What are some first small steps that could be taken towards solving the problem?");
        toimintaSuunnitelmaa.setEsimerkkiVastaus("Googlettelua, lukemista, iText-kirjaston installaamista ja esimerkin kopiointia.");
        
        oletuskysely.lisaaKysymys(nimeaOngelma);
        oletuskysely.lisaaKysymys(tunnetkoOngelmattomia);
        oletuskysely.lisaaKysymys(negaationKuvittelu);
        oletuskysely.lisaaKysymys(tietoLahteita);
        oletuskysely.lisaaKysymys(hakuTaitoja);
        oletuskysely.lisaaKysymys(muuttujiaVaihtokauppoja);
        oletuskysely.lisaaKysymys(relevanttejaAloja);
        oletuskysely.lisaaKysymys(heikkojaOletuksia);
        oletuskysely.lisaaKysymys(toimintaSuunnitelmaa);
        
        return oletuskysely;
    }
}