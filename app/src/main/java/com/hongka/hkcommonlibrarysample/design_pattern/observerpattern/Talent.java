package com.hongka.hkcommonlibrarysample.design_pattern.observerpattern;

/**
 * Created by jusung.kim@sk.com on 2017/12/04
 */

public interface Talent {
    public void addFan(Fan o);
    public void deleteFan(Fan o);
    public void speak();
}
