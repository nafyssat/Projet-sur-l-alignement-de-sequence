package Model;

public enum Nucleotide{
    //Nucleotides
    A,T,G,C;

    /**
     * convertie un caractere en Nucleotide
     * @param c est un caratere
     * @return une Nucleotide
     */
    public static Nucleotide convertirEnNucleotide(char c){
        //?? mieux vaut un switch ou des if ????
       /* if (c=='C') return C;
        if (c=='A') return A;
        if (c=='G') return G;
        if (c=='T') return T;
        return null;*/

        switch(c) {
           case 'C' : return C;
           case 'A' : return A;
           case 'T' : return T;
           case 'G' : return G;
           default : return null;
        }

    }

    public boolean nestpasNucleotide (char c){
        return (c=='B') || (c>'C' && c<'G' && c<'T') || c>'T';
    }



}