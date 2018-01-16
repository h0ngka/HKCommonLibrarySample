package com.hongka.hkcommonlibrarysample.design_pattern.templatemethodpattern;

import android.util.Log;

/**
 * Created by jusung.kim@sk.com on 2017/12/05
 */

public class Archer extends Person {
    public Archer(String name, int height) {
        super(name, height);
    }

    @Override
    protected void prepareWeapon() {
        Log.d("TemplateMethodPattern", "활을 손질합니다.");
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
        Log.d("TemplateMethodPattern", "화살을 준비합니다.");
    }
}
