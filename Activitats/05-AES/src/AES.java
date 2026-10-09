import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "LaClauSecretaQueVulguis";

    public static byte[] xifraAES(String msg, String clau) throws Exception {
        byte[] bMsg = msg.getBytes(StandardCharsets.UTF_8);

        iv = generaIv();
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        SecretKeySpec key = generaHash(clau);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        byte[] msgXifrat = cipher.doFinal(bMsg);

        byte[] ivMsgXifrat = new byte[MIDA_IV + msgXifrat.length];
        System.arraycopy(iv, 0, ivMsgXifrat, 0, MIDA_IV);
        System.arraycopy(msgXifrat, 0, ivMsgXifrat, MIDA_IV, msgXifrat.length);

        return ivMsgXifrat;
    }

    public static String desxifraAES(byte[] bIvMsgXifrat, String clau) throws Exception {
        IvParameterSpec ivSpec = new IvParameterSpec(extreureIv(bIvMsgXifrat));

        byte[] msgXifrat = getBytesXifrats(bIvMsgXifrat);

        SecretKeySpec key = generaHash(clau);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
        byte[] bMsg = cipher.doFinal(msgXifrat);

        return new String(bMsg, StandardCharsets.UTF_8);
    }

    private static byte[] generaIv() {
        byte[] nouIv = new byte[MIDA_IV];
        new SecureRandom().nextBytes(nouIv);
        return nouIv;
    }

    private static byte[] extreureIv(byte[] ivMsgXifrat) {
        return Arrays.copyOfRange(ivMsgXifrat, 0, MIDA_IV);
    }

    private static byte[] getBytesXifrats(byte[] ivMsgXifrat) {
        return Arrays.copyOfRange(ivMsgXifrat, MIDA_IV, ivMsgXifrat.length);
    }

    private static SecretKeySpec generaHash(String clau) throws Exception {
        MessageDigest md = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hash = md.digest(clau.getBytes(StandardCharsets.UTF_8));
        return new SecretKeySpec(hash, ALGORISME_XIFRAT);
    }

    public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet",
                         "Hola Andrés cómo está tu cuñado",
                         "Àgora ïlla Ôtto"};

        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];

            byte[] bXifrats = null;
            String desxifrat = "";
            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: "
                        + e.getLocalizedMessage());
            }
            System.out.println("--------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
    }
}