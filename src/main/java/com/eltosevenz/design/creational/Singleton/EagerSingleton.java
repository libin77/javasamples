package com.eltosevenz.design.creational.Singleton;

/* Thread safe but wastage of resource as instance creation at start*/
public class EagerSingleton {

    private static final EagerSingleton eagerSingleton = new EagerSingleton();

    private EagerSingleton(){}

    public static EagerSingleton getEagerSingleton() {
        return eagerSingleton;
    }
}
