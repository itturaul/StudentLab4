import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

final class Student {
    private final int id;
    private final String prenume;
    private final String nume;
    private final String formatieStudiu;

    public Student(int id, String prenume, String nume, String formatieStudiu) {
        this.id = id;
        this.prenume = prenume;
        this.nume = nume;
        this.formatieStudiu = formatieStudiu;
    }

    public int getId() { return id; }
    public String getPrenume() { return prenume; }
    public String getNume() { return nume; }
    public String getFormatieStudiu() { return formatieStudiu; }


    public Student schimbaFormatia(String nouaFormatie) {
        return new Student(this.id, this.prenume, this.nume, nouaFormatie);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id &&
                Objects.equals(prenume, student.prenume) &&
                Objects.equals(nume, student.nume) &&
                Objects.equals(formatieStudiu, student.formatieStudiu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, prenume, nume, formatieStudiu);
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' + ", formatie='" + formatieStudiu + '\'' + '}';
    }
}

public class Main {

    public static void main(String[] args) {
        List<Student> listaInitiala = new ArrayList<>();
        listaInitiala.add(new Student(1, "Popa", "Alis", "TI21/1"));
        listaInitiala.add(new Student(2, "Ionescu", "Ionut", "TI21/1"));
        listaInitiala.add(new Student(3, "Vasilescu", "Andrei", "TI21/1"));
        listaInitiala.add(new Student(4, "Georgescu", "Maria", "TI21/1"));
        listaInitiala.add(new Student(5, "Mihalcea", "Elena", "TI21/1"));

        System.out.println("--- Lista Inițială ---");
        listaInitiala.forEach(System.out.println);
        System.out.println("--------------------------------------------------\n");

        String formatiaA = "TI21/A";
        String formatiaB = "TI21/B";

        List<Student> nouaListaModificata = new ArrayList<>();

        int dimensiune = listaInitiala.size();
        int limitaPrimaFormatie = dimensiune / 2;
        if (dimensiune % 2 != 0) {
            limitaPrimaFormatie += 1; // Prima formație va avea studentul în plus
        }

        for (int i = 0; i < dimensiune; i++) {
            Student studentOriginal = listaInitiala.get(i);
            Student studentMutat;

            if (i < limitaPrimaFormatie) {
                studentMutat = studentOriginal.schimbaFormatia(formatiaA);
            } else {
                studentMutat = studentOriginal.schimbaFormatia(formatiaB);
            }
            nouaListaModificata.add(studentMutat);
        }

        System.out.println("--- Noua Listă (După Împărțire și Mutare Imutabilă) ---");
        for (Student s : nouaListaModificata) {
            System.out.println(s);
        }

        System.out.println("\n[Info] Număr studenți în " + formatiaA + ": " +
                nouaListaModificata.stream().filter(s -> s.getFormatieStudiu().equals(formatiaA)).count());
        System.out.println("[Info] Număr studenți în " + formatiaB + ": " +
                nouaListaModificata.stream().filter(s -> s.getFormatieStudiu().equals(formatiaB)).count());
    }
}