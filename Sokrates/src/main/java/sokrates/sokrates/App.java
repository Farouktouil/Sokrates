package sokrates.sokrates;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        String onnistuukoKysymys = "Onnistuuko?";
        Kysymys onnistuuko = new Kysymys(Kieli.SUOMI, onnistuukoKysymys);

        String esimerkkiVastaus = "Ihan hyvin tai jos ei, niin kyllä se siitä!";
        onnistuuko.lisaaEsimerkkiVastaus(esimerkkiVastaus);

        Kysely kannustaja = new Kysely();
        kannustaja.lisaaKysymys(onnistuuko);

        kannustaja.kysele();
    }
}
