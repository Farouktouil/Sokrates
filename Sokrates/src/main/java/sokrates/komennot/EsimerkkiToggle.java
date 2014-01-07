package sokrates.komennot;

import sokrates.kayttoliittyma.Tulostamo;
import sokrates.sovelluslogiikka.KyselyHallinta;
import sokrates.util.Lukija;

/**
 * Komento EsimerkkiToggle asettaa joko pois päältä tai takaisin päälle
 * KyselyHallinnan muistaman asetuksen koskien sitä, näytetäänkö käyttäjälle
 * kysymyksen kysymisen yhteydessä kysymykseen liittyvä esimerkkivastaus vai ei.
 *
 * @author Teo
 */
public class EsimerkkiToggle extends Komento {

    public EsimerkkiToggle(Lukija lukija, KyselyHallinta hallinta, String nimi, String seliteSuomeksi, String seliteEnglanniksi) {
        super(lukija, hallinta, nimi, seliteSuomeksi, seliteEnglanniksi);
    }

    /**
     * Komennon suorittaminen muuttaa KyselyHallinnan examples-booleanin
     * falseksi jos se oli true ja vastaavasti trueksi jos se oli false.
     * Asetuksen uusi tila tulostetaan käyttäjälle mieltärauhoittavaksi
     * tiedoksi.
     *
     * @return true jotta Sovelluksen looppi saa jatkua.
     */
    @Override
    public boolean suorita() {
        if (this.hallinta.getExamples() == true) {
            this.hallinta.setExamples(false);
            System.out.println(Tulostamo.examplesOff());
        } else if (this.hallinta.getExamples() == false) {
            this.hallinta.setExamples(true);
            System.out.println(Tulostamo.examplesOn());
        }
        return true;
    }
}