package com.hongka.hkcommonlibrarysample.design_pattern.compositepattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jusung.kim@sk.com on 2017/12/04
 */

public class Directory implements Node {
    private String mName;
    private List<Node> mChildren;

    public Directory(String name) {
        mName = name;
        mChildren = new ArrayList<>();
    }

    public void add(Node node) {
        mChildren.add(node);
    }

    @Override
    public String getName() {
        System.out.println(mName);
        for (Node node : mChildren) {
            node.getName();
        }
        return mName;
    }
}
