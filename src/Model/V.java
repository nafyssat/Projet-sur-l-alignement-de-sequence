import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/* A remettre les packages V.java , Vue.java dans le dossier Vue.   */

//Cette classe permet de modeliser l'affichage de la fenêtre sous forme de JButton[][]//

public class V {
    private Utilisateur user = new Utilisateur();
    private Matrice matrice = new Matrice();
    final JFrame frame = new JFrame("Exemple de JTable"); // Titre de la fenêtre
    JButton b=new JButton("zlsls");

    public V(){
        //frame.getContentPane().add(b,BorderLayout.WEST);
    }
    
    

    // Interface //

    public JPanel init(String a, String b, String c, String d, String e) {
        JPanel droite =new JPanel();
    
        JButton [][] res=new JButton[a.length()+2][b.length()+2];
        droite.setLayout(new GridLayout(a.length()+2,b.length()+2));

        for(int i=0;i<a.length()+2;i++){
            for(int j=0;j<b.length()+2;j++){
                res[i][j]=new JButton("  ");
                
            }
        }

        for (int i = 2; i < a.length() + 2; i++) {
            this.matrice.initialiser_Grille_Interface(a, b, Integer.parseInt(c), Integer.parseInt(d), Integer.parseInt(e));
            res[i][0].setText(" "+a.charAt(i-2));
            
        }

        for (int i = 2; i < b.length() + 2; i++) {
            this.matrice.initialiser_Grille_Interface(a, b, Integer.parseInt(c), Integer.parseInt(d), Integer.parseInt(e));
            res[0][i].setText(" "+b.charAt(i-2));
           
        }

        for(int i=1;i<a.length()+2;i++){
            for(int j=1;j<b.length()+2;j++){

                this.matrice.initialiser_Grille_Interface(a, b, Integer.parseInt(c), Integer.parseInt(d), Integer.parseInt(e));

                res[i][j].setText(" "+this.matrice.getGrille()[i][j].getValeur());
                res[i][j].setForeground(Color.MAGENTA);
              
            }
        }
        
       
        // ajouter la table au frame

        for(int i=0;i<a.length()+2;i++){
            for(int j=0;j<b.length()+2;j++){

                droite.add(res[i][j],BorderLayout.EAST);


            }
        }

       // frame.getContentPane().add(droite,BorderLayout.EAST);

        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // frame.setSize(800, 400);
        //frame.setVisible(true);
        //droite.setSize(1400,1400);
        //droite.setVisible(true);

        //droite.setLayout(null);
        
        droite.setBounds(70,80,100,30);
        return droite;

    }

    

    public static void main(String[] args) {
        V v = new V();
        //v.ajout("ataa", "cccgt", "1", "-1", "-3");
    }

}
