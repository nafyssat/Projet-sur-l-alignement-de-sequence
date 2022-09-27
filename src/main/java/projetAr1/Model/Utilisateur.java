package projetAr1.Model;

import java.util.Scanner;

public class Utilisateur {
    private Scanner sc=new Scanner(System.in);//pour lire match, mismatch et gap
    public Utilisateur(){}


    //TODO: sequence 1
     public Sequence demanderSequence(int p){
        Sequence se =new Sequence();
        System.out.println("Saisissez la"+((p==1)?" 1ère ": " 2ème ")+ "séquence: ");
        String s=sc.nextLine().toUpperCase();
        if(se.NucleotideValide(s) && s.length()<=20) {
            for (int i = 0; i < s.length(); i++) {
                se.ajouterNucleotide(s.charAt(i));
            }
            return se;
        }
        else{
            System.out.println("Erreur: la sequence n'est pas valide.");
            System.out.println("La séquence ne doit contenir que A, C, G ou T et de longueur<=20");
            System.out.println("Veuillez réessayer:");
            return demanderSequence(p);
        }
    }

    //TODO: demander match
    public int demanderMatch(){
        System.out.print("Donner le match score: ");
        int n=sc.nextInt();
        return n;
    }

    //TODO: demander mismatch
    public int demanderMismatch(){
        System.out.print("Donner le mismatch score: ");
        int n=sc.nextInt();
        return n;
    }

    //TODO: demander Gap
    public int demanderGap(){
        System.out.print("Donner le gap score:");
        int n=sc.nextInt();
        return n;
    }

    //TODO: demander match,mismatch & gap
    public int[] demanderMismatch_Match_Gap(){
        int [] res=new int [3];
        res[0]=demanderMatch();
        res[1]=demanderMismatch();
        res[2]=demanderGap();
        return res;
    }
}
