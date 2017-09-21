package com.hongka.hkcommonlibrarysample;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.view.View;
import android.widget.Toast;

import com.hongka.hkcommonlibrary.exoplayer2.PlayerActivity;
import com.hongka.hkcommonlibrarysample.exoplayer.ExoMainActivity;
import com.hongka.hkcommonlibrarysample.ipc.IpcTestActivity;
import com.hongka.hkcommonlibrarysample.model.MainItem;
import com.hongka.hkcommonlibrarysample.navigation.NavigationActivity;
import com.hongka.hkcommonlibrarysample.youtube.YTMainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by jusung.kim@sk.com on 2017/09/18
 */

public class MainViewModel extends BaseObservable {
    private Context mContext;
    public ObservableArrayList<MainItem> mMainItems = new ObservableArrayList<>();

    public MainViewModel(Context context) {
        mContext = context;
    }

    public void loadData() {
        List<MainItem> mainItems = new ArrayList<>();

        MainItem item1 = new MainItem();
        item1.mTitle.set("YouTube 키즈 채널");
        item1.mThumbnailUrl.set("https://lh5.ggpht.com/ZPd1wApboW20pq7XINmvDQ8lsXoNAJ3L-Fpr6eCufIf54Cpv6SZC5HtMgd-yf2FjnEg=w300");
        mainItems.add(item1);

        MainItem item2 = new MainItem();
        item2.mTitle.set("ExoPlayer 샘플 목록");
        item2.mThumbnailUrl.set("https://dn-mhke0kuv.qbox.me/dfeba581a1088ef86add.jpg?imageView2/1/w/800/h/600/q/85/format/jpg/interlace/1");
        mainItems.add(item2);

        MainItem item3 = new MainItem();
        item3.mTitle.set("ExoPlayer 일반");
        item3.mThumbnailUrl.set("https://www.thebitbag.com/wp-content/uploads/2015/01/Activate-ExoPlayer-to-load-YouTube-videos-faster-on-your-Android-device.jpg");
        mainItems.add(item3);

        MainItem item4 = new MainItem();
        item4.mTitle.set("ExoPlayer YouTube");
        item4.mThumbnailUrl.set("http://pinkwiki.cf/images/f/fc/Naeun.png");
        mainItems.add(item4);

        MainItem item5 = new MainItem();
        item5.mTitle.set("ExoPlayer Playlist");
        item5.mThumbnailUrl.set("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLdCA2S0wvPnd9SOS5kRSvnVaRnbWnsSKfAuU9donLAPWuASSyfw");
        mainItems.add(item5);

        MainItem item6 = new MainItem();
        item6.mTitle.set(mContext.getString(R.string.title_ipc_test));
        item6.mThumbnailUrl.set("https://image.mycelebs.com/celeb/sq/526_sq_01.jpg");
        mainItems.add(item6);

        MainItem item7 = new MainItem();
        item7.mTitle.set("Navigation");
        item7.mThumbnailUrl.set("https://pbs.twimg.com/media/C27uyBGUAAAdw4C.jpg");
        mainItems.add(item7);

        mMainItems.clear();
        mMainItems.addAll(mainItems);
    }

    public void onClick(View view, MainItem mainItem) {
        if (mainItem.mTitle.get().contains("YouTube 키즈 채널")) {
            view.getContext().startActivity(YTMainActivity.makeIntent(view.getContext()));
        }
        else if (mainItem.mTitle.get().contains("ExoPlayer 샘플")) {
            view.getContext().startActivity(ExoMainActivity.makeIntent(view.getContext(), mainItem.mTitle.get()));
        }
        else if (mainItem.mTitle.get().contains("ExoPlayer 일반")) {
            String url = "http://www.gs.co.kr/sites/default/files/GS%EA%B7%B8%EB%A3%B9PR%20%EB%B0%91%EA%B1%B0%EB%A6%84%ED%8E%B8%2830%EC%B4%88%29.mp4";
            view.getContext().startActivity(PlayerActivity.makeIntent(view.getContext(), url));
        }
        else if (mainItem.mTitle.get().contains("ExoPlayer YouTube")) {
            String url = "http://youtube.com/watch?v=NpBN0YeRMZw";
            view.getContext().startActivity(PlayerActivity.makeIntent(view.getContext(), url));
        }
        else if (mainItem.mTitle.get().contains("ExoPlayer Playlist")) {
            String[] playUrls = new String[] {
                    "http://www.gs.co.kr/sites/default/files/GS%EA%B7%B8%EB%A3%B9PR%20%EB%B0%91%EA%B1%B0%EB%A6%84%ED%8E%B8%2830%EC%B4%88%29.mp4",
                    "http://youtube.com/watch?v=NpBN0YeRMZw",
                    "https://www.youtube.com/watch?v=GH5K8WBy2qY"
            };
            view.getContext().startActivity(PlayerActivity.makeIntent(view.getContext(), playUrls));
        }
        else if (mainItem.mTitle.get().contains("IPC")) {
            view.getContext().startActivity(IpcTestActivity.makeIntent(view.getContext()));
        }
        else if (mainItem.mTitle.get().contains("Navigation")) {
            view.getContext().startActivity(NavigationActivity.makeIntent(view.getContext()));
        }
    }

    public void onButtonClick(View view, MainItem mainItem) {
        loadData();

        Random random = new Random();
        int rndNumber = random.nextInt(mMainItems.size());
        MainItem rndItem = mMainItems.get(rndNumber);

        mainItem.mTitle.set(rndItem.mTitle.get());
        mainItem.mThumbnailUrl.set(rndItem.mThumbnailUrl.get());

        Toast.makeText(view.getContext(), mainItem.mTitle.get(), Toast.LENGTH_SHORT).show();
    }
}
