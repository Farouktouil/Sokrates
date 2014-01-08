package sokrates.sovelluslogiikka;

import org.junit.Test;
import static org.junit.Assert.*;

public class KyselyHallintaTest {

    private KyselyHallinta hallinta;

    public KyselyHallintaTest() {
        this.hallinta = new KyselyHallinta();
    }

    @Test
    public void KyselynLisaamisenJalkeenHallinnassaOnYksiKysely() {
        hallinta.lisaaKysely("ongelmanratkaisu");

        assertEquals(1, hallinta.getKyselyt().size());
    }

    @Test
    public void KahdenKyselynLisaamisenJalkeenHallinnassaOnKaksiKyselya() {
        hallinta.lisaaKysely("ongelmanratkaisu");
        hallinta.lisaaKysely("paivakirja");
        assertEquals(2, hallinta.getKyselyt().size());
    }

    @Test
    public void SamanKyselynLisaaminenUudestaanEiVaikutaKyselyjenMaaraan() {
        hallinta.lisaaKysely("ongelmanratkaisu");
        hallinta.lisaaKysely("ongelmanratkaisu");
        assertEquals(1, hallinta.getKyselyt().size());
    }

    @Test
    public void LisatynKyselynNimenPerusteellaHaettaessaPalautetaanEpaTyhjaa() {
        hallinta.lisaaKysely("ongelmanratkaisu");
        assertTrue(!(hallinta.haeKyselyNimenPerusteella("ongelmanratkaisu") == null));
    }

    @Test
    public void LisaamattomanKyselynNimenPerusteellaHaettaessaPalautetaanTyhjaa() {
        assertTrue((hallinta.haeKyselyNimenPerusteella("inhottava interrogaatio") == null));
    }

    @Test
    public void KyselynLisaamisenJaPoistamisenJalkeenHallinnassaEiOleKyselyita() {
        hallinta.lisaaKysely("ongelmanratkaisu");
        Kysely ongelmanratkaisu = hallinta.haeKyselyNimenPerusteella("ongelmanratkaisu");
        hallinta.poistaKysely(ongelmanratkaisu);
        assertEquals(0, hallinta.getKyselyt().size());
    }

    @Test
    public void getExamplesPalauttaaAluksiTrue() {
        assertTrue(hallinta.getExamples());
    }

    @Test
    public void setExamplesTrueToimii() {
        hallinta.setExamples(true);
        assertTrue(hallinta.getExamples());
    }

    @Test
    public void setExamplesFalseToimii() {
        hallinta.setExamples(false);
        assertTrue(!hallinta.getExamples());
    }
}