package com.eltosevenz.multithreading;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
/*
Scenario:
Multiple worker threads perform a task in phases.

At the end of each phase:
CyclicBarrier ensures all worker threads synchronize before moving to the next phase.

After completing all phases:
CountDownLatch waits for all worker threads to finish their work before proceeding to the final action.
 */
class Worker implements Runnable {
    private final int id;
    private final CyclicBarrier barrier;
    private final CountDownLatch latch;

    public Worker(int id, CyclicBarrier barrier, CountDownLatch latch) {
        this.id = id;
        this.barrier = barrier;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            // Simulating multiple phases of work
            for (int phase = 1; phase <= 3; phase++) {
                System.out.println("Worker " + id + " is working on Phase " + phase);
                Thread.sleep((long) (Math.random() * 1000)); // Simulate work

                System.out.println("Worker " + id + " finished Phase " + phase + " and waiting at barrier");
                barrier.await(); // Wait for all workers to reach the barrier
            }

            System.out.println("Worker " + id + " completed all phases.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            latch.countDown(); // Signal that this worker is done
        }
    }
}

public class CountDownLatchCyclicBarrierExample {
    public static void main(String[] args) {
        final int WORKER_COUNT = 5;

        // A CyclicBarrier to synchronize phases
        CyclicBarrier barrier = new CyclicBarrier(WORKER_COUNT, () -> {
            System.out.println("All workers reached the barrier. Moving to the next phase...\n");
        });

        // A CountDownLatch to wait for all workers to complete
        CountDownLatch latch = new CountDownLatch(WORKER_COUNT);

        // Start worker threads
        for (int i = 1; i <= WORKER_COUNT; i++) {
            new Thread(new Worker(i, barrier, latch)).start();
        }

        try {
            // Main thread waits for all workers to finish
            latch.await();
            System.out.println("All workers completed their tasks. Main thread proceeds.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

