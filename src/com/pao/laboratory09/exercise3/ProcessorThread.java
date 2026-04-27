package com.pao.laboratory09.exercise3;

public class ProcessorThread implements Runnable {
    public volatile boolean activ = true;
    private final CoadaTranzactii coada;

    public ProcessorThread(CoadaTranzactii coada) {
        this.coada = coada;
    }

    @Override
    public void run() {
        try {
            while (activ) {
                String t = coada.extrage();
                if (t != null) {
                    System.out.println("[Processor] Factura pentru " + t + " | 2026-05-14");
                    Thread.sleep(80);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}