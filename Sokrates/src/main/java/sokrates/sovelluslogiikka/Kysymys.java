package sokrates.sovelluslogiikka;

import java.util.ArrayList;
import java.util.HashMap;

public class Kysymys {

    private HashMap<Kieli, String> kysymysKaikillaKielilla = new HashMap();
    private ArrayList<String> vastaukset = new ArrayList();
    private String esimerkkivastaus;

    public Kysymys(Kieli kieli, String kysymys) {
        this.kysymysKaikillaKielilla.put(kieli, kysymys);
    }

    public void lisaaKysymysEriKielella(Kieli kieli, String kysymys) {
        if (!this.kysymysKaikillaKielilla.containsKey(kieli)) {
            this.kysymysKaikillaKielilla.put(kieli, kysymys);
        }
    }

    public void setEsimerkkiVastaus(String esimerkkivastaus) {
        this.esimerkkivastaus = esimerkkivastaus;
    }

    public String getEsimerkkiVastaus() {
        return this.esimerkkivastaus;
    }

    public void lisaaVastaus(String vastaus) {
        this.vastaukset.add(vastaus);
    }

    public ArrayList<String> getVastaukset() {
        return this.vastaukset;
    }

    public HashMap<Kieli, String> getKysymysKaikillaKielilla() {
        return this.kysymysKaikillaKielilla;
    }

    public String getKysymysNykyisellaKielella() {
        Kieli nykyinenKieli = Asetukset.getKieli();
        String kysymysNykyisellaKielella = this.kysymysKaikillaKielilla.get(nykyinenKieli);

        return kysymysNykyisellaKielella;
    }
}