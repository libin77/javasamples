package com.eltosevenz.design.Singleton;

/*Inner class used. Best practice using jvm inbuilt. thread safe. lazy load . no sync overhead (BillPugh)*/
public class InnerClassSingleton {

    private InnerClassSingleton(){}

    private static class INNER {
        private static final InnerClassSingleton billPughSIngleton = new InnerClassSingleton();
    }

    public static InnerClassSingleton getInstance(){
        return INNER.billPughSIngleton;
    }
}
