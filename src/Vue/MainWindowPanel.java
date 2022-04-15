package Vue;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.Locale;
import java.util.Map;

import Model.*;
import Vue.MatricePanel;
import Controler.*;

public class MainWindowPanel extends JFrame {
    private MainWindowControler controler=new MainWindowControler();
    private HelpPanel help=new HelpPanel();
    private JPanel mainPanel=new JPanel();
    private AlignementPanel align=new AlignementPanel();
    private Utilisateur user=new Utilisateur();
    private JPanel alignement=new JPanel();
    private Container mainContainer=this.getContentPane();
    private MatricePanel v=new MatricePanel();
    private JLabel match=new JLabel("Match");
    private JLabel mismatch=new JLabel("Mismatch");
    private JLabel gap=new JLabel("Gap");
    private JPanel ajout = new JPanel();
    private JTextField seq1=new JTextField(29);
    private JTextField seq2=new JTextField(29);
    private JLabel seq1Label=new JLabel("Sequence    1: ");
    private JLabel seq2Label=new JLabel("Sequence    2: ");
    private JButton CustomPath=new JButton("Custom Path");
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
         Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
         int hauteur = (int)tailleEcran.getHeight();
         int largeur = (int)tailleEcran.getWidth();
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
         alignement.setPreferredSize(new Dimension((largeur-596),131));
         this.mainContainer.setLayout(new FlowLayout(FlowLayout.LEFT));
         this.mainContainer.add(mainPanel);
         this.mainContainer.add(alignement);
         this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4,4,4, Color.GREEN));

       /* OptimisePath.addActionListener(e -> {

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
         });*/
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



         seq1.addKeyListener(new KeyAdapter() {
             @Override
             public void keyReleased(KeyEvent e) {
                 seq1.setText(seq1.getText().toUpperCase());
                 if (!controler.getsequence().NucleotideValide(seq1.getText()) || !controler.getsequence().NucleotideValide(seq2.getText())) {
                     JOptionPane.showMessageDialog(mainContainer, "Error: Allowed charcaters are: A, C, T or G.", "Message", JOptionPane.ERROR_MESSAGE);
                     seq1.setText(seq1.getText().substring(0,seq1.getText().length()-1));
                 } else {
                     if (seq1.getText().length() == 0 && seq2.getText().length() == 0) {
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
                     } else if (seq1.getText().length() > 20 || seq2.getText().length() > 20) {
                         JOptionPane.showMessageDialog(mainContainer, "Error: Maximum length of sequence is 20 charcaters.", "Message", JOptionPane.ERROR_MESSAGE);
                         if (seq1.getText().length() > 20) {
                             seq1.setText(seq1.getText().substring(0, 20));
                         }
                         if (seq2.getText().length() > 20) {
                             seq2.setText(seq2.getText().substring(0, 20));
                         }
                     } else {
                         String s1 = seq1.getText().toUpperCase();
                         String s2 = seq2.getText().toUpperCase();
                         mainContainer.remove(ajout);
                         mainContainer.remove(alignement);
                         ajout.removeAll();
                         alignement.removeAll();
                         ajout = v.init(s1, s2, (int) a.getValue(), (int) b.getValue(), (int) c.getValue());
                         alignement = align.Align(s1, s2, (int) a.getValue(), (int) b.getValue(), (int) c.getValue());
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
         seq2.addKeyListener(new KeyAdapter() {
             @Override
             public void keyReleased(KeyEvent e) {
                 seq2.setText(seq2.getText().toUpperCase());
                 if (!controler.getsequence().NucleotideValide(seq1.getText()) || !controler.getsequence().NucleotideValide(seq2.getText())) {
                     JOptionPane.showMessageDialog(mainContainer, "Error: Allowed charcaters are: A, C, T or G.", "Message", JOptionPane.ERROR_MESSAGE);
                     seq2.setText(seq2.getText().substring(0, seq2.getText().length() - 1));
                 } else {
                     if (seq1.getText().length() == 0 && seq2.getText().length() == 0) {
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
                     } else if (seq1.getText().length() > 20 || seq2.getText().length() > 20) {
                         JOptionPane.showMessageDialog(mainContainer, "Error: Maximum length of sequence is 20 characters.");
                         if (seq1.getText().length() > 20) {
                             seq1.setText(seq1.getText().substring(0, 20));
                         }
                         if (seq2.getText().length() > 20) {
                             seq2.setText(seq2.getText().substring(0, 20));
                         }
                     } else {
                         String s1 = seq1.getText().toUpperCase();
                         String s2 = seq2.getText().toUpperCase();
                         mainContainer.remove(ajout);
                         mainContainer.remove(alignement);
                         ajout.removeAll();
                         alignement.removeAll();
                         ajout = v.init(s1, s2, (int) a.getValue(), (int) b.getValue(), (int) c.getValue());
                         alignement = align.Align(s1, s2, (int) a.getValue(), (int) b.getValue(), (int) c.getValue());
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
         });

         a.addChangeListener(new ChangeListener() {
             @Override
             public void stateChanged(ChangeEvent e) {
                 if (seq1.getText().length() <= 20  //Ici je n'ai pas trouvé une méthode
                         && seq2.getText().length() <= 20 //Pour appeler la méthode seqeunceValide()
                 ) {
                     String s1 = seq1.getText().toUpperCase();
                     String s2 = seq2.getText().toUpperCase();
                     mainContainer.remove(ajout);
                     mainContainer.remove(alignement);
                     ajout.removeAll();
                     alignement.removeAll();
                     ajout = v.init(s1, s2, (int) a.getValue(), (int) b.getValue(), (int) c.getValue());
                     alignement = align.Align(s1, s2, (int) a.getValue(), (int) b.getValue(), (int) c.getValue());
                     alignement.setBorder(BorderFactory.createTitledBorder("Alignement"));
                     alignement.setPreferredSize(new Dimension((largeur-596), 131));
                     ajout.setPreferredSize(new Dimension(58 * (s2.length() + 2), 25 * (s1.length() + 2)));
                     mainContainer.add(alignement);
                     mainContainer.add(ajout);
                     mainContainer.revalidate();
                     ajout.revalidate();
                     pack();
                 }
             }
         });

         b.addChangeListener(new ChangeListener() {
             @Override
             public void stateChanged(ChangeEvent e) {
                 if (seq1.getText().length() <= 20  //Ici je n'ai pas trouvé une méthode
                         && seq2.getText().length() <= 20 //Pour appeler la méthode seqeunceValide()
                 ) {
                     String s1 = seq1.getText().toUpperCase();
                     String s2 = seq2.getText().toUpperCase();
                     mainContainer.remove(ajout);
                     mainContainer.remove(alignement);
                     ajout.removeAll();
                     alignement.removeAll();
                     ajout = v.init(s1, s2, (int) a.getValue(), (int) b.getValue(), (int) c.getValue());
                     alignement = align.Align(s1, s2, (int) a.getValue(), (int) b.getValue(), (int) c.getValue());
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

         c.addChangeListener(new ChangeListener() {
             @Override
             public void stateChanged(ChangeEvent e) {
                 if (seq1.getText().length() <= 20  //Ici je n'ai pas trouvé une méthode
                         && seq2.getText().length() <= 20 //Pour appeler la méthode seqeunceValide()
                 ) {
                     String s1 = seq1.getText().toUpperCase();
                     String s2 = seq2.getText().toUpperCase();
                     mainContainer.remove(ajout);
                     mainContainer.remove(alignement);
                     ajout.removeAll();
                     alignement.removeAll();
                     ajout = v.init(s1, s2, (int) a.getValue(), (int) b.getValue(), (int) c.getValue());
                     alignement = align.Align(s1, s2, (int) a.getValue(), (int) b.getValue(), (int) c.getValue());
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
     

    public static void main(String [] args){
         MainWindowPanel p=new MainWindowPanel();
        p.setVisible(true);
    }
}