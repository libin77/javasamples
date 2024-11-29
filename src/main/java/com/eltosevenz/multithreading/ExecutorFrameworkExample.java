package com.eltosevenz.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
The Executor Framework in Java provides a way to manage and control thread execution efficiently using thread pools.

Scenario:
A program calculates the square of numbers using a thread pool.
 */
class SquareTask implements Runnable {
    private final int number;

    public SquareTask(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " calculated square of " + number + " as: " + (number * number));
    }
}

public class ExecutorFrameworkExample {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5, 6};

        // Create a fixed thread pool with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int number : numbers) {
            executor.submit(new SquareTask(number)); // Submit tasks to the executor
        }

        // Shut down the executor after all tasks are submitted
        executor.shutdown();
    }
}
