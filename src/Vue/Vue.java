package Vue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.*;


public class Vue extends JFrame {
    JPanel mainPanel=new JPanel();
    private Utilisateur user=new Utilisateur();
     private Matrice m=new Matrice();
     private Container mainContainer=this.getContentPane();
   
     //Sequence
     private JLabel sq1=new JLabel("Sequence1: ");
     private JLabel sq2=new JLabel("Sequence2: ");
     private JTextField s1=new JTextField(10);
     private JTextField s2=new JTextField(10);
     private JButton enter=new JButton("entrer");
     private JLabel message=new JLabel("Saisissez toute les valeurs !");
     private JTextField ma=new JTextField(2);
     private JTextField mi=new JTextField(2);
     private JTextField g=new JTextField(2);
     private JLabel match=new JLabel("Match");
     private JLabel mismatch=new JLabel("Mismatch");
    private JLabel gap=new JLabel("Gap");
     private V v=new V();

    /*private JButton enter=new JButton("entrer");
    private JLabel match=new JLabel("Match");
    private JLabel mismatch=new JLabel("Mismatch");
    private JLabel gap=new JLabel("Gap");
    */
    JTextField seq1=new JTextField(20);
    JTextField seq2=new JTextField(20);
    JTextField MatchSc=new JTextField(9);
    JTextField MismatchSc=new JTextField(9);
    JTextField GapSc=new JTextField(9);
    JLabel seq1Label=new JLabel("Séquence 1: ");
    JLabel seq2Label=new JLabel("Séquence 2: ");
    JButton CustomPath=new JButton("Custom Path");
    JButton ClearPath=new JButton("Clear Path");
    JButton OptimisePath=new JButton("Compute Optimal Alignment");
    SpinnerModel modelA = new SpinnerNumberModel(
            1, //valeur initiale
            -100, //valeur minimum
            100, //valeur maximum
            1 //pas
    );
    SpinnerModel modelB = new SpinnerNumberModel(
            -1, //valeur initiale
            -100, //valeur minimum
            100, //valeur maximum
            1 //pas
    );
    SpinnerModel modelC = new SpinnerNumberModel(
            -2, //valeur initiale
            -100, //valeur minimum
            100, //valeur maximum
            1 //pas
    );

     public Vue(){
        this.setTitle("Global Sequence Alignement");
        this.pack();
        this.setDefaultLookAndFeelDecorated(true);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setResizable(false);
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
         this.mainPanel.setPreferredSize(new Dimension(400,200));
         this.add(mainPanel,BorderLayout.WEST);


         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Creer des espaces et ajout des couleurs de fonds , d'espace
        mainContainer.setLayout((new BorderLayout(8,6)));
        mainContainer.setBackground(Color.YELLOW);
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4,4,4, Color.GREEN));
         mainContainer.add(enter,BorderLayout.PAGE_END);
        // mainPanel.add(new JSeparator(SwingConstants.VERTICAL),BorderLayout.LINE_START);
       //  mainPanel.add(a,BorderLayout.AFTER_LINE_ENDS);
         mainContainer.add(mainPanel,BorderLayout.WEST);
         mainContainer.add(enter,BorderLayout.PAGE_END);



         //Initialisation du JPanel de gauche
       /* JPanel middlePanel=new JPanel();
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
        a.add(g);*/

        //Ajout dans la fenêtre 

      //Lambda expression 
      //Une fois qu'on aura appuyer sur le boutons entrer on vérifiera que les sequences sont bonnes
      //On verifiera si le gap, match et mismatch sont bien des nombres

      /*enter.addActionListener(new ActionListener() {
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
        mainContainer.add(middlePanel,BorderLayout.WEST);*/
    }


    public static void main(String [] args){
        Vue p=new Vue();
        p.setVisible(true);
    }
}
