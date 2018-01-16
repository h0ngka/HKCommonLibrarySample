package com.hongka.hkcommonlibrarysample.design_pattern.adapterpattern.adapter;

import com.hongka.hkcommonlibrarysample.design_pattern.adapterpattern.engine.MyLib;
import com.hongka.hkcommonlibrarysample.design_pattern.adapterpattern.engine.PhotoEngine;

/**
 * Created by jusung.kim@sk.com on 2017/12/15
 */

public class MyLibAdapter implements PhotoEngine {
    MyLib lib;

    public MyLibAdapter(MyLib lib) {
        this.lib = lib;
    }

    @Override
    public void printPhoto(String fileName) {
        lib.printPhoto(fileName);
    }

    @Override
    public void printPhotoList(String[] listName) {
        lib.printPhotoList(listName);
    }

    @Override
    public void deletePhoto(String fileName) {
        lib.deletePhoto(fileName);
    }

    @Override
    public void addPhoto(String fileName) {
        lib.addPhoto(fileName);
    }
}
