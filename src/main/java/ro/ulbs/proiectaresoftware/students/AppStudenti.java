package ro.ulbs.proiectaresoftware.students;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class AppStudenti {

    public static void main(String[] args) {

        HashMap<Integer, Student> studenti = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader("studenti.txt"))) {
            String linie;

            while ((linie = br.readLine()) != null) {
                String[] parts = linie.split(",");

                int nrMatricol = Integer.parseInt(parts[0]);
                String nume = parts[1];
                String prenume = parts[2];
                String grupa = parts[3];

                Student s = new Student(nrMatricol, nume, prenume, grupa, 0.0);
                studenti.put(nrMatricol, s); // O(1)
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader("note_anon.txt"))) {
            String linie;

            while ((linie = br.readLine()) != null) {
                String[] parts = linie.split(",");

                int nrMatricol = Integer.parseInt(parts[0]);
                double nota = Double.parseDouble(parts[1]);

                Student s = studenti.get(nrMatricol);
                if (s != null) {
                    s.setNota(nota);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\nLista studenti cu note:");
        studenti.forEach((k, v) -> System.out.println(v));
    }
}

