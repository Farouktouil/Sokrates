package sokrates.komennot;

import sokrates.sovelluslogiikka.Asetukset;
import sokrates.sovelluslogiikka.Kieli;
import sokrates.sovelluslogiikka.KyselyHallinta;
import sokrates.util.Lukija;

/**
 * Komento VaihdaKieli vastaa Asetukset-luokan muistaman ohjelman kieliasetuksen
 * muuttamisesta.
 *
 * @author Teo
 */
public class VaihdaKieli extends Komento {

    public VaihdaKieli(Lukija lukija, KyselyHallinta hallinta, String nimi, String selite) {
        super(lukija, hallinta, nimi, selite);
    }

    /**
     * Komennon suorittaminen asettaa kieleksi ENGLANTI jos se oli SUOMI ja
     * vastaavasti SUOMI jos se oli ENGLANTI. Uusi voimaan tullut asetus
     * tulostetaan käyttäjälle tiedoksi.
     *
     * @return true jotta Sovelluksen kyselylooppi voi jatkua.
     */
    @Override
    public boolean suorita() {

        if (Asetukset.getKieli() == Kieli.SUOMI) {
            Asetukset.kieli = Kieli.ENGLANTI;
            System.out.println("    Kieli on nyt englanti.\n");
        } else if (Asetukset.getKieli() == Kieli.ENGLANTI) {
            Asetukset.kieli = Kieli.SUOMI;
            System.out.println("    Kieli on nyt suomi.\n");
        }

        return true;
    }
}