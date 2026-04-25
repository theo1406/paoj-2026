package com.pao.laboratory07.exercise3;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;

        int n = Integer.parseInt(sc.nextLine().trim());
        List<Comanda> comenzi = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            String[] tokens = line.split(" ");

            if (tokens[0].equals("STANDARD")) {
                comenzi.add(new ComandaStandard(tokens[1], Double.parseDouble(tokens[2]), tokens[3]));
            } else if (tokens[0].equals("DISCOUNTED")) {
                comenzi.add(new ComandaRedusa(tokens[1], Double.parseDouble(tokens[2]), Integer.parseInt(tokens[3]), tokens[4]));
            } else if (tokens[0].equals("GIFT")) {
                comenzi.add(new ComandaGratuita(tokens[1], tokens[2]));
            }
        }

        for (Comanda c : comenzi) {
            System.out.println(c.descriere());
        }

        while (sc.hasNextLine()) {
            String comandaStr = sc.nextLine().trim();
            if (comandaStr.isEmpty()) continue;
            String[] cmdTokens = comandaStr.split(" ");

            switch (cmdTokens[0]) {
                case "STATS" -> {
                    System.out.println("\n--- STATS ---");
                    Map<String, Double> stats = comenzi.stream()
                            .collect(Collectors.groupingBy(Comanda::getTip, Collectors.averagingDouble(Comanda::pretFinal)));

                    if (stats.containsKey("STANDARD")) System.out.printf("STANDARD: medie = %.2f lei\n", stats.get("STANDARD"));
                    if (stats.containsKey("DISCOUNTED")) System.out.printf("DISCOUNTED: medie = %.2f lei\n", stats.get("DISCOUNTED"));
                    if (stats.containsKey("GIFT")) System.out.printf("GIFT: medie = %.2f lei\n", stats.get("GIFT"));
                }
                case "FILTER" -> {
                    double threshold = Double.parseDouble(cmdTokens[1]);
                    System.out.printf("\n--- FILTER (>= %s) ---\n", cmdTokens[1]);
                    comenzi.stream()
                            .filter(c -> c.pretFinal() >= threshold)
                            .forEach(c -> System.out.println(c.descriere()));
                }
                case "SORT" -> {
                    System.out.println("\n--- SORT (by client, then by pret) ---");
                    comenzi.stream()
                            .sorted(Comparator.comparing(Comanda::getClient).thenComparing(Comanda::pretFinal))
                            .forEach(c -> System.out.println(c.descriere()));
                }
                case "SPECIAL" -> {
                    System.out.println("\n--- SPECIAL (discount > 15%) ---");
                    comenzi.stream()
                            .filter(c -> c instanceof ComandaRedusa cr && cr.getDiscountProcent() > 15)
                            .forEach(c -> System.out.println(c.descriere()));
                }
                case "QUIT" -> {
                    return;
                }
            }
        }
    }
}