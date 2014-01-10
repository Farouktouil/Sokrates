package sokrates.tiedostonkasittely;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Teo
 */
public class TiedostonLukija {

    public List<String> lueTiedostotKyselyiksi() {
        List<String> textFiles = textFiles("src/inquiries/");
        System.out.println(textFiles); // [diary.txt, dream diary.txt, problem solving.txt]
        return textFiles;
    }

    public List<String> textFiles(String directory) {
        List<String> textFiles = new ArrayList<>();
        File dir = new File(directory);

        for (File file : dir.listFiles()) {
            if (file.getName().endsWith((".txt"))) {
                textFiles.add(file.getName().substring(0, file.getName().length() - 4));
            }
        }

        return textFiles;
    }
}