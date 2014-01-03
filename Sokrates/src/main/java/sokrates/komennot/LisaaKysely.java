package sokrates.komennot;

import sokrates.sovelluslogiikka.KyselyHallinta;
import sokrates.util.Lukija;

/**
 * Komennon LisaaKysely avulla käyttäjä voi luoda ohjelmaan uuden kyselyn.
 *
 * @author Teo
 */
public class LisaaKysely extends Komento {

    public LisaaKysely(Lukija lukija, KyselyHallinta hallinta, String nimi, String selite) {
        super(lukija, hallinta, nimi, selite);
    }

    /**
     * Komento suoritettaessa käyttäjä laitetaan valitsemaan lisättävälle
     * kyselylle epätyhjä nimi tai tivataan uudestaan.
     *
     * Epätyhjä nimi (vihdoin jos onnekkaasti on saatu niin) käytetään nimenä
     * KyselyHallintaan lisättävälle kyselylle, ja ilmoitetaan käyttäjälle että
     * hänen antamansa niminen kysely on lisätty.
     *
     * @return true jotta Sovelluksen komentolooppi voi jatkua.
     */
    @Override
    public boolean suorita() {
        while (true) {
            String lisattavanKyselynNimi = lukija.lueMerkkijono("Anna kyselylle nimi: ");

            if (!lisattavanKyselynNimi.isEmpty()) {
                this.hallinta.lisaaKysely(lisattavanKyselynNimi);
                System.out.println("\n    Lisätty kysely nimeltä " + lisattavanKyselynNimi + ".\n");
                break;
            }
        }

        return true;
    }
}