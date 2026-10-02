import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Monoalfabetic {

    private static final char[] alfabetMajuscules = {
        'A','Á','À','B','C','Ç','D','E','É','È',
        'F','G','H','I','Í','Ì','Ï','J','K','L',
        'M','N','Ñ','O','Ó','Ò','P','Q','R','S',
        'T','U','Ú','Ù','Ü','V','W','X','Y','Z'
    };

    static char[] alfabetXifrat;

    
    public static char[] permutaAlfabet(char[] alfabet){
        List<Character> permutat = new ArrayList<>();
        for (char c : alfabet) {
            permutat.add(c);
        }

        Collections.shuffle(permutat);

        char[] arrayPermutat = new char[permutat.size()];
        for (int i = 0; i < permutat.size(); i++) {
            arrayPermutat[i] = permutat.get(i);
        }

        return arrayPermutat;
    }

    public static int findPos(char ch, char[] alfabet) {
        char chMajuscula = Character.toUpperCase(ch);
        for (int i = 0; i < alfabet.length; i++) {
            if (chMajuscula == alfabet[i]) return i;
        }
        return -1;
    }

    public static char transformaCaracter(char ch, char[] origen, char[] desti) {
        int pos = findPos(ch, origen);
        if (pos == -1) return ch;
        char substitut = desti[pos];
        return Character.isLowerCase(ch) ? Character.toLowerCase(substitut) : substitut;
    }

    private static String transforma(String cadena, char[] origen, char[] desti) {
        StringBuilder resultat = new StringBuilder();
        for (char ch : cadena.toCharArray()) {
            resultat.append(transformaCaracter(ch, origen, desti));
        }
        return resultat.toString();
    }

    public static String xifraMonoAlfa(String cadena) {
        return transforma(cadena, alfabetMajuscules, alfabetXifrat);
    }

    public static String desxifraMonoAlfa(String cadena) {
        return transforma(cadena, alfabetXifrat, alfabetMajuscules);
    }

    public static void main(String[] args) {
        alfabetXifrat = permutaAlfabet(alfabetMajuscules);

        System.out.println(alfabetMajuscules);
        System.out.println(alfabetXifrat);

        String[] tests = {
            "Test 01 àrbitre, coixí, Perímetre",
            "Test 02 Taüll, DÍA, año",
            "Test 03 Peça, Òrrius, Bòvila"
        };

        System.out.println("Xifratge:");
        String[] xifrats = new String[tests.length];
        for (int i = 0; i < tests.length; i++) {
            xifrats[i] = xifraMonoAlfa(tests[i]);
            System.out.println(tests[i] + " -> " + xifrats[i]);
        }

        System.out.println("Desxifratge:");
        for (String xifrat : xifrats) {
            System.out.println(xifrat + " -> " + desxifraMonoAlfa(xifrat));
        }
    }
}