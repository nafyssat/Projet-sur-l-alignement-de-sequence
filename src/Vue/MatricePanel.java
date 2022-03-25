package Vue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Locale;


import Model.*;
import Controler.*;

//Cette classe permet de modeliser l'affichage de la fenêtre sous forme de JButton[][]//

public class MatricePanel {
    private Utilisateur user = new Utilisateur();
    private MainWindowControler Controlleur = new MainWindowControler();


    // Interface //

    /**
     * @param a sequence 1
     * @param b sequence 2
     * @param c mismatch
     * @param d match
     * @param e gap
     * @return un Jpanel qu'on va ajouter à notre fenêtre principal dans la classe Vue
     */

    public JPanel init(String a, String b, int c, int d, int e) {
    	JPanel droite = new JPanel();
        JButton[][] res = new JButton[a.length() + 2][b.length() + 2];
        
        droite.setPreferredSize(new Dimension(20*(a.length()+2),20*(b.length()+2)));

        droite.setLayout(new GridLayout(a.length()+2,b.length()+2));

        for(int i=0;i<a.length()+2;i++){
            for(int j=0;j<b.length()+2;j++){
                res[i][j]=new JButton("  ");
                res[i][j].setForeground(Color.BLACK);
                res[i][j].setBackground(Color.WHITE);
                		//On initialise chaque JButton à " " pour éviter par la suite 
                                              //d'avoir des problème de NullPointerException
                
            }
        }

        for (int i = 2; i < a.length() + 2; i++) {

            res[i][0].setText(" "+a.charAt(i-2)); //ON complète la première colonne 
            res[i][0].setForeground(Color.white);
            res[i][0].setBackground(Color.BLUE);
          

            res[i][0].setBackground(Color.BLUE);
            res[i][0].setText(" "+String.valueOf(a.charAt(i-2)).toUpperCase()); //ON complète la première colonne

            
        }

        for (int i = 2; i < b.length() + 2; i++) {

            res[0][i].setText(" "+b.charAt(i-2)); //On complète la première ligne 
            res[0][i].setForeground(Color.white);
            res[0][i].setBackground(Color.BLUE);

            res[0][i].setBackground(Color.BLUE);
            res[0][i].setText(" "+String.valueOf(b.charAt(i-2)).toUpperCase()); //On complète la première ligne

           
        }

        for(int i=1;i<a.length()+2;i++){
            for(int j=1;j<b.length()+2;j++){

                //Ici nous devons appeler cette méthode pour calculer les valeur de chaque cases de 
                //la matrice. 

                Case[][] g=this.Controlleur.getGrillePanel().initialiser_Grille_Interface(a, b, c, d, e);
                res[i][j].setText(" "+g[i][j].getValeur());

            }}
        for(int i=2;i<a.length()+2;i++) {
        	for(int j=2;j<b.length()+2;j++) {
               
               
                //fenetre expliquant la calcul du score
               
                	 String [] s =this.Controlleur.getGrillePanel().affCalVal(i,j,c,d,e);
                	 
                	JFrame fr = new JFrame();
               	 	fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               	 	fr.setBackground(Color.blue);
               	 	fr.setSize(400, 400);
               	 	
               	 	JPanel panel_1 = new JPanel();
             		panel_1.setBackground(Color.WHITE);
             		
             		
             		
             		
             		
             		fr.add(panel_1);
             		panel_1.setLayout(new GridLayout(2,2,0,0));
             		
             		for(int z=0;z<4;z++) {
             			JPanel jp = new JPanel();

             			jp.setBorder(new EmptyBorder(10, 10, 10, 10));
                 		jp.setBackground(Color.blue);
                 		panel_1.add(jp);
                 		
             			JLabel jl = new JLabel();
             			jp.add(jl);
             			jl.setText(s[z]);
             			jl.setBounds(0, 0, 200, 100);
                 		jl.setForeground(Color.white);
                 		jl.setFont(new Font("Calibri", Font.ITALIC, 20));
                 		
             			
             		}
             		panel_1.setVisible(true);
             		
             	
              	 	fr.setVisible(false);
              	 	res[i][j].addMouseListener(new MouseAdapter() {

                         @Override
                         public void mouseEntered(MouseEvent e) {
                        	 fr.setVisible(true);
                        	
                         }
                         
                         @Override
                         public void mouseExited(MouseEvent e) {
                        	 fr.setVisible(false);
                         }
                     });
                	 
                	
                }
        }
        	for(int k=1;k<a.length()+2;k++) {
        		res[k][1].setBackground(Color.GRAY);
        		
        	}
        	for(int k=1;k<b.length()+2;k++) {
        		res[1][k].setBackground(Color.GRAY);
        		
        	}
        
              
              


               
              res[a.length()+1][b.length()+1].setBackground(Color.red);
              res[1][1].setBackground(Color.red);


      
       
        //backTracking
        int f=a.length()+1;
        int h=b.length()+1;
        
       
        while(f>=1 && h>=1) {
        	//on recupere la position du case a colorie
        	
        		
        	res[f][h].setBackground(Color.green);
        	int [] t  = this.Controlleur.getGrillePanel().caseAColorie(f, h, c, d, e);
        		f=t[0];
            	h=t[1];
            	if(f==1 && h==1) {
            		res[f][h].setBackground(Color.green);
            		break;
            	}
            	
        	
        	
        	
        }
      
       
        // ajouter la table au frame

        for(int i=0;i<a.length()+2;i++) {
            for (int j = 0; j < b.length() + 2; j++) {
                droite.add(res[i][j]);
            }
        }
        
        

        return droite;
    }


	
		
	
		
		
	

}
