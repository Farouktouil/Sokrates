package sokrates.kayttoliittyma;

import sokrates.sovelluslogiikka.Asetukset;
import sokrates.sovelluslogiikka.Kieli;

/**
 * Tulostamo vastaa käyttöliittymän kaksi- tai monikielisyydestä. Aina kun
 * ohjelma tulostaa tekstiä joka riippuu senhetkisestä kieliasetuksesta,
 * kutsutaan vastaavaa Tulostamon metodia, joka palauttaa kieliasetusta
 * vastaavan oikean muodon. Uuden kielen lisääminen onnistuisi lisäämällä uusi
 * if-ehto jokaiseen Tulostamon metodiin (ja tekemällä tarvittavat muutokset
 * komentoon VaihdaKieli, sekä Asetuksiin ja toki Kieli-luokkaan).
 *
 * @author Teo
 */
public class Tulostamo {

    /**
     * @return Teksti esimerkkivastausten asetuttua päälle.
     */
    public static String examplesOn() {
        if (Asetukset.getKieli() == Kieli.SUOMI) {
            return "    Esimerkkivastaukset ovat nyt päällä.\n";
        }
        return "    Example answers are now on.\n";
    }

    /**
     * @return Teksti esimerkkivastausten asetuttua pois päältä.
     */
    public static String examplesOff() {
        if (Asetukset.getKieli() == Kieli.SUOMI) {
            return "    Esimerkkivastaukset ovat nyt pois päältä.\n";
        }
        return "    Example answers are now off.\n";
    }

    /**
     * @return Teksti 'komento: ' ennen käyttäjän syöttämää komentoa.
     */
    public static String komento() {
        if (Asetukset.getKieli() == Kieli.SUOMI) {
            return "komento: ";
        }
        return "command: ";
    }

    /**
     * @return Teksti 'kysely: ' ennen käyttäjän syöttämää kyselyä.
     */
    public static String kysely() {
        if (Asetukset.getKieli() == Kieli.SUOMI) {
            return "kysely: ";
        }
        return "inquiry: ";
    }

    /**
     * @return Teksti 'peruuta' valikossa.
     */
    public static String peruuta() {
        if (Asetukset.getKieli() == Kieli.SUOMI) {
            return "peruuta";
        }
        return "cancel";
    }

    /**
     * @return Teksti joka pyytää käyttäjää nimeämään uuden kyselyn.
     */
    public static String annaKyselylleNimi() {
        if (Asetukset.getKieli() == Kieli.SUOMI) {
            return "Anna uudelle kyselylle nimi: ";
        }
        return "Give new inquiry a name: ";
    }

    /**
     * @return Teksti joka pyytää käyttäjää valitsemaan poistettavan kyselyn.
     */
    public static String valitsePoistettavaKysely() {
        if (Asetukset.getKieli() == Kieli.SUOMI) {
            return "Valitse poistettava kysely:";
        }
        return "Pick an inquiry to be removed:";
    }

    /**
     * @return Teksti joka pyytää käyttäjää valitsemaan kyseltävän kyselyn.
     */
    public static String valitseKyseltavaKysely() {
        if (Asetukset.getKieli() == Kieli.SUOMI) {
            return "Valitse kyseltävä kysely:";
        }
        return "Pick an inquiry to be performed:";
    }
    
    /**
     * @return Teksti joka pyytää käyttäjää valitsemaan kyseltävän kyselyn.
     */
    public static String valitseKohdeKysely() {
        if (Asetukset.getKieli() == Kieli.SUOMI) {
            return "Valitse kysely johon kysymyksiä lisätään:";
        }
        return "Pick an inquiry to which questions will be added:";
    }

    /**
     * @param nimi Juuri poistetun kyselyn nimi.
     * @return Teksti joka kertoo käyttäjälle minkä niminen kysely juuri
     * poistettiin.
     */
    public static String poistettuKyselyNimelta(String nimi) {
        if (Asetukset.getKieli() == Kieli.SUOMI) {
            return "    Poistettu kysely nimeltä '" + nimi + "'.";
        }
        return "    Removed the inquiry named '" + nimi + "'.";
    }

    /**
     * @return Teksti joka kertoo käyttäjälle että kyselyitä ei ole ja millä
     * komennolla kyselyn voi lisätä (hardcoded, tyhmää!).
     */
    public static String kyselyitaEiOle() {
        if (Asetukset.getKieli() == Kieli.SUOMI) {
            return "    Kyselyitä ei ole. Voit lisätä uuden kyselyn komennolla 1.\n";
        }
        return "    There are no inquiries. You can add an inquiry with command 1.\n";
    }

