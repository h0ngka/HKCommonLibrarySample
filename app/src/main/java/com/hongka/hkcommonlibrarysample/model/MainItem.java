package com.hongka.hkcommonlibrarysample.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;

/**
 * Created by jusung.kim@sk.com on 2017/05/19
 */

public class MainItem extends BaseObservable {
    @Bindable
    public ObservableField<String> mTitle = new ObservableField<>();
    @Bindable
    public ObservableField<String> mThumbnailUrl = new ObservableField<>();
}
