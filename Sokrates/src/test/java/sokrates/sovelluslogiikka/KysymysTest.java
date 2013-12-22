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
        this.kysymys = new Kysymys(Kieli.ENGLANTI, "Hello, world?");
        this.esimerkkivastaus = "Roger!";
        this.vastaus = "Hello! -World";
    }

    @Test
    public void YhdellaKielellaEksistoivaaKysymystaOnYksi() {
        assertEquals(1, kysymys.getKysymysKaikillaKielilla().size());
    }

    @Test
    public void KahdellaKielellaEksistoivaaKysymystaOnKaksi() {
        String samaSuomeksi = "Hei, maailma?";
        this.kysymys.lisaaKysymysEriKielella(Kieli.SUOMI, samaSuomeksi);

        assertEquals(2, kysymys.getKysymysKaikillaKielilla().size());
    }

    @Test
    public void KysymyksenLisaaminenUudestaanSamallaKielellaEiKasvataKysymystenMaaraa() {
        this.kysymys.lisaaKysymysEriKielella(Kieli.ENGLANTI, "Hello??? WORLD!!!");

        assertEquals(1, kysymys.getKysymysKaikillaKielilla().size());
    }

    @Test
    public void EsimVastauksettomanKysymyksenEsimVastausOnTyhja() {
        assertEquals(null, kysymys.getEsimerkkiVastaus());
    }

    @Test
    public void EsimVastauksenLisaamisenJalkeenKysymyksellaOnEsimVastaus() {
        this.kysymys.setEsimerkkiVastaus(esimerkkivastaus);

        assertTrue(!kysymys.getEsimerkkiVastaus().isEmpty());
    }

    @Test
    public void VastauksettomanKysymyksenVastausOnTyhja() {
        assertTrue(kysymys.getVastaukset().isEmpty());
    }

    @Test
    public void VastauksenLisaamisenJalkeenKysymyksellaOnYksiVastaus() {
        this.kysymys.lisaaVastaus(vastaus);

        assertEquals(1, kysymys.getVastaukset().size());
    }

    @Test
    public void getKysymysPalauttaaKysymyksenEnglanniksiKunKieliOnEnglanti() {
        Asetukset.kieli = Kieli.ENGLANTI;

        assertEquals("Hello, world?", this.kysymys.getKysymys());
    }

    @Test
    public void getKysymysPalauttaaKysymyksenSuomeksiKunKieliOnSuomi() {
        Kysymys kysymys = new Kysymys(Kieli.SUOMI, "Hei, maailma?");
        Asetukset.kieli = Kieli.SUOMI;

        assertEquals("Hei, maailma?", kysymys.getKysymys());
    }
}