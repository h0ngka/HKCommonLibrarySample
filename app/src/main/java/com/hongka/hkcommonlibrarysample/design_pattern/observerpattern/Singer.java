package com.hongka.hkcommonlibrarysample.design_pattern.observerpattern;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jusung.kim@sk.com on 2017/12/04
 */

public class Singer implements Talent {
    private ArrayList<Fan> fanList;
    public Singer() {
        fanList = new ArrayList<>();
    }

    @Override
    public void addFan(Fan o) {
        fanList.add(o);
    }

    @Override
    public void deleteFan(Fan o) {
        if (fanList.indexOf(o) >= 0) {
            fanList.remove(o);
        }
    }

    @Override
    public void speak() {
        String[] voices = {"사랑해요, 여러분!", "너의같은 팬 필요 없어!"};
        Random r = new Random();
        int index = r.nextInt(voices.length);
        for (int i=0; i<fanList.size(); i++) {
            Fan o = fanList.get(i);
            o.hear(voices[index]);
        }
    }
}
