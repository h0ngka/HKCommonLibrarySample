package com.hongka.hkcommonlibrarysample.design_pattern.adapterpattern.engine;

/**
 * Created by jusung.kim@sk.com on 2017/12/15
 */

public class MyLib {
    private StringBuilder mStringBuilder;

    public MyLib() {
        mStringBuilder = new StringBuilder();
    }

    public void printPhoto(String fileName) {
        mStringBuilder.append("printPhoto() : " + fileName + "\n");
    }

    public void printPhotoList(String[] listName) {
        for (String fileName : listName) {
            mStringBuilder.append("printPhotoList() : " + fileName + "\n");
        }
    }

    public void deletePhoto(String fileName) {
        mStringBuilder.append("deletePhoto() : " + fileName + "\n");
    }

    public void addPhoto(String fileName) {
        mStringBuilder.append("addPhoto() : " + fileName + "\n");
    }
}
