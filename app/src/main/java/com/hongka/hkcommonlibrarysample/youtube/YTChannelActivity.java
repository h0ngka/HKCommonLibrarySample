package com.hongka.hkcommonlibrarysample.youtube;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Visibility;
import android.view.MenuItem;

import com.hongka.hkcommonlibrary.retrofit.model.youtube.Channel;
import com.hongka.hkcommonlibrarysample.R;
import com.hongka.hkcommonlibrarysample.databinding.ActivityYtChannelBinding;

/**
 * Created by jusung.kim@sk.com on 2017/05/24
 */

public class YTChannelActivity extends AppCompatActivity {
    private ActivityYtChannelBinding mBinding;
    private Channel mChannel;

    public static Intent makeIntent(Context context, Channel channel) {
        Intent intent = new Intent(context, YTChannelActivity.class);
        intent.putExtra("channel_item", channel);
        return intent;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mChannel = getIntent().getParcelableExtra("channel_item");

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_yt_channel);

        setSupportActionBar(mBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(mChannel.snippet.title);

        initTabLayout();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setupWindowAnimations();
            ViewCompat.setTransitionName(mBinding.channelBannerImageView, mBinding.channelBannerImageView.getTransitionName());
            ViewCompat.setTransitionName(mBinding.channelProfileImageView, mBinding.channelProfileImageView.getTransitionName());
        }

        mBinding.setChannel(mChannel);
        mBinding.executePendingBindings();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition();
                } else {
                    finish();
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupWindowAnimations() {
        Visibility enterTransition = buildEnterTransition();
        getWindow().setEnterTransition(enterTransition);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private Visibility buildEnterTransition() {
        Fade enterTransition = new Fade();
        enterTransition.setDuration(500);

        return enterTransition;
    }

    private void initTabLayout() {
        mBinding.viewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager()));
        mBinding.tabs.setupWithViewPager(mBinding.viewPager);
        mBinding.tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mBinding.viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //NOT USE
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //NOT USE
            }
        });
    }

    private class SectionsPagerAdapter extends FragmentPagerAdapter {
        private static final int TAB_COUNT = 2;

        public SectionsPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    YTVideoListFragment ytVideoListFragment = YTVideoListFragment.newInstance();
                    ytVideoListFragment.requestLatestVideo(mChannel.id, "");
                    return ytVideoListFragment;
                case 1:
                    YTPlaylistListFragment ytPlaylistListFragment = YTPlaylistListFragment.newInstance();
                    ytPlaylistListFragment.requestPlaylistsData(mChannel.id, "");
                    return ytPlaylistListFragment;
            }

            return null;
        }

        @Override
        public int getCount() {
            return TAB_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "영상";
                case 1:
                    return "재생목록";
            }

            return null;
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }
    }
}
