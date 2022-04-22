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
    private MainWindowControler controler=new MainWindowControler();
    private Container mainContainer=this.getContentPane();


    private JPanel mainPanel=new JPanel();


    private JPanel alignement=new JPanel();
    private JPanel ajout = new JPanel();

     public MainWindowPanel(){
         this.setTitle("Global Sequence Alignement");
         this.pack();
         this.setDefaultLookAndFeelDecorated(true);
         this.setExtendedState(this.MAXIMIZED_BOTH);
         this.setDefaultCloseOperation(EXIT_ON_CLOSE);

         ImageIcon icon = new ImageIcon("/home/montassar/projet-ar1/src/Image/ADN.gif");
       /*  JOptionPane.showMessageDialog(
                 null,
                 "Welcome to Global Sequence Alignmenet Interface!",
                 "Hello", JOptionPane.INFORMATION_MESSAGE,
                 icon);*/

         Object[] choices = {"Let's Start"};
         Object defaultChoice = choices[0];
         JLabel welcome=new JLabel("Welcome to Global Sequence Alignmenet Interface!");
         welcome.setForeground(Color.BLACK);
         welcome.setFont(new Font("Arial",Font.BOLD,15));
         JOptionPane.showOptionDialog(this,
                 "",
                 "Welcome to Global Sequence Alignmenet Interface!",
                 JOptionPane.YES_OPTION,
                 JOptionPane.INFORMATION_MESSAGE,
                 icon,
                 choices,
                 defaultChoice);



         Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
         int largeur = (int)tailleEcran.getWidth();

         this.mainContainer.setLayout(new FlowLayout(FlowLayout.LEFT));
         this.mainPanel=Inputs.init();
         this.mainPanel.setPreferredSize(new Dimension(500, 130));
         this.mainPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
         this.alignement.setBorder(BorderFactory.createTitledBorder("Alignement"));
         this.alignement.setPreferredSize(new Dimension((largeur-596),131));

         this.mainContainer.add(mainPanel);
         this.mainContainer.add(alignement);
         this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4,4,4, Color.GREEN));


         //TODO: action listeners
         Inputs.getOptimisePath().addActionListener(e -> {
                    if (Inputs.getSeq1().getText().length() != 0 && Inputs.getSeq2().getText().length() != 0) {
                        String s1 = Inputs.getSeq1().getText().toUpperCase();
                        String s2 = Inputs.getSeq2().getText().toUpperCase();
                        mainContainer.remove(ajout);
                        mainContainer.remove(alignement);
                        ajout.removeAll();
                        alignement.removeAll();
                        ajout = v.init(s1, s2, (int) Inputs.getA().getValue(), (int) Inputs.getB().getValue(), (int) Inputs.getC().getValue());
                        alignement = align.Align(s1, s2, (int) Inputs.getA().getValue(), (int) Inputs.getB().getValue(), (int) Inputs.getC().getValue());
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

        Inputs.getClearPath().addActionListener(e -> {
            if (Inputs.getSeq1().getText().length() != 0 || Inputs.getSeq2().getText().length() != 0) {
                String s1 = Inputs.getSeq1().getText().toUpperCase();
                String s2 = Inputs.getSeq2().getText().toUpperCase();
                mainContainer.remove(ajout);
                mainContainer.remove(alignement);
                ajout.removeAll();
                alignement.removeAll();
                ajout = v.initClear(s1, s2, (int) Inputs.getA().getValue(), (int) Inputs.getB().getValue(), (int) Inputs.getC().getValue());
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

         /* Ici nous faisons une lambda expressions pour le boutons custom path
          */

       /*  CustomPath.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {


                 String s1=seq1.getText().toUpperCase();
                 String s2= seq2.getText().toUpperCase();

                 if(s1.length() > 0 && s2.length() > 0) {


                     alignement=align.getAlignement();

                     v.custom_path(s1, s2, v.getMatrice(),align , (int) a.getValue(), (int) b.getValue() , (int) c.getValue());


                 }
             }
         });
*/

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


         //TODO: add HelpPanel
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



     }
     

    public static void main(String [] args){
         MainWindowPanel p=new MainWindowPanel();
        p.setVisible(true);
    }
}