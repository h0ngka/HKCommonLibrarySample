package com.hongka.hkcommonlibrarysample.design_pattern.singletonpattern;

/**
 * Created by jusung.kim@sk.com on 2017/12/04
 */

public class Singleton {
    // Eager initialization
//    private static Singleton instance = new Singleton();
//    public static Singleton getInstance(){
//        return instance;
//    }

    private Singleton() {}

    // add synchronized keyword
//    private static Singleton instance;
//    public static synchronized  Singleton getInstance() {
//        if (instance == null) {
//            instance = new Singleton();
//        }
//        return instance;
//    }

    // SingletonHolder : thread-safe
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public String getMessage() {
        return "난 SingletonHolder.Singleton이당!";
    }
}
