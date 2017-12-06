package com.hongka.hkcommonlibrarysample.designpattern.abstractfactorypattern;

/**
 * Created by jusung.kim@sk.com on 2017/12/06
 */

public class MachineFactoryA implements MachineFactory {
    @Override
    public MachineA getMachineA() {
        return new MachineA1();
    }

    @Override
    public MachineB getMachineB() {
        return new MachineB1();
    }
}
