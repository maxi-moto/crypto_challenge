import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.DatatypeConverter;

/*
 * PRINTING RULES
 * Printing is done in the scoreString() method. Values are only printed if at
 * if there is at least one space and about ten words.
 *
 */

public class XorCipher {
    public static final int ASCII_CHARACTER_START   = 33;
    public static final int ASCII_CHARACTER_END     = 126;

    public static String xor(int asciiCode, byte[] decodedHexString) {
        byte[] decodedString = new byte[decodedHexString.length];
        int index = 0;
        for(byte b : decodedHexString) {
            decodedString[index++] = (byte) (asciiCode ^ ((int) b)); 
        }

        try {
            return new String(decodedString, "UTF-8");
        } catch (Exception e) {
            System.out.println("Failed to convert");
            return "";
        }
    }

    public static void scoreStrings(Map<Integer, String> decodedStrings) {
        for(Map.Entry<Integer, String> entry : decodedStrings.entrySet()) {
            char key                = Character.toChars(entry.getKey())[0];
            String decodedString    = entry.getValue();

            String[] words = decodedString.split("");
            if(words.length <= 6) {
                System.out.println(key + " -> " + decodedString);
            }
        } 
    }
    
    public static void main(String[] args) {
        if(args.length < 1) {
            System.out.println("Enter a hex string");
        } else {
            Map<Integer, String> decodedStrings = new HashMap<>();

            byte[] decodedHexString = DatatypeConverter.parseHexBinary(args[0]);
            for(int asciiCode = ASCII_CHARACTER_START; 
                    asciiCode <= ASCII_CHARACTER_END; 
                    asciiCode++) {
                decodedStrings.put(asciiCode, xor(asciiCode, decodedHexString));
            }

            scoreStrings(decodedStrings);
            
        }
    }
}
