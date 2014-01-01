package sokrates.komennot;

import sokrates.sovelluslogiikka.KyselyHallinta;
import sokrates.util.Lukija;

public class VaihdaOletusKysely extends Komento {

    public VaihdaOletusKysely(Lukija lukija, KyselyHallinta hallinta, String nimi, String selite) {
        super(lukija, hallinta, nimi, selite);
    }

    @Override
    public boolean suorita() {
        System.out.println("    Valitse jokin seuraavista kyselyistä:");
        tulostaKyselyjenNimet();

        while (true) {
            String syote = lukija.lueMerkkijono("    valittavan kyselyn nimi: ");

            if (kayttajanValitsemaKyselyOnOlemassa(syote)) {
                this.hallinta.setOletusKysely(this.hallinta.getKyselyt().get(syote));
                System.out.println("    Oletuskysely on nyt " + syote + ".");
                break;
            }
        }

        return true;
    }

    public void tulostaKyselyjenNimet() {
        for (String nimi : this.hallinta.getKyselyt().keySet()) {
            System.out.println("        " + nimi);
        }
    }

    public boolean kayttajanValitsemaKyselyOnOlemassa(String syote) {
        if (this.hallinta.getKyselyt().containsKey(syote)) {
            return true;
        }

        return false;
    }
}
