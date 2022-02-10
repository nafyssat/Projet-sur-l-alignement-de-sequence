import java.util.ArrayList;
public class Sequence {
    private ArrayList<Nucleotide> sequence;




    public Sequence (){
        this.sequence = new ArrayList <>();
    }
    //ajouter une fonction qui retourne la taille d'une séquence

    /**
     * Ajoute une Nucléotide à la séquence.
     * @param n Nucléotide.
     */
    public void ajouterNucleotide(char n){
        //TODO
        this.sequence.add(Nucleotide.convertirEnNucleotide(n));
    }

    /**
     * Vérifie si la longueur de la séquence est inferieure ou égale à 20.
     * @return true si la séquence est valide sinon false.
     */
    public boolean sequenceValide(){
        return this.sequence.size()<=20;
    }


}
