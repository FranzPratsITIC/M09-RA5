package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class XifradorPolialfabetic implements Xifrador {

    public static final char[] ALFABET = "AÀÁBCÇDEÈÉFGHIÌÍÏJKLMNÑOÒÓPQRSTUÙÚÜVWXYZ".toCharArray();
    private char[] alfabetPermutat;
    private Random rnd;

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        long llavor;
        try {
            llavor = Long.parseLong(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }
        initRandom(llavor);
        String resultat = xifraPoliAlfa(msg);
        return new TextXifrat(resultat.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        long llavor;
        try {
            llavor = Long.parseLong(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau de Polialfabètic ha de ser un String convertible a long");
        }
        if (xifrat == null) {
            return null;
        }
        initRandom(llavor);
        return desxifraPoliAlfa(xifrat.toString());
    }

    private void initRandom(long llavor) {
        this.rnd = new Random(llavor);
    }

    private void permutaAlfabet() {
        List<Character> llista = new ArrayList<>();
        for (char c : ALFABET) {
            llista.add(c);
        }
        Collections.shuffle(llista, this.rnd);
        this.alfabetPermutat = new char[ALFABET.length];
        for (int i = 0; i < llista.size(); i++) {
            this.alfabetPermutat[i] = llista.get(i);
        }
    }

    public String xifraPoliAlfa(String msg) {
        StringBuilder sb = new StringBuilder();
        for (char c : msg.toCharArray()) {
            permutaAlfabet();
            sb.append(substitueix(c, ALFABET, alfabetPermutat));
        }
        return sb.toString();
    }

    public String desxifraPoliAlfa(String msgXifrat) {
        StringBuilder sb = new StringBuilder();
        for (char c : msgXifrat.toCharArray()) {
            permutaAlfabet();
            sb.append(substitueix(c, alfabetPermutat, ALFABET));
        }
        return sb.toString();
    }

    private char substitueix(char c, char[] origen, char[] desti) {
        boolean esMinuscula = Character.isLowerCase(c);
        char cMaj = Character.toUpperCase(c);
        for (int i = 0; i < origen.length; i++) {
            if (origen[i] == cMaj) {
                char resultat = desti[i];
                return esMinuscula ? Character.toLowerCase(resultat) : resultat;
            }
        }
        return c;
    }
}
