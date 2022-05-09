package projetAr1;

import java.awt.EventQueue;
import java.util.Scanner;

import projetAr1.Vue.*;
import projetAr1.Model.*;

public class App {

    public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in);
        System.out.println("Welcome, to GENOalign!");
        System.out.println("Please select launching mode:[1 ? 2]");
        System.out.println("1-To launch in consol mode");
        System.out.println("2-To launch in graphic mode");
        int i=0;
        try{
            i=sc.nextInt();
        }catch(Exception e){
            e.printStackTrace();
        }
        if(i==1){
            Matrice m=new Matrice();
            m.launcher(true);
        }else {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {

                    MainWindowPanel mainWindow = new MainWindowPanel();
                    mainWindow.setVisible(true);
                }
            });
        }

    }
}
