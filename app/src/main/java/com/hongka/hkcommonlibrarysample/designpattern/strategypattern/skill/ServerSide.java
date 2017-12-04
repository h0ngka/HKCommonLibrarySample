package com.hongka.hkcommonlibrarysample.designpattern.strategypattern.skill;

/**
 * Created by jusung.kim@sk.com on 2017/12/04
 */

// 서버 사이드 스킬을 표현하기 위한 인터페이스
public interface ServerSide {
    // 서버 프로그래밍을 할 수 있는 스킬 목록이 나온다.
    public String getServerProgramming();
}
