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


    public static void main(String [] args){
        Utilisateur u=new Utilisateur ();

        Sequence s = u.demanderSequence();
        System.out.println(s);

    }
}