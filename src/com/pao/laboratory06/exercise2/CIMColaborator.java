package com.pao.laboratory06.exercise2;
import java.util.Scanner;

public class CIMColaborator extends Colaborator implements PersoanaFizica {
    private boolean bonus;

    @Override
    public void citeste(Scanner in) {
        super.citeste(in);
        String areBonus = in.next();
        this.bonus = "DA".equalsIgnoreCase(areBonus);
    }

    @Override
    public boolean areBonus() { return bonus; }

    @Override
    public String tipContract() { return "CIM"; }

    @Override
    public TipColaborator getTip() { return TipColaborator.CIM; }

    @Override
    public double calculeazaVenitNetAnual() {
        double net = venitLunar * 12 * 0.55;
        if (bonus) net += net * 0.10;
        return net;
    }

    @Override
    public void afiseaza() {
        System.out.printf("CIM: %s %s, venit net anual: %.2f lei\n", nume, prenume, calculeazaVenitNetAnual());
    }
}