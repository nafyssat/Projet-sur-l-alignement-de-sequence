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
    private JButton [][] res;
    private JPanel droite=new JPanel();
    private String [] alignement=new String [3]; //Cette attribut est pour l'affichage de l'alignement
    int count=0;
     int px=-1;
     int py=-1;
     String seq1="";
     String seq2="";
     int Score=0;

    public MatricePanel(){

    }
   


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


       // this.matrice=res;
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

    public JPanel initCustom(String a, String b, int c, int d, int e){
        JPanel droite = new JPanel();
         res = new JButton[a.length() + 2][b.length() + 2];

        droite.setPreferredSize(new Dimension(20*(a.length()+2),20*(b.length()+2)));

        droite.setLayout(new GridLayout(a.length()+2,b.length()+2));

        for(int i=0;i<a.length()+2;i++){
            for(int j=0;j<b.length()+2;j++){
                res[i][j]=new JButton("  ");
                res[i][j].setForeground(Color.BLACK);
                res[i][j].setBackground(Color.white);
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


            }
        }
        for(int k=1;k<a.length()+2;k++) {
            res[k][1].setBackground(Color.GRAY);

        }
        for(int k=1;k<b.length()+2;k++) {
            res[1][k].setBackground(Color.GRAY);

        }






        res[a.length()+1][b.length()+1].setBackground(Color.white);
        res[1][1].setBackground(Color.white);




        //backTracking
        int f=a.length()+1;
        int h=b.length()+1;


        while(f>=1 && h>=1) {
            //on recupere la position du case a colorie


            res[f][h].setBackground(Color.white);
            int [] t  = this.Controlleur.getGrillePanel().caseAColorie(f, h, c, d, e);
            f=t[0];
            h=t[1];
            if(f==1 && h==1) {
                res[f][h].setBackground(Color.white);
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

    public boolean isValid(int i,int j,int c,int d,int e) {
        if(count==0 || (i==px-1 && j==py-1) || (i==px && j==py-1)
                || (i==px-1 && j==py)){
            if(this.px!=-1 && this.py!=-1){
                if(i==px-1 && j==py-1){
                    this.seq1=res[i+1][0].getText()+seq1;
                    this.seq2=res[0][j+1].getText()+seq2;
                    if(res[i+1][0].getText().equals(res[0][j+1].getText())){
                        this.Score+=c;
                    }else{
                        this.Score+=e;
                    }
                }
                else if(i==px-1 && j==py){
                    this.seq1=res[i+1][0].getText()+seq1;
                    this.seq2="- "+seq2;
                    this.Score+=e;
                }else{
                    this.seq1="- "+seq1;
                    this.seq2=res[0][j+1].getText()+seq2;
                    this.Score+=e;
                }
            }
            count++;
            this.px=i;
            this.py=j;

            return true;
        }
        return false;
    }

    public JButton[][]getMatrice(){return this.res;}


}
