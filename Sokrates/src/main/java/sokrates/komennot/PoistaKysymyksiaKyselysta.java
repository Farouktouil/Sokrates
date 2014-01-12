package sokrates.komennot;

import java.io.File;
import java.util.ArrayList;
import sokrates.kayttoliittyma.Tulostamo;
import sokrates.sovelluslogiikka.Asetukset;
import sokrates.sovelluslogiikka.Kysely;
import sokrates.sovelluslogiikka.KyselyHallinta;
import sokrates.sovelluslogiikka.Kysymys;
import sokrates.tiedostonkasittely.TiedostonKirjoittaja;
import sokrates.tiedostonkasittely.TiedostonLukija;
import sokrates.util.Lukija;

/**
 * Antaa käyttäjän poistaa kysymyksiä valitsemastaan kyselystä.
 *
 * @author Teo
 */
public class PoistaKysymyksiaKyselysta extends Komento {

    /**
     * Kapseloitu TiedostonKirjoittaja
     */
    private TiedostonKirjoittaja tk;
    private TiedostonLukija tl;

    public PoistaKysymyksiaKyselysta(Lukija lukija, KyselyHallinta hallinta, TiedostonKirjoittaja tk, TiedostonLukija tl,
            String nimi, String seliteSuomeksi, String seliteEnglanniksi) {
        super(lukija, hallinta, nimi, seliteSuomeksi, seliteEnglanniksi);
        this.tk = tk;
        this.tl = tl;
    }

    /**
     * Suoritettaessa laitetaan käyttäjä valitsemaan yläluokan metodilla kysely,
     * josta kysymyksiä halutaan poistaa. Jos kyselyssä on kysymyksiä,
     * poistetaan niitä kutsumalla alempaa metodia.
     *
     * @return true jotta Sovelluksen komentolooppi voi jatkua.
     */
    @Override
    public boolean suorita() {
        System.out.println(Tulostamo.valitseKohdeKysely());
        Kysely kohdeKysely = super.kayttajanOsoittamaKysely();

        if (kohdeKysely == null) {
        } else if (kohdeKysely.getKysymykset().isEmpty()) {
            System.out.println(Tulostamo.kysymyksiaEiOle());
        } else {
            poistaKysymyksia(kohdeKysely);
        }

        return true;
    }

    /**
     * Looppaa käyttäjältä valintoja poistettavista kysymyksistä. Poistaa.
     *
     * Tarkistaa että käyttäjän syöttämä kysymyksen indeksi todella löytyy
     * kohdekyselyn kysymysten indeksilistasta, jotta ArrayList ei kaada
     * ohjelmaa.
     *
     * @param kohdeKysely Kysely josta kysymyksiä poistetaan
     */
    private void poistaKysymyksia(Kysely kohdeKysely) {
        while (true) {
            System.out.println(Tulostamo.valitsePoistettavaKysymys());
            tulostaKysymysVaihtoehdot(kohdeKysely);
            String syoteTeksti = lukija.lueMerkkijono(Tulostamo.kysymys());

            if (syoteTeksti.equals("x")) {
                System.out.println();
                break;
            }

            int syoteLuku = lukija.tulkitseKokonaisluvuksi(syoteTeksti);

            if (kohdeKysely.getKysymystenIndeksiLista().contains(syoteLuku)) {
                Kysymys poistettavaKysymys = kohdeKysely.getKysymykset().get(syoteLuku);
                String kohdeKyselynNimi = kohdeKysely.getNimi();

                kohdeKysely.poistaKysymys(poistettavaKysymys);
                File kohdeKyselyTiedosto = tl.getNimeaVastaavaKyselyTiedosto(kohdeKysely.getNimi());
                ArrayList<String> riviLista = tl.lueKyselyTiedostoRiviListaksi(kohdeKyselyTiedosto);
                tk.poistaKyselystaKysymys(kohdeKyselyTiedosto, riviLista, poistettavaKysymys);
                System.out.println(Tulostamo.poistettuKysymys(kohdeKyselynNimi));
            }

            System.out.println();
        }
    }

    /**
     * Tulostaa käyttäjälle kohdekyselyn sisältämät kysymykset sekä
     * peruutusvaihtoehdon joista tulee valita yksi.
     *
     * @param kohdeKysely Kysely josta poistetaan kysymyksiä.
     */
    private void tulostaKysymysVaihtoehdot(Kysely kohdeKysely) {
        for (int i = 0; i < kohdeKysely.getKysymykset().size(); i++) {
            System.out.println("  " + i + " = " + kohdeKysely.getKysymykset().get(i).getKysymysKielella(Asetukset.getKieli()));
        }
        System.out.println("(x = " + Tulostamo.peruuta() + ")\n");
    }
}