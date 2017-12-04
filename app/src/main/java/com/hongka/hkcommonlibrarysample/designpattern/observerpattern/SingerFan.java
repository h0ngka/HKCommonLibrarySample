package com.hongka.hkcommonlibrarysample.designpattern.observerpattern;

/**
 * Created by jusung.kim@sk.com on 2017/12/04
 */

public class SingerFan implements Fan {
    private String mName;

    public String getName() {
        return mName;
    }

    public SingerFan(String name) {
        mName = name;
    }

    @Override
    public void hear(String voice) {
        System.out.println(mName  + "은 \"" + voice + "\"를 들었습니다!");
    }
}
