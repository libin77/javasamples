package com.eltosevenz.multithreading;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

/*
Deadlock: A situation where two or more threads are blocked forever, waiting for each other to release locks.

Strategies to Avoid Deadlocks:
Lock ordering: Always acquire locks in a consistent order.
Timeouts: Use tryLock() with a timeout.
Avoid Nested Locks: Minimize the need for multiple locks.
*/

class Deadlock {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    //DeadLock
    public void task1() {
        synchronized (lock1) {
            synchronized (lock2) {
                System.out.println("Task 1");
            }
        }
    }

    public void task2() {
        synchronized (lock2) {
            synchronized (lock1) {
                System.out.println("Task 2");
            }
        }
    }
}

public class TryLockTimeoutExample {

    private static final ReentrantLock lock1 = new ReentrantLock();
    private static final ReentrantLock lock2 = new ReentrantLock();

    static class Task1 implements Runnable {
        @Override
        public void run() {
            try {
                // Try to acquire lock1
                if (lock1.tryLock(1000, TimeUnit.MILLISECONDS)) {
                    System.out.println(Thread.currentThread().getName() + " acquired lock1");
                    Thread.sleep(500); // Simulate work

                    // Try to acquire lock2
                    if (lock2.tryLock(1000, TimeUnit.MILLISECONDS)) {
                        try {
                            System.out.println(Thread.currentThread().getName() + " acquired lock2");
                            // Perform work
                        } finally {
                            lock2.unlock();
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + " could not acquire lock2");
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + " could not acquire lock1");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(Thread.currentThread().getName() + " was interrupted");
            } finally {
                if (lock1.isHeldByCurrentThread()) { // Correct usage of isHeldByCurrentThread
                    lock1.unlock();
                }
            }
        }
    }

    static class Task2 implements Runnable {
        @Override
        public void run() {
            try {
                // Try to acquire lock2
                if (lock2.tryLock(1000, TimeUnit.MILLISECONDS)) {
                    System.out.println(Thread.currentThread().getName() + " acquired lock2");
                    Thread.sleep(500); // Simulate work

                    // Try to acquire lock1
                    if (lock1.tryLock(1000, TimeUnit.MILLISECONDS)) {
                        try {
                            System.out.println(Thread.currentThread().getName() + " acquired lock1");
                            // Perform work
                        } finally {
                            lock1.unlock();
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + " could not acquire lock1");
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + " could not acquire lock2");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(Thread.currentThread().getName() + " was interrupted");
            } finally {
                if (lock2.isHeldByCurrentThread()) { // Correct usage of isHeldByCurrentThread
                    lock2.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Task1(), "Thread-1");
        Thread t2 = new Thread(new Task2(), "Thread-2");

        t1.start();
        t2.start();
    }
}

