import java.util.List;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SingleCharXor {
    public static List<String> readFile(String fileName) throws Exception {
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        List<String> linesFromFile = new ArrayList<>();
        while((line = bufferedReader.readLine()) != null) {
            linesFromFile.add(line);
        }

        return linesFromFile;
    }

    public static void main(String[] args) throws Exception {
        String fileName = "challenge_4.txt";
        System.out.println("Decoding file: " + fileName);
        List<String> decodedStrings = Cipher.decodeStrings(readFile(fileName));
    }
}
