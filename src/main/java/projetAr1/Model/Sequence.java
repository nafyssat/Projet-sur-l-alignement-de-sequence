package projetAr1.Model;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class Sequence {
    private ArrayList<Nucleotide> sequence;

    public Sequence() {
        this.sequence = new ArrayList<>();
    }

    //TODO: getter
    public ArrayList<Nucleotide> getSequence() {
        return this.sequence;
    }

    //TODO: ajouter un char à la séquence de nucléotide
    public void ajouterNucleotide(char n) {
        this.sequence.add(Nucleotide.convertirEnNucleotide(n));
    }

    //TODO: vérifier que la chaine de caractère ne contient que A, C, G ou T
    public boolean NucleotideValide(String s) {
        String res = s.toUpperCase();
        Nucleotide n = Nucleotide.A;
        boolean b = true;
        for (int i = 0; i < s.length(); i++) {
            if (n.nestpasNucleotide(res.charAt(i))) {
                b = false;
            }
        }
        return b;
    }
}
