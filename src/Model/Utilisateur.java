import java.util.Scanner;

public class Utilisateur {
    //Scanners : Sequences, mismatch, match, penalite
    Scanner sc=new Scanner(System.in);


    public Utilisateur(){

    }

    /**
     * Cette fonction demande a l'utilisateur de saisir une sequence
     * @return Sequence
     */
    public Sequence demanderSequence(){
        Sequence se =new Sequence();
        System.out.println("saisissez une sequence : ");

        String s=sc.nextLine().toUpperCase();
        for(int i=0;i<s.length();i++){
            se.ajouterNucleotide(s.charAt(i));
        }

        if(!se.sequenceValide()){
            System.out.println("la sequence n'est pas valide");
            return demanderSequence();
        }

        return se;
    }


    public static void main(String [] args){
        Utilisateur u=new Utilisateur ();

        Sequence s = u.demanderSequence();
        System.out.println(s);

    }
}