package com.pao.laboratory06.exercise3;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== EXERCIȚIUL 3 (Bonus) ===");

        Inginer i1 = new Inginer("Popescu", "Ion", "0711", 5000);
        Inginer i2 = new Inginer("Ionescu", "Ana", "0722", 7000);
        Inginer i3 = new Inginer("Georgescu", "Dan", "0733", 6000);

        Inginer[] ingineri = {i1, i2, i3};

        System.out.println("\n--- Sortare naturală (nume alfabetic) ---");
        Arrays.sort(ingineri);
        for(Inginer i : ingineri) System.out.println(i);

        System.out.println("\n--- Sortare alternativă (salariu descrescător) ---");
        Arrays.sort(ingineri, new ComparatorInginerSalariu());
        for(Inginer i : ingineri) System.out.println(i);

        System.out.println("\n--- Acces prin referință de interfață ---");
        PlataOnline p1 = i1;
        p1.autentificare("ion.popescu", "parola123");
        System.out.println("Sold curent (prin interfață): " + p1.consultareSold());

        System.out.println("\n--- PersoanaJuridica (Test SMS) ---");
        PersoanaJuridica pj = new PersoanaJuridica("Tech", "SRL", "0700112233");
        PlataOnlineSMS pjsms = pj;
        pjsms.trimiteSMS("Plata dumneavoastră a fost procesată cu succes.");

        PersoanaJuridica pjFaraTel = new PersoanaJuridica("NoPhone", "SRL", null);
        boolean succes = pjFaraTel.trimiteSMS("Salut");
        System.out.println("Status trimitere fără telefon: " + succes + " (Așa cum era cerut)");

        System.out.println("\n--- Constante financiare (Enum) ---");
        System.out.println("TVA curent: " + ConstanteFinanciare.TVA.getValoare());

        System.out.println("\n--- Tratarea erorilor (Edge Cases) ---");
        try {
            p1.autentificare(null, "123");
        } catch (IllegalArgumentException e) {
            System.out.println("Eroare prinsă corect: " + e.getMessage());
        }

        try {
            if (p1 instanceof PlataOnlineSMS) {
                ((PlataOnlineSMS) p1).trimiteSMS("Mesaj imposibil");
            } else {
                throw new UnsupportedOperationException("Inginerul nu are capabilitate SMS!");
            }
        } catch (UnsupportedOperationException e) {
            System.out.println("Eroare prinsă corect: " + e.getMessage());
        }
    }
}