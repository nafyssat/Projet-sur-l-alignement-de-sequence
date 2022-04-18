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
    private JButton compteur;
    JButton [][] matrice;


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
        JPanel droite = new JPanel();
        JButton[][] res = new JButton[a.length() + 2][b.length() + 2];

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

    public JButton [][] getMatrice(){
        return this.matrice;
    }

    /**
     *
     * @param m matrice
     * @param a sequence 1
     * @param b sequence 2
     * Cette fonction va remettre seulement les Jboutons de la matrices à la couleur blanches (seulement les JButtons
     * qui ont des entiers
     *
     */
    public void clearMatrice(JButton [][] m, String a, String b) {
        for(int i=1;i<a.length()+2;i++) {
            for(int j=1;j<b.length()+2;j++) {
                m[i][j].setSelected(false);
                m[i][j].setBackground(Color.white);
                m[i][j].setForeground(Color.orange);
            }
        }
    }
    /**
     *
     * @param m
     * @param i index de la matrice à la position i(ligne)
     * @param j index de la matrice à la position j (colonne)
     * Ici on veut vérifier si dans les Jbutton autours de celui de l'index i, j . On veut
     * vérifier s'il y'en a qu'ils sont selectionnés , pour que le chemin puisse être cyclique.
     * Cependant les fonctions isSelected() et doClick() veulent des valeurs final.
     */
    public void calcule_adjacence(JButton [][] m, int i, int j ) {
        m[i][j].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (m[i-1][j].isSelected() || m[i+1][j].isSelected() || m[i][j-1].isSelected()||
                        m[i][j+1].isSelected() || m[i-1][j-1].isSelected() || m[i+1][j+1].isSelected() ||
                        m[i-1][j+1].isSelected() || m[i+1][j-1].isSelected()) {


                    m[i][j].setForeground(Color.red);
                    m[i][j].setSelected(true);


                }
            }
        });

    }


    /**
     *
     * @param a sequence 1
     * @param b sequence 2
     * @param matrice
     * Nous allons choisir un chemin en partens de la fin.
     */
    public void custom_path(String a, String b,JButton [][] matrice){

        clearMatrice(matrice,a,b);

        for(int i=1;i<a.length()+2;i++) {
            for(int j=1;j<b.length()+2;j++) {
                int v1=i;
                int v2=j;


                matrice[i][j].addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {

                        compteur=matrice[v1][v2];

                        calcule_adjacence(matrice,a.length(),b.length());

                        matrice[a.length()+1][b.length()+1].setForeground(Color.red);
                        matrice[a.length()+1][b.length()+1].setSelected(true);

                        calcule_adjacence(matrice, v1,v2);

                        int p=0;
                        int s=0;

                    }

                });

            }
        }
    }

}
