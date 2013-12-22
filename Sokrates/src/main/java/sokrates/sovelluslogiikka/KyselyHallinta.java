package sokrates.sovelluslogiikka;

import java.util.HashMap;

public class KyselyHallinta {

    private HashMap<String, Kysely> kyselyt = new HashMap<>();
    private Kysely oletuskysely = null;
    private boolean examples = true;

    public void lisaaKysely(String nimi) {
        if (!this.kyselyt.containsKey(nimi)) {
            this.kyselyt.put(nimi, new Kysely());
        }
    }

    public Kysely haeKyselyNimenPerusteella(String nimi) {
        if (this.kyselyt.containsKey(nimi)) {
            return this.kyselyt.get(nimi);
        } else {
            return null;
        }
    }

    public Kysely getOletusKysely() {
        return this.oletuskysely;
    }

    public void setOletusKysely(Kysely kysely) {
        this.oletuskysely = kysely;
    }

    public HashMap<String, Kysely> getKyselyt() {
        return this.kyselyt;
    }

    public boolean getExamples() {
        return this.examples;
    }

    public void setExamples(boolean examples) {
        this.examples = examples;
    }
}
