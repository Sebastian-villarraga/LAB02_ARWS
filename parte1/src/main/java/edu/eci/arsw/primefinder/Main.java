package edu.eci.arsw.primefinder;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<PrimeFinderThread> threads = new ArrayList<>();

        // Rango total a evaluar
        int start = 0;
        int end = 30000000;
        int range = (end - start + 1) / 3;

        // Crear tres hilos con rangos divididos en tercios
        threads.add(new PrimeFinderThread(start, start + range - 1, 1));
        threads.add(new PrimeFinderThread(start + range, start + 2 * range - 1, 2));
        threads.add(new PrimeFinderThread(start + 2 * range, end, 3));

        // Iniciar hilos
        for (PrimeFinderThread thread : threads) {
            thread.start();
        }

        // Esperar a que terminen
        for (PrimeFinderThread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Mostrar total de primos
        int totalPrimes = 0;
        for (PrimeFinderThread thread : threads) {
            totalPrimes += thread.getPrimes().size();
        }
        System.out.println("Total of found primes: " + totalPrimes);
    }
}
