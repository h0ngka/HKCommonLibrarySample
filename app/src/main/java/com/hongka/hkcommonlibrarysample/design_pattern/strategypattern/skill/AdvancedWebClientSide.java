package com.hongka.hkcommonlibrarysample.design_pattern.strategypattern.skill;

/**
 * Created by jusung.kim@sk.com on 2017/12/04
 */

// 고급 웹 클라이언트 사이드 스킬
public class AdvancedWebClientSide implements ClientSide {
    @Override
    public String getClientProgramming() {
        return "Javascript, CSS, Html, AnguarJS";
    }
}
