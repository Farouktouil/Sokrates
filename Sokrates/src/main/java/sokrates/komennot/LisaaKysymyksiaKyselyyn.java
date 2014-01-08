package sokrates.komennot;

import sokrates.kayttoliittyma.Tulostamo;
import sokrates.sovelluslogiikka.Kysely;
import sokrates.sovelluslogiikka.KyselyHallinta;
import sokrates.sovelluslogiikka.Kysymys;
import sokrates.util.Lukija;

/**
 *
 * @author Teo
 */
public class LisaaKysymyksiaKyselyyn extends Komento {

    public LisaaKysymyksiaKyselyyn(Lukija lukija, KyselyHallinta hallinta, String nimi, String seliteSuomeksi, String seliteEnglanniksi) {
        super(lukija, hallinta, nimi, seliteSuomeksi, seliteEnglanniksi);
    }

    @Override
    public boolean suorita() {
        System.out.println(Tulostamo.valitseKohdeKysely());
        Kysely kohdeKysely = super.kayttajanOsoittamaKysely();
        lisaaKysymyksia(kohdeKysely);
        return true;
    }

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