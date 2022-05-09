package projetAr1.Model;

public class CaseNucleotide extends Case{
    private Nucleotide nuc;
    public CaseNucleotide(Nucleotide nuc){
        this.nuc= nuc;
    }
    public Nucleotide getNuc(){
        return this.nuc;
    }
}