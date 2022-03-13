package Model;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class Sequence {
    private ArrayList<Nucleotide> sequence;


    public Sequence (){
        this.sequence = new ArrayList <>();
    }

    
    public ArrayList<Nucleotide> getSequence (){
        return this.sequence;
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

    /**
     * 
     * @param s
     * @return true si s est une sequence false sinon, nécessaire pour l'interface graphique 
     * car en mode textuelle nous ne faisons pas ces vérifications.
     */
    public boolean NucleotideValide(String s){
        String res=s.toUpperCase();
        Nucleotide n=Nucleotide.A;
        boolean b=true;
          for(int i=0;i<s.length();i++){
        if(n.nestpasNucleotide(res.charAt(i))){
          
             b=false;
        }
        else 
        {
            b=true;
        }
        }
          return b;
     
}


}
