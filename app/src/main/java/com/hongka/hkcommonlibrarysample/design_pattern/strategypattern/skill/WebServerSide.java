package com.hongka.hkcommonlibrarysample.design_pattern.strategypattern.skill;

/**
 * Created by jusung.kim@sk.com on 2017/12/04
 */

// 웹 서버 사이드 스킬
public class WebServerSide implements ServerSide {
    @Override
    public String getServerProgramming() {
        return "Java";
    }
}
