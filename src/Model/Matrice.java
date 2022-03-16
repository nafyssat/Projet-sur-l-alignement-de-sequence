package Model;

import java.util.Scanner;
import static Model.Nucleotide.NuctoString;

public class Matrice {

        private Utilisateur user;
        private Case[][] grille;
        private Sequence a;
        private Sequence b;
        private int[] scores;

        public Matrice(){
            this.user=new Utilisateur();
        }


    public Case[][] initialiser_Grille_Interface(String s1, String s2, int match, int mismatch, int gap) {
        this.grille = new Case[s1.length() + 2][s2.length() + 2];

        for (int a = 2; a < s1.length() + 2; a++) {
            this.grille[a][0] = new CaseNucleotide(Nucleotide.convertirEnNucleotide(s1.charAt(a - 2)));
        }

        for (int b = 2; b < s2.length() + 2; b++) {
            this.grille[0][b] = new CaseNucleotide(Nucleotide.convertirEnNucleotide(s2.charAt(b - 2)));
        }
        this.grille[1][1] = new CaseEntier(0);
        for (int c = 1; c < s1.length() + 2; c++) {
            this.grille[c][1] = new CaseEntier((c - 1) * gap);
        }
        for (int d = 1; d < s2.length() + 2; d++) {
            this.grille[1][d] = new CaseEntier((d - 1) * gap);
        }
        for (int n = 2; n < s2.length() + 2; n++) {
            for (int m = 2; m < s1.length() + 2; m++) {
                this.grille[m][n] = new CaseEntier(ValeurCase_Interface(m, n, match, mismatch, gap));
            }
        }
        return this.grille;
    }

    public int ValeurCase_Interface(int i, int j, int match, int mismatch, int gap) {
        int a = (this.grille[i - 1][j - 1]).getValeur();
        if ((this.grille[0][j]).getNuc() == (this.grille[i][0]).getNuc()) {
            a += match;
        } else {
            a += mismatch;
        }
        int b = (this.grille[i - 1][j]).getValeur() + gap;
        int c = (this.grille[i][j - 1]).getValeur() + gap;

        return Math.max(Math.max(a, b), c);
    }

        public void initialiser_Grille(){
            this.a=user.demanderSequence();
            this.b=user.demanderSequence();
            this.scores=user.demanderMismatch_Match_Gap();
            this.grille=new Case[a.getSequence().size()+2][b.getSequence().size()+2];

            for(int a=2;a<this.a.getSequence().size()+2;a++){
                this.grille[a][0]=new CaseNucleotide(this.a.getSequence().get(a-2));
            }
            for(int b=2;b<this.b.getSequence().size()+2;b++){
                this.grille[0][b]=new CaseNucleotide(this.b.getSequence().get(b-2));
            }
            this.grille[1][1]=new CaseEntier(0);
            for(int c=1;c<this.a.getSequence().size()+2;c++){
                this.grille[c][1] = new CaseEntier((c-1)* this.scores[2]);
            }
            for(int d=1;d<this.b.getSequence().size()+2;d++){
                this.grille[1][d]=new CaseEntier((d-1)*this.scores[2]);
            }
            for(int n=2;n<this.b.getSequence().size()+2;n++){
                for(int m=2;m<this.a.getSequence().size()+2;m++){
                    this.grille[m][n]=new CaseEntier(ValeurCase(m,n));
                }
            }

        }

        public int ValeurCase(int i,int j){
            int a=( this.grille[i-1][j-1]).getValeur();
            if(( this.grille[0][j]).getNuc().equals((this.grille[i][0]).getNuc())){
                a+=this.scores[0];
            }else{
                a+=this.scores[1];
            }
            int b=(this.grille[i-1][j]).getValeur()+this.scores[2];
            int c=(this.grille[i][j-1]).getValeur()+this.scores[2];

            return Math.max(Math.max(a,b),c);
        }

        public void afficherMatrice(){
            int i=0;
            while(i<this.b.getSequence().size()+2){
                for(int j=0;j<this.a.getSequence().size()+2;j++) {
                    if (this.grille[j][i] == null) {
                        System.out.print("  |");
                    } else {
                        if (this.grille[j][i] instanceof CaseNucleotide) {
                            System.out.print(" "+( this.grille[j][i]).getNuc() + " | ");
                        } else {
                            if (( this.grille[j][i]).getValeur() >= 0) {
                                System.out.print(" "+( this.grille[j][i]).getValeur() + " |");
                            } else {
                                System.out.print(( this.grille[j][i]).getValeur() + " |");
                            }
                        }
                    }
                }
                i++;
                System.out.println();
            }
        }

