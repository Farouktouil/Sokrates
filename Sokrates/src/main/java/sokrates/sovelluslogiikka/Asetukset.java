package sokrates.sovelluslogiikka;

/**
 * Muistaa nykyisen kielen. Jos keksii lisää yhtä globaaleja asetuksia, voi
 * niitä tallentaa tänne. (Esim. tekstitiedostojen kohdekansio(t)? Vaiko
 * tietojenkasittely-pakkaukseen?)
 *
 * @author Teo
 */
public class Asetukset {

    /**
     * Ohjelman tämänhetkinen kieli, joka vaikuttaa siihen missä muodossa
     * esitetään kysymys, joka on olemassa esim. sekä suomeksi että englanniksi.
     */
    public static Kieli kieli = Kieli.SUOMI;

    public static Kieli getKieli() {
        return kieli;
    }
<<<<<<< HEAD
    
=======

>>>>>>> kaksikielistettiin tekstikäyttiskin
    public static void setKieli(Kieli k) {
        kieli = k;
    }
}