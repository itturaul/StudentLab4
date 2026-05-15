package ro.ulbs.proiectaresoftware.students;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Application {

    public static void main(String[] args) {
        Path caleIn = Paths.get("studenti_in.txt");
        Path caleOut = Paths.get("studenti_out.txt");

        List<Student> listaStudenti = new ArrayList<>();

        try {

            List<String> linii = Files.readAllLines(caleIn);

            for (String linie : linii) {
                if (linie.trim().isEmpty()) continue;


                String[] date = linie.split(",");

                if (date.length == 4) {
                    int nrMatricol = Integer.parseInt(date[0].trim());
                    String prenume = date[1].trim();
                    String nume = date[2].trim();
                    String formatieDeStudiu = date[3].trim();


                    listaStudenti.add(new Student(nrMatricol, prenume, nume, formatieDeStudiu, 0.0));
                }
            }


            System.out.println(String.format("%-15s %-15s %-15s %-15s",
                    "Nr. Matricol", "Prenume", "Nume", "Formație"));
            System.out.println("    ");
            for (Student s : listaStudenti) {
                System.out.println(s);
            }


            Collections.sort(listaStudenti, new Comparator<Student>() {
                @Override
                public int compare(Student s1, Student s2) {
                    return s1.getNume().compareToIgnoreCase(s2.getNume());
                }
            });

//tema de casa

            Collections.sort(listaStudenti, new Comparator<Student>() {



                @Override
                public int compare(Student s1, Student s2) {

                    int res = s1.getFormatieDeStudiu().compareToIgnoreCase(s2.getFormatieDeStudiu());

                    if (res == 0) {

                        return s1.getNume().compareToIgnoreCase(s2.getNume());

                    }


                    return res;

                }
            });

            List<String> deScrisSortat  = new ArrayList<>();
            for(Student s : listaStudenti){

                deScrisSortat.add(s.toString());

                }
            Files.write(Paths.get("studenti_out_sorted.txt"),deScrisSortat );





            List<String> deScris = new ArrayList<>();
            for (Student s : listaStudenti) {
                deScris.add(s.toString());
            }
            Files.write(caleOut, deScris);

            System.out.println("\n Studenții au fost sortați și salvați în fisier.");

        } catch (IOException e) {
            System.err.println("Eroare: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Eroare: " + e.getMessage());
        }
    }
    public static float gasesteNota(String prenume, String nume, HashMap<String, Student> map) {
        String cheia = nume + "_" + prenume;

        Student s = map.get(cheia); // O(1)

        if (s != null) {
            return (float) s.getNota();
        }

        return 0.0f;
    }
}


