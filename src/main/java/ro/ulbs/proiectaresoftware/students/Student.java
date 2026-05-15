package ro.ulbs.proiectaresoftware.students;


import java.util.Objects;

public class Student {
    private int nrMatricol;
    private String nume;
    private String prenume;
    private String grupa;
    private double nota;

    public Student(int nrMatricol, String nume, String prenume, String grupa, double nota) {
        this.nrMatricol = nrMatricol;
        this.nume = nume;
        this.prenume = prenume;
        this.grupa = grupa;
        this.nota = nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
    public double getNota() {return nota;}
    public int getNrMatricol() {
        return nrMatricol;
    }
    public String getFormatieDeStudiu() {return grupa;}
    public String getNume() {return nume;}
    public String getPrenume() {return prenume;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return nrMatricol == student.nrMatricol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nrMatricol);
    }

    @Override
    public String toString() {
        return "Student{" +
                "nrMatricol=" + nrMatricol +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", grupa='" + grupa + '\'' +
                ", nota=" + nota +
                '}';
    }
}
