package com.hongka.hkcommonlibrarysample.api;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;

import com.hongka.hkcommonlibrary.retrofit.RestClient;
import com.hongka.hkcommonlibrary.retrofit.api.YouTubeApi;
import com.hongka.hkcommonlibrary.retrofit.model.youtube.ChannelsResponse;
import com.hongka.hkcommonlibrarysample.common.Constants;

import retrofit2.Call;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

/**
 * Created by HongKa on 2017. 6. 19..
 */

public class YTApiService extends IntentService {

    public static Intent makeIntentByChannelInfoList(Context context, ResultReceiver resultReceiver, String channelIds) {
        Intent intent = new Intent(context, YTApiService.class);
        intent.putExtra(KEY_RESULT_RECEIVER, resultReceiver);
        intent.putExtra(KEY_YT_CHANNEL_IDS, channelIds);
        intent.putExtra(KEY_API, API.YT_CHANNEL_INFO_LIST);
        return intent;
    }

    public static final String KEY_RESULT_DATA = "KEY_RESULT_DATA";
    private static final String KEY_RESULT_RECEIVER = "KEY_RESULT_RECEIVER";
    private static final String KEY_API = "KEY_API";
    private static final String KEY_YT_CHANNEL_IDS = "KEY_YT_CHANNEL_IDS";

    public enum API {
        YT_CHANNEL_INFO_LIST
    }

    public YTApiService() {
        super("YTApiService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        ResultReceiver resultReceiver = (ResultReceiver) intent.getExtras().get(KEY_RESULT_RECEIVER);
        API api = (API) intent.getExtras().getSerializable(KEY_API);

        if (api == API.YT_CHANNEL_INFO_LIST) {
            String channelIds = intent.getStringExtra(KEY_YT_CHANNEL_IDS);
            requestChannelInfoList(channelIds, resultReceiver);
        }
    }

    private void requestChannelInfoList(String channelIds, ResultReceiver resultReceiver) {
        Call<ChannelsResponse> call = RestClient.getInstance(YouTubeApi.DOMAIN).getApi(YouTubeApi.class).getChannels(Constants.YOUTUBE_API_KEY, channelIds);
        try {
            Response<ChannelsResponse> response = call.execute();
            if (response.isSuccessful()) {
                ChannelsResponse channelsResponse = response.body();
                if (resultReceiver != null) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(KEY_RESULT_DATA, channelsResponse);
                    resultReceiver.send(RESULT_OK, bundle);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
