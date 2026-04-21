package com.pao.laboratory08.exercise1;

public class Student implements Cloneable {
    private String nume;
    private int varsta;
    private Adresa adresa;

    public Student(String nume, int varsta, Adresa adresa) {
        this.nume = nume;
        this.varsta = varsta;
        this.adresa = adresa;
    }

    public String getNume() { return nume; }
    public int getVarsta() { return varsta; }
    public Adresa getAdresa() { return adresa; }

    public Student shallowClone() {
        try {
            return (Student) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clonarea nu este suportata", e);
        }
    }

    public Student deepClone() {
        try {
            Student clona = (Student) super.clone();
            clona.adresa = (Adresa) this.adresa.clone();
            return clona;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clonarea nu este suportata", e);
        }
    }

    @Override
    public String toString() {
        return "Student{nume='" + nume + "', varsta=" + varsta + ", adresa=" + adresa + "}";
    }
}