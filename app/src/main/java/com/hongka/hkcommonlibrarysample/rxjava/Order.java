package com.hongka.hkcommonlibrarysample.rxjava;

/**
 * Created by jusung.kim@sk.com on 2018/02/13
 */

public class Order {
    private String mId;

    public Order(String id) {
        mId = id;
    }

    public String getId() {
        return mId;
    }

    @Override
    public String toString() {
        return "Order ID : " + mId;
    }
}
