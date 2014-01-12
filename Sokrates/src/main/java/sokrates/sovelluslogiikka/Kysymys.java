package sokrates.sovelluslogiikka;

import java.util.HashMap;

/**
 * Kysymys on olemassa monella eri kielellä ja muistaa HashMapin muotoilustaan
 * eri kielillä (avaimina). Kysymykseen liittyy esimerkkivastaus ja myöhemmin
 * mahdollisesti käyttäjän syötteestä kysymykseen liitettävä vastaus.
 *
 * @author Teo
 */
public class Kysymys {

    /**
     * Kysymys kaikilla eri kielillä, avaimena kieli ja arvona muotoilu
     * merkkijonona.
     */
    private HashMap<Kieli, String> kysymysKaikillaKielilla;
    /**
     * Kysymyksen esimerkkivastaus (tällä hetkellä riippumaton kielistä; vain
     * yksi per kysymys).
     */
    private String esimerkkivastaus;
    /**
     * Kysymykseen liitetty vastaus merkkijonona
     */
    private String vastaus;

    /**
     * Metodi luo uuden kysymyksen parametrina saaduilla muotoiluilla suomeksi
     * ja englanniksi. Esimerkkivastaus voidaan myös ottaa jo konstruktorissa
     * tai sitten vasta erikseen myöhemmin.
     *
     * @param kysymysSuomeksi Kysymyksen muotoilu suomeksi
     * @param kysymysEnglanniksi Kysymyksen muotoilu englanniksi
     * @param esimerkkiVastaus Kysymykseen liitettävä esimerkkivastaus
     */
    public Kysymys(String kysymysSuomeksi, String kysymysEnglanniksi, String esimerkkivastaus) {
        this.kysymysKaikillaKielilla = new HashMap();
        this.kysymysKaikillaKielilla.put(Kieli.SUOMI, kysymysSuomeksi);
        this.kysymysKaikillaKielilla.put(Kieli.ENGLANTI, kysymysEnglanniksi);
        this.esimerkkivastaus = esimerkkivastaus;
    }

    public String getEsimerkkiVastaus() {
        return this.esimerkkivastaus;
    }

    /**
     * Metodi lisää parametrina saadun merkkijonon vastaukseksi kysymykseen.
     *
     * @param vastaus Lisättävä vastaus
     */
    public void setVastaus(String vastaus) {
        this.vastaus = vastaus;
    }

    /**
     * @return Kysymykseen liitetty vastaus
     */
    public String getVastaus() {
        return this.vastaus;
    }

    /**
     * Metodi kysyy Asetukset-luokalta nykyisen kielen ja palauttaa sitten
     * kysymyksen muistamasta taulukosta kysymysKaikillaKielillä sen muotoilun,
     * joka vastaa nykyistä kieltä.
     *
     * @return kysymyksen String-muotoilu nykyisellä oletuskielellä
     */
    public String getKysymysKielella(Kieli kieli) {
        String kysymysNykyisellaKielella = this.kysymysKaikillaKielilla.get(kieli);
        return kysymysNykyisellaKielella;
    }
}