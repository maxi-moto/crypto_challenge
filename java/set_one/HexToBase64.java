import java.util.Base64;
import javax.xml.bind.DatatypeConverter;

public class HexToBase64 {
    public static void hexToBase64(String hexString) {
        try {
            System.out.println(Base64.getEncoder().encodeToString(DatatypeConverter.parseHexBinary(hexString)));
        } catch(Exception e) {
            System.out.println("Conversion error");
        }
    }

    public static void main(String[] args) {
        if( args.length < 1) {
            System.out.println("Enter hex string to convert");
        } else {
            hexToBase64(args[0]);
        }
    }
}
