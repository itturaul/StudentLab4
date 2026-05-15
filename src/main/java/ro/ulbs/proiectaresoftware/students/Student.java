package ro.ulbs.proiectaresoftware.students;


import java.util.Objects;

public class Student {
    private int nrMatricol;
    private String nume;
    private String prenume;
    private String grupa;
    private double nota;

    public Student(int nrMatricol, String nume, String prenume, String grupa) {
        this.nrMatricol = nrMatricol;
        this.nume = nume;
        this.prenume = prenume;
        this.grupa = grupa;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public int getNrMatricol() {
        return nrMatricol;
    }

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
