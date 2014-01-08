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
    public void EsimVastauksenLisaamisenJalkeenKysymyksellaOnEsimVastaus() {
        this.kysymys.setEsimerkkiVastaus("Roger!");

        assertTrue(!kysymys.getEsimerkkiVastaus().isEmpty());
    }

    @Test
    public void VastauksettomanKysymyksenVastausOnTyhja() {
        assertTrue(kysymys.getVastaus() == null);
    }

    @Test
    public void getKysymysNykyisellaKielellaPalauttaaKysymyksenEnglanniksiKunKieliOnEnglanti() {
        Asetukset.kieli = Kieli.ENGLANTI;

        assertEquals("Hello, world?", this.kysymys.getKysymysNykyisellaKielella());
    }

    @Test
    public void getKysymysNykyisellaKielellaPalauttaaKysymyksenSuomeksiKunKieliOnSuomi() {
        Kysymys kysymys = new Kysymys("Hei, maailma?", null, null);
        Asetukset.kieli = Kieli.SUOMI;

        assertEquals("Hei, maailma?", kysymys.getKysymysNykyisellaKielella());
    }
}