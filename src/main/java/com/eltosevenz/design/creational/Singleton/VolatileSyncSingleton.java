package com.eltosevenz.design.creational.Singleton;

/*
* Thread Safe, improved performance. But complex to implement also volatile required
*
* volatile:
* * The volatile keyword ensures visibility of changes to variables across threads.
* * It guarantees that reads and writes happen directly to main memory, not thread-local caches.
*
*
* ThreadLocal:
* * ThreadLocal provides thread-local variables, ensuring each thread has its own isolated copy.
* * Use Case: Storing per-thread data like user sessions or database connections.
* */
public class VolatileSyncSingleton {
    private static volatile VolatileSyncSingleton volatileSyncSingleton;

    private VolatileSyncSingleton(){}

    public static VolatileSyncSingleton getSyncBlockSingleton() {
        if(volatileSyncSingleton ==null){
            synchronized (VolatileSyncSingleton.class){
                if(volatileSyncSingleton ==null){
                    volatileSyncSingleton = new VolatileSyncSingleton();
                }
            }
        }
        return volatileSyncSingleton;
    }
}
