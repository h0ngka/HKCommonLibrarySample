package com.hongka.hkcommonlibrarysample.designpattern.templatemethodpattern;

/**
 * Created by jusung.kim@sk.com on 2017/12/05
 */

public class Warrior extends Person {
    @Override
    protected void prepareWeapon() {
        System.out.println("검을 닦습니다.");
    }

    @Override
    protected void prepareArmor() {
        System.out.println("갑옷을 입습니다.");
    }
}
