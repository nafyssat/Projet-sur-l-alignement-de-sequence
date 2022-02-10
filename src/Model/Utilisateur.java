import java.util.Scanner;

public class Utilisateur {
    //Scanners : Séquences, mismatch, match, pénalité
    Scanner sc=new Scanner(System.in);


    public Utilisateur(){

    }

    /**
     * Cette fonction va demander à l'utilisateur de saisir une séquence , si les
     * conditions de la séquences ont étés respectés , l'utilisateur devra saisir
     * sa seconde séquence
     * @return "finis" si toute les séquences ont étés saisit
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