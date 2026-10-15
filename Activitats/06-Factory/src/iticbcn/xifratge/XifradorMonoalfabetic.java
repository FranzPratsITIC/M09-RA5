package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XifradorMonoalfabetic implements Xifrador {

    public static final char[] ALFABET = "AÀÁBCÇDEÈÉFGHIÌÍÏJKLMNÑOÒÓPQRSTUÙÚÜVWXYZ".toCharArray();
    private char[] alfabetPermutat;

    public XifradorMonoalfabetic() {
        this.alfabetPermutat = permutaAlfabet(ALFABET);
    }

    private char[] permutaAlfabet(char[] alfabet) {
        List<Character> llista = new ArrayList<>();
        for (char c : alfabet) {
            llista.add(c);
        }
        Collections.shuffle(llista);
        char[] permutat = new char[alfabet.length];
        for (int i = 0; i < llista.size(); i++) {
            permutat[i] = llista.get(i);
        }
        return permutat;
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        String resultat = xifraMonoAlfa(msg);
        return new TextXifrat(resultat.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        if (xifrat == null) {
            return null;
        }
        return desxifraMonoAlfa(xifrat.toString());
    }

    public String xifraMonoAlfa(String cadena) {
        return transforma(cadena, ALFABET, alfabetPermutat);
    }

    public String desxifraMonoAlfa(String cadena) {
        return transforma(cadena, alfabetPermutat, ALFABET);
    }

    private String transforma(String cadena, char[] origen, char[] desti) {
        StringBuilder sb = new StringBuilder();
        for (char c : cadena.toCharArray()) {
            boolean esMinuscula = Character.isLowerCase(c);
            char cMaj = Character.toUpperCase(c);
            int pos = -1;
            for (int i = 0; i < origen.length; i++) {
                if (origen[i] == cMaj) {
                    pos = i;
                    break;
                }
            }
            if (pos != -1) {
                char substituir = desti[pos];
                sb.append(esMinuscula ? Character.toLowerCase(substituir) : substituir);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
