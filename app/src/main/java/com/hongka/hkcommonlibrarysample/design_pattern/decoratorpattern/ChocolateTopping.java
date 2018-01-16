package com.hongka.hkcommonlibrarysample.design_pattern.decoratorpattern;

/**
 * Created by jusung.kim@sk.com on 2017/11/10
 */

public class ChocolateTopping extends Topping {

    public ChocolateTopping(Cookie cookie) {
        super(cookie);
    }

    @Override
    public String getName() {
        return "초콜리맛 " + cookie.getName();
    }
}
