package sokrates.komennot;

import sokrates.kayttoliittyma.Tulostamo;
import sokrates.sovelluslogiikka.Kysely;
import sokrates.sovelluslogiikka.KyselyHallinta;
import sokrates.sovelluslogiikka.Kysymys;
import sokrates.util.Lukija;

/**
 * Antaa käyttäjän poistaa kysymyksiä valitsemastaan kyselystä.
 *
 * @author Teo
 */
public class PoistaKysymyksiaKyselysta extends Komento {

    public PoistaKysymyksiaKyselysta(Lukija lukija, KyselyHallinta hallinta, String nimi, String seliteSuomeksi, String seliteEnglanniksi) {
        super(lukija, hallinta, nimi, seliteSuomeksi, seliteEnglanniksi);
    }

    @Override
    public boolean suorita() {
        System.out.println(Tulostamo.valitseKohdeKysely());
        Kysely kohdeKysely = super.kayttajanOsoittamaKysely();

        if (kohdeKysely == null) {
            System.out.println(Tulostamo.kysymyksiaEiOle()); // tämä tulostuu vielä epätoivotusti vaikka painettiin "x"
        } else {
            poistaKysymyksia(kohdeKysely);
        }

        return true;
    }

    /**
     * Looppaa käyttäjältä valintoja poistettavista kysymyksistä. Poistaa.
     *
     * @param kohdeKysely
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
            System.out.println("  " + i + " = " + kohdeKysely.getKysymykset().get(i).getKysymysNykyisellaKielella());
        }
        System.out.println("(x = " + Tulostamo.peruuta() + ")\n");
    }
}