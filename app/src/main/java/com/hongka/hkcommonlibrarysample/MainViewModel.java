package com.hongka.hkcommonlibrarysample;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.view.View;
import android.widget.Toast;

import com.hongka.hkcommonlibrary.exoplayer2.PlayerActivity;
import com.hongka.hkcommonlibrarysample.data_structure.DataSturectureActivity;
import com.hongka.hkcommonlibrarysample.design_pattern.DesignPatternActivity;
import com.hongka.hkcommonlibrarysample.exoplayer.ExoMainActivity;
import com.hongka.hkcommonlibrarysample.fragment.FragmentMainActivity;
import com.hongka.hkcommonlibrarysample.includemerge.IncludeMergeActivity;
import com.hongka.hkcommonlibrarysample.ipc.IpcTestActivity;
import com.hongka.hkcommonlibrarysample.kotlin.KotlinActivity;
import com.hongka.hkcommonlibrarysample.model.MainItem;
import com.hongka.hkcommonlibrarysample.navigation.NavigationActivity;
import com.hongka.hkcommonlibrarysample.rxjava.RxJavaActivity;
import com.hongka.hkcommonlibrarysample.viewstub.ViewStubActivity;
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
        item4.mThumbnailUrl.set("https://cdn.gamemeca.com/gmdata/0000/122/651/091130_preview_zelda_sh_1.jpg");
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

        MainItem item8 = new MainItem();
        item8.mTitle.set(mContext.getString(R.string.title_fragment_test));
        item8.mThumbnailUrl.set("http://res.thegear.co.kr/images/20161021/20161021082835850311.jpg");
        mainItems.add(item8);

        MainItem item9 = new MainItem();
        item9.mTitle.set(mContext.getString(R.string.title_view_stub_test));
        item9.mThumbnailUrl.set("http://www.slist.kr/news/photo/201707/17505_52435_4754.jpg");
        mainItems.add(item9);

        MainItem item10 = new MainItem();
        item10.mTitle.set(mContext.getString(R.string.title_include_merge_test));
        item10.mThumbnailUrl.set("http://zdnet2.cbsistatic.com/hub/i/r/2015/10/29/7c2be59e-5a33-4a50-bf27-9c58152e8a6f/resize/770xauto/cd74a4598643a96110970b01734566fd/android-chrome-thumb.gif");
        mainItems.add(item10);

        MainItem item11 = new MainItem();
        item11.mTitle.set(mContext.getString(R.string.title_design_pattern));
        item11.mThumbnailUrl.set("http://cfile5.uf.tistory.com/image/21582C4153458D290CB90D");
        mainItems.add(item11);

        MainItem item12 = new MainItem();
        item12.mTitle.set(mContext.getString(R.string.title_data_structure));
        item12.mThumbnailUrl.set("http://image.zdnet.co.kr/2017/01/15/firstblood_6Vm05HSku.jpg");
        mainItems.add(item12);

        MainItem item13 = new MainItem();
        item13.mTitle.set(mContext.getString(R.string.title_kotlin));
        item13.mThumbnailUrl.set("https://monstertut.com/wp-content/uploads/2017/05/kotlin-tutorial-course-beginner-udemy.jpg");
        mainItems.add(item13);

        MainItem item14 = new MainItem();
        item14.mTitle.set(mContext.getString(R.string.title_rxjava));
        item14.mThumbnailUrl.set("http://icedtealabs.com/assets/images/android/rxjava.jpg");
        mainItems.add(item14);

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
        else if (mainItem.mTitle.get().contains("Fragment")) {
            view.getContext().startActivity(FragmentMainActivity.makeIntent(view.getContext()));
        }
        else if (mainItem.mTitle.get().contains("ViewStub")) {
            view.getContext().startActivity(ViewStubActivity.makeIntent(view.getContext()));
        }
        else if (mainItem.mTitle.get().contains("Include&Merge")) {
            view.getContext().startActivity(IncludeMergeActivity.makeIntent(view.getContext()));
        }
        else if (mainItem.mTitle.get().contains("Design")) {
            view.getContext().startActivity(DesignPatternActivity.makeIntent(view.getContext()));
        }
        else if (mainItem.mTitle.get().contains("Data")) {
            view.getContext().startActivity(DataSturectureActivity.makeIntent(view.getContext()));
        }
        else if (mainItem.mTitle.get().contains("kotlin")) {
            view.getContext().startActivity(KotlinActivity.Companion.makeIntent(view.getContext()));
        }
        else if (mainItem.mTitle.get().contains("RxJava")) {
            view.getContext().startActivity(RxJavaActivity.makeIntent(view.getContext()));
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
