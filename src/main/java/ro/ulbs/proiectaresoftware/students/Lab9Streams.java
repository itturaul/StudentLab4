package ro.proiectaresoftware.streams;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Lab9Streams {

    public static void main(String[] args) {
        List<Integer> numereAleatoare = new Random().ints(10, 5, 26)
                .boxed()
                .collect(Collectors.toList());

        System.out.println("Lista inițială: " + numereAleatoare);
        System.out.println("----------------------------------------");

        int suma = numereAleatoare.stream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("a) Suma elementelor: " + suma);

        int maxim = numereAleatoare.stream()
                .max(Integer::compare)
                .orElseThrow(); // Aruncă excepție doar dacă lista ar fi goală

        int minim = numereAleatoare.stream()
                .min(Integer::compare)
                .orElseThrow();

        System.out.println("b) Valoarea maximă: " + maxim);
        System.out.println("   Valoarea minimă: " + minim);

        List<Integer> filtrate = numereAleatoare.stream()
                .filter(n -> n >= 10 && n <= 20)
                .collect(Collectors.toList());
        System.out.println("c) Elemente în intervalul [10..20]: " + filtrate);

        List<Double> numereDouble = numereAleatoare.stream()
                .map(Integer::doubleValue) // sau .map(n -> (double) n)
                .collect(Collectors.toList());
        System.out.println("d) Lista transformată în Double: " + numereDouble);

        boolean contine12 = numereAleatoare.stream()
                .anyMatch(n -> n == 12);
        System.out.println("e) Se găsește valoarea 12 în listă? " + (contine12 ? "DA" : "NU"));
    }
}
