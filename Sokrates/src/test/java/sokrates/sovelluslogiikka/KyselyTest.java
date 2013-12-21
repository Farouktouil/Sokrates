/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sokrates.sovelluslogiikka;

import sokrates.sovelluslogiikka.Kysely;
import sokrates.sovelluslogiikka.Kysymys;
import sokrates.sovelluslogiikka.Kieli;
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
public class KyselyTest {
    private Kysely kysely;
    private Kysymys kysymys;
    
    public KyselyTest() {
    }
    
    @Before
    public void setUp() {
        this.kysely = new Kysely();
        this.kysymys = new Kysymys(Kieli.ENGLANTI, "Hello, world?");
    }
    
    @Test
    public void TyhjassaKyselyssaEiOleKysymyksia() {        
        assertTrue(kysely.getKysymykset().isEmpty());
    }
    
    @Test
    public void KysymyksenLisaamisenJalkeenKyselyssaOnYksiKysymys() {
        kysely.lisaaKysymys(kysymys);
        
        assertEquals(1, kysely.getKysymykset().size());
    }
    
    @Test
    public void SamanKysymyksenLisaaminenUudestaanEiKasvataKysymystenMaaraa() {
        kysely.lisaaKysymys(kysymys);
        kysely.lisaaKysymys(kysymys);
        
        assertEquals(1, kysely.getKysymykset().size());
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}