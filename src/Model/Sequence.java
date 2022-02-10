package Model;

import java.util.ArrayList;

public class Sequence {
    private ArrayList<Nucleotide> sequence;


    public Sequence (){
        this.sequence = new ArrayList <>();
    }

    /**
     * Ajoute une Nucleotide a la sequence.
     * @param n Nucleotide.
     */
    public void ajouterNucleotide(char n){
        //TODO

        this.sequence.add(Nucleotide.convertirEnNucleotide(n));

    }

    /**
     * Vérifie si la longueur de la sequence est inferieure ou egale a 20.
     * @return true si la sequence est valide sinon false.
     */
    public boolean sequenceValide(){
        return this.sequence.size()<=20;
    }


}
