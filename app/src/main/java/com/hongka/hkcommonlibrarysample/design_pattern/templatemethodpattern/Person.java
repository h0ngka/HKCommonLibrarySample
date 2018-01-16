package com.hongka.hkcommonlibrarysample.design_pattern.templatemethodpattern;

import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by jusung.kim@sk.com on 2017/12/05
 */

public abstract class Person implements Comparable {
    protected String mName;
    protected int mHeight;

    public Person(String name, int height) {
        mName = name;
        mHeight = height;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        Person otherPerson = (Person) o;
        if (this.mHeight < otherPerson.mHeight) {
            return 1;
        } else if (this.mHeight == otherPerson.mHeight) {
            return 0;
        } else {
            return -1;
        }
    }

    // 전투를 준비합니다.
    protected void readyToBattle() {
        startBodyTraining();
        prepareWeapon();
        prepareArmor();
        if (isUsingPrepareOther()) {
            prepareOther();
        }
    }

    // 상속 받은 클래스에서 수정할 수 없도록 final 키워드를 붙였습니다.
    protected final void startBodyTraining() {
        Log.d("TemplateMethodPattern", "체력을 단련합니다.");
    }

    // 무기를 손질할때 사용합니다.
    protected abstract void prepareWeapon();

    // 뭔가를 걸칠 때 사용합니다.
    protected abstract void prepareArmor();

    // 만약 다른 무언가를 사용하려면 true로 바꿔야 합니다.
    // 이 메소드는 "후킹(Hooking)" 용도로 사용합니다.
    protected boolean isUsingPrepareOther() {
        return false;
    }

    // 다른 무언가가 필요하면 사용합니다.
    // isUsingPrepareOther 값에 의해 오버라이드 해서 사용 합니다.
    protected void prepareOther() {

    }
}
