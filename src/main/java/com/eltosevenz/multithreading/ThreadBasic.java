package com.eltosevenz.multithreading;
/*
Thread Class vs. Runnable Interface:
Thread: Represents a thread of execution.
Runnable: Used to define the task to be executed by a thread.

Thread Lifecycle:
New, Runnable, Running, Blocked/Waiting, Terminated.

Concurrency vs. Parallelism:
Concurrency: Multiple tasks making progress (may not run simultaneously).
Parallelism: Tasks running at the same time on multiple cores.

Thread States: wait(), notify(), and notifyAll() for thread communication.
 */
class MyThread extends Thread{
    public void run(){
        System.out.println("Inside Thread -- Thread class");
    }
}

class MyRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("Inside Thread -- Runnable Interface");
    }
}
public class ThreadBasic {

    public static void main(String[] arg){
        //Class Thread
        MyThread myThread = new MyThread();
        myThread.start();

        //Interface Runnable
        MyRunnable myRunnable = new MyRunnable();
        new Thread(myRunnable).start();

        //Lambda Expression
        Runnable runnable = ()->System.out.println("Inside Thread -- Lambda");
        new Thread(runnable).start();

    }
}
