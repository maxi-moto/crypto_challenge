import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

public class Cipher {
    public static final int ASCII_CHARACTER_START   = 33;
    public static final int ASCII_CHARACTER_END     = 126;

    public static Map<Integer, List<String>> decodeStrings(List<String> encodedStrings) {
        Map<Integer, List<String>> decodedStrings = new HashMap<>();

        for(int asciiCode = ASCII_CHARACTER_START; 
                asciiCode <= ASCII_CHARACTER_END; 
                asciiCode++) {
            decodedStrings.put(asciiCode, xor(asciiCode, encodedStrings));
        }

        return decodedStrings;
    }

    public static List<String> xor(int asciiCode, List<String> encodedStrings) {
        List<String> decodedStrings = new ArrayList<>();

        for(String encodedString : encodedStrings) {
            byte[] encodedHexString = 
                DatatypeConverter.parseHexBinary(encodedString);
            byte[] decodedString = new byte[encodedHexString.length];
            
            int index = 0;
            for(byte b : encodedHexString) {
                decodedString[index++] = (byte) (asciiCode ^ ((int) b));
            }

            try {
                decodedStrings.add(new String(decodedString, "UTF-8"));
            } catch (Exception e) {
                System.out.println("Failed to convert string");
            }
        }

        return decodedStrings;
    }

    public static void scoreStrings(Map<Integer, List<String>> decodedStringMap) {
        for(Map.Entry<Integer, List<String>> entry : decodedStringMap.entrySet()) {
            char key                     = Character.toChars(entry.getKey())[0];
            List<String> decodedStrings  = entry.getValue();

            for(String decodedString : decodedStrings) {
                int score = 0;
                for(char c : decodedString.toCharArray()) {
                    if(Character.isLetter(c)) {
                        score += 5;
                    } else if(c == ' ') {
                        score += 2;
                    } else {
                        score -= 10;
                    }
                }

                if(score >= 100) {
                    System.out.print(key + " -> " + decodedString);
                }
            }
        }
    }
        
}
