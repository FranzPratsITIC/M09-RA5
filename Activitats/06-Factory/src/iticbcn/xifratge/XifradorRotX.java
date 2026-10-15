package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;

public class XifradorRotX implements Xifrador {

    public static final char[] MINUSCULES = "aàábcçdeèéfghiìíïjklmnñoòópqrstuùúüvwxyz".toCharArray();
    public static final char[] MAJUSCULES = "AÀÁBCÇDEÈÉFGHIÌÍÏJKLMNÑOÒÓPQRSTUÙÚÜVWXYZ".toCharArray();

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        int desplaçament = validaClau(clau);
        String resultat = xifraRotX(msg, desplaçament);
        return new TextXifrat(resultat.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        int desplaçament = validaClau(clau);
        if (xifrat == null) {
            return null;
        }
        return desxifraRotX(xifrat.toString(), desplaçament);
    }

    private int validaClau(String clau) throws ClauNoSuportada {
        try {
            int k = Integer.parseInt(clau);
            if (k < 0 || k > 40) {
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
            }
            return k;
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
    }

    public String xifraRotX(String cadena, int desplaçament) {
        StringBuilder sb = new StringBuilder();
        for (char c : cadena.toCharArray()) {
            sb.append(rotaCaracter(c, desplaçament, true));
        }
        return sb.toString();
    }

    public String desxifraRotX(String cadena, int desplaçament) {
        StringBuilder sb = new StringBuilder();
        for (char c : cadena.toCharArray()) {
            sb.append(rotaCaracter(c, desplaçament, false));
        }
        return sb.toString();
    }

    private char rotaCaracter(char c, int desplaçament, boolean xifrar) {
        for (int i = 0; i < MINUSCULES.length; i++) {
            if (MINUSCULES[i] == c) {
                int novaPos = xifrar ? (i + desplaçament) % MINUSCULES.length
                                     : (i - desplaçament + MINUSCULES.length) % MINUSCULES.length;
                return MINUSCULES[novaPos];
            }
        }
        for (int i = 0; i < MAJUSCULES.length; i++) {
            if (MAJUSCULES[i] == c) {
                int novaPos = xifrar ? (i + desplaçament) % MAJUSCULES.length
                                     : (i - desplaçament + MAJUSCULES.length) % MAJUSCULES.length;
                return MAJUSCULES[novaPos];
            }
        }
        return c;
    }
}
