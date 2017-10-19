package com.hongka.hkcommonlibrarysample.fragment;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.hongka.hkcommonlibrarysample.R;
import com.hongka.hkcommonlibrarysample.navigation.NavigationFragment;

/**
 * Created by jusung.kim@sk.com on 2017/10/19
 */

public class FragmentMainViewModel extends BaseObservable {
    private Context mContext;
    private FragmentManager mFragmentManager;

    public final ObservableField<Integer> mCount = new ObservableField<>(0);

    public FragmentMainViewModel(Context context) {
        mContext = context;
        mFragmentManager = ((FragmentActivity)mContext).getSupportFragmentManager();
    }

    public void onAddClick(View view) {
        int count = mCount.get();
        if (count < 0) {
            count = 0;
        }
        mCount.set(++count);

        // 같은 Fragment를 넣으면 에러 발생 : jaava.lang.IllegalStateException: Fragment already added: FragmentMainFragment{69b20df #1 id=0x7f0d008e}
        Fragment fragment = null;
        if (count%2==0) {
            fragment = FragmentMainFragment.newInstance();
            ((FragmentMainFragment)fragment).setViewModel(this);
        } else {
            fragment = NavigationFragment.newInstance();
        }

        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        String tag = "TAG_" + count;
        transaction.add(R.id.content_frame, fragment, tag);
        transaction.commit();
    }

    public void onReplaceClick(View view) {
        int count = 0;
        mCount.set(++count);

        FragmentMainFragment fragment = FragmentMainFragment.newInstance();
        fragment.setViewModel(this);
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        String tag = "TAG_" + count;
        transaction.replace(R.id.content_frame, fragment, tag); // replace 메소드는 content_frame에 쌓여있는 모든 Fragment를 다 날리고 현재꺼 하나만 넣어짐. (tag, 다른 Fragment 상관없이)
        transaction.commit();
    }

    public void onRemoveClick(View view) {
        int count = mCount.get();
        if (count <= 0) {
            return;
        }

        String tag = "TAG_" + count;
        Fragment fragment = mFragmentManager.findFragmentByTag(tag);
        if (fragment != null) {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.remove(fragment);
            transaction.commit();

            mCount.set(--count);
        }
    }
}
