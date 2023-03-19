package org.example;

import java.util.Scanner;

public class Main {
    private static final int numberOfThreads = 4;

    public static int[] createArr(int n) {
        int[] array = new int[n];

        // Заполняем массив числами от 1 до N
        for (int i = 0; i < n; i++) {
            array[i] = i + 1;
        }
        return array;
    }

    public static int calcPrimeNumb(int[] array) throws InterruptedException {
        int result = 0;

        // Делим элементы массива по потокам и стартуем потоки
        int numberOfElementsInThread = (int) Math.ceil(((double) array.length / (double) numberOfThreads));
        PrimeNumbThread[] threads = new PrimeNumbThread[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++) {
            int start = i * numberOfElementsInThread;
            int end = start + numberOfElementsInThread - 1;

            if (end > array.length - 1) {
                end = array.length - 1;
            }

            threads[i] = new PrimeNumbThread(array, start, end);
            threads[i].start();
        }

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i].join();
        }

        // Суммируем вычисленное количество простых чисел по потокам
        for (int i = 0; i < numberOfThreads; i++) {
                result += threads[i].count;
        }
        return result;
    }

    public static int calcSumNumb(int[] array) throws InterruptedException {
        int result = 0;

        // Делим элементы массива по потокам и стартуем потоки
        int numberOfElementsInThread = (int) Math.ceil(((double) array.length / (double) numberOfThreads));
        SumNumbThread[] threads = new SumNumbThread[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++) {
            int start = i * numberOfElementsInThread;
            int end = start + numberOfElementsInThread - 1;

            if (end > array.length - 1) {
                end = array.length - 1;
            }

            threads[i] = new SumNumbThread(array, start, end);
            threads[i].start();
        }

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i].join();
        }

        // Суммируем вычисленные суммы по потокам
        for (int i = 0; i < numberOfThreads; i++) {
            result += threads[i].sum;
        }
        return result;
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите N: ");
        int n = in.nextInt();
        // Создаем массив чисел от 1 до N (используется и для задания 1, и для задания 2)
        int[] array = createArr(n);
        System.out.println("Количество простых чисел: " + calcPrimeNumb(array));
        System.out.println();
        System.out.println("Сумма элементов в массиве: " + calcSumNumb(array));
    }
}