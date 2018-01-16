package com.hongka.hkcommonlibrarysample.design_pattern.factorymethodpattern;

/**
 * Created by jusung.kim@sk.com on 2017/12/05
 */

public class ModifiedSuperRobotFactory extends RobotFactory {
    @Override
    Robot createRobot(String name) {
        try {
            Class<?> cls = Class.forName(name);
            Object obj = cls.newInstance();
            return (Robot) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
