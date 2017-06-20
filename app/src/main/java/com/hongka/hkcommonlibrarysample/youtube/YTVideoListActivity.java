package com.hongka.hkcommonlibrarysample.youtube;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.hongka.hkcommonlibrarysample.R;
import com.hongka.hkcommonlibrarysample.databinding.ActivityYtVideoListBinding;

/**
 * Created by jusung.kim@sk.com on 2017/05/25
 */

public class YTVideoListActivity extends AppCompatActivity {
    ActivityYtVideoListBinding mBinding;

    public static Intent makeIntent(Context context, String playlistId) {
        Intent intent = new Intent(context, YTVideoListActivity.class);
        intent.putExtra("playlist_id", playlistId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_yt_video_list);

        final String playlistId = getIntent().getStringExtra("playlist_id");
        final YTVideoListFragment ytVideoListFragment = YTVideoListFragment.newInstance();
        replaceFragment(ytVideoListFragment);

        mBinding.getRoot().postDelayed(new Runnable() {
            @Override
            public void run() {
                ytVideoListFragment.requestPlaylistItems(playlistId, "");
            }
        }, 200);
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(mBinding.containerLayout.getId(), fragment, fragment.getClass().getSimpleName());
        ft.commit();
    }
}
