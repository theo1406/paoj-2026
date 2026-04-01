package com.pao.laboratory06.exercise3;

public class Inginer extends Angajat implements PlataOnline, Comparable<Inginer> {
    private double sold = 5000;

    public Inginer(String nume, String prenume, String telefon, double salariu) {
        super(nume, prenume, telefon, salariu);
    }

    @Override
    public void autentificare(String user, String parola) {
        if (user == null || user.isEmpty() || parola == null || parola.isEmpty()) {
            throw new IllegalArgumentException("User sau parola invalide");
        }
        System.out.println("Inginer " + nume + " autentificat cu succes.");
    }

    @Override
    public double consultareSold() {
        return sold;
    }

    @Override
    public boolean efectuarePlata(double suma) {
        if (suma <= 0) return false;
        if (sold >= suma) {
            sold -= suma;
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Inginer altul) {
        return this.nume.compareTo(altul.nume);
    }

    public double getSalariu() {
        return salariu;
    }

    @Override
    public String toString() {
        return "Inginer " + nume + " (Salariu: " + salariu + ")";
    }
}