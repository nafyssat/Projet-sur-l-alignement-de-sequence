package Vue;

import javax.swing.*;
import java.awt.*;
import Model.*;

//Cette classe permet de modeliser l'affichage de la fenêtre sous forme de JButton[][]//

public class V {
    private Utilisateur user = new Utilisateur();
    private Matrice matrice = new Matrice();
  

    // Interface //
    /**
     * 
     * @param a sequence 1
     * @param b sequence 2
     * @param c mismatch 
     * @param d match
     * @param e gap
     * @return un Jpanel qu'on va ajouter à notre fenêtre principal dans la classe Vue
     */

    public JPanel init(String a, String b, String c, String d, String e) {
        JPanel droite =new JPanel();
    
        JButton [][] res=new JButton[a.length()+2][b.length()+2];
        droite.setLayout(new GridLayout(a.length()+2,b.length()+2));

        for(int i=0;i<a.length()+2;i++){
            for(int j=0;j<b.length()+2;j++){
                res[i][j]=new JButton("  ");  //On initialise chaque JButton à " " pour éviter par la suite 
                                              //d'avoir des problème de NullPointerException
                
            }
        }

        for (int i = 2; i < a.length() + 2; i++) {
            res[i][0].setText(" "+a.charAt(i-2)); //ON complète la première colonne 
            
        }

        for (int i = 2; i < b.length() + 2; i++) {
            res[0][i].setText(" "+b.charAt(i-2)); //On complète la première ligne 
           
        }

        for(int i=1;i<a.length()+2;i++){
            for(int j=1;j<b.length()+2;j++){

                //Ici nous devons appeler cette méthode pour calculer les valeur de chaque cases de 
                //la matrice. 

                this.matrice.initialiser_Grille_Interface(a, b, Integer.parseInt(c), Integer.parseInt(d), Integer.parseInt(e));

                //C'est pourquoi nous pouvons utiliser la méthode getGrille() par la suite.
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

        
        droite.setBounds(70,80,100,30);
        return droite;

    }

    

    public static void main(String[] args) {
        V v = new V();
        //v.ajout("ataa", "cccgt", "1", "-1", "-3");
    }

}
