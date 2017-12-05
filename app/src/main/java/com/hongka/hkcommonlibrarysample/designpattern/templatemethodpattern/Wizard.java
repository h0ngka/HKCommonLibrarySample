package com.hongka.hkcommonlibrarysample.designpattern.templatemethodpattern;

/**
 * Created by jusung.kim@sk.com on 2017/12/05
 */

public class Wizard extends Person {
    @Override
    protected void prepareWeapon() {
        System.out.println("지팡이를 준비합니다.");
    }

    @Override
    protected void prepareArmor() {
        System.out.println("로브를 입습니다.");
    }
}
