package Model;

public class CaseNucleotide extends Case{

    private Nucleotide nuc;

    public CaseNucleotide(Nucleotide nuc){
        this.nuc= nuc;
    }

    public Nucleotide getVal(){
        return this.nuc;
    }
}