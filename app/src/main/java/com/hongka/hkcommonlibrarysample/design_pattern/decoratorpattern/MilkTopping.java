package com.hongka.hkcommonlibrarysample.design_pattern.decoratorpattern;

/**
 * Created by jusung.kim@sk.com on 2017/11/10
 */

public class MilkTopping extends Topping {
    public MilkTopping(Cookie cookie) {
        super(cookie);
    }

    @Override
    public String getName() {
        return "우유맛 " + cookie.getName();
    }
}
