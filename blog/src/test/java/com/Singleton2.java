package com;

public class Singleton2 {

    private static Singleton2 singleton2;

    public Singleton2() {
    }

    public static synchronized Singleton2 getSingleton2() {
        if (singleton2 == null) {
            singleton2 = new Singleton2();
        }
        return singleton2;
    }
}
