package sokrates.sovelluslogiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KyselyTest {

    private Kysely kysely;
    private Kysymys kysymys;

    public KyselyTest() {
    }

    @Before
    public void setUp() {
        this.kysely = new Kysely("");
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
}