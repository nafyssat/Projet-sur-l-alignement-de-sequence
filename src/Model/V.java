import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/* A remettre les packages V.java , Vue.java dans le dossier Vue.   */

//Cette classe permet de modeliser l'affichage de la fenêtre sous forme de JTable//

public class V {
    private Utilisateur user = new Utilisateur();
    private Matrice matrice = new Matrice();
    final JFrame frame = new JFrame("Exemple de JTable"); // Titre de la fenêtre

    // Interface //

    /**
     * 
     * @param a sequence 1
     * @param b sequence 2
     * @param c match
     * @param d mismatch
     * @param e gap
     *          Nous affiche une matrice dans une nouvelle fenêtre
     */

    public void init(String a, String b, String c, String d, String e) {

        JTable table = new JTable(
                matrice.getMatrice(a, b, Integer.parseInt(c), Integer.parseInt(d), Integer.parseInt(e)),
                matrice.tabString(a));
        // Ici du coup j'ai inverser l'ordre de sequences 1 et 2
        // On initialise le tableau de tableau d'objet avec la fonction
        // init_G_Interface(...),
        // et le tableau d' objet avec tabString().
        // On aura un resultat si on saisit au terminal pour sq1=a , sq2=agc ,
        // match=mismatch=gap=1
        // Si j'aurai saisit directement a,b,c,d,e en paramètre ça m'aurait mis des
        // erreur d'index,

        JScrollPane scroll = new JScrollPane(table);// Permet l'affichage de la matrice
        table.setFillsViewportHeight(true);

        JLabel labelHead = new JLabel("Matrice");
        labelHead.setFont(new Font("Arial", Font.TRUETYPE_FONT, 20));

        frame.getContentPane().add(labelHead, BorderLayout.EAST);
        // ajouter la table au frame
        frame.getContentPane().add(scroll, BorderLayout.EAST);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        V v = new V();
        v.init("aa", "ca", "1", "1", "2");

        // A mettre au terminal pour V.java les valeurs sq1=a, sq2=agc,
        // match=mismatch=gap=1,
        // Vue.java la même chose 3 fois de suite , 2 fois au terminal
        // 1 fois dans la fenêtre
    }

}
