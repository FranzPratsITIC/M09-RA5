import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.spec.KeySpec;
import java.util.HexFormat;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {
    
    int npass = 0;

    public String getSHA512AmbSalt(String pw, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(pw.getBytes(StandardCharsets.UTF_8));
            
            HexFormat hex = HexFormat.of();
            return hex.formatHex(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getPBKDF2AmbSalt(String pw, String salt) {
        try {
            // 65536 iteraciones y 128 bits (16 bytes)
            KeySpec spec = new PBEKeySpec(pw.toCharArray(), salt.getBytes(StandardCharsets.UTF_8), 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] bytes = factory.generateSecret(spec).getEncoded();
            
            HexFormat hex = HexFormat.of();
            return hex.formatHex(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String forcaBruta(String alg, String hashTarget, String salt) {
        npass = 0;
        char[] charset = "abcdefABCDEF1234567890!".toCharArray();
        char[] p = new char[6];

        for (int i0 = 0; i0 < charset.length; i0++) {
            p[0] = charset[i0];
            for (int i1 = 0; i1 < charset.length; i1++) {
                p[1] = charset[i1];
                for (int i2 = 0; i2 < charset.length; i2++) {
                    p[2] = charset[i2];
                    for (int i3 = 0; i3 < charset.length; i3++) {
                        p[3] = charset[i3];
                        for (int i4 = 0; i4 < charset.length; i4++) {
                            p[4] = charset[i4];
                            for (int i5 = 0; i5 < charset.length; i5++) {
                                p[5] = charset[i5];
                                npass++;
                                
                                String intentoPw = new String(p);
                                String intentoHash;
                                
                                if (alg.equals("SHA-512")) {
                                    intentoHash = getSHA512AmbSalt(intentoPw, salt);
                                } else {
                                    intentoHash = getPBKDF2AmbSalt(intentoPw, salt);
                                }
                                
                                if (intentoHash != null && intentoHash.equals(hashTarget)) {
                                    return intentoPw;
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public String getInterval(long t1, long t2) {
        long diff = t2 - t1;
        long millis = diff % 1000;
        long segons = (diff / 1000) % 60;
        long minuts = (diff / (60 * 1000)) % 60;
        long hores = (diff / (60 * 60 * 1000)) % 24;
        long dies = diff / (24 * 60 * 60 * 1000);

        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis", dies, hores, minuts, segons, millis);
    }

    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";
        Hashes h = new Hashes();
        
        String[] aHashes = {h.getSHA512AmbSalt(pw, salt),
                            h.getPBKDF2AmbSalt(pw, salt) };
        String pwTrobat = null;
        String[] algorismes = {"SHA-512", "PBKDF2"};
        
        for(int i=0; i< aHashes.length; i++){
            System.out.printf("===========================\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: %s\n",aHashes[i]);
            System.out.printf("---------------------------\n");
            System.out.printf("-- Inici de força bruta ---\n");
            
            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();
            
            System.out.printf("Pass : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps : %s\n", h.getInterval(t1, t2));
            System.out.printf("---------------------------\n\n");
        }
    }
}