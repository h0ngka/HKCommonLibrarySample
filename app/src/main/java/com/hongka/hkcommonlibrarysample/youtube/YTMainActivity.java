package com.hongka.hkcommonlibrarysample.youtube;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.hongka.hkcommonlibrary.retrofit.RestClient;
import com.hongka.hkcommonlibrary.retrofit.api.YouTubeApi;
import com.hongka.hkcommonlibrary.retrofit.model.youtube.Channel;
import com.hongka.hkcommonlibrary.retrofit.model.youtube.ChannelsResponse;
import com.hongka.hkcommonlibrarysample.R;
import com.hongka.hkcommonlibrarysample.common.Constants;
import com.hongka.hkcommonlibrarysample.databinding.ActivityYtMainBinding;
import com.hongka.hkcommonlibrarysample.databinding.RecyclerViewYtMainItemBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YTMainActivity extends AppCompatActivity {
    private ActivityYtMainBinding mBinding;

    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, YTMainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_yt_main);
        setSupportActionBar(mBinding.toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.title_activity_channels);

        requestChannelsData("UCdet8uJfTFlACtY05BQmJ1Q,UCDHIA4d_e4SOrODpU7nrVtA,UCx8IhwapX8E7uooFYJIeVZw,UCN106RQxroojNzkEMuRC0BA,UCUEbQMiMQbn5gOIe9ljEcWw,UC0SVqB_1E2Kgh3cx3Y8xtfA,UC1WEOQjm8tyT1pXY6xaoYZQ");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void requestChannelsData(String channelId) {
        Call<ChannelsResponse> call = RestClient.getInstance(YouTubeApi.DOMAIN).getApi(YouTubeApi.class)
                .getChannels(Constants.YOUTUBE_API_KEY, channelId);
        call.enqueue(new Callback<ChannelsResponse>() {
            @Override
            public void onResponse(Call<ChannelsResponse> call, Response<ChannelsResponse> response) {
                if (response.isSuccessful()) {
                    ChannelsResponse channelsResponse = response.body();
                    List<Channel> items = channelsResponse.items;
                    mBinding.recyclerView.setAdapter(new RecyclerViewAdapter(items));

//                    for (Channel item : items) {
//                        Log.i("Test", "comma : " + NumberUtils.commaString(NumberUtils.parse(item.statistics.videoCount, 0)));
//                        Log.i("Test", "channelId : " + item.id);
//                        Log.i("Test", "snippet.title : " + item.snippet.title);
//                        Log.i("Test", "snippet.thumbnails.high.url : " + item.snippet.thumbnails.high.url);
//                        Log.i("Test", "brandingSettings.channel.title : " + item.brandingSettings.channel.title);
//                        Log.i("Test", "brandingSettings.image.bannerMobileImageUrl : " + item.brandingSettings.image.bannerMobileImageUrl);
//                    }
                }
            }

            @Override
            public void onFailure(Call<ChannelsResponse> call, Throwable t) {

            }
        });
    }

    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.BindingHolder> {
        private List<Channel> mChannelList;

        public RecyclerViewAdapter(List<Channel> channelList) {
            mChannelList = channelList;
        }

        @Override
        public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_yt_main_item, parent, false);
            return new YTMainActivity.RecyclerViewAdapter.BindingHolder(view);
        }

        @Override
        public void onBindViewHolder(BindingHolder holder, int position) {
            Channel channel = mChannelList.get(position);
            holder.mBinding.setChannel(channel);
            holder.mBinding.setPresenter(new Presenter());
            holder.mBinding.executePendingBindings();
        }

        @Override
        public int getItemCount() {
            if (mChannelList != null) {
                return mChannelList.size();
            }
            return 0;
        }

        public class BindingHolder extends RecyclerView.ViewHolder {
            private RecyclerViewYtMainItemBinding mBinding;

            public BindingHolder(View itemView) {
                super(itemView);
                mBinding = DataBindingUtil.bind(itemView);
            }
        }
    }

    public class Presenter {
        public void onClick(View view, Channel channel) {
            Intent intent = YTChannelActivity.makeIntent(YTMainActivity.this, channel);

            // 롤리팝만 지원하는 코드...
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                View channelBannerImageView = view.findViewById(R.id.channel_banner_image_view);
                View channelProfileImageView = view.findViewById(R.id.channel_profile_image_view);
                ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(YTMainActivity.this,
                        Pair.create((View)channelBannerImageView, channelBannerImageView.getTransitionName()),
                        Pair.create((View)channelProfileImageView, channelProfileImageView.getTransitionName()));
                ActivityCompat.startActivity(YTMainActivity.this, intent, activityOptions.toBundle());
            } else {
                startActivity(intent);
            }
        }
    }
}
