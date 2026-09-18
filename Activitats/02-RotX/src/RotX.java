public class RotX {
/*
    static char[] minuscules = {
        'a','á','à','b','c','ç','d','e','é','è',
        'f','g','h','i','í','ì','ï','j','k','l',
        'm','n','ñ','o','ó','ò','p','q','r','s',
        't','u','ú','ù','ü','v','w','x','y','z'
    };

    static char[] majuscules = {
        'A','Á','À','B','C','Ç','D','E','É','È',
        'F','G','H','I','Í','Ì','Ï','J','K','L',
        'M','N','Ñ','O','Ó','Ò','P','Q','R','S',
        'T','U','Ú','Ù','Ü','V','W','X','Y','Z'
    };

    public static boolean esMinuscula(char ch) {
        for (char chMin : minuscules) {
            if (ch == chMin) return true;
        }
        return false;
    }

    public static int findPos(char ch) {
        char chMin = Character.toLowerCase(ch);
        for (int i = 0; i < minuscules.length; i++) {
            if (chMin == minuscules[i]) return i;
        }
        return -1;
    }

    public static char xifraCaracterRot13(char ch) {
        int pos = findPos(ch);
        if (pos == -1) return ch;
        int novaPos = (pos + 13) % minuscules.length;
        return esMinuscula(ch) ? minuscules[novaPos] : majuscules[novaPos];
    }

    public static char desxifraCaracterRot13(char ch) {
        int pos = findPos(ch);
        if (pos == -1) return ch;
        int novaPos = ((pos - 13) + minuscules.length) % minuscules.length;
        return esMinuscula(ch) ? minuscules[novaPos] : majuscules[novaPos];
    }

    public static String xifraRot13(String cadena) {
        String resultat = "";
        for (int i = 0; i < cadena.length(); i++) {
            resultat += xifraCaracterRot13(cadena.charAt(i));
        }
        return resultat;
    }

    public static String desxifraRot13(String cadena) {
        String resultat = "";
        for (int i = 0; i < cadena.length(); i++) {
            resultat += desxifraCaracterRot13(cadena.charAt(i));
        }
        return resultat;
    }

    public static void main(String[] args) {
        String[] proves = {
            "ABC",
            "XYZ",
            "Hola, Mr. calçot",
            "Perdó, per tu què és?"
        };

        System.out.println("Xifrat");
        System.out.println("---------");
        for (String p : proves) {
            System.out.println(p + "\t\t=> " + xifraRot13(p));
        }

        System.out.println();
        System.out.println("Desxifrat");
        System.out.println("---------");
        for (String p : proves) {
            String xifrat = xifraRot13(p);
            System.out.println(xifrat + "\t=> " + desxifraRot13(xifrat));
        }
    }
*/
}