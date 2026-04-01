package com.pao.laboratory06.exercise3;

import java.util.ArrayList;
import java.util.List;

public class PersoanaJuridica extends Persoana implements PlataOnlineSMS {
    private List<String> smsTrimise = new ArrayList<>();
    private double sold = 10000; // Sold simulat

    public PersoanaJuridica(String nume, String prenume, String telefon) {
        super(nume, prenume, telefon);
    }

    @Override
    public void autentificare(String user, String parola) {
        if (user == null || user.isEmpty() || parola == null || parola.isEmpty()) {
            throw new IllegalArgumentException("User sau parola invalide");
        }
        System.out.println("PJ " + nume + " autentificat cu succes.");
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
    public boolean trimiteSMS(String mesaj) {
        if (telefon == null || telefon.trim().isEmpty()) return false;
        if (mesaj == null || mesaj.trim().isEmpty()) return false;

        smsTrimise.add(mesaj);
        System.out.println("SMS trimis către " + telefon + ": " + mesaj);
        return true;
    }

    public List<String> getSmsTrimise() {
        return smsTrimise;
    }
}