package sokrates.sovelluslogiikka;

import java.util.ArrayList;
import java.util.TreeMap;

public class KyselyHallinta {

    private ArrayList<Kysely> kyselyt = new ArrayList<>();
    private TreeMap<Integer, String> nimiTaulukko = new TreeMap<>();
    private Kysely oletuskysely = null;
    private boolean examples = true;

    public void lisaaKysely(String nimi) {
        if (!this.nimiTaulukko.containsValue(nimi)) {
            this.kyselyt.add(new Kysely(nimi));
            this.nimiTaulukko.put(this.kyselyt.size(), nimi);
        }
    }

    public void poistaKysely(String nimi) {
        if (this.nimiTaulukko.containsValue(nimi)) {
            // luultavasti ei toimi näin!
            this.kyselyt.remove(nimi);
        }
    }

    public Kysely haeKyselyNimenPerusteella(String nimi) {
        if (this.nimiTaulukko.containsValue(nimi)) {
            for (Kysely kysely : this.kyselyt) {
                if (kysely.toString().equals(nimi)) {
                    return kysely;
                }
            }
        }

        return null;
    }

    public Kysely getOletusKysely() {
        return this.oletuskysely;
    }

    public void setOletusKysely(Kysely kysely) {
        this.oletuskysely = kysely;
    }

    public ArrayList<Kysely> getKyselyt() {
        return this.kyselyt;
    }

    public TreeMap<Integer, String> getNimiTaulukko() {
        return this.nimiTaulukko;
    }

    public boolean getExamples() {
        return this.examples;
    }

    public void setExamples(boolean examples) {
        this.examples = examples;
    }
}