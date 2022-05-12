package projetAr1.Vue;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class HelpPanel extends JFrame {
    public HelpPanel(){
        this.setTitle("Whats is GENOaligner?");
        this.setSize(1000,700);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(1,2));
        JPanel a=new JPanel();
        a.setLayout(new GridLayout(4,1));
        JPanel b=new JPanel();
        JLabel titre=new JLabel("<html><h1>GENOaligner:</h1><br>" +
                "Here we present an interactive example of the Needleman-Wunsch global alignment algorithm.");
        JLabel contenu=new JLabel("<html>-The purpose of this interface is" +
                " to visually illustrate how the alignment matrix is constructed and how the Needleman-Wunsch dynamic programing algorithm fills " +
                "this matrix based on user defined Match, Mismatch and Gap Scores.<br>Experiment by changing the various Scores, altering the two Sequences and noting how the alignment " +
                "matrix values, trace back alignment path (in red), and overall alignment score change.");
        JLabel contenu3=new JLabel("<html><br>-Mousing over the matrix itself will show you how the individual values are calculated (based on the highlighted scores in the previous 3 cells of the matrix " +
                "and how different paths through the matrix translate into the final alignment." +
                "<br>Finally, you can also construct a 'Custom Path' through the matrix by selecting the 'Custom Path' button and clicking on a connected series individual matrix cells to see how your trace back path through the" +
                " matrix translates into a particular alignment with various path dependent matches, mismatches and gaps.");
        JLabel contenu4=new JLabel("<html>=>For more informations, we invite you to visit this website: <br>"+
        "https://fr.wikipedia.org/wiki/Alignement_de_s%C3%A9quences");
        contenu.setFont(new Font("Italic", Font.BOLD,12));
        contenu4.setForeground(new Color(0,0,255));
        contenu3.setForeground(new Color(255,0,0));
        a.add(titre,BorderLayout.CENTER);
        a.add(contenu,BorderLayout.CENTER);
        a.add(contenu3);
        a.add(contenu4);
        ImageIcon img=null;
        try{
            img=new ImageIcon("Image/help.gif");
        }catch (Exception e){
            e.printStackTrace();
        }
        JLabel d=new JLabel(img);
        d.setPreferredSize(new Dimension(500,650));
        b.add(d,BorderLayout.CENTER);
        this.add(a);
        this.add(b);
    }
}
