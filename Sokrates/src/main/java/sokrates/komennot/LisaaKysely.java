package sokrates.komennot;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sokrates.kayttoliittyma.Tulostamo;
import sokrates.sovelluslogiikka.KyselyHallinta;
import sokrates.tiedostonkasittely.TiedostonKirjoittaja;
import sokrates.util.Lukija;

/**
 * Komennon LisaaKysely avulla käyttäjä voi luoda ohjelmaan uuden kyselyn.
 *
 * @author Teo
 */
public class LisaaKysely extends Komento {

    /**
     * Kapseloitu TiedostonKirjoittaja
     */
    private TiedostonKirjoittaja tk;
    
    public LisaaKysely(Lukija lukija, KyselyHallinta hallinta, TiedostonKirjoittaja tk,
            String nimi, String seliteSuomeksi, String seliteEnglanniksi) {
        super(lukija, hallinta, nimi, seliteSuomeksi, seliteEnglanniksi);
        this.tk = tk;
    }

    /**
     * Komento suoritettaessa käyttäjä laitetaan valitsemaan lisättävälle
     * kyselylle epätyhjä nimi tai tivataan uudestaan.
     *
     * Epätyhjää nimeä (vihdoin jos onnekkaasti on saatu niin) käytetään nimenä
     * KyselyHallintaan lisättävälle kyselylle, ja ilmoitetaan käyttäjälle että
     * hänen antamansa niminen kysely on lisätty. TiedostonKirjoittajaa
     * käsketään luomaan kyselytiedosto valitulla nimellä.
     *
     * @return true jotta Sovelluksen komentolooppi voi jatkua.
     */
    @Override
    public boolean suorita() {
        while (true) {
            String lisattavanKyselynNimi = lukija.lueMerkkijono(Tulostamo.annaKyselylleNimi());
            
            if (this.hallinta.getKyselyNimenPerusteella(lisattavanKyselynNimi) != null) {
                System.out.println(Tulostamo.tamanNiminenKyselyOnJoOlemassa(lisattavanKyselynNimi));
                return true;
            }
            
            if (!lisattavanKyselynNimi.isEmpty() && !lisattavanKyselynNimi.contains("?")) {
                this.hallinta.lisaaKysely(lisattavanKyselynNimi);
                try {
                    tk.luoKyselyTiedostoNimelta(lisattavanKyselynNimi);
                } catch (IOException ex) {
                    Logger.getLogger(LisaaKysely.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(Tulostamo.lisattyKyselyNimelta(lisattavanKyselynNimi));
                break;
            }
        }
        
        return true;
    }
}