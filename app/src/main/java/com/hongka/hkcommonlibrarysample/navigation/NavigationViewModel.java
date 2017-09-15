package com.hongka.hkcommonlibrarysample.navigation;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.view.View;

import com.hongka.hkcommonlibrarysample.MainActivity;

/**
 * Created by jusung.kim@sk.com on 2017/09/15
 */

public class NavigationViewModel extends BaseObservable {
    private Context mContext;
    public final ObservableField<String> mTitle = new ObservableField<>();

    public NavigationViewModel(Context context) {
        mContext = context;
    }

    /**
     * 참고
     * http://arabiannight.tistory.com/entry/286
     * singleTask, singleInstance 관련 : https://developer.android.com/guide/topics/manifest/activity-element.html
     */
    public void onStandardClick(View view) {
        mTitle.set("onStandardClick");

        Intent intent = MainActivity.makeIntent(view.getContext());
        mContext.startActivity(intent);
    }

    public void onSingleTopClick(View view) {
        mTitle.set("onSingleTopClick");

        Intent intent = MainActivity.makeIntent(view.getContext());
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        mContext.startActivity(intent);
    }

    public void onClearTopClick(View view) {
        mTitle.set("onClearTopClick");

        Intent intent = MainActivity.makeIntent(view.getContext());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mContext.startActivity(intent);
    }

    public void onClearTopAndSingleTopClick(View view) {
        mTitle.set("onClearTopAndSingleTopClick");

        Intent intent = MainActivity.makeIntent(view.getContext());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        mContext.startActivity(intent);
    }

    public void onReorderToFrontClick(View view) {
        mTitle.set("onReorderToFrontClick");

        Intent intent = MainActivity.makeIntent(view.getContext());
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        mContext.startActivity(intent);
    }

    public void onNoHistoryClick(View view) {
        mTitle.set("onNoHistoryClick");

        Intent intent = MainActivity.makeIntent(view.getContext());
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        mContext.startActivity(intent);
    }
}
