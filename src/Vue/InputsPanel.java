package Vue;
import javax.swing.*;
import java.awt.*;

public class InputsPanel extends JFrame {
    private JLabel match = new JLabel("Match");
    private JLabel mismatch = new JLabel("Mismatch");
    private JLabel gap = new JLabel("Gap");
    private JTextField seq1 = new JTextField(29);
    private JTextField seq2 = new JTextField(29);
    private JLabel seq1Label = new JLabel("Sequence    1: ");
    private JLabel seq2Label = new JLabel("Sequence    2: ");
    private JButton CustomPath = new JButton("Custom Path");
    private JButton ClearPath = new JButton("Clear Path");
    private JButton OptimisePath = new JButton("Optimal Alignment");
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
    private JSpinner a = new JSpinner(modelA);
    private JSpinner b = new JSpinner(modelB);
    private JSpinner c = new JSpinner(modelC);

    public InputsPanel() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setPreferredSize(new Dimension(500, 130));
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
        this.add(seq1Label);
        this.add(seq1);
        this.add(seq2Label);
        this.add(seq2);
        this.add(match);
        this.add(mismatch);
        this.add(gap);
        this.add(a);
        this.add(b);
        this.add(c);
        this.add(CustomPath);
        this.add(ClearPath);
        this.add(OptimisePath);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    //TODO: getters
    public JSpinner getA(){ return this.a;}
    public JSpinner getB(){ return  this.b;}
    public JSpinner getC(){ return  this.c;}
    public JTextField getSeq1(){ return  this.seq1;}
    public JTextField getSeq2(){ return this.seq2;}
    public JButton getCustomPath(){ return  this.CustomPath;}
    public JButton getClearPath(){ return  this.ClearPath;}
    public JButton getOptimisePath(){ return this.OptimisePath;}

}
