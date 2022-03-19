package Controler;
import Model.*;

public class MainWindowControler {
    private Matrice grillePanel;

    public MainWindowControler(){
        this.grillePanel=new Matrice();
    }

    public Matrice getGrillePanel(){ return this.grillePanel;}
}
