package sokrates.komennot;

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
 * kayttajanValitsemaKysely(), joka on sekin täällä copypasten välttämiseksi,
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

    protected Lukija lukija;
    private String nimi;
    private String selite;
    protected KyselyHallinta hallinta;

    /**
     * Selkeyden vuoksi _jokainen_ komento muistaa Lukijan ja KyselyHallinnan,
     * vaikka joka ikinen komento ei niitä tarvitsisikaan.
     *
     * Jokaisella komennolla on nimi ja selite. Sovelluksen puolelta nähdään,
     * että nimi on oikeastaan numero, mutta koodin puolesta myös esimerkiksi
     * kirjaimella käynnistettäviä komentoja voisi helposti lisätä.
     *
     * @param lukija Lukija
     * @param hallinta KyselyHallinta
     * @param nimi Komennolle luotaessa annettu nimi (käytännössä yksi numero)
     * @param selite Käyttäjälle tulostettava kuvaus siitä, mitä komento tekee
     */
    public Komento(Lukija lukija, KyselyHallinta hallinta, String nimi, String selite) {
        this.lukija = lukija;
        this.nimi = nimi;
        this.selite = selite;
        this.hallinta = hallinta;
    }

    public String getNimi() {
        return this.nimi;
    }

    public String getSelite() {
        return this.selite;
    }

    /**
     * Metodia hyödyntää useampikin Komennon aliluokka eli käyttäjän kutsuma
     * erityiskomento. Tässä pääpiirteittäin tulostetaan KyselyHallinnan
     * nimiTaulukosta saadut KyselyHallinnan muistamien kyselyjen nimet, joista
     * käyttäjä laitetaan syötteenlukuloopilla valitsemaan yksi.
     *
     * Syötteenlukulooppi muistuttaa Sovelluksen käyttämää looppia, mutta
     * käyttää Lukijan metodia lueKokonaisLuku(String kysymys). Looppi katkeaa
     * jos ja vain jos käyttäjän syöte on myös avaimena KyselyHallinnan
     * nimiTaulukossa (jolloin syötettä vastaa jokin kysely).
     *
     * Syötettä vastaavalla avaimella saadaan nimiTaulukosta kyselyn nimi, jolla
     * KyselyHallinnasta haetaan itse palautettava kysely.
     *
     * Näin siis käyttäjältä kokonaislukua tivaamalla saadaan lopulta käyttäjä
     * valitsemaan jokin kysely.
     *
     * @return Valittu kysely
     */
    public Kysely kayttajanOsoittamaKysely() {
        Kysely kysely = null;

        if (this.hallinta.getKyselyt().isEmpty()) {
            System.out.println("    Kyselyitä ei ole. Voit lisätä uuden kyselyn komennolla 3.\n");
            return kysely;
        }

        tulostaKyselyVaihtoehdot();
        System.out.println();

        while (true) {
            int syote = lukija.lueKokonaisluku("kysely: ");

            if (!(this.hallinta.getKyselyt().get(syote) == null)) {
                String valitunKyselynNimi = this.hallinta.getKyselyt().get(syote).getNimi();
                kysely = this.hallinta.haeKyselyNimenPerusteella(valitunKyselynNimi);
                break;
            }
        }

        return kysely;
    }

    private void tulostaKyselyVaihtoehdot() {
        for (int i = 0; i < this.hallinta.getKyselyt().size(); i++) {
            System.out.println("  " + i + " "
                    + this.hallinta.getKyselyt().get(i).getNimi());
        }
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