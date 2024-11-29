package com.eltosevenz.multithreading;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/*
1. Concurrent Collections
ConcurrentHashMap: Thread-safe map operations, used here to store incremented integers.
CopyOnWriteArrayList: Thread-safe list operations, used to store string items.

2. Atomic Variables
AtomicInteger: Ensures atomic increment and retrieval of a counter variable.

3. Semaphore
Controls access to a simulated resource, allowing only 3 threads at a time.

4. Executor Framework
A thread pool of 5 threads is used to submit tasks for execution.
 */

public class AdvancedConcurrencyExample {
    // Shared resources
    private static final ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();
    private static final CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
    private static final AtomicInteger counter = new AtomicInteger(0);
    private static final Semaphore semaphore = new Semaphore(3); // Max 3 threads can access the resource
    private static final ReentrantLock lock = new ReentrantLock(); // Optional for mutual exclusion

    // Task to add items to the ConcurrentHashMap
    static class MapTask implements Runnable {
        @Override
        public void run() {
            int value = counter.incrementAndGet(); // Atomic operation
            map.put(value, "Value-" + value);
            System.out.println(Thread.currentThread().getName() + " added to map: " + value);
        }
    }

    // Task to add items to the CopyOnWriteArrayList
    static class ListTask implements Runnable {
        @Override
        public void run() {
            String value = "Item-" + counter.incrementAndGet(); // Atomic operation
            list.add(value);
            System.out.println(Thread.currentThread().getName() + " added to list: " + value);
        }
    }

    // Task to access a shared resource using Semaphore
    static class SemaphoreTask implements Runnable {
        @Override
        public void run() {
            try {
                semaphore.acquire(); // Acquire a permit
                System.out.println(Thread.currentThread().getName() + " acquired semaphore. Accessing resource...");
                Thread.sleep(1000); // Simulate work with the resource
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                System.out.println(Thread.currentThread().getName() + " releasing semaphore.");
                semaphore.release(); // Release the permit
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Submit tasks for ConcurrentHashMap
        for (int i = 0; i < 5; i++) {
            executor.submit(new MapTask());
        }

        // Submit tasks for CopyOnWriteArrayList
        for (int i = 0; i < 5; i++) {
            executor.submit(new ListTask());
        }

        // Submit tasks for Semaphore
        for (int i = 0; i < 5; i++) {
            executor.submit(new SemaphoreTask());
        }

        // Shutdown executor
        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Print final contents of ConcurrentHashMap and CopyOnWriteArrayList
        System.out.println("Final Map: " + map);
        System.out.println("Final List: " + list);
    }
}
