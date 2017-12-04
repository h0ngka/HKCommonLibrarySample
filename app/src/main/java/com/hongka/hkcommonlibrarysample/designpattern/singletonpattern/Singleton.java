package com.hongka.hkcommonlibrarysample.designpattern.singletonpattern;

/**
 * Created by jusung.kim@sk.com on 2017/12/04
 */

public class Singleton {
    // Eager initialization
//    private static Singleton instance = new Singleton();
//    public static Singleton getInstance(){
//        return instance;
//    }

    private static Singleton instance;

    private Singleton() {}

    public static synchronized  Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public String getMessage() {
        return "난 Single이당!";
    }
}
