package Vue;

import Controler.MainWindowControler;
import Model.Case;
import Model.Utilisateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.Locale;
import java.util.Map;

public class AlignementPanel {
    private Utilisateur user=new Utilisateur();
    private HelpPanel help=new HelpPanel();
    private MainWindowControler controlleur=new MainWindowControler();

    //Partie custom path
    /*private JPanel alignement=new JPanel();

    public JPanel setAlignement(JPanel a){
        return this.alignement=a;
    }
    public JPanel getAlignement(){
        return this.alignement;
    }
    */

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
        AL.setLayout(new BorderLayout());
        JLabel h=new JLabel("For more details, click here!");
        Font font = h.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        h.setFont(font.deriveFont(attributes));
        h.setForeground(new Color(0,0,255));
        AL.add(h,BorderLayout.NORTH);
        h.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                help.setVisible(true);
            }
        });
        AL.add(labelArea,BorderLayout.WEST);

        //this.alignement=AL;
        return AL;




    }

    public JPanel clearpath() {
        AlignementPanel n = new AlignementPanel();
       JPanel p =  n.Align("","",0,0,0);
        return p;
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
