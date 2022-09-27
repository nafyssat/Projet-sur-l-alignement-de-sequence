package projetAr1.Vue;

import projetAr1.Controler.MainWindowControler;
import projetAr1.Model.Case;
import projetAr1.Model.Utilisateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.Locale;
import java.util.Map;

public class AlignementPanel {
    private HelpPanel help;
    private MainWindowControler controlleur;

    public AlignementPanel(){
        this.help=new HelpPanel();
        this.controlleur=new MainWindowControler();
    }
   

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
        return AL;
    }

    public JPanel Align_custom(String a, String b,int c){
        String m="";
        String n="";
        for (int i=0;i<a.length();i++) {
            if (String.valueOf(a.charAt(i)).equals("-")) {
                m += " " + String.valueOf(a.charAt(i)).toUpperCase()+" ";
            } else {
                m += String.valueOf(a.charAt(i)).toUpperCase()+" ";
            }
        }
        for(int j=0;j<b.length();j++) {
            if (String.valueOf(b.charAt(j)).equals("-")) {
                n += " " + String.valueOf(b.charAt(j)).toUpperCase()+" ";
            } else {
                n += String.valueOf(b.charAt(j)).toUpperCase()+" ";
            }
        }
        JTextArea labelArea = new JTextArea("\n"+m+"\n"+n+"\n"+"\n"+"Score: "+c);
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
        return AL;
    }

    public JPanel clearpath() {
        JPanel p = Align("","",0,0,0);
        return p;
    }

}
