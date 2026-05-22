package com.pao.laboratory10.exercise3;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Tranzactie> tranzactii = Arrays.asList(
                new Tranzactie(1, 1500.0, "2024-01-10", TipTranzactie.CREDIT, "RO11INGB123"),
                new Tranzactie(2, 200.5, "2024-01-15", TipTranzactie.DEBIT, "RO11INGB123"),
                new Tranzactie(3, 450.0, "2024-01-22", TipTranzactie.DEBIT, "RO22BTRL456"),
                new Tranzactie(4, 3000.0, "2024-02-05", TipTranzactie.CREDIT, "RO33BRD789"),
                new Tranzactie(5, 120.0, "2024-02-14", TipTranzactie.DEBIT, "RO11INGB123"),
                new Tranzactie(6, 85.0, "2024-02-28", TipTranzactie.DEBIT, "RO22BTRL456"),
                new Tranzactie(7, 4100.0, "2024-03-01", TipTranzactie.CREDIT, "RO99BCR999"),
                new Tranzactie(8, 600.0, "2024-03-10", TipTranzactie.DEBIT, "RO11INGB123"),
                new Tranzactie(9, 150.0, "2024-03-15", TipTranzactie.DEBIT, "RO33BRD789"),
                new Tranzactie(10, 2000.0, "2024-03-25", TipTranzactie.CREDIT, "RO22BTRL456")
        );

        System.out.println("--- 1. Tranzactii de tip CREDIT ---");
        tranzactii.stream()
                .filter(t -> t.getTip() == TipTranzactie.CREDIT)
                .forEach(System.out::println);

        System.out.println("\n--- 2. Suma totala procesata ---");
        double total = tranzactii.stream()
                .mapToDouble(Tranzactie::getSuma)
                .sum();
        System.out.printf(Locale.US, "Total procesat: %.2f RON\n", total);

        System.out.println("\n--- 3. Suma totala per luna ---");
        Map<String, Double> sumaPerLuna = tranzactii.stream()
                .collect(Collectors.groupingBy(
                        t -> t.getData().substring(0, 7),
                        TreeMap::new,
                        Collectors.summingDouble(Tranzactie::getSuma)
                ));
        sumaPerLuna.forEach((luna, suma) ->
                System.out.printf(Locale.US, "%s: %.2f RON\n", luna, suma));

        System.out.println("\n--- 4. Top 3 tranzactii ---");
        tranzactii.stream()
                .sorted(Comparator.comparingDouble(Tranzactie::getSuma).reversed())
                .limit(3)
                .forEach(System.out::println);


        System.out.println("\n--- 5. Conturi sursa unice ---");
        List<String> conturiUnice = tranzactii.stream()
                .map(Tranzactie::getContSursa)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Conturi sursa unice: " + conturiUnice);

        System.out.println("\n--- 6. Suma medie per tranzactie ---");
        double medie = tranzactii.stream()
                .mapToDouble(Tranzactie::getSuma)
                .average()
                .orElse(0.0);
        System.out.printf(Locale.US, "Suma medie: %.2f RON\n", medie);


        System.out.println("\n--- 7. Generare Extras de Cont pe Luni ---");
        Map<String, List<Tranzactie>> tranzactiiPerLuna = tranzactii.stream()
                .collect(Collectors.groupingBy(
                        t -> t.getData().substring(0, 7),
                        TreeMap::new,
                        Collectors.toList()
                ));

        tranzactiiPerLuna.forEach((luna, lista) -> {
            double totalLuna = lista.stream().mapToDouble(Tranzactie::getSuma).sum();
            System.out.printf(Locale.US, "EXTRAS DE CONT - %s: %d tranzactii, total: %.2f RON\n",
                    luna, lista.size(), totalLuna);
        });
    }
}