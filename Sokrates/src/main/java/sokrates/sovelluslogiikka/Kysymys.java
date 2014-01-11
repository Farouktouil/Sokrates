package sokrates.sovelluslogiikka;

import java.util.HashMap;

/**
 * Luokka Kysymys edustaa kysymyksiä. Kysymys on olemassa aina vähintään yhdellä
 * kielellä. Kysymys muistaa listan kysymyksestä kaikilla eri kielillä, ja
 * listaan voidaan lisätä kysymyksen erikielisiä muotoiluja. Kysymys muistaa
 * esimerkkivastauksensa (ainakin toistaiseksi vain yhdellä kielellä)
 * kysymykseen liitetyn vastauksen (String).
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
    public String getKysymysNykyisellaKielella() {
        Kieli nykyinenKieli = Asetukset.getKieli();
        String kysymysNykyisellaKielella = this.kysymysKaikillaKielilla.get(nykyinenKieli);
        return kysymysNykyisellaKielella;
    }
}