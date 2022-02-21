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

     private JLabel match=new JLabel("Match");
     private JLabel mismatch=new JLabel("Mismatch");
     private JLabel gap=new JLabel(" Gap");
     private JTextField ma=new JTextField(2);
     private JTextField mi=new JTextField(2);
     private JTextField g=new JTextField(2);

     JButton [][] matrice=new JButton[4][4];



    public Vue(String s){
        super(s);
        this.setSize(1000, 700);
        this.setLocation(100,100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        mainContainer.setLayout((new BorderLayout(8,6))); //Ecart
        //mainContainer.setBackground(Color.YELLOW);
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4,4,4, Color.GREEN));
        
        

        JPanel middlePanel=new JPanel();
        middlePanel.setLayout(new FlowLayout(4,4,4));

        JPanel gridPanel=new JPanel();
        gridPanel.setLayout(new GridLayout(2,2)); //4,1,5,5

        gridPanel.add(sq1);
        gridPanel.add(s1);
        gridPanel.add(sq2);
        gridPanel.add(s2);
 

        JPanel a=new JPanel();
        a.setLayout((new GridLayout(2,3)));

       
        

        a.add(match);
        a.add(mismatch);
        a.add(gap);
        a.add(ma);
        a.add(mi);
        a.add(g);
      mainContainer.add(enter,BorderLayout.PAGE_END);

      enter.addActionListener((ActionEvent e) -> {

        if(user.sequence(s2.getText())==true && user.sequence(s1.getText())==true
          ){

            Integer.parseInt(g.getText());
            Integer.parseInt(this.ma.getText());
            Integer.parseInt(this.mi.getText());
            System.out.println("oui");
            matrice=new JButton[s1.getText().length()+2][s2.getText().length()+2];
            matrice(s1.getText(),s2.getText());
            this.hello();


                 } else {
                     System.out.println("non");
                 }
      });

     /* enter.addActionPerformed((ActionEvent e) ->{


      });
       
        */    
        
      
        
        s2.addActionListener((ActionEvent e) -> {
            if(s2.getText()!=null ){
                if(user.sequence(s2.getText())==true){
                    System.out.print("oui");
                }
                else
                {
                    System.out.print("noon");
                }

            }
        });


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

        middlePanel.add(gridPanel);
        middlePanel.add(new JSeparator(SwingConstants.VERTICAL),BorderLayout.LINE_START);
        middlePanel.add(a,BorderLayout.AFTER_LINE_ENDS);
        mainContainer.add(middlePanel,BorderLayout.WEST);
      

        //2 ème moitié du tableau//
        
    }

    
            public void init(String a,String b){
                for(int i=0;i<a.length()+2;i++){
                    for(int j=0;j<b.length()+2;j++){
                        this.matrice[i][j]=new JButton("0");

                    }
                }
            }
            public void hello(){
                System.out.println("helloooooo");
            }

            public void matrice(String a,String b){

               // JLabel droite=new JLabel();
                JPanel res=new JPanel();
                res.setVisible(true);
       
                for(int i=2;i<a.length()+2;i++){
                    for(int j=2;j<b.length()+2;j++){

                       this.init(a, b);
                        matrice[i][0].setText(" "+a.charAt(i-2));
                        matrice[0][j].setText(" "+b.charAt(j-2));
                        matrice[1][1].setText("0");
                        String s=g.getText();
                        matrice[2][1].setText(s);
                        matrice[1][2].setText(s);


                        matrice[i][j].setText(" "+this.ValeurCaseInterface(i, j));
                        
                        res.add(matrice[i][j]);

                        mainContainer.add(res,BorderLayout.CENTER);
                    }
                }
                
            }
             //PArtie interface//
        public int ValeurCaseInterface(int i,int j){
            int a=Integer.parseInt( this.matrice[i-1][j-1].getText());
            if(( this.matrice[0][j]).equals((this.matrice[i][0]))){
                a+=Integer.parseInt(ma.getText());
            }else{
                a+=Integer.parseInt(mi.getText());
            }
            int b=Integer.parseInt(this.matrice[i-1][j].getText())+Integer.parseInt(g.getText());
            int c=Integer.parseInt(this.matrice[i][j-1].getText())+Integer.parseInt(g.getText());

            return Math.max(Math.max(a,b),c);
        }


    public static void main(String [] args){
        Vue p=new Vue("ar1");
        p.setVisible(true);
    }
}
