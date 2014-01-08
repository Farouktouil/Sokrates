package sokrates.komennot;

import sokrates.kayttoliittyma.Tulostamo;
import sokrates.sovelluslogiikka.Kysely;
import sokrates.sovelluslogiikka.KyselyHallinta;
import sokrates.sovelluslogiikka.Kysymys;
import sokrates.util.Lukija;

/**
 * Komento LisaaKysymyksiaKyselyyn antaa käyttäjän lisätä valitsemaansa kyselyyn
 * niin monta kysymystä kuin käyttäjä haluaa peräkkäin. Kun haluaa voi peruuttaa
 * takaisin.
 *
 * @author Teo
 */
public class LisaaKysymyksiaKyselyyn extends Komento {
    
        private Komento ohje;

    public LisaaKysymyksiaKyselyyn(Lukija lukija, KyselyHallinta hallinta, Komento ohje, String nimi, String seliteSuomeksi, String seliteEnglanniksi) {
        super(lukija, hallinta, nimi, seliteSuomeksi, seliteEnglanniksi);
        this.ohje = ohje;
    }

    @Override
    public boolean suorita() {
        System.out.println(Tulostamo.valitseKohdeKysely());
        Kysely kohdeKysely = super.kayttajanOsoittamaKysely();
        lisaaKysymyksia(kohdeKysely);
        return ohje.suorita();
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
            if (syote.equals(null)) {
                continue;
            }
            if (syote.equals("x")) {
                System.out.println();
                break;
            }
            String kysymysSuomeksi = lukija.lueMerkkijono(Tulostamo.muotoileKysymysSuomeksi());
            String kysymysEnglanniksi = lukija.lueMerkkijono(Tulostamo.muotoileKysymysEnglanniksi());
            String esimerkkiVastaus = lukija.lueMerkkijono(Tulostamo.muotoileEsimerkkiVastaus());
            kohdeKysely.lisaaKysymys(new Kysymys(kysymysSuomeksi, kysymysEnglanniksi, esimerkkiVastaus));
            System.out.println(Tulostamo.lisattyKysymys(kohdeKysely.getNimi()));
        }
    }
}