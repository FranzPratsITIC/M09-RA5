import java.util.Random;

public class Factory {

    //-----------------------------------------------------//
//Codi Comu//
//-----------------------------------------------------//

private static final char[] ALFABET =
    "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();

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

public static String transforma(String cadena, char[] origen, char[] desti) {
    if (cadena == null) return null;

    StringBuilder resultat = new StringBuilder(cadena.length());
    for (char ch : cadena.toCharArray()) {
        resultat.append(transformaCaracter(ch, origen, desti));
    }
    return resultat.toString();
}

// Permutació aleatòria (Fisher-Yates). Mateixa clau => mateixa seqüència.
public static char[] permutaAlfabet(char[] alfabet, Random random) {
    char[] permutat = alfabet.clone();
    for (int i = permutat.length - 1; i > 0; i--) {
        int j = random.nextInt(i + 1);
        char temp = permutat[i];
        permutat[i] = permutat[j];
        permutat[j] = temp;
    }
    return permutat;
}

// Desplaça l'alfabet X posicions (admet valors negatius i més grans que n).
public static char[] rotaAlfabet(char[] alfabet, int desplacament) {
    int n = alfabet.length;
    char[] rotat = new char[n];
    for (int i = 0; i < n; i++) {
        rotat[i] = alfabet[Math.floorMod(i + desplacament, n)];
    }
    return rotat;
}

//-----------------------------------------------------//
//RotX//
//-----------------------------------------------------//

public static String xifraRotX(String cadena, int desplacament) {
    return transforma(cadena, ALFABET, rotaAlfabet(ALFABET, desplacament));
}

public static String desxifraRotX(String cadena, int desplacament) {
    return transforma(cadena, rotaAlfabet(ALFABET, desplacament), ALFABET);
}

//-----------------------------------------------------//
//MonoAlfabetic//
//-----------------------------------------------------//

private static final char[] alfabetXifrat = permutaAlfabet(ALFABET, new Random());

public static String xifraMonoAlfa(String cadena) {
    return transforma(cadena, ALFABET, alfabetXifrat);
}

public static String desxifraMonoAlfa(String cadena) {
    return transforma(cadena, alfabetXifrat, ALFABET);
}

//-----------------------------------------------------//
//PoliAlfabetic//
//-----------------------------------------------------//

private static final long CLAU_SECRETA = 12345;

private static String transformaPoli(String cadena, boolean xifra) {
    if (cadena == null) return null;

    Random random = new Random(CLAU_SECRETA); // sempre la mateixa seqüència
    StringBuilder resultat = new StringBuilder(cadena.length());

    for (char ch : cadena.toCharArray()) {
        char[] permutat = permutaAlfabet(ALFABET, random);
        resultat.append(xifra
            ? transformaCaracter(ch, ALFABET, permutat)
            : transformaCaracter(ch, permutat, ALFABET));
    }
    return resultat.toString();
}

public static String xifraPoliAlfa(String cadena) {
    return transformaPoli(cadena, true);
}

public static String desxifraPoliAlfa(String cadena) {
    return transformaPoli(cadena, false);
}


    
}
