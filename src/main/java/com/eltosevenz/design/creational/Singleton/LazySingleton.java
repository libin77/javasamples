package com.eltosevenz.design.creational.Singleton;

/*Not thread safe. But instance created when only required*/
public class LazySingleton {

    private static LazySingleton lazySingleton;

    private LazySingleton(){}

    public static LazySingleton getLazySingleton() {
        if(lazySingleton == null){
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }
}
