import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class V extends JFrame{
    private JButton [][] res=new JButton[5][5];
    private JTextField seq1=new JTextField(10);
    private JTextField seq2=new JTextField(10);
    private JButton enter=new JButton();
    private Utilisateur user;
    private Matrice matrice;



    public V(String s){
        super(s);
    this.setSize(1000, 700);
    this.setLocation(100,100);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




    }



    public boolean verifieSequence(){
       return true;
    }


    public void init(){
        JPanel o=new JPanel();
        String a="agcaa";
        String b="aaggc";
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                this.res[i][0]=new JButton(" "+a.charAt(i));
                this.res[0][j]=new JButton(" "+b.charAt(j));
                o.add(this.res[i][j]);
                this.getContentPane().add(o);
            }


            }
           
        }
        

    public static void main(String[] args){
        V v=new V("projet");
        v.setVisible(true);
    }


}
