package com.pao.laboratory09.exercise3;

import java.util.LinkedList;
import java.util.Queue;

public class CoadaTranzactii {
    private final Queue<String> coada = new LinkedList<>();
    private final int capacitate = 5;
    private boolean esteDeschisa = true;

    public synchronized void adauga(String tranzactie, String atmId) throws InterruptedException {
        while (coada.size() == capacitate) {
            System.out.println("[ATM-" + atmId + "] astept loc...");
            wait();
        }
        coada.add(tranzactie);
        notifyAll();
    }

    public synchronized String extrage() throws InterruptedException {
        while (coada.isEmpty() && esteDeschisa) {
            wait();
        }
        if (coada.isEmpty()) return null;

        String t = coada.poll();
        notifyAll();
        return t;
    }

    public synchronized void inchide() {
        esteDeschisa = false;
        notifyAll();
    }
}