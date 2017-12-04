package com.hongka.hkcommonlibrarysample.designpattern.strategypattern.programmer;

import com.hongka.hkcommonlibrarysample.designpattern.strategypattern.skill.AdvancedWebClientSide;
import com.hongka.hkcommonlibrarysample.designpattern.strategypattern.skill.ClientSide;
import com.hongka.hkcommonlibrarysample.designpattern.strategypattern.skill.ServerSide;
import com.hongka.hkcommonlibrarysample.designpattern.strategypattern.skill.WebServerSide;

/**
 * Created by jusung.kim@sk.com on 2017/12/04
 */

public class AmateurProgrammer extends Programmer {
    // 아마추어 프로그래머는 클라이언트쪽은 잘 알고 있지만 서버쪽은 평범합니다.
    public AmateurProgrammer() {
        ClientSide cs = new AdvancedWebClientSide();
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
