package com.pao.laboratory06.exercise2;
import java.util.Scanner;

public abstract class Colaborator implements IOperatiiCitireScriere {
    protected String nume;
    protected String prenume;
    protected double venitLunar;

    public abstract double calculeazaVenitNetAnual();
    public abstract TipColaborator getTip();

    @Override
    public void citeste(Scanner in) {
        this.nume = in.next();
        this.prenume = in.next();
        this.venitLunar = in.nextDouble();
    }
}