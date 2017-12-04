package com.hongka.hkcommonlibrarysample.designpattern.strategypattern.skill;

/**
 * Created by jusung.kim@sk.com on 2017/12/04
 */

// 고급 웹 서버 사이드 스킬
public class AdvancedWebServerSide implements ServerSide {
    @Override
    public String getServerProgramming() {
        return "Java, C, C++, Python, Ruby";
    }
}
