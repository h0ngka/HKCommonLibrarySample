package com.hongka.hkcommonlibrarysample.design_pattern.templatemethodpattern;

import android.util.Log;

/**
 * Created by jusung.kim@sk.com on 2017/12/05
 */

public class Wizard extends Person {
    public Wizard(String name, int height) {
        super(name, height);
    }

    @Override
    protected void prepareWeapon() {
        Log.d("TemplateMethodPattern", "지팡이를 준비합니다.");
    }

    @Override
    protected void prepareArmor() {
        Log.d("TemplateMethodPattern", "로브를 입습니다.");
    }
}
