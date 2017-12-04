package com.hongka.hkcommonlibrarysample.designpattern.decoratorpattern;

/**
 * Created by jusung.kim@sk.com on 2017/11/10
 */

public abstract class Topping implements Cookie {
    protected Cookie cookie;

    public Topping(Cookie cookie) {
        this.cookie = cookie;
    }

    @Override
    public abstract String getName();
}
