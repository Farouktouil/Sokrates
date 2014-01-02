package sokrates.sovelluslogiikka;

/**
 * Muistaa nykyisen kielen.
 * Jos keksii lisää yhtä globaaleja asetuksia, voi niitä tallentaa tänne.
 * (Esim. tekstitiedostojen kohdekansio(t)? Vaiko tietojenkasittely-pakkaukseen?)
 *
 * @author Teo
 */
public class Asetukset {

    public static Kieli kieli = Kieli.SUOMI;

    public static Kieli getKieli() {
        return kieli;
    }
}