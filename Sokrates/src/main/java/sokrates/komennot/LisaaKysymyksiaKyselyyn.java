package sokrates.komennot;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sokrates.kayttoliittyma.Tulostamo;
import sokrates.sovelluslogiikka.Kysely;
import sokrates.sovelluslogiikka.KyselyHallinta;
import sokrates.sovelluslogiikka.Kysymys;
import sokrates.tiedostonkasittely.TiedostonKirjoittaja;
import sokrates.tiedostonkasittely.TiedostonLukija;
import sokrates.util.Lukija;

/**
 * Komento LisaaKysymyksiaKyselyyn antaa käyttäjän lisätä valitsemaansa kyselyyn
 * niin monta kysymystä kuin käyttäjä haluaa peräkkäin. Kun haluaa voi peruuttaa
 * takaisin.
 *
 * @author Teo
 */
public class LisaaKysymyksiaKyselyyn extends Komento {

    private TiedostonKirjoittaja tk;
    private TiedostonLukija tl;

    public LisaaKysymyksiaKyselyyn(Lukija lukija, KyselyHallinta hallinta, TiedostonKirjoittaja tk, TiedostonLukija tl,
            String nimi, String seliteSuomeksi, String seliteEnglanniksi) {
        super(lukija, hallinta, nimi, seliteSuomeksi, seliteEnglanniksi);
        this.tk = tk;
        this.tl = tl;
    }

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
     * esimerkkivastauksia kysymyksiin. Välissä kysytään haluaako palata
     * takaisin vai lisätä vielä uuden kysymyksen.
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
                tk.kirjoitaTiedostoonRivit(tl.getNimeaVastaavaKyselyTiedosto(kohdeKyselynNimi), kysymysSuomeksi, kysymysEnglanniksi, esimerkkiVastaus);
            } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                Logger.getLogger(LisaaKysymyksiaKyselyyn.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(Tulostamo.lisattyKysymys(kohdeKysely.getNimi()));
        }
    }
}