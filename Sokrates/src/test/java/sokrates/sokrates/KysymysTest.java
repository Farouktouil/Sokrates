/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokrates.sokrates;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tepi
 */
public class KysymysTest {

    private Kysymys kysymys;
    private String esimerkkivastaus;
    private String vastaus;

    public KysymysTest() {
    }

    @Before
    public void setUp() {
        this.kysymys = new Kysymys(Kieli.ENGLANTI, "Hello, world?");
        this.esimerkkivastaus = "Roger!";
        this.vastaus = "Hello! -World";
    }
    
    @Test
    public void YhdellaKielellaEksistoivaaKysymystaOnYksi() {        
        assertEquals(1, kysymys.kysymysKaikillaKielilla().size());
    }
    
    @Test
    public void KahdellaKielellaEksistoivaaKysymystaOnKaksi() {      
        String samaSuomeksi = "Hei, maailma?";
        this.kysymys.lisaaKysymysEriKielella(Kieli.SUOMI, samaSuomeksi);
        
        assertEquals(2, kysymys.kysymysKaikillaKielilla().size());
    }
    
    @Test
    public void KysymyksenLisaaminenUudestaanSamallaKielellaEiKasvataKysymystenMaaraa() {        
        this.kysymys.lisaaKysymysEriKielella(Kieli.ENGLANTI, "Hello??? WORLD!!!");
        
        assertEquals(1, kysymys.kysymysKaikillaKielilla().size());
    }
    
    @Test
    public void EsimVastauksettomanKysymyksenEsimVastausOnTyhja() {        
        assertEquals(null, kysymys.esimerkkivastaus());
    }
    
    @Test
    public void EsimVastauksenLisaamisenJalkeenKysymyksellaOnEsimVastaus() {        
        this.kysymys.lisaaEsimerkkiVastaus(esimerkkivastaus);
        
        assertTrue(!kysymys.esimerkkivastaus().isEmpty());
    }
    
    @Test
    public void VastauksettomanKysymyksenVastausOnTyhja() {        
        assertTrue(kysymys.vastaukset().isEmpty());
    }
    
    @Test
    public void VastauksenLisaamisenJalkeenKysymyksellaOnYksiVastaus() {       
        this.kysymys.lisaaVastaus(vastaus);
        
        assertEquals(1, kysymys.vastaukset().size());
    }
}