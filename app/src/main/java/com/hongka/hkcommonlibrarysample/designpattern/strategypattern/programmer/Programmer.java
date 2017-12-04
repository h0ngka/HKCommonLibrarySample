package com.hongka.hkcommonlibrarysample.designpattern.strategypattern.programmer;

import com.hongka.hkcommonlibrarysample.designpattern.strategypattern.skill.ClientSide;
import com.hongka.hkcommonlibrarysample.designpattern.strategypattern.skill.ServerSide;

/**
 * Created by jusung.kim@sk.com on 2017/12/04
 */

// 프로그래머라면 이 클래스를 상속 또는 추가 구현 해야 합니다.
public abstract class Programmer {
    private ClientSide mClientSide;
    private ServerSide mServerSide;

    public Programmer() {}

    // 스킬을 세팅 할 수 있도록 getter/setter를 설정합니다.
    public ClientSide getClientSide() {
        return mClientSide;
    }

    public void setClientSide(ClientSide clientSide) {
        mClientSide = clientSide;
    }

    public ServerSide getServerSide() {
        return mServerSide;
    }

    public void setServerSide(ServerSide serverSide) {
        mServerSide = serverSide;
    }

    // 클라이언트 프로그래밍 스킬을 나열합니다.
    public String getClientProgramming() {
        return mClientSide.getClientProgramming();
    }

    // 서버 프로그래밍 스킬을 나열합니다.
    public String getServerProgramming() {
        return mServerSide.getServerProgramming();
    }

    // 해당 메소드는 프로그래머가 가진 모든 스킬을 출력해야 합니다.
    public abstract String getAllSkill();
}
