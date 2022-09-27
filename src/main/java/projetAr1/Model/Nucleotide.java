package projetAr1.Model;

public enum Nucleotide {
    A, T, G, C;

    //TODO: convertir un char en nucléotide
    public static Nucleotide convertirEnNucleotide(char c) {
        switch (c) {
            case 'C':
                return C;
            case 'A':
                return A;
            case 'T':
                return T;
            case 'G':
                return G;
            default:
                return null;
        }
    }

    //TODO: convertir un nucléotide en string
    public static String NuctoString(Nucleotide n) {
        switch (n) {
            case C:
                return "C";
            case A:
                return "A";
            case T:
                return "T";
            case G:
                return "G";
            default:
                return null;
        }
    }

    //TODO: vérifier qu'un char est soit: A, C, T ou G
    public boolean nestpasNucleotide(char c) {
        return (c == 'B') || (c > 'C' && c < 'G') || (c > 'G' && c < 'T') || c > 'T';
    }
}