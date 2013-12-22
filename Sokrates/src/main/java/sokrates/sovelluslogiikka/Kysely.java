package sokrates.sovelluslogiikka;

import java.util.ArrayList;

public class Kysely {

    private ArrayList<Kysymys> kysymykset = new ArrayList<>();

    public void lisaaKysymys(Kysymys kysymys) {
        if (!this.kysymykset.contains(kysymys)) {
            this.kysymykset.add(kysymys);
        }
    }

    public ArrayList<Kysymys> getKysymykset() {
        return this.kysymykset;
    }
}
