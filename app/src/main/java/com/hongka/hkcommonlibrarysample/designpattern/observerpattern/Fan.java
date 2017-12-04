package com.hongka.hkcommonlibrarysample.designpattern.observerpattern;

/**
 * Created by jusung.kim@sk.com on 2017/12/04
 */

public interface Fan {
    // 팬은 자신이 좋아하는 연예인의 소리를 들을 수 있다.
    public void hear(String voice);
}
