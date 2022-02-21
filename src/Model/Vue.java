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
   



    public Vue(String s){
        super(s);
        this.setSize(1000, 700);
        this.setLocation(100,100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container mainContainer=this.getContentPane();
        mainContainer.setLayout((new BorderLayout(8,6))); //Ecart
        mainContainer.setBackground(Color.YELLOW);
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4,4,4, Color.GREEN));



        
        //Sequence
        JLabel sq1=new JLabel("Sequence1 :");
        JLabel sq2=new JLabel("Sequence2 :");
        JTextField s1=new JTextField(10);
        JTextField s2=new JTextField(10);

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

        JLabel match=new JLabel("Match");
        JLabel mismatch=new JLabel("Mismatch");
        JLabel gap=new JLabel(" Gap");
        JTextField ma=new JTextField(2);
        JTextField mi=new JTextField(2);
        JTextField g=new JTextField(2);

        a.add(match);
        a.add(mismatch);
        a.add(gap);
        a.add(ma);
        a.add(mi);
        a.add(g);
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



            /*
            mainContainer.add(sq1,BorderLayout.WEST);
            mainContainer.add(sq2,BorderLayout.WEST);
            */

    }

    public static void main(String [] args){
        Vue p=new Vue("ar1");
        p.setVisible(true);
    }
}
