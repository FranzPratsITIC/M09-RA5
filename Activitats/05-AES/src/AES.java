import javax.crypto.*;
import java.security.*;

public class AES {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "Hello";

    public byte[] xifraAES(String
        [] xifraAES(String msg, String password){

    }

    public String desxifraAES(byte[] bMsgXifrat, String password){

    }
    
}
