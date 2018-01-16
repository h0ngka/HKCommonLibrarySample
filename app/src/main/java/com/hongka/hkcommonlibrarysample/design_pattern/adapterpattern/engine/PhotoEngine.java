package com.hongka.hkcommonlibrarysample.design_pattern.adapterpattern.engine;

/**
 * Created by jusung.kim@sk.com on 2017/12/15
 */

public interface PhotoEngine {
    public void printPhoto(String fileName);
    public void printPhotoList(String[] listName);
    public void deletePhoto(String fileName);
    public void addPhoto(String fileName);
}
