package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.*;


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
     private JLabel message=new JLabel("Saisissez toute les valeurs !");

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
        this.setSize(1400, 1800);
        this.setLocation(5,5);
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

        if(user.sequence(s2.getText())==true && s1.getText().length()<=20  //Ici je n'ai pas trouvé une méthode
          && user.sequence(s1.getText())==true && s2.getText().length()<=20 //Pour appeler la méthode seqeunceValide()
          ){

            Integer.parseInt(g.getText()); //On vérifie s'ils sont bien des Integer
            Integer.parseInt(ma.getText()); //Idem
            Integer.parseInt(mi.getText()); //Idem
            System.out.println("oui");
            JPanel ajout=v.init(s1.getText(),s2.getText(),ma.getText(),mi.getText(),g.getText()); //Dans ce cas on affiche la matrice
            ajout.setSize(1200,1200);
            ajout.setBackground(Color.CYAN); 
            getContentPane().add(ajout,BorderLayout.CENTER);
            setSize(1400,1800);

            setVisible(true);
          

            //Si les conditions sont bonnes on affichera la matrice à droite de notre fenêtre 

         
        } else {
                     System.out.println("non"); //Sinon on affiche un message d'erreur 
                 }
                }
      });

     
   
        //On verifiera si le gap le mismatch et le match sont bien des nombres 
       
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
