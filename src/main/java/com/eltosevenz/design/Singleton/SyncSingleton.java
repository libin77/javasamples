package com.eltosevenz.design.Singleton;

/*Thread safe, But performance issue*/
public class SyncSingleton {
    private static SyncSingleton syncSingleton;

    private SyncSingleton(){}

    public static synchronized SyncSingleton getLazySingleton() {
        if(syncSingleton == null){
            syncSingleton = new SyncSingleton();
        }
        return syncSingleton;
    }
}
