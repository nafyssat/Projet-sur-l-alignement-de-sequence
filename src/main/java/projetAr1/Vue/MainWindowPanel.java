package projetAr1.Vue;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.util.Map;
import projetAr1.Model.*;
import projetAr1.Controler.*;

public class MainWindowPanel extends JFrame {
    private InputsPanel Inputs=new InputsPanel();
    private HelpPanel help=new HelpPanel();
    private MatricePanel matrice=new MatricePanel();
    private AlignementPanel align=new AlignementPanel();
    private Utilisateur user=new Utilisateur();
    private MainWindowControler controler=new MainWindowControler();
    private Container mainContainer=this.getContentPane();
    private JPanel mainPanel=new JPanel();
    private JPanel alignement=new JPanel();
    private JPanel ajout = new JPanel();


    public MainWindowPanel() {
        this.setTitle("GENOaligner");
        this.pack();
        this.setDefaultLookAndFeelDecorated(true);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


        //TODO: welcome panel
        ImageIcon icon = null;
        try {
           icon = new ImageIcon("Image/ADN.gif");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Object[] choices = {"Let's Start"};
        Object defaultChoice = choices[0];
        JLabel welcome = new JLabel("Welcome to GENOaligner!");
        welcome.setForeground(Color.BLACK);
        welcome.setFont(new Font("Arial", Font.BOLD, 15));
        JOptionPane.showOptionDialog(this,
                "",
                "Welcome to GENOaligner!",
                JOptionPane.YES_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                icon,
                choices,
                defaultChoice);


        Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int largeur = (int) tailleEcran.getWidth();

        this.mainContainer.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.mainPanel = Inputs.init();
        this.mainPanel.setPreferredSize(new Dimension(500, 130));
        this.mainPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        this.alignement.setBorder(BorderFactory.createTitledBorder("Alignement"));
        this.alignement.setPreferredSize(new Dimension((largeur - 596), 131));

        this.mainContainer.add(mainPanel);
        this.mainContainer.add(alignement);
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GREEN));


        //TODO: action listeners
        Inputs.getOptimisePath().addActionListener(e -> {
            if (Inputs.getSeq1().getText().length() != 0 && Inputs.getSeq2().getText().length() != 0) {
                String s1 = Inputs.getSeq1().getText().toUpperCase();
                String s2 = Inputs.getSeq2().getText().toUpperCase();
                mainContainer.remove(ajout);
                mainContainer.remove(alignement);
                ajout.removeAll();
                alignement.removeAll();
                ajout = matrice.init(s1, s2, (int) Inputs.getA().getValue(), (int) Inputs.getB().getValue(), (int) Inputs.getC().getValue());
                alignement = align.Align(s1, s2, (int) Inputs.getA().getValue(), (int) Inputs.getB().getValue(), (int) Inputs.getC().getValue());
                alignement.setBorder(BorderFactory.createTitledBorder("Alignement"));
                alignement.setPreferredSize(new Dimension((largeur - 596), 131));
                ajout.setPreferredSize(new Dimension(58 * (s2.length() + 2), 25 * (s1.length() + 2)));
                mainContainer.add(alignement);
                mainContainer.add(ajout);
                mainContainer.revalidate();
            }
        });

