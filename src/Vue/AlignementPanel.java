package Vue;

import Controler.MainWindowControler;
import Model.Case;
import Model.Utilisateur;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

public class AlignementPanel {
    private Utilisateur user=new Utilisateur();
    private MainWindowControler controlleur=new MainWindowControler();

    public JPanel Align(String a, String b, int c, int d, int e){
        Case[][] g=this.controlleur.getGrillePanel().initialiser_Grille_Interface(a, b, c, d, e);
        String[]x=controlleur.getGrillePanel().afficher_alignement_Inter(a,b,c,d,e);
        String m="";
        String n="";
        for (int i=x[0].length()-1;i>=0;i--) {
            if (String.valueOf(x[0].charAt(i)).equals("-")) {
                m += " " + String.valueOf(x[0].charAt(i)).toUpperCase()+" ";
            } else {
                m += String.valueOf(x[0].charAt(i)).toUpperCase()+" ";
            }
        }
        for(int j=x[1].length()-1;j>=0;j--) {
            if (String.valueOf(x[1].charAt(j)).equals("-")) {
                n += " " + String.valueOf(x[1].charAt(j)).toUpperCase()+" ";
            } else {
                n += String.valueOf(x[1].charAt(j)).toUpperCase()+" ";
            }
        }
        JTextArea labelArea = new JTextArea("\n"+m+"\n"+n+"\n"+"\n"+x[2]);
        labelArea.setFont(labelArea.getFont().deriveFont(Font.BOLD));

        labelArea.setEditable(false);
        labelArea.setOpaque(false);
        JPanel AL=new JPanel();
        /*JLabel one=new JLabel(m+"<html></br></html>");
        JLabel two=new JLabel(n);
        AL.add(one);
        AL.add(two);*/
        AL.setLayout(new BorderLayout());
        AL.add(labelArea,BorderLayout.WEST);
        return AL;


    }
    public static void main(String[]args){
        AlignementPanel vv=new AlignementPanel();
        JPanel n=vv.Align("agt","gttc",1,1,-2);
        n.setPreferredSize(new Dimension(200,300));
        JFrame f=new JFrame();
        f.add(n);
        f.setSize(200,200);
        f.setVisible(true);

    }
}
