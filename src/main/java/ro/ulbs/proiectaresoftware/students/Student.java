package ro.ulbs.proiectaresoftware.students;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student {
    public static void main(String[] args) {

    }
    private int numarMatricol;
    private String prenume;
    private String nume;
    private String formatieDeStudiu;


    public Student(int numarMatricol, String prenume, String nume, String formatieDeStudiu) {
        this.numarMatricol = numarMatricol;
        this.prenume = prenume;
        this.nume = nume;
        this.formatieDeStudiu = formatieDeStudiu;
    }


    public int getNumarMatricol() { return numarMatricol; }
    public String getPrenume() { return prenume; }
    public String getNume() { return nume; }
    public String getFormatieDeStudiu() { return formatieDeStudiu; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(prenume, student.prenume) &&
                Objects.equals(nume, student.nume) &&
                Objects.equals(formatieDeStudiu, student.formatieDeStudiu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prenume, nume, formatieDeStudiu);
    }

    @Override
    public String toString() {
        return String.format("%-15d %-15s %-15s %-15s",
                numarMatricol, prenume, nume, formatieDeStudiu);
    }
}

package ro.proiectaresoftware;

public final class Student {
    private final String nume;
    private final int id;

    public Student(String nume, int id) {
        this.nume = nume;
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Student: " + nume + " (ID: " + id + ")";
    }
}

