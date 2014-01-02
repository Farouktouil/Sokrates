package sokrates.util;

import java.util.Scanner;

public class Lukija {

    private Scanner lukija = new Scanner(System.in);

    public String lueMerkkijono() {
        return lukija.nextLine();
    }

    public String lueMerkkijono(String kysymys) {
        System.out.print(kysymys);
        return lukija.nextLine();
    }

    public int lueKokonaisluku() {
        return Integer.parseInt(lukija.nextLine());
    }

    public int lueKokonaisluku(String kysymys) {
        System.out.print(kysymys);
        return Integer.parseInt(lukija.nextLine());
    }
}