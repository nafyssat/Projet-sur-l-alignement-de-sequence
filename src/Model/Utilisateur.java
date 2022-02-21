//package Model;

import java.util.Scanner;

public class Utilisateur {
    //Scanners : Sequences, mismatch, match, penalite
    Scanner sc=new Scanner(System.in);


    public Utilisateur(){

    }

    public boolean sequence(String s){
        Sequence se=new Sequence();
        boolean b=false;
        if(se.sequenceValide()==true && se.NucleotideValide(s)==true){
            b=true;
        }
        else
        {
            b=false;
        }
        return b;

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

    

    //math , mismatch , gap

    /**
     * Cette fonction demande le match de l'utilisateur
     * @return le match score sous forme de int
     */

    public int demanderMatch(){
        System.out.print("saisissez un match : ");
        int n=sc.nextInt();
        if(n<=10 && n>=-10){
            return n;
        }
        else 
        { 
        return demanderMatch();
        }
    }
    /**
     * Cette fonction demande le mismatch score de l'utilisateur
     * @return le mismatch score sous forme de int 
     */
    public int demanderMismatch(){
        System.out.print("saisissez un mismatch : ");
        int n=sc.nextInt();
        if(n<=10 && n>=-10){
            return n;
        }
        else 
        { 
        return demanderMismatch();
        }
    }
    /**
     * Cette fonction demande le gap score de l'utilisateur
     * @return le gap score sous forme de int 
     */
    public int demanderGap(){
        System.out.print("saisissez un gap : ");
        int n=sc.nextInt();
        if(n<=10 && n>=-10){
            return n;
        }
        else 
        { 
        return demanderGap();
        }
    }
/**
 * Cette fonction réunis les fonctions demanderMatch() , demanderMismatch(),
 * demanderGap() dans un tableau d'entier 
 * @return un tableau d'entier
 */
    public int[] demanderMismatch_Match_Gap(){
        int [] res=new int [3];
        res[0]=demanderMatch();
        res[1]=demanderMismatch();
        res[2]=demanderGap();
        return res;
    }


    

    public static void main(String [] args){
        Utilisateur u=new Utilisateur ();

       // Sequence s = u.demanderSequence();
        //System.out.println(s);
       int i=u.demanderMatch();
       System.out.println(i);
      System.out.println(u.demanderMismatch_Match_Gap()[0]);


    }
}
