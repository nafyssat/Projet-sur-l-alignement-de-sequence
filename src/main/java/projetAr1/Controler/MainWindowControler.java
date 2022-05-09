package projetAr1.Controler;
import projetAr1.Model.*;

public class MainWindowControler {
    private Matrice grillePanel;
    private Sequence sequence;

    public MainWindowControler(){
        this.grillePanel=new Matrice();
        this.sequence=new Sequence();
    }

    public Matrice getGrillePanel(){ return this.grillePanel;}
    public Sequence getsequence(){return this.sequence;}
}
