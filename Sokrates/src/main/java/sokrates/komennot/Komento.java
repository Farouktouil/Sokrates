package sokrates.komennot;

import sokrates.sovelluslogiikka.Kysely;
import sokrates.sovelluslogiikka.KyselyHallinta;
import sokrates.util.Lukija;

public abstract class Komento {

    protected Lukija lukija;
    private String nimi;
    private String selite;
    protected KyselyHallinta hallinta;

    public Komento(Lukija lukija, KyselyHallinta hallinta, String nimi, String selite) {
        this.lukija = lukija;
        this.nimi = nimi;
        this.selite = selite;
        this.hallinta = hallinta;
    }

    public String getNimi() {
        return nimi;
    }

    public String getSelite() {
        return selite;
    }
    
    public Kysely kayttajaValitseeKyselyn() {
        Kysely kysely = null;
        
        System.out.println("    Valitse jokin seuraavista kyselyistä:");
        tulostaKyselyjenNimet();

        while (true) {
            String syote = lukija.lueMerkkijono("    valittavan kyselyn nimi: ");

            if (kayttajanValitsemaKyselyOnOlemassa(syote)) {
                kysely = this.hallinta.haeKyselyNimenPerusteella(syote);
                break;
            }
        }
        
        return kysely;
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

    public abstract boolean suorita();
}