package projetAr1.Vue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;
import projetAr1.Model.*;
import projetAr1.Controler.*;

public class MatricePanel {
    //private Utilisateur user = new Utilisateur();
    private MainWindowControler Controlleur = new MainWindowControler();
    private JButton [][] res;//representant la matrice
    private int count=0; //pour custom path: pour vérifier que la c'est la 1ère case à choisir
    private int px=-1;// pour custom path: px et py pour les positions de la dernière case du chemin
    private int py=-1;
    private String seq1="";//pour custom path: l'alignement
    private String seq2="";
    private int Score=0;

    public MatricePanel(){

    }
    //TODO: fonctions qui initiliase la matrice
    public JPanel init(String a, String b, int c, int d, int e) {
        this.res = new JButton[a.length() + 2][b.length() + 2];
        JPanel droite=new JPanel();
        //droite.setPreferredSize(new Dimension(20 * (a.length() + 2), 20 * (b.length() + 2)));
        droite.setLayout(new GridLayout(a.length() + 2, b.length() + 2));
        //TODO: initiliaser le texte des Jbuttons pour éviter d'avoir NullPointerException par la suite
        for (int i = 0; i < a.length() + 2; i++) {
            for (int j = 0; j < b.length() + 2; j++) {
                res[i][j] = new JButton("  ");
                res[i][j].setForeground(Color.BLACK);
                res[i][j].setBackground(Color.WHITE);
            }
        }
        //TODO: remplir la 1ère colonne de la matrice
        for (int i = 2; i < a.length() + 2; i++) {
            res[i][0].setText(" " + a.charAt(i - 2));
            res[i][0].setForeground(Color.white);
            res[i][0].setBackground(Color.BLUE);
            res[i][0].setText(" " + String.valueOf(a.charAt(i - 2)).toUpperCase());
        }
        //TODO: remplir la 1ère ligne
        for (int i = 2; i < b.length() + 2; i++) {
            res[0][i].setText(" " + b.charAt(i - 2));
            res[0][i].setForeground(Color.white);
            res[0][i].setBackground(Color.BLUE);
            res[0][i].setText(" " + String.valueOf(b.charAt(i - 2)).toUpperCase());
        }
        //TODO: remplier le reste de la matrice
        for (int i = 1; i < a.length() + 2; i++) {
            for (int j = 1; j < b.length() + 2; j++) {
                Case[][] g = this.Controlleur.getGrillePanel().initialiser_Grille_Interface(a, b, c, d, e);
                res[i][j].setText(" " + g[i][j].getValeur());
            }
        }
        //TODO: fentere expliquant comment la valeur de chaque case a été calculé
        for (int i = 2; i < a.length() + 2; i++) {
            for (int j = 2; j < b.length() + 2; j++){
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
        //TODO: backtraking
        int f = a.length() + 1;
        int h = b.length() + 1;
        while (f >= 1 && h >= 1) {
            res[f][h].setBackground(Color.red);
            int[] t = this.Controlleur.getGrillePanel().caseAColorie(f, h, c, d, e);
            f = t[0];
            h = t[1];
            if (f == 1 && h == 1) {
                res[f][h].setBackground(Color.red);
                break;
            }
        }
        //TODO: ajouter la matrice au JPanel
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

    public JPanel initCustom(String a, String b, int c, int d, int e){
        JPanel droite = new JPanel();
        res = new JButton[a.length() + 2][b.length() + 2];
        droite.setLayout(new GridLayout(a.length()+2,b.length()+2));
        //TODO: initiliaser les JButtons
        for(int i=0;i<a.length()+2;i++){
            for(int j=0;j<b.length()+2;j++){
                res[i][j]=new JButton("  ");
            }
        }
        //TODO: remplir la 1ère colonne
        for (int i = 2; i < a.length() + 2; i++) {
            res[i][0].setText(" "+a.charAt(i-2));
            res[i][0].setText(" "+String.valueOf(a.charAt(i-2)).toUpperCase());
        }
        //TODO: remplirla 1ère ligne
        for (int i = 2; i < b.length() + 2; i++) {
            res[0][i].setText(" "+b.charAt(i-2));
            res[0][i].setText(" "+String.valueOf(b.charAt(i-2)).toUpperCase()); //On complète la première ligne
        }
        //TODO: remplir le reste de la matrice
        for(int i=1;i<a.length()+2;i++) {
            for (int j = 1; j < b.length() + 2; j++) {
                Case[][] g = this.Controlleur.getGrillePanel().initialiser_Grille_Interface(a, b, c, d, e);
                res[i][j].setText(" " + g[i][j].getValeur());
            }
        }
        //TODO ajouter la table au Jpanel
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
        //TODO: changer les couleurs
        for(int m=2;m<a.length()+2;m++){
            for(int n=2;n<b.length()+2;n++){
                res[m][1].setBackground(Color.LIGHT_GRAY);
                res[1][n].setBackground(Color.LIGHT_GRAY);
                res[0][n].setBackground(new Color(230, 252, 237));
                res[0][n].setForeground(new Color(203, 235, 247));
                res[m][0].setBackground(new Color(230, 252, 237));
                res[m][0].setForeground(new Color(203, 235, 247));
                res[m][n].setBackground(Color.WHITE);
                res[0][0].setBackground(Color.WHITE);
                res[1][0].setBackground(Color.WHITE);
                res[0][1].setBackground(Color.WHITE);
                res[1][1].setBackground(Color.LIGHT_GRAY);


            }
        }
        return droite;
    }

    //TODO: Vérifier si la case à selectionner pendant le chemin est valide
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

    //TODO: getters
    public JButton[][]getMatrice(){return this.res;}
    public int getCount(){return this.count;}
    public int getPx(){return this.px;}
    public int getPy(){return this.py;}
    public int getScore(){return this.Score;}
    public String getSeq1(){return this.seq1;}
    public String getSeq2(){return this.seq2;}
    //TODO: setters
    public void setCount(int i){this.count=i;}
    public void setPx(int i){this.px=i;}
    public void setPy(int i){this.py=i;}
    public void setScore(int i){this.Score=i;}
    public void setSeq1(String s){this.seq1=s;}
    public void setSeq2(String s){this.seq2=s;}

}
