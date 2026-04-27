package com.pao.laboratory09.exercise2;

import com.pao.laboratory09.exercise1.TipTranzactie;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;

public class Main {
    private static final String OUTPUT_FILE = "output/lab09_ex2.bin";
    private static final int RECORD_SIZE = 32;

    public static void main(String[] args) throws Exception {
        new File("output").mkdirs();
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;

        int n = sc.nextInt();

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(OUTPUT_FILE))) {
            for (int i = 0; i < n; i++) {
                int id = sc.nextInt();
                double suma = sc.nextDouble();
                String data = sc.next();
                TipTranzactie tip = TipTranzactie.valueOf(sc.next());

                ByteBuffer bb = ByteBuffer.allocate(RECORD_SIZE).order(ByteOrder.LITTLE_ENDIAN);
                bb.putInt(id);
                bb.putDouble(suma);

                byte[] dataBytes = data.getBytes("US-ASCII");
                byte[] paddedData = new byte[10];
                System.arraycopy(dataBytes, 0, paddedData, 0, Math.min(dataBytes.length, 10));
                for (int j = dataBytes.length; j < 10; j++) paddedData[j] = ' ';
                bb.put(paddedData);

                bb.put((byte) (tip == TipTranzactie.CREDIT ? 0 : 1));
                bb.put((byte) 0);
                bb.put(new byte[8]);

                dos.write(bb.array());
            }
        }

        try (RandomAccessFile raf = new RandomAccessFile(OUTPUT_FILE, "rw")) {
            while (sc.hasNext()) {
                String cmd = sc.next();
                if (cmd.equals("READ")) {
                    int idx = sc.nextInt();
                    printRecord(raf, idx);
                } else if (cmd.equals("UPDATE")) {
                    int idx = sc.nextInt();
                    String statusStr = sc.next();
                    byte statusByte = (byte) (statusStr.equals("PROCESSED") ? 1 : 2);

                    raf.seek((long) idx * RECORD_SIZE + 23);
                    raf.writeByte(statusByte);
                    System.out.println("Updated [" + idx + "]: " + statusStr);
                } else if (cmd.equals("PRINT_ALL")) {
                    for (int i = 0; i < n; i++) {
                        printRecord(raf, i);
                    }
                }
            }
        }
    }

    private static void printRecord(RandomAccessFile raf, int idx) throws Exception {
        raf.seek((long) idx * RECORD_SIZE);
        byte[] bytes = new byte[RECORD_SIZE];
        raf.readFully(bytes);

        ByteBuffer bb = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN);
        int id = bb.getInt();
        double suma = bb.getDouble();

        byte[] dataBytes = new byte[10];
        bb.get(dataBytes);
        String data = new String(dataBytes, "US-ASCII").trim();

        byte tipByte = bb.get();
        String tip = tipByte == 0 ? "CREDIT" : "DEBIT";

        byte statusByte = bb.get();
        String status = statusByte == 0 ? "PENDING" : (statusByte == 1 ? "PROCESSED" : "REJECTED");

        System.out.printf(Locale.US, "[%d] id=%d data=%s tip=%s suma=%.2f RON status=%s\n",
                idx, id, data, tip, suma, status);
    }
}