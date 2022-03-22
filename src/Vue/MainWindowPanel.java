package Vue;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.*;


public class MainWindowPanel extends JFrame {
    JPanel mainPanel=new JPanel();
    AlignementPanel align=new AlignementPanel();
    private Utilisateur user=new Utilisateur();
    private Container mainContainer=this.getContentPane();
    private MatricePanel v=new MatricePanel();
    //private JButton enter=new JButton("entrer");
    private JLabel match=new JLabel("Match");
    private JLabel mismatch=new JLabel("Mismatch");
    private JLabel gap=new JLabel("Gap");
    private JPanel ajout = new JPanel();
    JTextField seq1=new JTextField(20);
    JTextField seq2=new JTextField(20);
    JLabel seq1Label=new JLabel("Sequence    1: ");
    JLabel seq2Label=new JLabel("Sequence    2: ");
    JButton CustomPath=new JButton("Custom Path");
    JButton ClearPath=new JButton("Clear Path");
    JButton OptimisePath=new JButton("Compute Optimal Alignment");
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
         JPanel align=new JPanel();
         align.setBorder(BorderFactory.createTitledBorder("Alignement"));
         JSpinner a = new JSpinner(modelA);
         JSpinner b= new JSpinner(modelB);
         JSpinner c = new JSpinner(modelC);
         a.setPreferredSize(new Dimension(103,20));
         b.setPreferredSize(new Dimension(103,20));
         c.setPreferredSize(new Dimension(103,20));
         match.setPreferredSize(new Dimension(103,30));
         mismatch.setPreferredSize(new Dimension(103,30));
         gap.setPreferredSize(new Dimension(103,30));
         CustomPath.setPreferredSize(new Dimension(157,20));
         ClearPath.setPreferredSize(new Dimension(157,20));
         OptimisePath.setPreferredSize(new Dimension(320,20));
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
         this.mainPanel.setPreferredSize(new Dimension(400,130));
         this.add(mainPanel,BorderLayout.WEST);
         this.mainPanel.setBorder(BorderFactory.createTitledBorder("Paramètres"));
         JLabel al=new JLabel("Score=3");
         align.add(al);
         align.setPreferredSize(new Dimension(870,130));
         this.mainContainer.setLayout(new FlowLayout(FlowLayout.LEFT));
         this.mainContainer.add(mainPanel);
         this.mainContainer.add(align);
         //GridBagConstraints N=new GridBagConstraints();
         /*N.fill=GridBagConstraints.HORIZONTAL;
         N.gridx=0;
         N.gridy=0;*/
         //mainPanel.setBackground(Color.LIGHT_GRAY);
       //  mainContainer.add(mainPanel,N);
         this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4,4,4, Color.GREEN));
       /*N.weightx=1.0;
         N.gridx=1;
         N.gridy=0;
         mainContainer.add(align,N);
         N.weightx = 1;
         N.weighty = 1;*/

        /* mainContainer.add(new JLabel(" "), N);
         JPanel tr=new JPanel();
         N.gridx=1;
         N.gridy=0;
         tr.setPreferredSize(new Dimension(400,400));*/
       //  mainContainer.add(tr,N);
         JPanel n=v.init("ag","c",1,-10,-20);
         n.setPreferredSize(new Dimension(232,78));
         mainContainer.add(n);
         OptimisePath.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {

                 if (user.sequence(seq1.getText()) == true && seq1.getText().length() <= 20  //Ici je n'ai pas trouvé une méthode
                         && user.sequence(seq2.getText()) == true && seq2.getText().length() <= 20 //Pour appeler la méthode seqeunceValide()
                 ) {
                     String s1=seq1.getText().toUpperCase();
                     String s2= seq2.getText().toUpperCase();
                     ajout=v.init(s1,s2, (int) a.getValue(), (int) b.getValue(), (int) c.getValue());
                     /*ajout.setPreferredSize(new Dimension(s1.length()*20,s2.length()*20));
                     ajout.removeAll();
                     ajout.revalidate();
                     ajout.repaint();
                //     ajout.setLayout(new BorderLayout());
                     ajout.add(v.init(s1,s2, (int) a.getValue(), (int) b.getValue(), (int) c.getValue()));
                    // mainContainer.add(ajout);            //Dans ce cas on affiche la matrice
                     ajout.setVisible(true);*/
                     mainContainer.add(ajout);
                 }
             }
         });
         this.setTitle("Global Sequence Alignement");
        this.pack();
        this.setDefaultLookAndFeelDecorated(true);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        //this.setResizable(false);

       /* JSpinner a = new JSpinner(modelA);
         JSpinner b= new JSpinner(modelB);
         JSpinner c = new JSpinner(modelC);
         a.setPreferredSize(new Dimension(103,20));
         b.setPreferredSize(new Dimension(103,20));
         c.setPreferredSize(new Dimension(103,20));
         match.setPreferredSize(new Dimension(103,30));
         mismatch.setPreferredSize(new Dimension(103,30));
         gap.setPreferredSize(new Dimension(103,30));
         CustomPath.setPreferredSize(new Dimension(157,20));
         ClearPath.setPreferredSize(new Dimension(157,20));
         OptimisePath.setPreferredSize(new Dimension(320,20));
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
         this.mainPanel.setPreferredSize(new Dimension(400,200));
         this.add(mainPanel,BorderLayout.WEST);
         this.mainPanel.setBorder(BorderFactory.createTitledBorder("Paramètres"));
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         mainContainer.setLayout((new BorderLayout(8,6)));
         mainContainer.setBackground(Color.YELLOW);
         this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4,4,4, Color.GREEN));
         //mainContainer.add(OptimisePath,BorderLayout.PAGE_END);
         mainContainer.add(mainPanel,BorderLayout.WEST);
         //mainContainer.add(enter,BorderLayout.PAGE_END);
         

         getContentPane().add(ajout, BorderLayout.CENTER);
         ajout.setBackground(Color.CYAN);
         ajout.setVisible(false);



      //Lambda expression
         OptimisePath.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
                if (user.sequence(seq1.getText()) == true && seq1.getText().length() <= 20  //Ici je n'ai pas trouvé une méthode
                        && user.sequence(seq2.getText()) == true && seq2.getText().length() <= 20 //Pour appeler la méthode seqeunceValide()
                ) {

                	
                	String s1=seq1.getText().toUpperCase();
                	String s2= seq2.getText().toUpperCase();
                	//�a permet de tester plusieurs sequence sur le meme fenetre sans avoir a rexecuter
                	ajout.removeAll();
                	ajout.revalidate();
                	ajout.repaint();
                	ajout.setLayout(new BorderLayout());
                    ajout.add(v.init(s1,s2, (int) a.getValue(), (int) b.getValue(), (int) c.getValue()), BorderLayout.CENTER); //Dans ce cas on affiche la matrice
                    ajout.setVisible(true);

                    JPanel AlignPane=align.Align(seq1.getText(),seq2.getText(), (int) a.getValue(), (int) b.getValue(), (int) c.getValue());
                    getContentPane().add(AlignPane,BorderLayout.WEST);




                    /*JPanel ajout =v.init(seq1.getText(), seq2.getText(), (int) a.getValue(), (int) b.getValue(), (int) c.getValue());
                    ajout.setBackground(Color.CYAN);
                    getContentPane().add(ajout, BorderLayout.CENTER);
                    ajout.setPreferredSize(new Dimension(35 * (seq1.getText().length() + 2), 25 * (seq2.getText().length() + 2)));
                    setVisible(true);*/
/*
                }
            }
        });
        /**
         * le chemin
         */
      
            	
          

    }
     

    public static void main(String [] args){
    	 
          
        MainWindowPanel p=new MainWindowPanel();
        p.setVisible(true);
    }
}