package com.hongka.hkcommonlibrarysample.includemerge;

import android.content.Context;
import android.databinding.BaseObservable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Toast;

import com.hongka.hkcommonlibrarysample.R;
import com.hongka.hkcommonlibrarysample.fragment.FragmentMainFragment;

/**
 * Created by jusung.kim@sk.com on 2017/10/24
 */

public class IncludeMergeViewModel extends BaseObservable {
    private Context mContext;
    private FragmentManager mFragmentManager;

    public IncludeMergeViewModel(Context context) {
        mContext = context;
        mFragmentManager = ((FragmentActivity) mContext).getSupportFragmentManager();
    }

    public void onClick(View view) {
        String tag = (String) view.getTag();
        if (tag.contains("include")) {
            replaceIncludeFragment();
        } else if (tag.contains("merge")) {
            replaceMergeFragment();
        }
    }

    private void replaceIncludeFragment() {
        IncludeFragment fragment = IncludeFragment.newInstance();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.content_frame, fragment); // replace 메소드는 content_frame에 쌓여있는 모든 Fragment를 다 날리고 현재꺼 하나만 넣어짐. (tag, 다른 Fragment 상관없이)
        transaction.commit();
    }

    private void replaceMergeFragment() {
        MergeFragment fragment = MergeFragment.newInstance();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.content_frame, fragment); // replace 메소드는 content_frame에 쌓여있는 모든 Fragment를 다 날리고 현재꺼 하나만 넣어짐. (tag, 다른 Fragment 상관없이)
        transaction.commit();
    }
}
