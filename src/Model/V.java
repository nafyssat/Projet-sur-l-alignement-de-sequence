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
    private Utilisateur user=new Utilisateur();
    private Matrice m=new Matrice();
    final JFrame frame = new JFrame("Exemple de JTable"); //Titre de la fenêtre 
   
        public String[] tabString(String a){ 
            //On va se servir de cette fonction pour afficher la première ligne du tableau
            //Cette fonction va afficher un nouveau tableau
            // avec chaque caractère , lettre de a
            String [] res=new String [a.length()+2]; //Initialisation de la taille du tableau 
                                                    //On va ajouter +2 pour laisser des espaces 
                                                    //entre les deux sequences 
            res[0]=" "; 
            res[1]=" ";
            for(int i=0;i<a.length();i++){  //Ici on va donc a partir de l'indice 2 ajouter 
                                            //chaque lettre de a pour que ça forme un tableau 
                res[i+2]=" "+a.charAt(i);
    
            }
            return res;
        }

        //Interface //
        /**
         * 
         * @param sq1 sequence 1
         * @param sq2 sequence 2
         * @param ma match 
         * @param mi mismatch 
         * @param g gap 
         * @return un tableau de tableau d'objet car pour initialiser le JTable,
          il nous faut un tableau de tableau d'objet, et un tableau d'objet.

         */
        public Object [][] init_G_Interface(String sq1,String sq2,String ma,String mi,String g){
            //On va initialiser les valeur du tableau en fonction des valeurs que l'utilisateur aura
            //saisit. On ne va pas afficher la sequence 1 du coup comme on va déjà l'afficher 
            //dans le JTable dans la fonction init
            //Ici j'ai inverser l'affichage des sequences 1 et 2, car ça me mettais des erreur 
            //d'index.

            //Pour la suite j'ai repris le même modèle que la fonction initialiser_Grille(), de la
            //classe Matrice 
            Object [][] data=new Object[sq1.length()+2][sq2.length()+2];
            data=new Object[sq1.length()+2][sq2.length()+2];



            data[1][1]=new CaseEntier(0);
    
            for(int a=2;a<sq1.length()+2;a++){
                data[a][0]=sq1.charAt(a-2);
            }
        
            for(int c=1;c<sq1.length()+2;c++){  //J'ai juste ajouter des getValeur() car sinon ça me 
                                                //l'adress de la case 
                data[c][1] =new CaseEntier((c-1)*Integer.parseInt(g)).getValeur();
            }
            for(int d=1;d<sq2.length()+2;d++){
                data[1][d]=new CaseEntier((d-1)*Integer.parseInt(g)).getValeur();
            }
            for(int n=2;n<sq2.length()+2;n++){
                for(int m=2;m<sq1.length()+2;m++){
                    data[m][n]=new CaseEntier(this.m.ValeurCase(m,n)).getValeur();
                }
            }
            return data;
            
         }
         /**
          * 
          * @param a sequence 1
          * @param b sequence 2
          * @param c match 
          * @param d mismatch 
          * @param e gap
          * Nous affiche une matrice dans une nouvelle fenêtre 
          */

         public void init(String a, String b, String c, String d, String e){
           
            JTable table = new JTable(this.init_G_Interface("a","agc","1","1","1"),this.tabString("agc"));
                                                                //Ici du coup j'ai inverser l'ordre de sequences 1 et 2
            //On initialise le tableau de tableau d'objet avec la fonction init_G_Interface(...),
            //et le tableau d' objet avec tabString(). 
            //On aura un resultat si on saisit au terminal pour sq1=a , sq2=agc , match=mismatch=gap=1
            //Si j'aurai saisit directement a,b,c,d,e en paramètre ça m'aurait mis des erreur d'index,

            JScrollPane scroll = new JScrollPane(table);//Permet l'affichage de la matrice
            table.setFillsViewportHeight(true);
     
            JLabel labelHead = new JLabel("Matrice");
            labelHead.setFont(new Font("Arial",Font.TRUETYPE_FONT,20));
     
            frame.getContentPane().add(labelHead,BorderLayout.EAST);
            //ajouter la table au frame
            frame.getContentPane().add(scroll,BorderLayout.EAST);
     
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 400);
            frame.setVisible(true);
        }

       
    public static void main(String[] args){
        V v=new V();
        v.init("aa","aaaa","1","1","2");

        //A mettre au terminal pour V.java les valeurs sq1=a, sq2=agc, match=mismatch=gap=1,
        //                          Vue.java la même chose 3 fois de suite , 2 fois au terminal
                                                        // 1 fois dans la fenêtre 
    }


}
