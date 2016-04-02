import javax.xml.bind.DatatypeConverter;

public class RepeatingXor {
    public static String encrypt(byte[] message, byte[] key) {
        byte encodedMessage[] = new byte[message.length];

        for(int index = 0; index < message.length; index++) {
            encodedMessage[index] = (byte) (message[index] ^ key[index % key.length]); 
        }

        return DatatypeConverter.printHexBinary(encodedMessage);
    }

    public static void main(String args[]) {
        if(args.length < 2) {
            System.out.println("Enter message and key");
        } else {
            byte[] message  = args[0].getBytes();
            byte[] key      = args[1].getBytes();
            
            System.out.println(encrypt(message, key));
        }
    }
}
