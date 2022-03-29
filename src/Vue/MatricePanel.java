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
                //C'est pourquoi nous pouvons utiliser la méthode getGrille() par la suite.
               
                //fenetre expliquant la calcul du score
               
                	 String [] s =this.Controlleur.getGrillePanel().affCalVal(i,j,c,d,e);
                	 
                	JFrame fr = new JFrame();
               	 	fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               	 	fr.setBackground(Color.blue);
               	 	fr.setSize(400, 400);
               	 	//fr.setLayout(new GridLayout(2,2));
               	 	JPanel panel_1 = new JPanel();
             		panel_1.setBackground(Color.WHITE);
             		
             		
             		
             		
             		//panel_1.setBounds(76, 242, 185, 156);
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
                	 
                	 
                	 JPanel dialog = new JPanel();
                	 //droite.add(dialog);
                	 JLabel test = new JLabel("test");
                	 dialog.add(test);
                	 dialog.setVisible(false);
                	 
                	 res[i][j].addMouseListener(new MouseAdapter() {

                         @Override
                         public void mouseEntered(MouseEvent e) {
                        	 fr.setVisible(true);
                        	/* Point p  = MouseInfo.getPointerInfo().getLocation();
                        	 dialog.setVisible(true);
                        	 dialog.setBounds(p.x - 10, p.y - 10, 400, 200);*/
                         }
                         
                         @Override
                         public void mouseExited(MouseEvent e) {
                        	 fr.dispose();
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
        
              
              


               // res[i][j].setForeground(Color.MAGENTA);
              res[a.length()+1][b.length()+1].setBackground(Color.red);
              res[1][1].setBackground(Color.red);


      
       int f=a.length()+1;
        int h=b.length()+1;
        //int z=a.length()+1;
       // int y=b.length()+1;
       
        while(f>1 && h>1) {
        	
        	
        	int [] t  = this.Controlleur.getGrillePanel().caseAColorie(f, h, c, d, e);
        	/*int n=t.length;
        	if(n==2) {*/
        		f=t[0];
            	h=t[1];
        	/*}else {
        		f=t[0];
        		h=t[1];
        		z=t[2];
        		y=t[3];
        		res[z][y].setBackground(Color.red);
        	}*/
        	
        	res[f][h].setBackground(Color.green);
        	
        	
        	
        }
      
      
       
        // ajouter la table au frame

        for(int i=0;i<a.length()+2;i++) {
            for (int j = 0; j < b.length() + 2; j++) {
                if(i==0 || j==0){
                    res[i][j].setFont(new Font("Italic", Font.BOLD, 13));
                }else{
                    res[i][j].setFont(new Font("Calibri", Font.BOLD, 8));
                }
                droite.add(res[i][j]);
            }
        }


        
        

        return droite;
    }



	
		
	
		
		
	

}
