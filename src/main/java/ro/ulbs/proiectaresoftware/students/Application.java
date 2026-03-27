package ro.ulbs.proiectaresoftware.students;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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


                    listaStudenti.add(new Student(nrMatricol, prenume, nume, formatieDeStudiu));
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
}

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

                Student s = new Student(nrMatricol, nume, prenume, grupa);
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

public static float gasesteNota(String prenume, String nume, HashMap<String, Student> map) {
    String cheia = nume + "_" + prenume;

    Student s = map.get(cheia); // O(1)

    if (s != null) {
        return (float) s.getNota();
    }

    return 0.0f;
}