        public void afficher_Info(){
            System.out.println("**********************************************");
            System.out.print("Match score: "+this.scores[0]+"    ");
            System.out.print("Mismatch score: "+this.scores[1]+"    ");
            System.out.println("Gap score: "+this.scores[2]);
            System.out.println();
            afficher_alignement();
            System.out.println();
            System.out.println("**********************************************");
        }

        public void afficher_alignement(){
            String m="";
            String n="";
            int i=this.a.getSequence().size()+1;
            int j=this.b.getSequence().size()+1;
            while(i>=2 && j>=2){
                int x=(this.grille[i][j]).getValeur();
                int y=(this.grille[i-1][j-1]).getValeur();
                int z=(this.grille[i][j-1]).getValeur();
                int w=(this.grille[i-1][j]).getValeur();
                if(x==y+ matchOrMistach(this.a.getSequence().get(i-2),this.b.getSequence().get(j-2))){
                    m+=NuctoString(this.a.getSequence().get(i-2));
                    n+=NuctoString(this.b.getSequence().get(j-2));
                    i--;
                    j--;
                }else if(x==w+this.scores[2]){
                    m+=NuctoString(this.a.getSequence().get(i-2));
                    n+="-";
                    i--;
                }else if(x==z+this.scores[2]){
                    m+="-";
                    n+=NuctoString(this.b.getSequence().get(j-2));
                    j--;
                }
            }
            while(i>=2){
                m+=NuctoString(this.a.getSequence().get(i-2));
                n+="-";
                i--;
            }
            while(j>=2){
                m+="-";
                n+=NuctoString(this.b.getSequence().get(j-2));
                j--;
            }
            for(int k=m.length()-1;k>=0;k--){
                System.out.print(m.charAt(k)+" ");
            }
            System.out.println();
            for(int l=n.length()-1;l>=0;l--){
                System.out.print(n.charAt(l)+" ");
            }
        }

        public int matchOrMistach(Nucleotide a,Nucleotide b){
            return a.equals(b)? this.scores[0]: this.scores[1];
        }

    public void DemanderAction(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Veuillez choisir la modification à faire");
        System.out.println("Taper 1,2,3,4 ou 5");
        System.out.println("1-modifier la première séquence");
        System.out.println("2- modifier la deuxième séquence");
        System.out.println("3- modifier le Gap score");
        System.out.println("4- modifier le Match score");
        System.out.println("5- modifier le Mistach score");
        int r=0;
        try{
            r=sc.nextInt();
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        switch (r){
            case 1: ModifierSeq(1); break;
            case 2: ModifierSeq(2); break;
            case 3: ModifGap(); break;
            case 4: ModifMatch(); break;
            case 5: ModifMismatch(); break;
            default: throw new Error("Invalid number");
        }
    }

    public void launcher(){
        initialiser_Grille();
        afficher_Info();
        afficherMatrice();
        DemanderAction();
    }

    public void ModifierSeq(int n){
        Scanner sc=new Scanner(System.in);
        Sequence se=new Sequence();
        System.out.println("Saisissez la nouvelle séquence:");
        String s=sc.nextLine().toUpperCase();
        for(int i=0;i<s.length();i++){
            se.ajouterNucleotide(s.charAt(i));
        }
        if(!se.sequenceValide()){
            System.out.println("La séquence n'est pas valide");
            System.out.println("Voulez-vous réessayer [O/N]?");
            String r=sc.next();
            if(r.equals("O")){
                ModifierSeq(n);
            }
        }else {
            if (n == 1) {
                this.a = se;
            } else {
                this.b = se;
            }
        }
        launcher();
    }
    public void ModifGap() {
        this.scores[2] = this.user.demanderGap();
        launcher();
    }
    public void ModifMatch(){
        this.scores[0]=this.user.demanderMatch();
        launcher();
    }

    public void ModifMismatch() {
        this.scores[1] = this.user.demanderMismatch();
        launcher();
    }



    public static void main(String[]args){
        Matrice m=new Matrice();
        m.launcher();

    }

    }