package com.hongka.hkcommonlibrarysample.design_pattern.templatemethodpattern;

import android.util.Log;

/**
 * Created by jusung.kim@sk.com on 2017/12/05
 */

public class Warrior extends Person {
    public Warrior(String name, int height) {
        super(name, height);
    }

    @Override
    protected void prepareWeapon() {
        Log.d("TemplateMethodPattern", "검을 닦습니다.");
    }

    @Override
    protected void prepareArmor() {
        Log.d("TemplateMethodPattern", "갑옷을 입습니다.");
    }
}
