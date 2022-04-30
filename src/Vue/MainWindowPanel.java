package Vue;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.Map;
import Model.*;;
import Controler.*;

public class MainWindowPanel extends JFrame {
    private InputsPanel Inputs=new InputsPanel();
    private HelpPanel help=new HelpPanel();
    private MatricePanel v=new MatricePanel();
    private AlignementPanel align=new AlignementPanel();
    private Utilisateur user=new Utilisateur();
     JPanel alignement=new JPanel();
    private Container mainContainer=this.getContentPane();


    private JPanel mainPanel=new JPanel();


    private JPanel alignement=new JPanel();
    private JPanel ajout = new JPanel();
    private JTextField seq1=new JTextField(29);
    private JTextField seq2=new JTextField(29);
    private JLabel seq1Label=new JLabel("Sequence    1: ");
    private JLabel seq2Label=new JLabel("Sequence    2: ");
    private JButton CustomPath=new JButton("Custom Path");
    private JButton CustomAlign=new JButton("Custom Alignment");
    private JButton ClearPath=new JButton("Clear Path");
    private JButton OptimisePath=new JButton("Optimal Alignment");
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

     public MainWindowPanel() {
         this.setTitle("Global Sequence Alignement");
         this.pack();
         this.setDefaultLookAndFeelDecorated(true);
         this.setExtendedState(this.MAXIMIZED_BOTH);
         this.setDefaultCloseOperation(EXIT_ON_CLOSE);
         JSpinner a = new JSpinner(modelA);
         JSpinner b = new JSpinner(modelB);
         JSpinner c = new JSpinner(modelC);
         a.setPreferredSize(new Dimension(138, 18));//20
         b.setPreferredSize(new Dimension(138, 18));
         c.setPreferredSize(new Dimension(138, 18));
         match.setPreferredSize(new Dimension(138, 15));//30//103
         mismatch.setPreferredSize(new Dimension(138, 15));
         gap.setPreferredSize(new Dimension(138, 15));
         CustomPath.setPreferredSize(new Dimension(125, 20));//20/157
         ClearPath.setPreferredSize(new Dimension(118, 20));//157
         OptimisePath.setPreferredSize(new Dimension(173, 20));//320
         Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
         int hauteur = (int) tailleEcran.getHeight();
         int largeur = (int) tailleEcran.getWidth();
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
         //this.mainPanel.add(CustomAlign);
         this.mainPanel.add(ClearPath);
         this.mainPanel.add(OptimisePath);
         this.mainPanel.setPreferredSize(new Dimension(500, 130));
         this.mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
         this.add(mainPanel, BorderLayout.WEST);
         //this.mainPanel.setBorder(BorderFactory.createTitledBorder("Paramètres"));
         this.mainPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
         alignement.setBorder(BorderFactory.createTitledBorder("Alignement"));
         alignement.setPreferredSize(new Dimension((largeur - 596), 131));
         this.mainContainer.setLayout(new FlowLayout(FlowLayout.LEFT));
         this.mainPanel=Inputs.init();
         this.mainPanel.setPreferredSize(new Dimension(500, 130));
         this.mainPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
         this.alignement.setBorder(BorderFactory.createTitledBorder("Alignement"));
         this.alignement.setPreferredSize(new Dimension((largeur-596),131));

         this.mainContainer.add(mainPanel);
         this.mainContainer.add(alignement);
         this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GREEN));

         OptimisePath.addActionListener(e -> {
             if (seq1.getText().length() != 0 && seq2.getText().length() != 0) {
                 String s1 = seq1.getText().toUpperCase();
                 String s2 = seq2.getText().toUpperCase();
                 mainContainer.remove(ajout);
                 mainContainer.remove(alignement);
                 ajout.removeAll();
                 alignement.removeAll();
                 ajout = v.init(s1, s2, (int) a.getValue(), (int) b.getValue(), (int) c.getValue());
                 alignement = align.Align(s1, s2, (int) a.getValue(), (int) b.getValue(), (int) c.getValue());
                 alignement.setBorder(BorderFactory.createTitledBorder("Alignement"));
                 alignement.setPreferredSize(new Dimension((largeur - 596), 131));
                 ajout.setPreferredSize(new Dimension(58 * (s2.length() + 2), 25 * (s1.length() + 2)));
                 mainContainer.add(alignement);
                 mainContainer.add(ajout);
                 mainContainer.revalidate();
                 ajout.revalidate();
                 ajout.repaint();
             }
         });

         ClearPath.addActionListener(e -> {
             if (seq1.getText().length() != 0 || seq2.getText().length() != 0) {
                 String s1 = seq1.getText().toUpperCase();
                 String s2 = seq2.getText().toUpperCase();
                 mainContainer.remove(ajout);
                 mainContainer.remove(alignement);
                 ajout.removeAll();
                 alignement.removeAll();
                 ajout = v.initClear(s1, s2, (int) a.getValue(), (int) b.getValue(), (int) c.getValue());
                 alignement = align.clearpath();
                 alignement.setBorder(BorderFactory.createTitledBorder("Alignement"));
                 alignement.setPreferredSize(new Dimension((largeur - 596), 131));
                 ajout.setPreferredSize(new Dimension(58 * (s2.length() + 2), 25 * (s1.length() + 2)));
                 mainContainer.add(alignement);
                 mainContainer.add(ajout);
                 mainContainer.revalidate();
                 ajout.revalidate();
                 ajout.repaint();
             }
         });


         CustomPath.addActionListener(new ActionListener() {
                                          public void actionPerformed(ActionEvent e) {
                                              v.count=0;
                                              v.seq1="";
                                              v.seq2="";
                                              v.px=-1;
                                              v.py=-1;
                                              v.Score=0;
                                              String s1 = seq1.getText().toUpperCase();
                                              String s2 = seq2.getText().toUpperCase();

                                              if (s1.length() > 0 && s2.length() > 0) {
                                                  mainContainer.remove(ajout);
                                                  mainContainer.remove(alignement);
                                                  ajout.removeAll();
                                                  alignement.removeAll();
                                                  ajout = v.initCustom(s1, s2, (int) a.getValue(), (int) b.getValue(), (int) c.getValue());
                                                  alignement = align.clearpath();
                                                  alignement.setBorder(BorderFactory.createTitledBorder("Alignement"));
                                                  alignement.setPreferredSize(new Dimension((largeur - 596), 131));
                                                  ajout.setPreferredSize(new Dimension(58 * (s2.length() + 2), 25 * (s1.length() + 2)));
                                                  mainContainer.add(alignement);
                                                  mainContainer.add(ajout);
                                                  mainContainer.revalidate();
                                                  ajout.revalidate();
                                                  ajout.repaint();
                                                  for(int i=1;i<s1.length()+2;i++) {
                                                      for (int j = 1; j < s2.length() + 2; j++) {
                                                          int finalI = i;
                                                          int finalJ = j;
                                                          if (s1.length() > 0 && s2.length() > 0) {
                                                              v.getMatrice()[i][j].addMouseListener(new MouseAdapter() {

                                                                  @Override
                                                                  public void mouseClicked(MouseEvent x) {
                                                                      if (v.isValid(finalI, finalJ, (int) a.getValue(), (int) b.getValue(), (int) c.getValue())) {
                                                                          v.getMatrice()[finalI][finalJ].setBackground(Color.red);
                                                                          mainContainer.remove(ajout);
                                                                          mainContainer.remove(alignement);
                                                                          alignement.removeAll();
                                                                       //   ajout = v.initCustom(s1, s2, (int) a.getValue(), (int) b.getValue(), (int) c.getValue());
                                                                          alignement = align.Align_custom(v.seq1,v.seq2,v.Score);
                                                                          alignement.setBorder(BorderFactory.createTitledBorder("Alignement"));
                                                                          alignement.setPreferredSize(new Dimension((largeur - 596), 131));
                                                                          ajout.setPreferredSize(new Dimension(58 * (s2.length() + 2), 25 * (s1.length() + 2)));
                                                                          mainContainer.add(alignement);
                                                                          mainContainer.add(ajout);
                                                                          mainContainer.revalidate();
                                                                          ajout.revalidate();
                                                                          ajout.repaint();
                                                                      }

                                                                  }
                                                              });
                                                          }
                                                      }
                                                  }
                                                 /* alignement.setBorder(BorderFactory.createTitledBorder("Alignement"));
                                                  alignement.setPreferredSize(new Dimension((largeur - 596), 131));
                                                  ajout.setPreferredSize(new Dimension(58 * (s2.length() + 2), 25 * (s1.length() + 2)));
                                                  mainContainer.add(alignement);
                                                  mainContainer.add(ajout);
                                                  mainContainer.revalidate();
                                                  ajout.revalidate();
                                                  ajout.repaint();*/
                                              }
                                          }
                                      });




         Inputs.getSeq1().addKeyListener(new KeyAdapter() {
             @Override
             public void keyReleased(KeyEvent e) {
                 Inputs.getSeq1().setText( Inputs.getSeq1().getText().toUpperCase());
                 if (!controler.getsequence().NucleotideValide( Inputs.getSeq1().getText()) || !controler.getsequence().NucleotideValide( Inputs.getSeq2().getText())) {
                     JOptionPane.showMessageDialog(mainContainer, "Error: Allowed charcaters are: A, C, T or G.", "Message", JOptionPane.ERROR_MESSAGE);
                     Inputs.getSeq1().setText( Inputs.getSeq1().getText().substring(0, Inputs.getSeq1().getText().length()-1));
                 } else {
                     if ( Inputs.getSeq1().getText().length() == 0 &&  Inputs.getSeq2().getText().length() == 0) {
                         alignement.removeAll();
                         JLabel h=new JLabel("For more details, click here!");
                         alignement.setLayout(new BorderLayout());
                         Font font = h.getFont();
                         Map attributes = font.getAttributes();
                         attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                         h.setFont(font.deriveFont(attributes));
                         h.setForeground(new Color(0,0,255));

                         alignement.add(h,BorderLayout.NORTH);
                         h.addMouseListener(new MouseAdapter() {
                             @Override
                             public void mouseClicked(MouseEvent e) {
                                 help.setVisible(true);
                             }
                         });
                         mainContainer.remove(ajout);
                         pack();
                     } else if ( Inputs.getSeq1().getText().length() > 20 ||  Inputs.getSeq2().getText().length() > 20) {
                         JOptionPane.showMessageDialog(mainContainer, "Error: Maximum length of sequence is 20 charcaters.", "Message", JOptionPane.ERROR_MESSAGE);
                         if ( Inputs.getSeq1().getText().length() > 20) {
                             Inputs.getSeq1().setText( Inputs.getSeq1().getText().substring(0, 20));
                         }
                         if ( Inputs.getSeq2().getText().length() > 20) {
                             Inputs.getSeq2().setText( Inputs.getSeq2().getText().substring(0, 20));
                         }
                     } else {
                         String s1 =  Inputs.getSeq1().getText().toUpperCase();
                         String s2 =  Inputs.getSeq2().getText().toUpperCase();
                         mainContainer.remove(ajout);
                         mainContainer.remove(alignement);
                         ajout.removeAll();
                         alignement.removeAll();
                         ajout = v.init(s1, s2, (int)  Inputs.getA().getValue(), (int)  Inputs.getB().getValue(), (int) Inputs.getC().getValue());
                         alignement = align.Align(s1, s2, (int) Inputs.getA().getValue(), (int) Inputs.getB().getValue(), (int) Inputs.getC().getValue());
                         alignement.setBorder(BorderFactory.createTitledBorder("Alignement"));
                         alignement.setPreferredSize(new Dimension((largeur-596), 131));
                         ajout.setPreferredSize(new Dimension(58 * (s2.length() + 2), 25 * (s1.length() + 2)));
                         mainContainer.add(alignement);
                         mainContainer.add(ajout);
                         mainContainer.revalidate();
                         ajout.revalidate();
                         ajout.repaint();
                     }
                 }
             }

            // }
         });

         Inputs.getSeq2().addKeyListener(new KeyAdapter() {
             @Override
             public void keyReleased(KeyEvent e) {
                 Inputs.getSeq1().setText( Inputs.getSeq1().getText().toUpperCase());
                 if (!controler.getsequence().NucleotideValide( Inputs.getSeq1().getText()) || !controler.getsequence().NucleotideValide( Inputs.getSeq2().getText())) {
                     JOptionPane.showMessageDialog(mainContainer, "Error: Allowed charcaters are: A, C, T or G.", "Message", JOptionPane.ERROR_MESSAGE);
                     Inputs.getSeq1().setText( Inputs.getSeq1().getText().substring(0, Inputs.getSeq1().getText().length()-1));
                 } else {
                     if ( Inputs.getSeq1().getText().length() == 0 &&  Inputs.getSeq2().getText().length() == 0) {
                         alignement.removeAll();
                         JLabel h=new JLabel("For more details, click here!");
                         alignement.setLayout(new BorderLayout());
                         Font font = h.getFont();
                         Map attributes = font.getAttributes();
                         attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                         h.setFont(font.deriveFont(attributes));
                         h.setForeground(new Color(0,0,255));

                         alignement.add(h,BorderLayout.NORTH);
                         h.addMouseListener(new MouseAdapter() {
                             @Override
                             public void mouseClicked(MouseEvent e) {
                                 help.setVisible(true);
                             }
                         });
                         mainContainer.remove(ajout);
                         pack();
                     } else if ( Inputs.getSeq1().getText().length() > 20 ||  Inputs.getSeq2().getText().length() > 20) {
                         JOptionPane.showMessageDialog(mainContainer, "Error: Maximum length of sequence is 20 charcaters.", "Message", JOptionPane.ERROR_MESSAGE);
                         if ( Inputs.getSeq1().getText().length() > 20) {
                             Inputs.getSeq1().setText( Inputs.getSeq1().getText().substring(0, 20));
                         }
                         if ( Inputs.getSeq2().getText().length() > 20) {
                             Inputs.getSeq2().setText( Inputs.getSeq2().getText().substring(0, 20));
                         }
                     } else {
                         String s1 =  Inputs.getSeq1().getText().toUpperCase();
                         String s2 =  Inputs.getSeq2().getText().toUpperCase();
                         mainContainer.remove(ajout);
                         mainContainer.remove(alignement);
                         ajout.removeAll();
                         alignement.removeAll();
                         ajout = v.init(s1, s2, (int)  Inputs.getA().getValue(), (int)  Inputs.getB().getValue(), (int) Inputs.getC().getValue());
                         alignement = align.Align(s1, s2, (int) Inputs.getA().getValue(), (int) Inputs.getB().getValue(), (int) Inputs.getC().getValue());
                         alignement.setBorder(BorderFactory.createTitledBorder("Alignement"));
                         alignement.setPreferredSize(new Dimension((largeur-596), 131));
                         ajout.setPreferredSize(new Dimension(58 * (s2.length() + 2), 25 * (s1.length() + 2)));
                         mainContainer.add(alignement);
                         mainContainer.add(ajout);
                         mainContainer.revalidate();
                         ajout.revalidate();
                         ajout.repaint();
                     }
                 }
             }

             // }
         });

         Inputs.getA().addChangeListener(new ChangeListener() {
             @Override
             public void stateChanged(ChangeEvent e) {
                 if (Inputs.getSeq1().getText().length() <= 20  //Ici je n'ai pas trouvé une méthode
                         && Inputs.getSeq2().getText().length() <= 20 //Pour appeler la méthode seqeunceValide()
                 ) {
                     String s1 = Inputs.getSeq1().getText().toUpperCase();
                     String s2 = Inputs.getSeq2().getText().toUpperCase();
                     mainContainer.remove(ajout);
                     mainContainer.remove(alignement);
                     ajout.removeAll();
                     alignement.removeAll();
                     ajout = v.init(s1, s2, (int) Inputs.getA().getValue(), (int) Inputs.getB().getValue(), (int) Inputs.getC().getValue());
                     alignement = align.Align(s1, s2, (int) Inputs.getA().getValue(), (int) Inputs.getB().getValue(), (int) Inputs.getC().getValue());
                     alignement.setBorder(BorderFactory.createTitledBorder("Alignement"));
                     alignement.setPreferredSize(new Dimension((largeur-596), 131));
                     ajout.setPreferredSize(new Dimension(58 * (s2.length() + 2), 25 * (s1.length() + 2)));
                     mainContainer.add(alignement);
                     mainContainer.add(ajout);
                     mainContainer.revalidate();
                     ajout.revalidate();
                 }
             }
         });


         Inputs.getB().addChangeListener(new ChangeListener() {
             @Override
             public void stateChanged(ChangeEvent e) {
                 if (Inputs.getSeq1().getText().length() <= 20  //Ici je n'ai pas trouvé une méthode
                         && Inputs.getSeq2().getText().length() <= 20 //Pour appeler la méthode seqeunceValide()
                 ) {
                     String s1 = Inputs.getSeq1().getText().toUpperCase();
                     String s2 = Inputs.getSeq2().getText().toUpperCase();
                     mainContainer.remove(ajout);
                     mainContainer.remove(alignement);
                     ajout.removeAll();
                     alignement.removeAll();
                     ajout = v.init(s1, s2, (int) Inputs.getA().getValue(), (int) Inputs.getB().getValue(), (int) Inputs.getC().getValue());
                     alignement = align.Align(s1, s2, (int) Inputs.getA().getValue(), (int) Inputs.getB().getValue(), (int) Inputs.getC().getValue());
                     alignement.setBorder(BorderFactory.createTitledBorder("Alignement"));
                     alignement.setPreferredSize(new Dimension((largeur-596), 131));
                     ajout.setPreferredSize(new Dimension(58 * (s2.length() + 2), 25 * (s1.length() + 2)));
                     mainContainer.add(alignement);
                     mainContainer.add(ajout);
                     mainContainer.revalidate();
                     ajout.revalidate();
                 }
             }
         });



         Inputs.getC().addChangeListener(new ChangeListener() {
             @Override
             public void stateChanged(ChangeEvent e) {
                 if (Inputs.getSeq1().getText().length() <= 20  //Ici je n'ai pas trouvé une méthode
                         && Inputs.getSeq2().getText().length() <= 20 //Pour appeler la méthode seqeunceValide()
                 ) {
                     String s1 = Inputs.getSeq1().getText().toUpperCase();
                     String s2 = Inputs.getSeq2().getText().toUpperCase();
                     mainContainer.remove(ajout);
                     mainContainer.remove(alignement);
                     ajout.removeAll();
                     alignement.removeAll();
                     ajout = v.init(s1, s2, (int) Inputs.getA().getValue(), (int) Inputs.getB().getValue(), (int) Inputs.getC().getValue());
                     alignement = align.Align(s1, s2, (int) Inputs.getA().getValue(), (int) Inputs.getB().getValue(), (int) Inputs.getC().getValue());
                     alignement.setBorder(BorderFactory.createTitledBorder("Alignement"));
                     alignement.setPreferredSize(new Dimension((largeur-596), 131));
                     ajout.setPreferredSize(new Dimension(58 * (s2.length() + 2), 25 * (s1.length() + 2)));
                     mainContainer.add(alignement);
                     mainContainer.add(ajout);
                     mainContainer.revalidate();
                     ajout.revalidate();
                 }
             }
         });

    }

    public Container getMainContainer(){return this.mainContainer;}





    public static void main(String [] args) {
         MainWindowPanel p=new MainWindowPanel();
        p.setVisible(true);

    }

}
