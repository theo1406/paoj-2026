package com.pao.laboratory08.exercise2;

import com.pao.laboratory08.exercise1.Student;
import com.pao.laboratory08.exercise1.Adresa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String FILE_PATH = "src/com/pao/laboratory08/tests/studenti.txt";
    private static final String OUT_PATH = "rezultate.txt";

    public static void main(String[] args) throws Exception {
        List<Student> studenti = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String nume = parts[0].trim();
                    int varsta = Integer.parseInt(parts[1].trim());
                    String oras = parts[2].trim();
                    String strada = parts[3].trim();
                    studenti.add(new Student(nume, varsta, new Adresa(oras, strada)));
                }
            }
        }

        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;
        int prag = scanner.nextInt();

        List<Student> filtered = new ArrayList<>();
        for (Student s : studenti) {
            if (s.getVarsta() >= prag) {
                filtered.add(s);
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(OUT_PATH))) {
            for (Student s : filtered) {
                bw.write(s.toString());
                bw.newLine();
            }

            System.out.println("Filtru: varsta >= " + prag);
            System.out.println("Rezultate: " + filtered.size() + " studenti\n");
            for (Student s : filtered) {
                System.out.println(s);
            }
            System.out.println("\nScris in: " + OUT_PATH);
        }
    }
}