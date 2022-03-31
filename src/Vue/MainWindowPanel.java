package Vue;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.*;
import Vue.MatricePanel;


public class MainWindowPanel extends JFrame {
    JPanel mainPanel=new JPanel();
    AlignementPanel align=new AlignementPanel();
    private Utilisateur user=new Utilisateur();
    JPanel alignement=new JPanel();
    private Container mainContainer=this.getContentPane();
    private MatricePanel v=new MatricePanel();
    private JLabel match=new JLabel("Match");
    private JLabel mismatch=new JLabel("Mismatch");
    private JLabel gap=new JLabel("Gap");
    private JPanel ajout = new JPanel();
    JTextField seq1=new JTextField(28);
    JTextField seq2=new JTextField(28);
    JLabel seq1Label=new JLabel("Sequence    1: ");
    JLabel seq2Label=new JLabel("Sequence    2: ");
    JButton CustomPath=new JButton("Custom Path");
    JButton ClearPath=new JButton("Clear Path");
    JButton OptimisePath=new JButton("Optimal Alignment");
    SpinnerModel modelA = new SpinnerNumberModel(
            1, //valeur initiale
            -500, //valeur minimum
            500, //valeur maximum
            1 //pas
    );
    SpinnerModel modelB = new SpinnerNumberModel(
            -1, //valeur initiale
            -500, //valeur minimum
            500, //valeur maximum
            1 //pas
    );
    SpinnerModel modelC = new SpinnerNumberModel(
            -2, //valeur initiale
            -500, //valeur minimum
            500, //valeur maximum
            1 //pas
    );

     public MainWindowPanel(){
         this.setTitle("Global Sequence Alignement");
         this.pack();
         this.setDefaultLookAndFeelDecorated(true);
         this.setExtendedState(this.MAXIMIZED_BOTH);
         this.setDefaultCloseOperation(EXIT_ON_CLOSE);
         JSpinner a = new JSpinner(modelA);
         JSpinner b= new JSpinner(modelB);
         JSpinner c = new JSpinner(modelC);
         a.setPreferredSize(new Dimension(138,18));//20
         b.setPreferredSize(new Dimension(138,18));
         c.setPreferredSize(new Dimension(138,18));
         match.setPreferredSize(new Dimension(138,15));//30//103
         mismatch.setPreferredSize(new Dimension(138,15));
         gap.setPreferredSize(new Dimension(138,15));
         CustomPath.setPreferredSize(new Dimension(125,20));//20/157
         ClearPath.setPreferredSize(new Dimension(118,20));//157
         OptimisePath.setPreferredSize(new Dimension(173,20));//320
         this.mainPanel.add(seq1Label);
         this.mainPanel.add(seq1);
         this.mainPanel.add(seq2Label);
         this.mainPanel.add(seq2);
         this.mainPanel.add(match);
         this.mainPanel.add(mismatch);
         this.mainPanel.add(gap);
         this.mainPanel.add(a);
         this.mainPanel.add(b);
         this.mainPanel.add(c);
         this.mainPanel.add(CustomPath);
         this.mainPanel.add(ClearPath);
         this.mainPanel.add(OptimisePath);
         this.mainPanel.setPreferredSize(new Dimension(500,130));
         this.mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
         this.add(mainPanel,BorderLayout.WEST);
         //this.mainPanel.setBorder(BorderFactory.createTitledBorder("Paramètres"));
         this.mainPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
         alignement.setBorder(BorderFactory.createTitledBorder("Alignement"));
         alignement.setPreferredSize(new Dimension(770,131));
         this.mainContainer.setLayout(new FlowLayout(FlowLayout.LEFT));
         this.mainContainer.add(mainPanel);
         this.mainContainer.add(alignement);
         this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4,4,4, Color.GREEN));

        OptimisePath.addActionListener(e -> {

             if (user.sequence(seq1.getText()) == true && seq1.getText().length() <= 20  //Ici je n'ai pas trouvé une méthode
                     && user.sequence(seq2.getText()) == true && seq2.getText().length() <= 20 //Pour appeler la méthode seqeunceValide()
             ) {
                 String s1=seq1.getText().toUpperCase();
                 String s2= seq2.getText().toUpperCase();
                 mainContainer.remove(ajout);
                 mainContainer.remove(alignement);
                 ajout.removeAll();
                 alignement.removeAll();
                 ajout=v.init(s1,s2, (int) a.getValue(), (int) b.getValue(), (int) c.getValue());
                 alignement=align.Align(s1,s2, (int) a.getValue(), (int) b.getValue(), (int) c.getValue());
                 alignement.setBorder(BorderFactory.createTitledBorder("Alignement"));
                 alignement.setPreferredSize(new Dimension(770,131));
                 ajout.setPreferredSize(new Dimension(58*(s2.length()+2),25*(s1.length()+2)));
                 mainContainer.add(alignement);
                 mainContainer.add(ajout);
                 mainContainer.revalidate();
                 this.pack();
             }
         });
    }
     

    public static void main(String [] args){
         MainWindowPanel p=new MainWindowPanel();
        p.setVisible(true);
    }
}