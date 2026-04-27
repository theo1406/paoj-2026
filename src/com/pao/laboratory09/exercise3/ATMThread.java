package com.pao.laboratory09.exercise3;

public class ATMThread extends Thread {
    private final int id;
    private final CoadaTranzactii coada;

    public ATMThread(int id, CoadaTranzactii coada) {
        this.id = id;
        this.coada = coada;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 4; i++) {
                String tx = "Tranzactie #" + id + "0" + i + " " + (100 * i) + " RON";
                coada.adauga(tx, String.valueOf(id));
                System.out.println("[ATM-" + id + "] trimite: " + tx);
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}