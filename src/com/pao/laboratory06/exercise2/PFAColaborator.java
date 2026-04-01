package com.pao.laboratory06.exercise2;
import java.util.Scanner;

public class PFAColaborator extends Colaborator implements PersoanaFizica {
    private double cheltuieliLunare;

    @Override
    public void citeste(Scanner in) {
        super.citeste(in);
        this.cheltuieliLunare = in.nextDouble();
    }

    @Override
    public String tipContract() { return "PFA"; }

    @Override
    public TipColaborator getTip() { return TipColaborator.PFA; }

    @Override
    public double calculeazaVenitNetAnual() {
        double venitNet = (venitLunar - cheltuieliLunare) * 12;
        double smbAnual = 48600; // Valoarea din enunț

        double impozit = 0.10 * venitNet;

        double cass = 0;
        if (venitNet < 6 * smbAnual) cass = 0.10 * (6 * smbAnual);
        else if (venitNet <= 72 * smbAnual) cass = 0.10 * venitNet;
        else cass = 0.10 * (72 * smbAnual);

        double cas = 0;
        if (venitNet >= 12 * smbAnual && venitNet <= 24 * smbAnual) {
            cas = 0.25 * (12 * smbAnual);
        } else if (venitNet > 24 * smbAnual) {
            cas = 0.25 * (24 * smbAnual);
        }

        return venitNet - impozit - cass - cas;
    }

    @Override
    public void afiseaza() {
        System.out.printf("PFA: %s %s, venit net anual: %.2f lei\n", nume, prenume, calculeazaVenitNetAnual());
    }
}