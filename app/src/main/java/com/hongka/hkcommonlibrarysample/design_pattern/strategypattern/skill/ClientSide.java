package com.hongka.hkcommonlibrarysample.design_pattern.strategypattern.skill;

/**
 * Created by jusung.kim@sk.com on 2017/12/04
 */

// 클라이언트 사이드 스킬을 표현하기 위한 인터페이스
public interface ClientSide {
    // 클라이언트 프로그래밍을 할 수 있는 스킬 목록이 나온다.
    public String getClientProgramming();
}
