package com.pao.laboratory09.exercise1;

import java.io.*;
import java.util.*;

public class Main {
    private static final String OUTPUT_FILE = "output/lab09_ex1.ser";

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        new File("output").mkdirs();
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;

        int n = sc.nextInt();
        List<Tranzactie> tranzactii = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int id = sc.nextInt();
            double suma = sc.nextDouble();
            String data = sc.next();
            String src = sc.next();
            String dst = sc.next();
            TipTranzactie tip = TipTranzactie.valueOf(sc.next());

            Tranzactie t = new Tranzactie(id, suma, data, src, dst, tip);
            t.note = "procesat";
            tranzactii.add(t);
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(OUTPUT_FILE))) {
            oos.writeObject(tranzactii);
        }

        List<Tranzactie> deserializate;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(OUTPUT_FILE))) {
            deserializate = (List<Tranzactie>) ois.readObject();
        }

        while (sc.hasNext()) {
            String cmd = sc.next();
            if (cmd.equals("LIST")) {
                for (Tranzactie t : deserializate) {
                    System.out.println(t.toString());
                }
            } else if (cmd.equals("FILTER")) {
                String prefix = sc.next();
                boolean gasit = false;
                for (Tranzactie t : deserializate) {
                    if (t.data.startsWith(prefix)) {
                        System.out.println(t.toString());
                        gasit = true;
                    }
                }
                if (!gasit) System.out.println("Niciun rezultat.");
            } else if (cmd.equals("NOTE")) {
                int id = sc.nextInt();
                boolean gasit = false;
                for (Tranzactie t : deserializate) {
                    if (t.id == id) {
                        System.out.println("NOTE[" + id + "]: " + t.note);
                        gasit = true;
                        break;
                    }
                }
                if (!gasit) System.out.println("NOTE[" + id + "]: not found");
            }
        }
    }
}