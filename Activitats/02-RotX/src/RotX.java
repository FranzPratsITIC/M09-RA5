public class RotX {

    private static final char[] minuscules = {
        'a', 'à', 'á', 'b', 'c', 'ç', 'd', 'e', 'è', 'é', 'f', 'g', 'h',
        'i', 'ì', 'í', 'ï', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'ò', 'ó',
        'p', 'q', 'r', 's', 't', 'u', 'ù', 'ú', 'ü', 'v', 'w', 'x', 'y', 'z'
    };

    private static final char[] majuscules = {
        'A', 'À', 'Á', 'B', 'C', 'Ç', 'D', 'E', 'È', 'É', 'F', 'G', 'H',
        'I', 'Ì', 'Í', 'Ï', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ò', 'Ó',
        'P', 'Q', 'R', 'S', 'T', 'U', 'Ù', 'Ú', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
    };

    public static String xifraRotX(String cadena, int desplaçament) {
        int n = minuscules.length;
        int d = ((desplaçament % n) + n) % n;

        StringBuilder resultat = new StringBuilder();

        for (char c : cadena.toCharArray()) {
            int index = indexOf(minuscules, c);
            if (index != -1) {
                resultat.append(minuscules[(index + d) % n]);
                continue;
            }
            index = indexOf(majuscules, c);
            if (index != -1) {
                resultat.append(majuscules[(index + d) % n]);
                continue;
            }
            resultat.append(c);
        }

        return resultat.toString();
    }

    public static String desxifraRotX(String cadena, int desplaçament) {
        int n = minuscules.length;
        int d = ((desplaçament % n) + n) % n;
        return xifraRotX(cadena, n - d);
    }

    public static void forcaBrutaRotX(String cadenaXifrada) {
        int n = minuscules.length;
        System.out.println("Missatge xifrat: " + cadenaXifrada);
        System.out.println("----------------");
        for (int desplaçament = 1; desplaçament <= n; desplaçament++) {
            System.out.println("(" + desplaçament + ")->" + desxifraRotX(cadenaXifrada, desplaçament));
        }
    }

    private static int indexOf(char[] alfabet, char c) {
        for (int i = 0; i < alfabet.length; i++) {
            if (alfabet[i] == c) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] desplaçaments = {0, 2, 4, 6};
        String[] missatges = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per tu què és?"};

        System.out.println("Xifrat");
        System.out.println("------");
        for (String missatge : missatges) {
            for (int d : desplaçaments) {
                System.out.println("(" + d + ")-" + missatge + "\t=> " + xifraRotX(missatge, d));
            }
        }

        System.out.println();
        System.out.println("Desxifrat");
        System.out.println("---------");
        for (int d : desplaçaments) {
            String xifrat = xifraRotX("Perdó, per tu què és?", d);
            System.out.println("(" + d + ")" + xifrat + "\t=> " + desxifraRotX(xifrat, d));
        }

        System.out.println();
        forcaBrutaRotX(xifraRotX("Perdó, per tu què és?", 6));
    }
}
