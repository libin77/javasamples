package com.eltosevenz.multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Refer image synchronized vs ReentrantLock for ref
// synchronized(this) -- example
class SharedCounterSync {
    private int counter = 0; // Shared resource

    // Increment method: critical section
    public void increment() {
        synchronized (this) { // Synchronize on the current instance
            counter++;
            System.out.println(Thread.currentThread().getName() + " incremented counter to: " + counter);
        }
    }

    // Get counter value
    public int getCounter() {
        return counter;
    }
}

// Lock Example
class SharedCounter {
    private int counter = 0; // Shared resource
    private Lock lock = new ReentrantLock(); // Lock to protect the critical section

    // Increment method: critical section
    public void increment() {
        lock.lock(); // Acquire the lock
        try {
            counter++;
            System.out.println(Thread.currentThread().getName() + " incremented counter to: " + counter);
        } finally {
            lock.unlock(); // Release the lock
        }
    }

    // Get counter value
    public int getCounter() {
        return counter;
    }
}

class CounterTask implements Runnable {
    private SharedCounter sharedCounter;

    public CounterTask(SharedCounter sharedCounter) {
        this.sharedCounter = sharedCounter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) { // Each thread increments the counter 5 times
            sharedCounter.increment();
            try {
                Thread.sleep(100); // Simulate some delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class LockThreadCritical {
    public static void main(String[] args) {
        SharedCounter sharedCounter = new SharedCounter();

        // Create multiple threads
        Thread t1 = new Thread(new CounterTask(sharedCounter), "Thread-1");
        Thread t2 = new Thread(new CounterTask(sharedCounter), "Thread-2");
        Thread t3 = new Thread(new CounterTask(sharedCounter), "Thread-3");

        // Start the threads
        t1.start();
        t2.start();
        t3.start();

        // Wait for all threads to complete
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print final counter value
        System.out.println("Final Counter Value: " + sharedCounter.getCounter());
    }
}
