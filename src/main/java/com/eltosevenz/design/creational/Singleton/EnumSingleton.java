package com.eltosevenz.design.creational.Singleton;

public enum EnumSingleton {
    Instance;

    public void doSomething(){

    }
}

/*
* Simple Implementation, built-int thread safety.
* Serilization handles automatically
* Prevent relection to create multiple instance
* But enum based not suitable for complex class operations
 */

//Declare enum
//enum DAYS  { Monday, Tuesday};


