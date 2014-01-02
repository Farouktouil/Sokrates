package sokrates.sovelluslogiikka;

import java.util.ArrayList;

public class Kysely {

    private ArrayList<Kysymys> kysymykset = new ArrayList<>();
    private String nimi;

    public Kysely(String nimi) {
        this.nimi = nimi;
    }

    public void lisaaKysymys(Kysymys kysymys) {
        if (!this.kysymykset.contains(kysymys)) {
            this.kysymykset.add(kysymys);
        }
    }

    public ArrayList<Kysymys> getKysymykset() {
        return this.kysymykset;
    }

    @Override
    public String toString() {
        return this.nimi;
    }
}