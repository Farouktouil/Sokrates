package sokrates.komennot;

import java.util.HashMap;
import sokrates.kayttoliittyma.Tulostamo;
import sokrates.sovelluslogiikka.Asetukset;
import sokrates.sovelluslogiikka.Kieli;
import sokrates.sovelluslogiikka.Kysely;
import sokrates.sovelluslogiikka.KyselyHallinta;
import sokrates.util.Lukija;

/**
 * Copypasten välttämiseksi jokaisella komennolla on yläluokka Komento, minkä
 * ansiosta jokaisella Komennon perivällä erikoiskomennolla on vasta täällä
 * eksplikoitu konstruktori, oliomuuttujat sekä getteri komennon nimelle ja
 * selitteelle.
 *
 * Lisäksi jotkin Komennon aliluokat kutsuvat Komennon metodia
 * kayttajanOsoittamaKysely(), joka on sekin täällä copypasten välttämiseksi,
 * sillä moni komento vaatii käyttäjää valitsemaan yhden KyselyHallinnan
 * muistamista kyselyistä.
 *
 * Jokaisen Komennon aliluokan tulee toteuttaa metodi suorita(), joka kullakin
 * komennolla pitää sisällään sen toiminnallisuuden minkä ajamista varten
 * komento on olemassa.
 *
 * @author Teo
 */
public abstract class Komento {

    /**
     * Jokainen komento muistaa lukijan, vaikka ihan jokainen ei sitä
     * tarvitsisikaan.
     */
    protected Lukija lukija;
    /**
     * Jokaisella komennolla on nimi, jonka hyödyntämistapa näkyy Sovelluksen
     * puolella.
     */
    private String nimi;
    /**
     * Jokainen komento muistaa selitteensä kaikilla kielillä.
     */
    private HashMap<Kieli, String> seliteKaikillaKielilla;
    /**
     * Jokainen komento muistaa KyselyHallinnan, vaikka ihan jokainen ei sitä
     * tarvitsisikaan.
     */
    protected KyselyHallinta hallinta;

    /**
     * Selkeyden vuoksi _jokainen_ komento muistaa Lukijan ja KyselyHallinnan,
     * vaikka joka ikinen komento ei niitä tarvitsisikaan.
     *
     * Jokaisella komennolla on nimi ja selite.
     *
     * @param lukija Lukija
     * @param hallinta KyselyHallinta
     * @param nimi Komennolle luotaessa annettu nimi (käytännössä yksi numero
     * tai kirjain)
     * @param seliteSuomeksi Käyttäjälle tulostettava kuvaus siitä, mitä komento
     * tekee, suomeksi
     * @param seliteEnglanniksi Käyttäjälle tulostettava kuvaus siitä, mitä
     * komento tekee, englanniksi
     */
    public Komento(Lukija lukija, KyselyHallinta hallinta, String nimi, String seliteSuomeksi, String seliteEnglanniksi) {
        this.lukija = lukija;
        this.hallinta = hallinta;
        this.nimi = nimi;
        this.seliteKaikillaKielilla = new HashMap<>();
        this.seliteKaikillaKielilla.put(Kieli.SUOMI, seliteSuomeksi);
        this.seliteKaikillaKielilla.put(Kieli.ENGLANTI, seliteEnglanniksi);
    }

    public String getNimi() {
        return this.nimi;
    }

    /**
     * @return Komennon selite nykyisellä kielellä, joka haetaan Asetuksista
     * kutsumalla metodia getKieli()
     */
    public String getSelite() {
        return this.seliteKaikillaKielilla.get(Asetukset.getKieli());
    }

    /**
     * Metodia hyödyntää useampikin Komennon aliluokka eli käyttäjän kutsuma
     * erityiskomento.
     *
     * Jos hallinnan mukaan kyselyjä on 0, tulostetaan valittelu ja palautetaan
     * null. Muuten tulostutetaan toisella metodilla kyselyvaihtoehdot.
     *
     * Syötteenlukuloopilla annetaan käyttäjälle mahdollisuus peruuttaa
     * valinnasta tai valita jokin listatuista kyselyistä. Jos käyttäjän syöte
     * (kokonaislukuna) vastaa hallinnan kyselyissä jotain sellaista indeksiä,
     * jota vastaa epätyhjä arvo (kysely), niin getataan tuo kysely tämän
     * metodin palauttamaksi kyselyksi, eli käyttäjän valitsemaksi kyselyksi.
     *
     * @return Valittu kysely (tai null)
     */
    public Kysely kayttajanOsoittamaKysely() {
        Kysely kysely = null;

        if (this.hallinta.getKyselyt().isEmpty()) {
            System.out.println(Tulostamo.kyselyitaEiOle());
            return kysely;
        }

        tulostaKyselyVaihtoehdot();

        while (true) {
            String syoteTeksti = lukija.lueMerkkijono(Tulostamo.kysely());
            System.out.println();

            if (syoteTeksti.equals("x")) {
                break;
            }

            int syoteLuku = lukija.tulkitseKokonaisluvuksi(syoteTeksti);

            if (!(this.hallinta.getKyselyt().get(syoteLuku) == null)) {
                kysely = this.hallinta.getKyselyt().get(syoteLuku);
                break;
            }
        }

        return kysely;
    }

    /**
     * Metodi tulostaa jokaisen hallinnan muistaman kyselyn indekseineen sekä
     * peruutusvaihtoehdon.
     */
    private void tulostaKyselyVaihtoehdot() {
        for (int i = 0; i < this.hallinta.getKyselyt().size(); i++) {
            System.out.println("  " + i + " " + this.hallinta.getKyselyt().get(i).getNimi());
        }
        System.out.println("  x " + Tulostamo.peruuta() + "\n");
    }

    /**
     * Jokaisen komennon on toteutettava metodi suorita(), joka sisältää ja/tai
     * käynnistää toiminnallisuuden jota varten komento on omana
     * kokonaisuutenaan olemassa.
     *
     * @return true tai false (riippuen siitä, halutaanko ohjelman enää jatkuvan
     * kyseisen komennon suorittamisen jälkeen -- useimmiten halutaan).
     */
    public abstract boolean suorita();
}