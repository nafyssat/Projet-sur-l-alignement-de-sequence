package Vue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Locale;


import Model.*;
import Controler.*;

//Cette classe permet de modeliser l'affichage de la fenêtre sous forme de JButton[][]//

public class MatricePanel {
    private Utilisateur user = new Utilisateur();
    private MainWindowControler Controlleur = new MainWindowControler();
    private JButton [][] matrice;
    private JPanel droite=new JPanel();
    private String [] alignement=new String [3]; //Cette attribut est pour l'affichage de l'alignement
    private boolean custom;
   


    // Interface //

    /**
     * @param a sequence 1
     * @param b sequence 2
     * @param c mismatch
     * @param d match
     * @param e gap
     * @return un Jpanel qu'on va ajouter à notre fenêtre principal dans la classe Vue
     */

    public JPanel init(String a, String b, int c, int d, int e) { 
        JButton [][] res = new JButton[a.length() + 2][b.length() + 2];

        droite.setPreferredSize(new Dimension(20 * (a.length() + 2), 20 * (b.length() + 2)));

        droite.setLayout(new GridLayout(a.length() + 2, b.length() + 2));

        for (int i = 0; i < a.length() + 2; i++) {
            for (int j = 0; j < b.length() + 2; j++) {
                res[i][j] = new JButton("  ");
                res[i][j].setForeground(Color.BLACK);
                res[i][j].setBackground(Color.WHITE);
                //On initialise chaque JButton à " " pour éviter par la suite
                //d'avoir des problème de NullPointerException

            }
        }

        for (int i = 2; i < a.length() + 2; i++) {

            res[i][0].setText(" " + a.charAt(i - 2)); //ON complète la première colonne
            res[i][0].setForeground(Color.white);
            res[i][0].setBackground(Color.BLUE);


            res[i][0].setBackground(Color.BLUE);
            res[i][0].setText(" " + String.valueOf(a.charAt(i - 2)).toUpperCase()); //ON complète la première colonne


        }

        for (int i = 2; i < b.length() + 2; i++) {

            res[0][i].setText(" " + b.charAt(i - 2)); //On complète la première ligne
            res[0][i].setForeground(Color.white);
            res[0][i].setBackground(Color.BLUE);

            res[0][i].setBackground(Color.BLUE);
            res[0][i].setText(" " + String.valueOf(b.charAt(i - 2)).toUpperCase()); //On complète la première ligne


        }

        for (int i = 1; i < a.length() + 2; i++) {
            for (int j = 1; j < b.length() + 2; j++) {

                //Ici nous devons appeler cette méthode pour calculer les valeur de chaque cases de 
                //la matrice. 

                Case[][] g = this.Controlleur.getGrillePanel().initialiser_Grille_Interface(a, b, c, d, e);
                res[i][j].setText(" " + g[i][j].getValeur());

            }
        }
        for (int i = 2; i < a.length() + 2; i++) {
            for (int j = 2; j < b.length() + 2; j++) {


                //fenetre expliquant la calcul du score

                String[] s = this.Controlleur.getGrillePanel().affCalVal(i, j, c, d, e);
                JFrame fr = new JFrame();
                fr.setLayout(new FlowLayout(FlowLayout.CENTER));
                fr.setSize(new Dimension(360, 188));
                for (int v = 0; v < 4; v++) {
                    JPanel w = new JPanel();
                    w.setPreferredSize(new Dimension(170, 70));
                    w.setBackground(Color.BLUE);
                    JLabel l = new JLabel(s[v]);
                    l.setFont(new Font("Calibri", Font.BOLD, 10));
                    w.add(l, BorderLayout.CENTER);
                    fr.add(w, BorderLayout.CENTER);
                }
                fr.setVisible(false);
                res[i][j].addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        fr.setVisible(true);

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        fr.dispose();
                    }
                });


            }
        }
        for (int k = 1; k < a.length() + 2; k++) {
            res[k][1].setBackground(Color.GRAY);

        }
        for (int k = 1; k < b.length() + 2; k++) {
            res[1][k].setBackground(Color.GRAY);

        }


        res[a.length() + 1][b.length() + 1].setBackground(Color.red);
        res[1][1].setBackground(Color.red);


        //backTracking
        int f = a.length() + 1;
        int h = b.length() + 1;


        while (f >= 1 && h >= 1) {
            //on recupere la position du case a colorie


            res[f][h].setBackground(Color.red);
            int[] t = this.Controlleur.getGrillePanel().caseAColorie(f, h, c, d, e);
            f = t[0];
            h = t[1];
            if (f == 1 && h == 1) {
                res[f][h].setBackground(Color.red);
                break;
            }


        }


        // ajouter la table au frame

        for (int i = 0; i < a.length() + 2; i++) {
            for (int j = 0; j < b.length() + 2; j++) {
                if (i == 0 || j == 0) {
                    res[i][j].setFont(new Font("Italic", Font.BOLD, 13));
                } else {
                    res[i][j].setFont(new Font("Calibri", Font.BOLD, 8));
                }
                droite.add(res[i][j]);
            }
        }
        res[1][1].setBackground(Color.green);


        this.matrice=res;
        return droite;
    }


    public JPanel initClear(String a, String b, int c, int d, int e) {



        JPanel droite = new JPanel();
        JButton[][] res = new JButton[a.length() + 2][b.length() + 2];

        droite.setPreferredSize(new Dimension(20*(a.length()+2),20*(b.length()+2)));

        droite.setLayout(new GridLayout(a.length()+2,b.length()+2));

        for(int i=0;i<a.length()+2;i++){
            for(int j=0;j<b.length()+2;j++){
                res[i][j]=new JButton("  ");
                res[i][j].setForeground(Color.BLACK);
                res[i][j].setBackground(Color.WHITE);
                //On initialise chaque JButton à " " pour éviter par la suite
                //d'avoir des problème de NullPointerException

            }
        }

        for (int i = 2; i < a.length() + 2; i++) {

            res[i][0].setText(" "+a.charAt(i-2)); //ON complète la première colonne
            res[i][0].setForeground(Color.white);
            res[i][0].setBackground(Color.lightGray);


            res[i][0].setBackground(Color.lightGray);
            res[i][0].setText(" "+String.valueOf(a.charAt(i-2)).toUpperCase()); //ON complète la première colonne


        }

        for (int i = 2; i < b.length() + 2; i++) {

            res[0][i].setText(" "+b.charAt(i-2)); //On complète la première ligne
            res[0][i].setForeground(Color.white);
            res[0][i].setBackground(Color.lightGray);

            res[0][i].setBackground(Color.lightGray);
            res[0][i].setText(" "+String.valueOf(b.charAt(i-2)).toUpperCase()); //On complète la première ligne


        }

        for(int i=1;i<a.length()+2;i++){
            for(int j=1;j<b.length()+2;j++){

                //Ici nous devons appeler cette méthode pour calculer les valeur de chaque cases de
                //la matrice.

                Case[][] g=this.Controlleur.getGrillePanel().initialiser_Grille_Interface(a, b, c, d, e);
                res[i][j].setText(" "+g[i][j].getValeur());

            }}
        for(int i=2;i<a.length()+2;i++) {
            for(int j=2;j<b.length()+2;j++) {


                //fenetre expliquant la calcul du score

                String [] s =this.Controlleur.getGrillePanel().affCalVal(i,j,c,d,e);
                JFrame fr = new JFrame();
                fr.setLayout(new FlowLayout(FlowLayout.CENTER));
                fr.setSize(new Dimension(360,188));
                for(int v=0;v<4;v++){
                    JPanel w=new JPanel();
                    w.setPreferredSize(new Dimension(170,70));
                    w.setBackground(Color.lightGray);
                    JLabel l=new JLabel(s[v]);
                    l.setFont(new Font("Calibri", Font.BOLD,10));
                    w.add(l,BorderLayout.CENTER);
                    fr.add(w,BorderLayout.CENTER);
                }
                fr.setVisible(false);
                res[i][j].addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        fr.setVisible(true);

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        fr.dispose();
                    }
                });


            }
        }
        for(int k=1;k<a.length()+2;k++) {
            res[k][1].setBackground(Color.GRAY);

        }
        for(int k=1;k<b.length()+2;k++) {
            res[1][k].setBackground(Color.GRAY);

        }






        res[a.length()+1][b.length()+1].setBackground(Color.WHITE);
        res[1][1].setBackground(Color.WHITE);




        //backTracking
        int f=a.length()+1;
        int h=b.length()+1;


        while(f>=1 && h>=1) {
            //on recupere la position du case a colorie


            res[f][h].setBackground(Color.WHITE);
            int [] t  = this.Controlleur.getGrillePanel().caseAColorie(f, h, c, d, e);
            f=t[0];
            h=t[1];
            if(f==1 && h==1) {
                res[f][h].setBackground(Color.WHITE);
                break;
            }




        }


        // ajouter la table au frame

        for(int i=0;i<a.length()+2;i++) {
            for (int j = 0; j < b.length() + 2; j++) {
                if(i==0 || j==0){
                    res[i][j].setFont(new Font("Italic", Font.BOLD, 13));
                }else{
                    res[i][j].setFont(new Font("Calibri", Font.BOLD, 8));
                }
                droite.add(res[i][j]);
            }
        }
        res[1][1].setBackground(Color.white);





        return droite;
    }

    //Custom path



    /**
     *
     * @param m matrice
     * @param a sequence 1
     * @param b sequence 2
     * Cette fonction va remettre seulement les Jboutons de la matrices à la couleur blanches (seulement les JButtons
     * qui ont des entiers
     *
     */
    public void clearMatrice( String a, String b) {
        for(int i=2;i<a.length()+2;i++) {
            for(int j=2;j<b.length()+2;j++) {
                
                matrice[i][j].setBackground(Color.white);
            }
        }
    }
    /**
     *
     * @param m
     * @param i index de la matrice à la position i(ligne)
     * @param j index de la matrice à la position j (colonne)
     * Ici on veut vérifier si dans les Jbutton autours de celui de l'index i, j . On veut
     * vérifier s'il y'en a qu'ils sont de couleur jaunes, pour que le chemin puisse être cyclique.
     */
    public boolean calcule_adjacence( int i, int j ) {
        matrice[i][j].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (matrice[i-1][j].getBackground()==Color.yellow || matrice[i+1][j].getBackground()==Color.yellow
                 || matrice[i][j-1].getBackground()==Color.yellow || matrice[i][j+1].getBackground()==Color.yellow
                 || matrice[i][j+1].getBackground()==Color.yellow || matrice[i-1][j-1].getBackground()==Color.yellow
                 || matrice[i+1][j+1].getBackground()==Color.yellow ||  matrice[i-1][j+1].getBackground()==Color.yellow
                 || matrice[i+1][j-1].getBackground()==Color.yellow) {


                    matrice[i][j].setBackground(Color.yellow);
                    custom=true;
 

                }
                else
                {
                    JFrame fr = new JFrame();
                    fr.setLayout(new FlowLayout(FlowLayout.CENTER));
                    fr.setSize(new Dimension(300,120));
                    JPanel w=new JPanel();
                    w.setPreferredSize(new Dimension(300,120));
                    w.setBackground(Color.lightGray);
                    JLabel l=new JLabel("Ce bouton n'est pas valide ");
                    l.setFont(new Font("Calibri", Font.CENTER_BASELINE,15));
                    l.setForeground(Color.RED);
                    w.add(l,BorderLayout.CENTER);
                    fr.add(w,BorderLayout.CENTER);
                    
                 
                    fr.setVisible(true);
                    
                	custom=false;
                }
            }
        });
        return custom;

    }


    /**
     *
     * @param a sequence 1
     * @param b sequence 2
     * @param matrice
     * Nous allons choisir un chemin en partant de la fin.
     * C'est pourquoi nous avons mis directement la case matrice[a.length()+1][b.length()+1] à 
     * la couleur jaune, pour qu'on puisse suivre le chemin à partir de ce dernier.
     */
   public JPanel custom_path(String a, String b,  int ma, int mi, int gap){

       this.init(a, b, ma, mi, gap);

        clearMatrice(a,b);

        
        for(int i=2;i<a.length()+2;i++) {
            for(int j=2;j<b.length()+2;j++) {
                int v1=i;
                int v2=j;

                      
                        matrice[a.length()+1][b.length()+1].setBackground(Color.yellow);
                   

                        if(calcule_adjacence(v1, v2)==true){
                            droite.add(matrice[v1][v2]);
                        }
                   
            }
            
        }
        return droite;
        
    }

    /**
     * 
     * @param a sequence 1
     * @param b sequence 2
     * @return l'alignement
     * Nous refaisons la même méthode que celle dans dans la classe Matrice : afficher_alignement_Inter
     * qui calcule l'alignement. Cependant dans la méthode afficher_alignement_Inter , on calculer
     * l'alignement en fonction de la valeur de la matrice, donc avec un chemin déjà tracé. Mais 
     * dans cette méthode c'est différent car on choisit le chemin, donc au lieu de regarder les valeurs
     * de la matrice , on regarde les cases coloriés en jaune et on applique les règles de l'alignement de 
     * manière différentes par la suite.
     */

    public String [] getAlign_custom(String a, String b) {
        int i=a.length()+1;
        int j=b.length()+1;
        while(i>=2 && j>=2){
            Color x=this.matrice[i][j].getBackground();
            Color y=this.matrice[i-1][j-1].getBackground();
            Color z=this.matrice[i][j-1].getBackground();
            Color w=this.matrice[i-1][j].getBackground();
            
            if(x==Color.yellow && y==Color.yellow && z==Color.white && w==Color.white){
                   alignement[0]+=a.charAt((i));
                   alignement[1]+=b.charAt((j));
                i--;
                j--;
            }else if(x==Color.yellow && w==Color.yellow && z==Color.white && y==Color.white ){
                alignement[0]+=a.charAt((i));
                alignement[1]+="-";
                i--;
            }else if(x==Color.yellow && z==Color.yellow && y==Color.white && w==Color.white){
                alignement[0]+="-";
                alignement[1]+=b.charAt((j));
                j--;
            }
        }
        
        while(i>=2){
            alignement[0]+=a.charAt((i));
            alignement[1]+="-";
            i--;
        }
        while(j>=2){
            alignement[0]+="-";
            alignement[1]+=b.charAt((j));
            j--;
        }
      
        alignement[2]="Score: "+this.matrice[a.length()+1][b.length()+1];
       
        return this.alignement;
    }

}
