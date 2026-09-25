import java.util.*;

public class Monoalfabetic {

    static char[] minuscules = {
        'a','á','à','b','c','ç','d','e','é','è',
        'f','g','h','i','í','ì','ï','j','k','l',
        'm','n','ñ','o','ó','ò','p','q','r','s',
        't','u','ú','ù','ü','v','w','x','y','z'
    };
    public static char[] alfabetXifrat;

    static char[] majuscules = {
        'A','Á','À','B','C','Ç','D','E','É','È',
        'F','G','H','I','Í','Ì','Ï','J','K','L',
        'M','N','Ñ','O','Ó','Ò','P','Q','R','S',
        'T','U','Ú','Ù','Ü','V','W','X','Y','Z'
    };

    public static char[] permutaAlfabet(char[] alfabet){
        char[] arrayPermutat = null;

        List<Character> permutat = new ArrayList<>();
        for (char c : alfabet) {
            permutat.add(c);
        }

        Collections.shuffle(permutat);

        for (int i = 0; i < permutat.size(); i++) {
            arrayPermutat[i] = permutat.get(i);
        }

        return arrayPermutat;
    }

    public static int findPos(char ch, boolean xifra) {
        char chMin = Character.toLowerCase(ch);
        if (xifra){
            for (int i = 0; i < minuscules.length; i++) {
                if (chMin == minuscules[i]) return i;
            }
            return -1;
        }else{
            for (int i = 0; i < minuscules.length; i++) {
                if (chMin == alfabetXifrat[i]) return i;
            }
            return -1;
        }
    }

    public static char xifraCaracter(char ch) {
        int pos = findPos(ch, true);
        return alfabetXifrat[pos];
    }

    public static char desxifraCaracter(char ch) {
        int pos = findPos(ch, false);
        return minuscules[pos];
    }

    public static String xifra(String cadena) {
        String resultat = "";
        for (int i = 0; i < cadena.length(); i++) {
            resultat += xifraCaracterRot13(cadena.charAt(i));
        }
        return resultat;
    }

    public static String desxifra(String cadena) {
        String resultat = "";
        for (int i = 0; i < cadena.length(); i++) {
            resultat += desxifraCaracterRot13(cadena.charAt(i));
        }
        return resultat;
    }

    public static void main(String[] args) {
        
    }
}