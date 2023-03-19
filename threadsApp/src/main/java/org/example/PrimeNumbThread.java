package org.example;

public class PrimeNumbThread extends Thread {
    private int[] array;
    int start;
    int end;
    public int count;

    public PrimeNumbThread(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    // Метод для определения, является ли число простым
    private boolean isSimplestNumber(int number) {
        if (number==1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    // Метод старта потока с подсчетом простых чисел
    public void run() {
        System.out.println(String.format("Thread started in range: %d:%d", start, end));
        for (int i = start; i <= end; i++) {
            if (isSimplestNumber(array[i])) {
                count++;
            }
        }
    }
}
