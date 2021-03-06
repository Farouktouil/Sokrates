package sokrates.sovelluslogiikka;

/**
 * Muistaa nykyisen kielen.
 *
 * @author Teo
 */
public class Asetukset {

    /**
     * Ohjelman tämänhetkinen kieli, joka vaikuttaa siihen missä muodossa
     * esitetään kysymys, joka on olemassa esim. sekä suomeksi että englanniksi,
     * ja missä muodossa esitetään tekstikäyttöliittymän tulosteet.
     */
    public static Kieli kieli = Kieli.SUOMI;

    public static Kieli getKieli() {
        return kieli;
    }

    public static void setKieli(Kieli k) {
        kieli = k;
    }
}