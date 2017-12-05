package com.hongka.hkcommonlibrarysample.designpattern.templatemethodpattern;

/**
 * Created by jusung.kim@sk.com on 2017/12/05
 */

public class Archer extends Person {
    @Override
    protected void prepareWeapon() {
        System.out.println("활을 손질합니다.");
    }

    @Override
    protected void prepareArmor() {

    }

    @Override
    protected boolean isUsingPrepareOther() {
        return true;
    }

    @Override
    protected void prepareOther() {
        System.out.println("화살을 준비합니다.");
    }
}
