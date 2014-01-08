package sokrates.komennot;

import sokrates.kayttoliittyma.Tulostamo;
import sokrates.sovelluslogiikka.KyselyHallinta;
import sokrates.util.Lukija;

/**
 * Komennon LisaaKysely avulla käyttäjä voi luoda ohjelmaan uuden kyselyn.
 *
 * @author Teo
 */
public class LisaaKysely extends Komento {

    public LisaaKysely(Lukija lukija, KyselyHallinta hallinta, String nimi, String seliteSuomeksi, String seliteEnglanniksi) {
        super(lukija, hallinta, nimi, seliteSuomeksi, seliteEnglanniksi);
    }

    /**
     * Komento suoritettaessa käyttäjä laitetaan valitsemaan lisättävälle
     * kyselylle epätyhjä nimi tai tivataan uudestaan.
     *
     * Epätyhjää nimeä (vihdoin jos onnekkaasti on saatu niin) käytetään nimenä
     * KyselyHallintaan lisättävälle kyselylle, ja ilmoitetaan käyttäjälle että
     * hänen antamansa niminen kysely on lisätty.
     *
     * @return true jotta Sovelluksen komentolooppi voi jatkua.
     */
    @Override
    public boolean suorita() {
        while (true) {
            String lisattavanKyselynNimi = lukija.lueMerkkijono(Tulostamo.annaKyselylleNimi());

            if (!(this.hallinta.haeKyselyNimenPerusteella(lisattavanKyselynNimi) == null)) {
                System.out.println(Tulostamo.tamanNiminenKyselyOnJoOlemassa(lisattavanKyselynNimi));
                return true;
            }
            if (!lisattavanKyselynNimi.isEmpty()) {
                this.hallinta.lisaaKysely(lisattavanKyselynNimi);
                System.out.println(Tulostamo.lisattyKyselyNimelta(lisattavanKyselynNimi));
                break;
            }
        }
        return true;
    }
}