    /**
     * @return Teksti joka kertoo käyttäjälle että kyselyssä ei ole yhtään
     * kysymystä.
     */
    public static String kyselyssaEiOleYhtaanKysymysta() {
        if (Asetukset.getKieli() == Kieli.SUOMI) {
            return "Kyselyssä ei ole yhtään kysymystä.\n";
        }
        return "The inquiry has no questions in it.\n";
    }

    /**
     * @param nimi Juuri luodun tekstitiedoston nimi.
     * @return Teksti joka kertoo käyttäjälle että kysely on valmis ja
     * minkäniminen tekstitiedosto löytyy juurikansiosta.
     */
    public static String kyselyOnValmis(String nimi) {
        if (Asetukset.getKieli() == Kieli.SUOMI) {
            return "Kysely on valmis. Tekstitiedosto nimeltä '" + nimi + "' löytyy juurikansiosta.\n";
        }
        return "The inquiry is completed. Text file named '" + nimi + "' is found in the root folder.\n";
    }

    /**
     * @param esimerkkiVastaus Jonkin kysymyksen esimerkkivastaus.
     * @return Rivi joka tulostetaan kysymyksen yhteydessä mikäli asetus
     * esimerkkivastausten näyttämisestä on true.
     */
    public static String esimerkkiVastaus(String esimerkkiVastaus) {
        if (Asetukset.getKieli() == Kieli.SUOMI) {
            return "    (esim. " + esimerkkiVastaus + ")";
        }
        return "    (e.g. " + esimerkkiVastaus + ")";
    }

    /**
     * @param nimi Jo olemassaolevaksi havaittu kyselyn nimi.
     * @return Teksti joka kertoo käyttäjälle että jonkin niminen kysely on jo
     * olemassa.
     */
    public static String tamanNiminenKyselyOnJoOlemassa(String nimi) {
        if (Asetukset.getKieli() == Kieli.SUOMI) {
            return "\n    Kysely nimeltä '" + nimi + "' on jo olemassa.\n";
        }
        return "\n    An inquiry named '" + nimi + "' already exists.\n";
    }

    /**
     * @param nimi Juuri lisätyn kyselyn nimi.
     * @return Teksti joka kertoo käyttäjälle että on lisätty tietyn niminen
     * kysely.
     */
    public static String lisattyKyselyNimelta(String nimi) {
        if (Asetukset.getKieli() == Kieli.SUOMI) {
            return "\n    Lisätty kysely nimeltä '" + nimi + "'.\n";
        }
        return "\n    Added an inquiry named '" + nimi + "'.\n";
    }
    
    public static String muotoileKysymysSuomeksi() {
        if (Asetukset.getKieli() == Kieli.SUOMI) {
            return "\nMuotoile kysymys suomeksi: ";
        }
        return "\nFormulate the question in Finnish: ";
    }
    
    public static String muotoileKysymysEnglanniksi() {
        if (Asetukset.getKieli() == Kieli.SUOMI) {
            return "Muotoile kysymys englanniksi: ";
        }
        return "Formulate the question in English: ";
    }
    
    public static String muotoileEsimerkkiVastaus() {
        if (Asetukset.getKieli() == Kieli.SUOMI) {
            return "Muotoile esimerkkivastaus: ";
        }
        return "Formulate an example answer: ";
    }
    
    public static String lisattyKysymys(String kohdeKyselynNimi) {
        if (Asetukset.getKieli() == Kieli.SUOMI) {
            return "\n    Lisätty kysymys kyselyyn '" + kohdeKyselynNimi + "'.\n";
        }
        return "\n    A question was added to the inquiry '" + kohdeKyselynNimi + "'.\n";
    }
    
    public static String xPalaaTakaisinKysymyksiaLisatessa(String kohdeKyselynNimi) {
        if (Asetukset.getKieli() == Kieli.SUOMI) {
            return "x palaa takaisin, muuten paina enter lisätäksesi uuden kysymyksen kyselyyn '" + kohdeKyselynNimi + "': ";
        }
        return "x returns back, otherwise press enter to add new question to the inquiry '" + kohdeKyselynNimi + "': ";
    }
}