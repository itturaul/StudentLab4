import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class AplicatieCuBursaTest {

    @Test
    @DisplayName("Test structurat AAA pentru verificarea algoritmului de sortare")
    public void testSorteazaStudenti() {
        AplicatieCuBursa app = new AplicatieCuBursa();
        List<StudentBursier> listaNesortata = app.genereaza();

        List<StudentBursier> rezultatulSortat = app.sorteaza(listaNesortata);

        assertEquals(5, rezultatulSortat.size(), "Dimensiunea listei nu ar trebui să se modifice după sortare.");

        assertEquals("ISM141/1", rezultatulSortat.get(0).getFormatieStudiu());
        assertEquals("Mihalcea", rezultatulSortat.get(0).getNume());


        StudentBursier bursaMica = rezultatulSortat.get(3);
        StudentBursier bursaMare = rezultatulSortat.get(4);

        assertEquals("Bianca", bursaMica.getPrenume());
        assertEquals(100.00, bursaMica.getCuantumBursa(), 0.01);

        assertEquals("Bianca", bursaMare.getPrenume());
        assertEquals(780.80, bursaMare.getCuantumBursa(), 0.01);

        assertTrue(bursaMica.getCuantumBursa() < bursaMare.getCuantumBursa(),
                "Sortarea finală după cuantumul bursei nu a funcționat corect.");
    }
}