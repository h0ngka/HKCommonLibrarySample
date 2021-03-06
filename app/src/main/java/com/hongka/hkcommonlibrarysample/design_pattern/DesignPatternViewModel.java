package com.hongka.hkcommonlibrarysample.design_pattern;

import android.content.Context;
import android.databinding.BaseObservable;
import android.view.View;

import com.hongka.hkcommonlibrarysample.design_pattern.abstractfactorypattern.AbstractFactoryPatternActivity;
import com.hongka.hkcommonlibrarysample.design_pattern.adapterpattern.AdapterPatternActivity;
import com.hongka.hkcommonlibrarysample.design_pattern.builderpattern.BuilderPatternActivity;
import com.hongka.hkcommonlibrarysample.design_pattern.compositepattern.CompositePatternActivity;
import com.hongka.hkcommonlibrarysample.design_pattern.decoratorpattern.DecoratorPatternActivity;
import com.hongka.hkcommonlibrarysample.design_pattern.factorymethodpattern.FactoryMethodPatternActivity;
import com.hongka.hkcommonlibrarysample.design_pattern.observerpattern.ObserverPatternActivity;
import com.hongka.hkcommonlibrarysample.design_pattern.singletonpattern.SingletonPatternActivity;
import com.hongka.hkcommonlibrarysample.design_pattern.strategypattern.StrategyPatternActivity;
import com.hongka.hkcommonlibrarysample.design_pattern.templatemethodpattern.TemplateMethodPatternActivity;

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
        } else if (tag.contains("Factory Method")) {
            view.getContext().startActivity(FactoryMethodPatternActivity.makeIntent(view.getContext()));
        } else if (tag.contains("Abstract Factory")) {
            view.getContext().startActivity(AbstractFactoryPatternActivity.makeIntent(view.getContext()));
        } else if (tag.contains("Builder Pattern")) {
            view.getContext().startActivity(BuilderPatternActivity.makeIntent(view.getContext()));
        } else if (tag.contains("Adapter Pattern")) {
            view.getContext().startActivity(AdapterPatternActivity.makeIntent(view.getContext()));
        }
    }
}
