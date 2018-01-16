package com.hongka.hkcommonlibrarysample.design_pattern.adapterpattern.adapter;

import com.hongka.hkcommonlibrarysample.design_pattern.adapterpattern.engine.ALib;
import com.hongka.hkcommonlibrarysample.design_pattern.adapterpattern.engine.PhotoEngine;

/**
 * Created by jusung.kim@sk.com on 2017/12/15
 */

public class ALibAdapter implements PhotoEngine {
    private ALib lib;

    public ALibAdapter(ALib lib) {
        this.lib = lib;
    }

    @Override
    public void printPhoto(String fileName) {
        lib.printPhoto(fileName);
    }

    @Override
    public void printPhotoList(String[] listName) {
        for (int i=0; i<listName.length; i++) {
            lib.printPhoto(listName[i]);
        }
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