        Inputs.getClearPath().addActionListener(e -> {
            if (Inputs.getSeq1().getText().length() != 0 || Inputs.getSeq2().getText().length() != 0) {
                String s1 = Inputs.getSeq1().getText().toUpperCase();
                String s2 = Inputs.getSeq2().getText().toUpperCase();
                matrice.getMatrice()[1][1].setBackground(Color.WHITE);
                for (int i = 2; i < s1.length() + 2; i++) {
                    matrice.getMatrice()[i][1].setBackground(Color.LIGHT_GRAY);
                }
                for (int j = 2; j < s2.length() + 2; j++) {
                    matrice.getMatrice()[1][j].setBackground(Color.LIGHT_GRAY);
                }
                for (int m = 2; m < s1.length() + 2; m++) {
                    for (int n = 2; n < s2.length() + 2; n++) {
                        int finalN = n;
                        int finalM = m;
                        matrice.getMatrice()[0][finalN].setBackground(new Color(230, 252, 237));
                        matrice.getMatrice()[0][finalN].setForeground(new Color(203, 235, 247));
                        matrice.getMatrice()[finalM][0].setBackground(new Color(230, 252, 237));
                        matrice.getMatrice()[finalM][0].setForeground(new Color(203, 235, 247));
                        matrice.getMatrice()[m][n].setBackground(Color.WHITE);

                        matrice.getMatrice()[m][n].addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseEntered(MouseEvent e) {
                                matrice.getMatrice()[0][finalN].setBackground(Color.BLUE);
                                matrice.getMatrice()[finalM][0].setBackground(Color.BLUE);
                                matrice.getMatrice()[0][finalN].setForeground(Color.WHITE);
                                matrice.getMatrice()[finalM][0].setForeground(Color.WHITE);
                                matrice.getMatrice()[finalM - 1][finalN - 1].setBackground(Color.GREEN);
                                matrice.getMatrice()[finalM - 1][finalN].setBackground(Color.GREEN);
                                matrice.getMatrice()[finalM][finalN - 1].setBackground(Color.GREEN);

                            }

                            @Override
                            public void mouseExited(MouseEvent e) {
                                matrice.getMatrice()[0][finalN].setBackground(new Color(230, 252, 237));
                                matrice.getMatrice()[0][finalN].setForeground(new Color(203, 235, 247));
                                matrice.getMatrice()[finalM][0].setBackground(new Color(230, 252, 237));
                                matrice.getMatrice()[finalM][0].setForeground(new Color(203, 235, 247));
                                if (finalN - 1 == 1 && finalM - 1 == 1) {
                                    matrice.getMatrice()[finalM - 1][finalN - 1].setBackground(Color.LIGHT_GRAY);
                                    matrice.getMatrice()[finalM - 1][finalN].setBackground(Color.LIGHT_GRAY);
                                    matrice.getMatrice()[finalM][finalN - 1].setBackground(Color.LIGHT_GRAY);
                                } else if (finalN - 1 == 1) {
                                    matrice.getMatrice()[finalM - 1][finalN - 1].setBackground(Color.LIGHT_GRAY);
                                    matrice.getMatrice()[finalM][finalN - 1].setBackground(Color.LIGHT_GRAY);
                                    matrice.getMatrice()[finalM - 1][finalN].setBackground(Color.WHITE);
                                } else if (finalM - 1 == 1) {
                                    matrice.getMatrice()[finalM - 1][finalN - 1].setBackground(Color.LIGHT_GRAY);
                                    matrice.getMatrice()[finalM][finalN - 1].setBackground(Color.WHITE);
                                    matrice.getMatrice()[finalM - 1][finalN].setBackground(Color.LIGHT_GRAY);
                                } else {
                                    matrice.getMatrice()[finalM - 1][finalN - 1].setBackground(Color.WHITE);
                                    matrice.getMatrice()[finalM][finalN - 1].setBackground(Color.WHITE);
                                    matrice.getMatrice()[finalM - 1][finalN].setBackground(Color.WHITE);
                                }
                            }
                        });
                    }
                }
                mainContainer.remove(alignement);
                mainContainer.remove(ajout);
                alignement.removeAll();
                alignement = align.clearpath();
                alignement.setBorder(BorderFactory.createTitledBorder("Alignement"));
                alignement.setPreferredSize(new Dimension((largeur - 596), 131));
                ajout.setPreferredSize(new Dimension(58 * (s2.length() + 2), 25 * (s1.length() + 2)));
                mainContainer.add(alignement);
                mainContainer.add(ajout);
                mainContainer.revalidate();
            }
        });

        Inputs.getCustomPath().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                matrice.setCount(0);
                matrice.setSeq1("");
                matrice.setSeq2("");
                matrice.setPx(-1);
                matrice.setPy(-1);
                matrice.setScore(0);
                String s1 = Inputs.getSeq1().getText().toUpperCase();
                String s2 = Inputs.getSeq2().getText().toUpperCase();
                if (s1.length() > 0 && s2.length() > 0) {
                    mainContainer.remove(ajout);
                    mainContainer.remove(alignement);
                    ajout.removeAll();
                    alignement.removeAll();
                    ajout = matrice.initCustom(s1, s2, (int) Inputs.getA().getValue(), (int) Inputs.getB().getValue(), (int) Inputs.getC().getValue());
                    alignement = align.clearpath();
                    alignement.setBorder(BorderFactory.createTitledBorder("Alignement"));
                    alignement.setPreferredSize(new Dimension((largeur - 596), 131));
                    ajout.setPreferredSize(new Dimension(58 * (s2.length() + 2), 25 * (s1.length() + 2)));
                    mainContainer.add(alignement);
                    mainContainer.add(ajout);
                    mainContainer.revalidate();
                    ajout.revalidate();
                    ajout.repaint();
                    for (int i = 1; i < s1.length() + 2; i++) {
                        for (int j = 1; j < s2.length() + 2; j++) {
                            int finalI = i;
                            int finalJ = j;
                            if (s1.length() > 0 && s2.length() > 0) {
                                matrice.getMatrice()[i][j].addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mouseClicked(MouseEvent x) {
                                        if (matrice.isValid(finalI, finalJ, (int) Inputs.getA().getValue(), (int) Inputs.getB().getValue(), (int) Inputs.getC().getValue())) {
                                            matrice.getMatrice()[finalI][finalJ].setBackground(Color.red);
                                            if (finalI == 1 && finalJ == 1) {
                                                matrice.getMatrice()[finalI][finalJ].setBackground(Color.GREEN);
                                            } else {
                                                if (finalI != 1) {
                                                    matrice.getMatrice()[finalI][0].setBackground(Color.BLUE);
                                                    matrice.getMatrice()[finalI][0].setForeground(Color.WHITE);
                                                }
                                                if (finalJ != 1) {
                                                    matrice.getMatrice()[0][finalJ].setBackground(Color.BLUE);
                                                    matrice.getMatrice()[0][finalJ].setForeground(Color.WHITE);
                                                }
                                            }
                                            mainContainer.remove(ajout);
                                            mainContainer.remove(alignement);
                                            alignement.removeAll();
                                            alignement = align.Align_custom(matrice.getSeq1(), matrice.getSeq2(), matrice.getScore());
                                            alignement.setBorder(BorderFactory.createTitledBorder("Alignement"));
                                            alignement.setPreferredSize(new Dimension((largeur - 596), 131));
                                            ajout.setPreferredSize(new Dimension(58 * (s2.length() + 2), 25 * (s1.length() + 2)));
                                            mainContainer.add(alignement);
                                            mainContainer.add(ajout);
                                            mainContainer.revalidate();
                                        } else {
                                            JOptionPane.showMessageDialog(mainContainer,
                                                    "Selected button is not valid\n the path buttons must be consecutive in an increasing direction.",
                                                    " WARNING ",
                                                    JOptionPane.WARNING_MESSAGE);
                                        }

                                    }
                                });
                            }
                        }
                    }
                }
            }
        });


        Inputs.getSeq1().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                Inputs.getSeq1().setText(Inputs.getSeq1().getText().toUpperCase());
                if (!controler.getsequence().NucleotideValide(Inputs.getSeq1().getText()) || !controler.getsequence().NucleotideValide(Inputs.getSeq2().getText())) {
                    JOptionPane.showMessageDialog(mainContainer, "Error: Allowed charcaters are: A, C, T or G.", "Message", JOptionPane.ERROR_MESSAGE);
                    Inputs.getSeq1().setText(Inputs.getSeq1().getText().substring(0, Inputs.getSeq1().getText().length() - 1));
                } else {
                    if (Inputs.getSeq1().getText().length() == 0 && Inputs.getSeq2().getText().length() == 0) {
                        alignement.removeAll();
                        JLabel h = new JLabel("For more details, click here!");
                        alignement.setLayout(new BorderLayout());
                        Font font = h.getFont();
                        Map attributes = font.getAttributes();
                        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                        h.setFont(font.deriveFont(attributes));
                        h.setForeground(new Color(0, 0, 255));

                        alignement.add(h, BorderLayout.NORTH);
                        h.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                help.setVisible(true);
                            }
                        });
                        mainContainer.remove(ajout);
                        pack();
                    } else if (Inputs.getSeq1().getText().length() > 20 || Inputs.getSeq2().getText().length() > 20) {
                        JOptionPane.showMessageDialog(mainContainer, "Error: Maximum length of sequence is 20 charcaters.", "Message", JOptionPane.ERROR_MESSAGE);
                        if (Inputs.getSeq1().getText().length() > 20) {
                            Inputs.getSeq1().setText(Inputs.getSeq1().getText().substring(0, 20));
                        }
                        if (Inputs.getSeq2().getText().length() > 20) {
                            Inputs.getSeq2().setText(Inputs.getSeq2().getText().substring(0, 20));
                        }
                    } else {
                        String s1 = Inputs.getSeq1().getText().toUpperCase();
                        String s2 = Inputs.getSeq2().getText().toUpperCase();
                        mainContainer.remove(ajout);
                        mainContainer.remove(alignement);
                        ajout.removeAll();
                        alignement.removeAll();
                        ajout = matrice.init(s1, s2, (int) Inputs.getA().getValue(), (int) Inputs.getB().getValue(), (int) Inputs.getC().getValue());
                        alignement = align.Align(s1, s2, (int) Inputs.getA().getValue(), (int) Inputs.getB().getValue(), (int) Inputs.getC().getValue());
                        alignement.setBorder(BorderFactory.createTitledBorder("Alignement"));
                        alignement.setPreferredSize(new Dimension((largeur - 596), 131));
                        ajout.setPreferredSize(new Dimension(58 * (s2.length() + 2), 25 * (s1.length() + 2)));
                        mainContainer.add(alignement);
                        mainContainer.add(ajout);
                        mainContainer.revalidate();
                    }
                }
            }

            // }
        });

        Inputs.getSeq2().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                Inputs.getSeq2().setText(Inputs.getSeq2().getText().toUpperCase());
                if (!controler.getsequence().NucleotideValide(Inputs.getSeq1().getText()) || !controler.getsequence().NucleotideValide(Inputs.getSeq2().getText())) {
                    JOptionPane.showMessageDialog(mainContainer, "Error: Allowed charcaters are: A, C, T or G.", "Message", JOptionPane.ERROR_MESSAGE);
                    Inputs.getSeq2().setText(Inputs.getSeq2().getText().substring(0, Inputs.getSeq2().getText().length() - 1));
                } else {
                    if (Inputs.getSeq1().getText().length() == 0 && Inputs.getSeq2().getText().length() == 0) {
                        alignement.removeAll();
                        JLabel h = new JLabel("For more details, click here!");
                        alignement.setLayout(new BorderLayout());
                        Font font = h.getFont();
                        Map attributes = font.getAttributes();
                        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                        h.setFont(font.deriveFont(attributes));
                        h.setForeground(new Color(0, 0, 255));

                        alignement.add(h, BorderLayout.NORTH);
                        h.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                help.setVisible(true);
                            }
                        });
                        mainContainer.remove(ajout);
                        pack();
                    } else if (Inputs.getSeq1().getText().length() > 20 || Inputs.getSeq2().getText().length() > 20) {
                        JOptionPane.showMessageDialog(mainContainer, "Error: Maximum length of sequence is 20 charcaters.", "Message", JOptionPane.ERROR_MESSAGE);
                        if (Inputs.getSeq1().getText().length() > 20) {
                            Inputs.getSeq1().setText(Inputs.getSeq1().getText().substring(0, 20));
                        }
                        if (Inputs.getSeq2().getText().length() > 20) {
                            Inputs.getSeq2().setText(Inputs.getSeq2().getText().substring(0, 20));
                        }
                    } else {
                        String s1 = Inputs.getSeq1().getText().toUpperCase();
                        String s2 = Inputs.getSeq2().getText().toUpperCase();
                        mainContainer.remove(ajout);
                        mainContainer.remove(alignement);
                        ajout.removeAll();
                        alignement.removeAll();
                        ajout = matrice.init(s1, s2, (int) Inputs.getA().getValue(), (int) Inputs.getB().getValue(), (int) Inputs.getC().getValue());
                        alignement = align.Align(s1, s2, (int) Inputs.getA().getValue(), (int) Inputs.getB().getValue(), (int) Inputs.getC().getValue());
                        alignement.setBorder(BorderFactory.createTitledBorder("Alignement"));
                        alignement.setPreferredSize(new Dimension((largeur - 596), 131));
                        ajout.setPreferredSize(new Dimension(58 * (s2.length() + 2), 25 * (s1.length() + 2)));
                        mainContainer.add(alignement);
                        mainContainer.add(ajout);
                        mainContainer.revalidate();
                    }
                }
            }

            // }
        });

        Inputs.getA().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (Inputs.getSeq1().getText().length() <= 20
                        && Inputs.getSeq2().getText().length() <= 20
                ) {
                    String s1 = Inputs.getSeq1().getText().toUpperCase();
                    String s2 = Inputs.getSeq2().getText().toUpperCase();
                    mainContainer.remove(ajout);
                    mainContainer.remove(alignement);
                    ajout.removeAll();
                    alignement.removeAll();
                    ajout = matrice.init(s1, s2, (int) Inputs.getA().getValue(), (int) Inputs.getB().getValue(), (int) Inputs.getC().getValue());
                    alignement = align.Align(s1, s2, (int) Inputs.getA().getValue(), (int) Inputs.getB().getValue(), (int) Inputs.getC().getValue());
                    alignement.setBorder(BorderFactory.createTitledBorder("Alignement"));
                    alignement.setPreferredSize(new Dimension((largeur - 596), 131));
                    ajout.setPreferredSize(new Dimension(58 * (s2.length() + 2), 25 * (s1.length() + 2)));
                    mainContainer.add(alignement);
                    mainContainer.add(ajout);
                    mainContainer.revalidate();
                }
            }
        });


        Inputs.getB().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (Inputs.getSeq1().getText().length() <= 20
                        && Inputs.getSeq2().getText().length() <= 20
                ) {
                    String s1 = Inputs.getSeq1().getText().toUpperCase();
                    String s2 = Inputs.getSeq2().getText().toUpperCase();
                    mainContainer.remove(ajout);
                    mainContainer.remove(alignement);
                    ajout.removeAll();
                    alignement.removeAll();
                    ajout = matrice.init(s1, s2, (int) Inputs.getA().getValue(), (int) Inputs.getB().getValue(), (int) Inputs.getC().getValue());
                    alignement = align.Align(s1, s2, (int) Inputs.getA().getValue(), (int) Inputs.getB().getValue(), (int) Inputs.getC().getValue());
                    alignement.setBorder(BorderFactory.createTitledBorder("Alignement"));
                    alignement.setPreferredSize(new Dimension((largeur - 596), 131));
                    ajout.setPreferredSize(new Dimension(58 * (s2.length() + 2), 25 * (s1.length() + 2)));
                    mainContainer.add(alignement);
                    mainContainer.add(ajout);
                    mainContainer.revalidate();
                }
            }
        });


        Inputs.getC().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (Inputs.getSeq1().getText().length() <= 20
                        && Inputs.getSeq2().getText().length() <= 20
                ) {
                    String s1 = Inputs.getSeq1().getText().toUpperCase();
                    String s2 = Inputs.getSeq2().getText().toUpperCase();
                    mainContainer.remove(ajout);
                    mainContainer.remove(alignement);
                    ajout.removeAll();
                    alignement.removeAll();
                    ajout = matrice.init(s1, s2, (int) Inputs.getA().getValue(), (int) Inputs.getB().getValue(), (int) Inputs.getC().getValue());
                    alignement = align.Align(s1, s2, (int) Inputs.getA().getValue(), (int) Inputs.getB().getValue(), (int) Inputs.getC().getValue());
                    alignement.setBorder(BorderFactory.createTitledBorder("Alignement"));
                    alignement.setPreferredSize(new Dimension((largeur - 596), 131));
                    ajout.setPreferredSize(new Dimension(58 * (s2.length() + 2), 25 * (s1.length() + 2)));
                    mainContainer.add(alignement);
                    mainContainer.add(ajout);
                    mainContainer.revalidate();
                    ajout.revalidate();
                }
            }
        });


        //TODO: add HelpPanel
        JLabel h = new JLabel("For more details, click here!");
        alignement.setLayout(new BorderLayout());
        Font font = h.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        h.setFont(font.deriveFont(attributes));
        h.setForeground(new Color(0, 0, 255));
        alignement.add(h, BorderLayout.NORTH);
        h.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                help.setVisible(true);
            }
        });
    }
}
