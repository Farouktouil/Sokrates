package sokrates.sovelluslogiikka;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Luokka Kysymys edustaa kysymyksiä. Kysymys on olemassa aina vähintään yhdellä
 * kielellä. Kysymys muistaa listan kysymyksestä kaikilla kielillä, ja listaan
 * voidaan lisätä kysymyksen erikielisiä muotoiluja. Kysymys muistaa
 * esimerkkivastauksensa (ainakin toistaiseksi vain yhdellä kielellä) ja listan
 * vastauksista joita se on saanut (toistaiseksi 1 per kysymys, mutta pidetään
 * avoimena mahdollisuus vastata useasti samaan kysymykseen).
 *
 * @author Teo
 */
public class Kysymys {

    private HashMap<Kieli, String> kysymysKaikillaKielilla;
    private ArrayList<String> vastaukset;
    private String esimerkkivastaus;

    /**
     * Metodi luo uuden kysymyksen joka on olemassa vähintään parametrina
     * annetulla kielellä, jolla sen muotoilu on parametrina annettu merkkijono.
     * Kysymykseen luodaan HashMap<Kieli, String>, johon voidaan lisätä sama
     * kysymys mahdollisesti muillakin kielillä, ja asetetaan sinne aluksi
     * parametrina annettu kysymys parametrina annetulla kielellä.
     *
     * Luodaan myös ArrayList<String> kysymykseen saatuja vastauksia varten.
     *
     * @param kieli
     * @param kysymys
     */
    public Kysymys(Kieli kieli, String kysymys) {
        this.kysymysKaikillaKielilla = new HashMap();
        this.kysymysKaikillaKielilla.put(kieli, kysymys);
        this.vastaukset = new ArrayList();
    }

    /**
     * Metodi lisää kysymyksen myös parametrina annetulla kielellä parametrina
     * annetussa muodossa (nimittäin kysymyksen muistamaan HashMapiin
     * kysymysKaikillaKielillä).
     *
     * @param kieli
     * @param kysymys
     */
    public void lisaaKysymysEriKielella(Kieli kieli, String kysymys) {
        if (!this.kysymysKaikillaKielilla.containsKey(kieli)) {
            this.kysymysKaikillaKielilla.put(kieli, kysymys);
        }
    }

    public void setEsimerkkiVastaus(String esimerkkivastaus) {
        this.esimerkkivastaus = esimerkkivastaus;
    }

    public String getEsimerkkiVastaus() {
        return this.esimerkkivastaus;
    }

    public void lisaaVastaus(String vastaus) {
        this.vastaukset.add(vastaus);
    }

    public ArrayList<String> getVastaukset() {
        return this.vastaukset;
    }

    public HashMap<Kieli, String> getKysymysKaikillaKielilla() {
        return this.kysymysKaikillaKielilla;
    }

    /**
     * Metodi kysyy Asetukset-luokalta nykyisen kielen ja palauttaa sitten
     * kysymyksen muistamasta taulukosta kysymysKaikillaKielillä sen muotoilun,
     * joka vastaa nykyistä kieltä.
     *
     * @return kysymyksen String-muotoilu nykyisellä oletuskielellä
     */
    public String getKysymysNykyisellaKielella() {
        Kieli nykyinenKieli = Asetukset.getKieli();
        String kysymysNykyisellaKielella = this.kysymysKaikillaKielilla.get(nykyinenKieli);

        return kysymysNykyisellaKielella;
    }
}