import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Random;

public class Polialfabetic {

    private static final long clauSecreta = 12345;

    private static final char[] alfabetMajuscules = {
        'A','Á','À','B','C','Ç','D','E','É','È',
        'F','G','H','I','Í','Ì','Ï','J','K','L',
        'M','N','Ñ','O','Ó','Ò','P','Q','R','S',
        'T','U','Ú','Ù','Ü','V','W','X','Y','Z'
    };

    private static Random random;

    public static void initRandom(long clau) {
        random = new Random(clau);
    }

    private static char[] alfabetXifrat;

    public static void permutaAlfabet(){
        List<Character> permutat = new ArrayList<>();
        for (char c : alfabetMajuscules) {
            permutat.add(c);
        }

        Collections.shuffle(permutat, random);

        alfabetXifrat = new char[permutat.size()];
        for (int i = 0; i < permutat.size(); i++) {
            alfabetXifrat[i] = permutat.get(i);
        }
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

    public static String xifraPoliAlfa(String cadena) {
        StringBuilder resultat = new StringBuilder();
        for (char ch : cadena.toCharArray()) {
            permutaAlfabet();
            resultat.append(transformaCaracter(ch, alfabetMajuscules, alfabetXifrat));
        }
        return resultat.toString();
    }

    public static String desxifraPoliAlfa(String cadena) {
        StringBuilder resultat = new StringBuilder();
        for (char ch : cadena.toCharArray()) {
            permutaAlfabet();
            resultat.append(transformaCaracter(ch, alfabetXifrat, alfabetMajuscules));
        }
        return resultat.toString();
    }

    public static void main(String[] args) {
        String msgs[] = {"Test 01 àrbritre, coixí, Perímetre",
                        "Test 02 Taüll, DÍA, año",
                        "Test 03 Peça, Òrrius, Bòvila"};
        String msgsXifrats[] = new String[msgs.length];

        System.out.println("Xifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println("Desxifratge:\n----------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }
}