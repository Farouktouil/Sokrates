package sokrates.komennot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sokrates.kayttoliittyma.Tulostamo;
import sokrates.sovelluslogiikka.Kysely;
import sokrates.sovelluslogiikka.KyselyHallinta;
import sokrates.sovelluslogiikka.Kysymys;
import sokrates.tiedostonkasittely.*;
import sokrates.util.Lukija;

/**
 * Komento LisaaKysymyksiaKyselyyn antaa käyttäjän lisätä valitsemaansa kyselyyn
 * niin monta kysymystä kuin käyttäjä haluaa peräkkäin. Kun haluaa voi peruuttaa
 * takaisin.
 *
 * @author Teo
 */
public class LisaaKysymyksiaKyselyyn extends Komento {

    /**
     * Kapseloitu TiedostonKirjoittaja
     */
    private TiedostonKirjoittaja tk;
    /**
     * Kapseloitu TiedostonLukija
     */
    private TiedostonLukija tl;

    public LisaaKysymyksiaKyselyyn(Lukija lukija, KyselyHallinta hallinta, TiedostonKirjoittaja tk, TiedostonLukija tl,
            String nimi, String seliteSuomeksi, String seliteEnglanniksi) {
        super(lukija, hallinta, nimi, seliteSuomeksi, seliteEnglanniksi);
        this.tk = tk;
        this.tl = tl;
    }

    /**
     * Suoritettaessa kehotetaan käyttäjää valitsemaan kysely johon kysymyksiä
     * lisätään. Epätyhjällä valinnalla kutsutaan alempaa metodia
     * lisaaKysymyksia.
     *
     * @return true jotta Sovelluksen looppi saa jatkua.
     */
    @Override
    public boolean suorita() {
        System.out.println(Tulostamo.valitseKohdeKysely());
        Kysely kohdeKysely = super.kayttajanOsoittamaKysely();

        if (kohdeKysely != null) {
            lisaaKysymyksia(kohdeKysely);
        }

        return true;
    }

    /**
     * Looppaa käyttäjältä kysymyksen muotoiluja suomeksi ja englanniksi sekä
     * esimerkkivastauksia kysymyksiin. Välissä kysytään aina haluaako palata
     * takaisin vai lisätä vielä uuden kysymyksen.
     *
     * TiedostonLukijalta selvitetään kohdekyselyn nimeä vastaava
     * kyselytiedosto, johon TiedostonKirjoittajaa käsketään lisäämään
     * kysymyksen tiedot.
     *
     * @param kohdeKysely Kysely johon kysymyksiä lisätään
     */
    public void lisaaKysymyksia(Kysely kohdeKysely) {
        while (true) {
            String syote = lukija.lueMerkkijono(Tulostamo.vaihtoehdotKysymyksiaLisatessa(kohdeKysely.getNimi()));

            if (syote.equals("x")) {
                System.out.println();
                break;
            }

            String kysymysSuomeksi = lukija.lueMerkkijono(Tulostamo.muotoileKysymysSuomeksi());
            String kysymysEnglanniksi = lukija.lueMerkkijono(Tulostamo.muotoileKysymysEnglanniksi());
            String esimerkkiVastaus = lukija.lueMerkkijono(Tulostamo.muotoileEsimerkkiVastaus());
            kohdeKysely.lisaaKysymys(new Kysymys(kysymysSuomeksi, kysymysEnglanniksi, esimerkkiVastaus));

            String kohdeKyselynNimi = kohdeKysely.getNimi();
            try {
                File kohdeTiedosto = tl.getNimeaVastaavaKyselyTiedosto(kohdeKyselynNimi);
                tk.kirjoitaTiedostoonRivit(kohdeTiedosto, kysymysSuomeksi, kysymysEnglanniksi, esimerkkiVastaus);
            } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                Logger.getLogger(LisaaKysymyksiaKyselyyn.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println(Tulostamo.lisattyKysymys(kohdeKysely.getNimi()));
        }
    }
}