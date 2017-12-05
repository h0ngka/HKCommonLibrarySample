package com.hongka.hkcommonlibrarysample.designpattern.factorymethodpattern;

/**
 * Created by jusung.kim@sk.com on 2017/12/05
 */

public class SuperRobotFactory extends RobotFactory {
    @Override
    Robot createRobot(String name) {
        switch (name) {
            case "super" :
                return new SuperRobot();
            case "power" :
                return new PowerRobot();
        }
        return null;
    }
}
