import javax.xml.bind.DatatypeConverter;

public class FixedXor {
    public static void print(byte[] xor) {
        try {
        System.out.println(DatatypeConverter.printHexBinary(xor).toLowerCase()
                + " -> " + new String(xor, "UTF-8"));
        } catch (Exception e) {
            System.out.println("Error converting");
        }
    }

    public static byte[] xor(byte[] string, byte[] key, int length) {
        byte[] xor = new byte[length];
        for (int index = 0; index < length; index++) {
            xor[index] = (byte) (((int) string[index]) ^ ((int) key[index]));
        }

        return xor;
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Enter hex string and hex key in order given");
        } else {
            byte[] string    = DatatypeConverter.parseHexBinary(args[0]);
            byte[] key       = DatatypeConverter.parseHexBinary(args[1]);

            print(xor(string, key, string.length));
        }
    }
}
