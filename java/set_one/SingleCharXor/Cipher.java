import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

public class Cipher {
    public static final int ASCII_CHARACTER_START   = 33;
    public static final int ASCII_CHARACTER_END     = 126;

    private Map<Integer, List<String>> decodedStrings = new HashMap<>();

    public static void decodeStrings(List<String> encodedStrings) {
        for(int asciiCode = ASCII_CHARACTER_START; 
                asciiCode <= ASCII_CHARACTER_END; 
                asciiCode++) {
            decodedStrings.put(asciiCode, xor(asciiCode, encodedStrings));
        }
    }

    public static List<String> xor(int asciiCode, List<String> encodedStrings) {
        List<String> decodedStrings = new ArrayList();

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
        
}
