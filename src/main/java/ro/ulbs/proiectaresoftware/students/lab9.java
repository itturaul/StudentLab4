package ro.ulbs.proiectaresoftware.students;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StudentStreamExercises {
    public static void main(String[] args) {
        List<Student> studentiCuNote = Arrays.asList(
                new Student(1025, "Andrei", "Popa", "ISM141/2", 8.70),
                new Student(1024, "Ioan", "Mihalcea", "ISM141/1", 10),
                new Student(1026, "Anamaria", "Prodan", "TI131/1", 8.90),
                new Student(1029, "Bianca", "Popescu", "TI131/1", 10),
                new Student(1029, "Maria", "Pana", "TI131/2", 4.10),
                new Student(1029, "Gabriela", "Mohanu", "TI131/2", 7.33),
                new Student(1029, "Marius", "Nasta", "TI131/2", 3.20),
                new Student(1029, "Marius", "Nasta", "TI131/1", 5.12),
                new Student(1029, "Andrei", "Dobrescu", "TI131/2", 2.22)
        );

        System.out.println("--- a) Studenți cu nota 10 ---");
        studentiCuNote.stream()
                .filter(s -> s.getNota() == 10)
                .forEach(System.out::println);

        System.out.println("\n--- b) Studenți cu nota sub 5 ---");
        studentiCuNote.stream()
                .filter(s -> s.getNota() < 5)
                .forEach(System.out::println);

        List<Student> listaModificata = studentiCuNote.stream()
                .map(s -> {
                    if (s.getNota() < 4) {
                        return new Student(s.getNumarMatricol(), s.getPrenume(), s.getNume(), s.getFormatieDeStudiu(), 4.0);
                    }
                    return s;
                })
                .collect(Collectors.toList());
        System.out.println("\n--- c) Lista cu note rotunjite la minim 4 ---");
        listaModificata.forEach(System.out::println);

        double sumaNote = studentiCuNote.stream()
                .map(Student::getNota)
                .reduce(0.0, Double::sum);
        System.out.println("\n--- d) Suma notelor: " + String.format("%.2f", sumaNote));

        double media = sumaNote / studentiCuNote.size();
        System.out.println("--- e) Media notelor: " + String.format("%.2f", media));
    }
}