package sokrates.kayttoliittyma;

import sokrates.sovelluslogiikka.Asetukset;
import sokrates.sovelluslogiikka.Kieli;

/**
 *
 * @author Teo
 */
public class Tulostamo {

    public static String examplesOn() {
        if (Asetukset.getKieli() == Kieli.SUOMI) {
            return "    Esimerkkivastaukset ovat nyt päällä.\n";
        }
        return "    Example answers are now on.\n";
    }

    public static String examplesOff() {
        if (Asetukset.getKieli() == Kieli.SUOMI) {
            return "    Esimerkkivastaukset ovat nyt pois päältä.\n";
        }
        return "    Example answers are now off.\n";
    }

    public static String komento() {
        if (Asetukset.getKieli() == Kieli.SUOMI) {
            return "komento: ";
        }
        return "command: ";
    }

    public static String kysely() {
        if (Asetukset.getKieli() == Kieli.SUOMI) {
            return "kysely: ";
        }
        return "inquiry: ";
    }

    public static String annaKyselylleNimi() {
        if (Asetukset.getKieli() == Kieli.SUOMI) {
            return "Anna kyselylle nimi: ";
        }
        return "Give the inquiry a name: ";
    }

    public static String valitsePoistettavaKysely() {
        if (Asetukset.getKieli() == Kieli.SUOMI) {
            return "Valitse poistettava kysely:";
        }
        return "Pick inquiry to be removed:";
    }

    public static String valitseKyseltavaKysely() {
        if (Asetukset.getKieli() == Kieli.SUOMI) {
            return "Valitse kyseltävä kysely:";
        }
        return "Pick inquiry to be performed:";
    }

    public static String uusiOletusKyselyOnNyt(String nimi) {
        if (Asetukset.getKieli() == Kieli.SUOMI) {
            return "\n    Oletuskysely on nyt '" + nimi + "'.\n";
        }
        return "\n    Default inquiry is now '" + nimi + "'.\n";
    }

    public static String poistettuKyselyNimelta(String nimi) {
        if (Asetukset.getKieli() == Kieli.SUOMI) {
            return "\n    Poistettu kysely nimeltä '" + nimi + "'.";
        }
        return "\n    Removed inquiry '" + nimi + "'.";
    }

    public static String oletusKyselyaEiOleValittu() {
        if (Asetukset.getKieli() == Kieli.SUOMI) {
            return "    Oletuskyselyä ei ole nyt valittu. Voit valita oletuskyselyn komennolla 2.";
        }
        return "    Default inquiry is now unpicked. You can pick a default inquiry with command 2.";
    }

    public static String kyselyitaEiOle() {
        if (Asetukset.getKieli() == Kieli.SUOMI) {
            return "    Kyselyitä ei ole. Voit lisätä uuden kyselyn komennolla 3.\n";
        }
        return "    There are no inquiries. You can add an inquiry with command 3.\n";
    }

    public static String kyselyssaEiOleYhtaanKysymysta() {
        if (Asetukset.getKieli() == Kieli.SUOMI) {
            return "Kyselyssä ei ole yhtään kysymystä.\n";
        }
        return "The inquiry has no questions in it.\n";
    }

    public static String kyselyOnValmis(String nimi) {
        if (Asetukset.getKieli() == Kieli.SUOMI) {
            return "Kysely on valmis. Tekstitiedosto '" + nimi + "' löytyy juurikansiosta.\n";
        }
        return "The inquiry is completed. Text file '" + nimi + "' is found in the root folder.\n";
    }

    public static String esimerkkiVastaus(String esimerkkiVastaus) {
        if (Asetukset.getKieli() == Kieli.SUOMI) {
            return "    (esim. " + esimerkkiVastaus + ")";
        }
        return "    (e.g. " + esimerkkiVastaus + ")";
    }

    public static String tamanNiminenKyselyOnJoOlemassa(String nimi) {
        if (Asetukset.getKieli() == Kieli.SUOMI) {
            return "\n    Kysely nimeltä '" + nimi + "' on jo olemassa.\n";
        }
        return "\n    An inquiry named '" + nimi + "' already exists.\n";
    }

    public static String lisattyKyselyNimelta(String nimi) {
        if (Asetukset.getKieli() == Kieli.SUOMI) {
            return "\n    Lisätty kysely nimeltä '" + nimi + "'.\n";
        }
        return "\n    Added an inquiry named '" + nimi + "'.\n";
    }
}