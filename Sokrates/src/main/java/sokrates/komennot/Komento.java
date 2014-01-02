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
        return this.nimi;
    }

    public String getSelite() {
        return this.selite;
    }

    public Kysely kayttajanOsoittamaKysely() {
        Kysely kysely = null;
        System.out.println("Valitse kysely:");
        tulostaKyselyVaihtoehdot();
        System.out.println();

        while (true) {
            int syote = lukija.lueKokonaisluku("kysely: ");
            if (this.hallinta.getNimiTaulukko().containsKey(syote)) {
                String valitunKyselynNimi = this.hallinta.getNimiTaulukko().get(syote);
                kysely = this.hallinta.haeKyselyNimenPerusteella(valitunKyselynNimi);
                break;
            }
        }

        return kysely;
    }

    public void tulostaKyselyVaihtoehdot() {
        for (Integer avainluku : this.hallinta.getNimiTaulukko().keySet()) {
            System.out.println("  " + avainluku + " "
                    + this.hallinta.getNimiTaulukko().get(avainluku));
        }
    }

    public abstract boolean suorita();
}