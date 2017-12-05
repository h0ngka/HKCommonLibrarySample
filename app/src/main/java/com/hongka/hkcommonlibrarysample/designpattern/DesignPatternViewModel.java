package com.hongka.hkcommonlibrarysample.designpattern;

import android.content.Context;
import android.databinding.BaseObservable;
import android.view.View;

import com.hongka.hkcommonlibrarysample.designpattern.compositepattern.CompositePatternActivity;
import com.hongka.hkcommonlibrarysample.designpattern.decoratorpattern.DecoratorPatternActivity;
import com.hongka.hkcommonlibrarysample.designpattern.observerpattern.ObserverPatternActivity;
import com.hongka.hkcommonlibrarysample.designpattern.singletonpattern.SingletonPatternActivity;
import com.hongka.hkcommonlibrarysample.designpattern.strategypattern.StrategyPatternActivity;
import com.hongka.hkcommonlibrarysample.designpattern.templatemethodpattern.TemplateMethodPatternActivity;

/**
 * Created by jusung.kim@sk.com on 2017/12/01
 */

public class DesignPatternViewModel extends BaseObservable {
    private Context mContext;

    public DesignPatternViewModel(Context context) {
        mContext = context;
    }

    public void onClick(View view) {
        String tag = (String) view.getTag();

        if (tag.contains("Decorator")) {
            view.getContext().startActivity(DecoratorPatternActivity.makeIntent(view.getContext()));
        } else if (tag.contains("Strategy")) {
            view.getContext().startActivity(StrategyPatternActivity.makeIntent(view.getContext()));
        } else if (tag.contains("Observer")) {
            view.getContext().startActivity(ObserverPatternActivity.makeIntent(view.getContext()));
        } else if (tag.contains("Singleton")) {
            view.getContext().startActivity(SingletonPatternActivity.makeIntent(view.getContext()));
        } else if (tag.contains("Composite")) {
            view.getContext().startActivity(CompositePatternActivity.makeIntent(view.getContext()));
        } else if (tag.contains("Template Method")) {
            view.getContext().startActivity(TemplateMethodPatternActivity.makeIntent(view.getContext()));
        }
    }
}
