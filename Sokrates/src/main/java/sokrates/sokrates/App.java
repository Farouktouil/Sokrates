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

// http://sourcemaking.com/design_patterns/command
// https://github.com/Vaakapallo/NPC-dangerzone/tree/master/NPCBibtexter/src/main/java/CommandInterpreter
// https://github.com/Vaakapallo/NPC-dangerzone/blob/master/NPCBibtexter/src/main/java/textUI/TextUI.java