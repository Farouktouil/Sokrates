package sokrates.sovelluslogiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KysymysTest {

    private Kysymys kysymys;
    private String esimerkkivastaus;
    private String vastaus;

    @Before
    public void setUp() {
        this.kysymys = new Kysymys("Hei maailma?", "Hello, world?", null);
        this.vastaus = "Hello! -World";
    }

    @Test
    public void EsimVastauksettomanKysymyksenEsimVastausOnTyhja() {
        assertEquals(null, kysymys.getEsimerkkiVastaus());
    }

    @Test
    public void VastauksenLisaamisenJalkeenKysymyksellaOnVastaus() {
        this.kysymys.setVastaus("Roger!");
        assertTrue(!kysymys.getVastaus().isEmpty());
    }

    @Test
    public void VastauksettomanKysymyksenVastausOnTyhja() {
        assertTrue(kysymys.getVastaus() == null);
    }

    @Test
    public void getKysymysKielellaPalauttaaKysymyksenEnglanniksiKunKieliOnEnglanti() {
        Asetukset.kieli = Kieli.ENGLANTI;
        assertEquals("Hello, world?", this.kysymys.getKysymysKielella(Asetukset.getKieli()));
    }

    @Test
    public void getKysymysKielellaPalauttaaKysymyksenSuomeksiKunKieliOnSuomi() {
        Kysymys q = new Kysymys("Hei, maailma?", null, null);
        Asetukset.setKieli(Kieli.SUOMI);
        assertEquals("Hei, maailma?", q.getKysymysKielella(Asetukset.getKieli()));
    }
}