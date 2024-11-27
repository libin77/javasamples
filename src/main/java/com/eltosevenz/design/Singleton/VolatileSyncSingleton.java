package com.eltosevenz.design.Singleton;

/*Thread Safe, improved performance. But complex to implement also volatile required*/
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
