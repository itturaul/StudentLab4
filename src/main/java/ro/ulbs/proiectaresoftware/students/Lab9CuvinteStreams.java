package ro.proiectaresoftware.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Lab9CuvinteStreams {

    public static void main(String[] args) {
        String text = "Acesta este un program scris in java pentru expresii lambda";

        List<String> cuvinte = Arrays.asList(text.split(" "));
        System.out.println("Lista initiala de cuvinte: " + cuvinte);
        System.out.println("--------------------------------------------------");

        List<String> cuvinteLungi = cuvinte.stream()
                .filter(c -> c.length() >= 5)
                .collect(Collectors.toList());

        long numarCuvinteLungi = cuvinteLungi.size();

        System.out.println("a) Lista filtrata (lungime >= 5): " + cuvinteLungi);
        System.out.println("   Numarul de cuvinte gasite: " + numarCuvinteLungi);

        List<String> cuvinteOrdonate = cuvinteLungi.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("b) Lista noua ordonata alfabetic: " + cuvinteOrdonate);

        Optional<String> cuvantCuP = cuvinte.stream()
                .filter(c -> c.toLowerCase().startsWith("p"))
                .findFirst();

        if (cuvantCuP.isPresent()) {
            System.out.println("c) Un element care incepe cu 'p': " + cuvantCuP.get());
        } else {
            System.out.println("c) Nu s-a gasit niciun cuvant care sa inceapa cu 'p'.");
        }
    }
}