//package Vue;

//import Model.Utilisateur;
//package Model;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;




public class Vue extends JFrame {

     private Utilisateur user=new Utilisateur();
     private Matrice m=new Matrice();
     private Container mainContainer=this.getContentPane();
   
     //Sequence
     private JLabel sq1=new JLabel("Sequence1 :");
     private JLabel sq2=new JLabel("Sequence2 :");
     private JTextField s1=new JTextField(10);
     private JTextField s2=new JTextField(10);
     private JButton enter=new JButton("entrer");

     //Score 
     private JLabel match=new JLabel("Match");
     private JLabel mismatch=new JLabel("Mismatch");
     private JLabel gap=new JLabel(" Gap");
     private JTextField ma=new JTextField(2);
     private JTextField mi=new JTextField(2);
     private JTextField g=new JTextField(2);
     private V v=new V();

    public Vue(String s){
        super(s);
        this.setSize(1000, 700);
        this.setLocation(100,100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Creer des espaces et ajout des couleurs de fonds , d'espace
        mainContainer.setLayout((new BorderLayout(8,6))); 
        mainContainer.setBackground(Color.YELLOW);
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4,4,4, Color.GREEN));
        
        

        //Initialisation du JPanel de gauche 
        JPanel middlePanel=new JPanel();
        middlePanel.setLayout(new FlowLayout(4,4,4));

        JPanel gridPanel=new JPanel(); 
        gridPanel.setLayout(new GridLayout(2,2)); 


        //Ajout des JLabel et JTextFiel des sequences
        gridPanel.add(sq1); 
        gridPanel.add(s1);
        gridPanel.add(sq2);
        gridPanel.add(s2);
 

        //Initialisation du JPanel de gauche , à côté du JPanel middlePanel
        JPanel a=new JPanel();
        a.setLayout((new GridLayout(2,3)));

       
        
       //Ajout des JLabel et JTextFiel des sequences
        a.add(match);
        a.add(mismatch);
        a.add(gap);
        a.add(ma);
        a.add(mi);
        a.add(g);

        //Ajout dans la fenêtre 
      mainContainer.add(enter,BorderLayout.PAGE_END);

      //Lambda expression 
      //Une fois qu'on aura appuyer sur le boutons entrer on vérifiera que les sequences sont bonnes
      //On verifiera si le gap, match et mismatch sont bien des nombres

      enter.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){

        if(user.sequence(s2.getText())==true && user.sequence(s1.getText())==true
          ){

            Integer.parseInt(g.getText());
            Integer.parseInt(ma.getText());
            Integer.parseInt(mi.getText());
            System.out.println("oui");
            v.init(s1.getText(),s2.getText(),ma.getText(),mi.getText(),g.getText()); 
            //Si les conditions sont bonnes on affichera une nouvelle fenêtre.

         
        } else {
                     System.out.println("non"); //Sinon on affiche un message d'erreur 
                 }
                }
      });

     
      //Quand on appuyera sur entrer on verifiera que la chaîne de caractère est bien une séquence
        s2.addActionListener((ActionEvent e) -> { 
            if(s2.getText()!=null ){
                if(user.sequence(s2.getText())==true){
                    System.out.print("oui"); //On aura un message dans le terminal en fonction de la validité 
                }
                else
                {
                    System.out.print("noon");
                }

            }
        });

        //Idem
        s1.addActionListener((ActionEvent e) -> { 
            if(s1.getText()!=null ){
                if(user.sequence(s1.getText())==true){
                    System.out.print("oui");
                }
                else
                {
                    System.out.print("noon");
                }

            }
        });


        //On verifiera si le gap le mismatch et le match sont bien des nombres 
        g.addActionListener((ActionEvent e) ->  {
            try {
                Integer.parseInt(g.getText());
                System.out.println("An integer");
            }
            catch (NumberFormatException o) {
                System.out.println(" Not An integer");
            }
        });
        mi.addActionListener((ActionEvent e) ->  {
            try {
                Integer.parseInt(mi.getText());
                System.out.println("An integer");
            }
            catch (NumberFormatException o) {
                System.out.println(" Not An integer");
            }
        });
        ma.addActionListener((ActionEvent e) ->  {
            try {
                int p=Integer.parseInt(ma.getText());
                System.out.println(p);
            }
            catch (NumberFormatException o) {
                System.out.println(" Not An integer");
            }
        });

        //Ajout des scores dans la fenêtre 
        middlePanel.add(gridPanel);
        middlePanel.add(new JSeparator(SwingConstants.VERTICAL),BorderLayout.LINE_START);
        middlePanel.add(a,BorderLayout.AFTER_LINE_ENDS);
        mainContainer.add(middlePanel,BorderLayout.WEST);
      
    }


    public static void main(String [] args){
        Vue p=new Vue("ar1");
        p.setVisible(true);
    }
}
