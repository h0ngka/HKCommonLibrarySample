package com.hongka.hkcommonlibrarysample;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hongka.hkcommonlibrary.exoplayer2.PlayerActivity;
import com.hongka.hkcommonlibrarysample.databinding.ActivityMainBinding;
import com.hongka.hkcommonlibrarysample.databinding.RecyclerViewMainItemBinding;
import com.hongka.hkcommonlibrarysample.exoplayer.ExoMainActivity;
import com.hongka.hkcommonlibrarysample.ipc.IpcTestActivity;
import com.hongka.hkcommonlibrarysample.model.MainItem;
import com.hongka.hkcommonlibrarysample.navigation.NavigationActivity;
import com.hongka.hkcommonlibrarysample.youtube.YTMainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();

    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.recyclerView.setAdapter(new RecyclerViewAdapter(getData()));
    }

    private List<MainItem> getData() {
        List<MainItem> mainItemList = new ArrayList<>();

        MainItem item1 = new MainItem();
        item1.mTitle.set("YouTube 키즈 채널");
        item1.mThumbnailUrl.set("https://lh5.ggpht.com/ZPd1wApboW20pq7XINmvDQ8lsXoNAJ3L-Fpr6eCufIf54Cpv6SZC5HtMgd-yf2FjnEg=w300");
        mainItemList.add(item1);

        MainItem item2 = new MainItem();
        item2.mTitle.set("ExoPlayer 샘플 목록");
        item2.mThumbnailUrl.set("https://dn-mhke0kuv.qbox.me/dfeba581a1088ef86add.jpg?imageView2/1/w/800/h/600/q/85/format/jpg/interlace/1");
        mainItemList.add(item2);

        MainItem item3 = new MainItem();
        item3.mTitle.set("ExoPlayer 일반");
        item3.mThumbnailUrl.set("https://www.thebitbag.com/wp-content/uploads/2015/01/Activate-ExoPlayer-to-load-YouTube-videos-faster-on-your-Android-device.jpg");
        mainItemList.add(item3);

        MainItem item4 = new MainItem();
        item4.mTitle.set("ExoPlayer YouTube");
        item4.mThumbnailUrl.set("http://pinkwiki.cf/images/f/fc/Naeun.png");
        mainItemList.add(item4);

        MainItem item5 = new MainItem();
        item5.mTitle.set("ExoPlayer Playlist");
        item5.mThumbnailUrl.set("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLdCA2S0wvPnd9SOS5kRSvnVaRnbWnsSKfAuU9donLAPWuASSyfw");
        mainItemList.add(item5);

        MainItem item6 = new MainItem();
        item6.mTitle.set(getString(R.string.title_ipc_test));
        item6.mThumbnailUrl.set("https://image.mycelebs.com/celeb/sq/526_sq_01.jpg");
        mainItemList.add(item6);

        MainItem item7 = new MainItem();
        item7.mTitle.set("Navigation");
        item7.mThumbnailUrl.set("https://pbs.twimg.com/media/C27uyBGUAAAdw4C.jpg");
        mainItemList.add(item7);

        return mainItemList;
    }

    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.BindingHolder> {
        private List<MainItem> mMainItemList;

        public RecyclerViewAdapter(List<MainItem> mainItemList) {
            mMainItemList = mainItemList;
        }

        @Override
        public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_main_item, parent, false);
            return new BindingHolder(view);
        }

        @Override
        public void onBindViewHolder(BindingHolder holder, int position) {
            MainItem mainItem = mMainItemList.get(position);
            holder.mBinding.setMainItem(mainItem);
            holder.mBinding.setPresenter(new Presenter());
            holder.mBinding.executePendingBindings();
        }

        @Override
        public int getItemCount() {
            if (mMainItemList != null) {
                return mMainItemList.size();
            }
            return 0;
        }

        public class BindingHolder extends RecyclerView.ViewHolder {
            RecyclerViewMainItemBinding mBinding;

            public BindingHolder(View itemView) {
                super(itemView);
                mBinding = DataBindingUtil.bind(itemView);
            }
        }
    }

    public class Presenter {
        public void onClick(View view, MainItem mainItem) {
            if (mainItem.mTitle.get().contains("YouTube 키즈 채널")) {
                startActivity(YTMainActivity.makeIntent(view.getContext()));
            }
            else if (mainItem.mTitle.get().contains("ExoPlayer 샘플")) {
                startActivity(ExoMainActivity.makeIntent(view.getContext(), mainItem.mTitle.get()));
            }
            else if (mainItem.mTitle.get().contains("ExoPlayer 일반")) {
                String url = "http://www.gs.co.kr/sites/default/files/GS%EA%B7%B8%EB%A3%B9PR%20%EB%B0%91%EA%B1%B0%EB%A6%84%ED%8E%B8%2830%EC%B4%88%29.mp4";
                startActivity(PlayerActivity.makeIntent(view.getContext(), url));
            }
            else if (mainItem.mTitle.get().contains("ExoPlayer YouTube")) {
                String url = "http://youtube.com/watch?v=NpBN0YeRMZw";
                startActivity(PlayerActivity.makeIntent(view.getContext(), url));
            }
            else if (mainItem.mTitle.get().contains("ExoPlayer Playlist")) {
                String[] playUrls = new String[] {
                        "http://www.gs.co.kr/sites/default/files/GS%EA%B7%B8%EB%A3%B9PR%20%EB%B0%91%EA%B1%B0%EB%A6%84%ED%8E%B8%2830%EC%B4%88%29.mp4",
                        "http://youtube.com/watch?v=NpBN0YeRMZw",
                        "https://www.youtube.com/watch?v=GH5K8WBy2qY"
                };
                startActivity(PlayerActivity.makeIntent(view.getContext(), playUrls));
            }
            else if (mainItem.mTitle.get().contains("IPC")) {
                startActivity(IpcTestActivity.makeIntent(view.getContext()));
            }
            else if (mainItem.mTitle.get().contains("Navigation")) {
                startActivity(NavigationActivity.makeIntent(view.getContext()));
            }
        }

        public void onButtonClick(View view, MainItem mainItem) {
            List<MainItem> mainItemList = getData();
            for (MainItem item : mainItemList) {
                if (item.mTitle.get().equals(mainItem.mTitle.get())) {
                    mainItemList.remove(item);
                    break;
                }
            }

            Random random = new Random();
            int rndNumber = random.nextInt(mainItemList.size());
            MainItem rndItem = mainItemList.get(rndNumber);

            mainItem.mTitle.set(rndItem.mTitle.get());
            mainItem.mThumbnailUrl.set(rndItem.mThumbnailUrl.get());

            Toast.makeText(view.getContext(), mainItem.mTitle.get(), Toast.LENGTH_SHORT).show();
        }
    }
}
