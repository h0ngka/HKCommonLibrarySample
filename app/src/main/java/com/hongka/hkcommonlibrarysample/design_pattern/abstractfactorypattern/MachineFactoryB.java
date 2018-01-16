package com.hongka.hkcommonlibrarysample.design_pattern.abstractfactorypattern;

/**
 * Created by jusung.kim@sk.com on 2017/12/06
 */

public class MachineFactoryB implements MachineFactory {
    @Override
    public MachineA getMachineA() {
        return new MachineA2();
    }

    @Override
    public MachineB getMachineB() {
        return new MachineB2();
    }
}
