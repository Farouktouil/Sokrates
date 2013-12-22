/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

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
        this.komennot = new TreeMap<String, Komento>();
        this.hallinta = new KyselyHallinta();
        luoKomennot(hallinta);
        this.ohje = new Ohje(lukija, hallinta, null, null, komennot.values());
        Kysely oletuskysely = luoOletusKysely();
        hallinta.setOletusKysely(oletuskysely);
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
    }

    private void luoKomento(Komento komento) {
        komennot.put(komento.getNimi(), komento);
    }
    
    public Kysely luoOletusKysely() {
        hallinta.lisaaKysely("ongelmanratkaisu");
        Kysely oletuskysely = hallinta.haeKyselyNimenPerusteella("ongelmanratkaisu");
        
        Kysymys onkoOngelmia = new Kysymys(Kieli.SUOMI, "Ongelma?");
        onkoOngelmia.lisaaKysymysEriKielella(Kieli.ENGLANTI, "Problem?");
        onkoOngelmia.setEsimerkkiVastaus("Osa koodista ei toimi");
        
        Kysymys ekaAskel = new Kysymys(Kieli.SUOMI, "Mitä et ole vielä kokeillut?");
        ekaAskel.lisaaKysymysEriKielella(Kieli.ENGLANTI, "What haven't you tried yet?");
        ekaAskel.setEsimerkkiVastaus("Pala kerrallaan rakentamista tbh");
        
        oletuskysely.lisaaKysymys(onkoOngelmia);
        oletuskysely.lisaaKysymys(ekaAskel);
        
        return oletuskysely;
    }
}