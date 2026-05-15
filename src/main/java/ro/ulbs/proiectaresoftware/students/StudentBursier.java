package ro.ulbs.proiectaresoftware.students;

public class StudentBursier extends Student {

    double bursa;
    public StudentBursier(int nrMatricol, String nume, String prenume, String grupa, double nota, double bursa) {
        super(nrMatricol, nume, prenume, grupa, nota);
        this.bursa = bursa;

    }
    public double getBursa() {return bursa;}
}
