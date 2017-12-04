package com.hongka.hkcommonlibrarysample.designpattern.compositepattern;

/**
 * Created by jusung.kim@sk.com on 2017/12/04
 */

public class File implements Node {
    private String mName;

    public File(String name) {
        mName = name;
    }

    @Override
    public String getName() {
        System.out.println(mName);
        return mName;
    }
}
