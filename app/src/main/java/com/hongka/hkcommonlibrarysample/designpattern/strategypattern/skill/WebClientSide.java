package com.hongka.hkcommonlibrarysample.designpattern.strategypattern.skill;

/**
 * Created by jusung.kim@sk.com on 2017/12/04
 */

// 웹 클라이언트 사이드 스킬
public class WebClientSide implements ClientSide {
    @Override
    public String getClientProgramming() {
        return "Javascript, Html";
    }
}
