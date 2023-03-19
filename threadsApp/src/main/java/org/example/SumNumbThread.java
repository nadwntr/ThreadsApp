package org.example;

public class SumNumbThread extends Thread {
    private int[] array;
    int start;
    int end;
    public int sum;

    public SumNumbThread(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    // Метод старта потока с подсчетом суммы чисел
    public void run() {
        System.out.println(String.format("Thread started in range: %d:%d", start, end));
        for (int i = start; i <= end; i++) {
            sum += array[i];
        }
    }
}
