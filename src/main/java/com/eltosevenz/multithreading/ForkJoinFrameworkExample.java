package com.eltosevenz.multithreading;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

/*
The Fork/Join Framework is designed for parallelism, breaking down large tasks into smaller tasks and joining their results.

Scenario:
A program calculates the sum of an array using the Fork/Join Framework.
Example RealTime: Recursive problems like quicksort, Fibonacci series, etc.
 */
class SumTask extends RecursiveTask<Integer> {
    private final int[] array;
    private final int start;
    private final int end;

    private static final int THRESHOLD = 3; // Threshold for splitting tasks

    public SumTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        // If the task size is small, compute directly
        if ((end - start) <= THRESHOLD) {
            int sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            System.out.println(Thread.currentThread().getName() + " calculated sum from index " + start + " to " + (end - 1) + " as: " + sum);
            return sum;
        } else {
            // Split task into two subtasks
            int mid = (start + end) / 2;
            SumTask leftTask = new SumTask(array, start, mid);
            SumTask rightTask = new SumTask(array, mid, end);

            // Fork the subtasks
            leftTask.fork();
            rightTask.fork();

            // Join the results
            return leftTask.join() + rightTask.join();
        }
    }
}

public class ForkJoinFrameworkExample {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8};

        // Create ForkJoinPool
        ForkJoinPool pool = new ForkJoinPool();

        // Submit the main task
        int result = pool.invoke(new SumTask(array, 0, array.length));

        System.out.println("Final Sum: " + result);
    }
}

