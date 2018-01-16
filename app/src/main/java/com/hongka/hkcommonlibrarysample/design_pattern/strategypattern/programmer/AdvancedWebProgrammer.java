package com.hongka.hkcommonlibrarysample.design_pattern.strategypattern.programmer;

import com.hongka.hkcommonlibrarysample.design_pattern.strategypattern.skill.AdvancedWebClientSide;
import com.hongka.hkcommonlibrarysample.design_pattern.strategypattern.skill.AdvancedWebServerSide;
import com.hongka.hkcommonlibrarysample.design_pattern.strategypattern.skill.ClientSide;
import com.hongka.hkcommonlibrarysample.design_pattern.strategypattern.skill.ServerSide;

/**
 * Created by jusung.kim@sk.com on 2017/12/04
 */

public class AdvancedWebProgrammer extends Programmer {
    // 웹 프로그래머는 기본 웹 관련 서버/클라이언트 사이드 스킬을 알고 있습니다.
    public AdvancedWebProgrammer() {
        ClientSide cs = new AdvancedWebClientSide();
        ServerSide ss = new AdvancedWebServerSide();
        setClientSide(cs);
        setServerSide(ss);
    }

    // 모든 스킬을 나열하는 문자열을 만듭니다.
    @Override
    public String getAllSkill() {
        String display = getClientProgramming() + ", " + getServerProgramming();
        return display;
    }
}
