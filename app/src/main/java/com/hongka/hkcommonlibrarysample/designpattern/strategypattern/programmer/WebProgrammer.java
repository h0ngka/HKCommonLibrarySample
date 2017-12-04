package com.hongka.hkcommonlibrarysample.designpattern.strategypattern.programmer;

import com.hongka.hkcommonlibrarysample.designpattern.strategypattern.skill.ClientSide;
import com.hongka.hkcommonlibrarysample.designpattern.strategypattern.programmer.Programmer;
import com.hongka.hkcommonlibrarysample.designpattern.strategypattern.skill.ServerSide;
import com.hongka.hkcommonlibrarysample.designpattern.strategypattern.skill.WebClientSide;
import com.hongka.hkcommonlibrarysample.designpattern.strategypattern.skill.WebServerSide;

/**
 * Created by jusung.kim@sk.com on 2017/12/04
 */

public class WebProgrammer extends Programmer {
    // 웹 프로그래머는 웹 관련 서버/클라이언트 사이드 스킬을 알고 있습니다.
    public WebProgrammer() {
        ClientSide cs = new WebClientSide();
        ServerSide ss = new WebServerSide();
        setClientSide(cs);
        setServerSide(ss);
    }
    @Override
    public String getAllSkill() {
        String display = getClientProgramming() + ", " + getServerProgramming();
        return display;
    }
}
