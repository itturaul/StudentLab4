package lab2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        List<Integer> x = new ArrayList<>();
        List<Integer> y = new ArrayList<>();
        List<Integer> xPlusY = new ArrayList<>();           // a
        Set<Integer> zSet = new TreeSet<>();                // b
        List<Integer> xMinusY = new ArrayList<>();          // c
        int p = 4;
        List<Integer> xPlusYLimitedByP = new ArrayList<>(); // d

        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            x.add(random.nextInt(11));
        }
        for (int i = 0; i < 7; i++) {
            y.add(random.nextInt(11));
        }

        Collections.sort(x);
        Collections.sort(y);

        System.out.println("Lista x (sortată): " + x);
        System.out.println("Lista y (sortată): " + y);
        System.out.println("Valoarea p: " + p);
        System.out.println("--------------------------------------------------");

        xPlusY.addAll(x);
        xPlusY.addAll(y);
        Collections.sort(xPlusY);
        System.out.println("a) xPlusY (toate elementele, sortate): " + xPlusY);


        zSet.addAll(x);
        zSet.retainAll(y);
        System.out.println("b) zSet (valori comune, unice, sortate): " + zSet);

        xMinusY.addAll(x);
        xMinusY.removeAll(y);
        System.out.println("c) xMinusY (elemente din x care nu sunt în y): " + xMinusY);

        Set<Integer> tempSet = new TreeSet<>();

        for (int num : x) {
            if (num <= p) tempSet.add(num);
        }
        for (int num : y) {
            if (num <= p) tempSet.add(num);
        }

        xPlusYLimitedByP.addAll(tempSet);
        System.out.println("d) xPlusYLimitedByP (elemente <= " + p + ", unice, sortate): " + xPlusYLimitedByP);
    }
}