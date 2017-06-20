package com.hongka.hkcommonlibrarysample.api;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;

import com.hongka.hkcommonlibrary.retrofit.RestClient;
import com.hongka.hkcommonlibrary.retrofit.api.YouTubeApi;
import com.hongka.hkcommonlibrary.retrofit.model.youtube.BaseResponse;
import com.hongka.hkcommonlibrary.retrofit.model.youtube.ChannelsResponse;
import com.hongka.hkcommonlibrary.retrofit.model.youtube.PlaylistItemsResponse;
import com.hongka.hkcommonlibrary.retrofit.model.youtube.PlaylistsResponse;
import com.hongka.hkcommonlibrary.retrofit.model.youtube.SearchResponse;
import com.hongka.hkcommonlibrary.retrofit.model.youtube.VideosResponse;
import com.hongka.hkcommonlibrarysample.common.Constants;

import retrofit2.Call;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

/**
 * Created by HongKa on 2017. 6. 19..
 */

public class YTApiService extends IntentService {

    public static Intent makeIntentForChannelInfoList(Context context, ResultReceiver resultReceiver, String channelIds) {
        Intent intent = new Intent(context, YTApiService.class);
        intent.putExtra(KEY_RESULT_RECEIVER, resultReceiver);
        intent.putExtra(KEY_CHANNEL_IDS, channelIds);
        intent.putExtra(KEY_API, API.CHANNEL_INFO_LIST);
        return intent;
    }

    public static Intent makeIntentForPlaylists(Context context, ResultReceiver resultReceiver, String channelId, String pageToken) {
        Intent intent = new Intent(context, YTApiService.class);
        intent.putExtra(KEY_RESULT_RECEIVER, resultReceiver);
        intent.putExtra(KEY_CHANNEL_ID, channelId);
        intent.putExtra(KEY_PAGE_TOKEN, pageToken);
        intent.putExtra(KEY_API, API.PLAYLISTS);
        return intent;
    }

    public static Intent makeIntentForPlaylistItems(Context context, ResultReceiver resultReceiver, String playlistId, String pageToken) {
        Intent intent = new Intent(context, YTApiService.class);
        intent.putExtra(KEY_RESULT_RECEIVER, resultReceiver);
        intent.putExtra(KEY_PLAYLIST_ID, playlistId);
        intent.putExtra(KEY_PAGE_TOKEN, pageToken);
        intent.putExtra(KEY_API, API.PLAYLIST_ITEMS);
        return intent;
    }

    public static Intent makeIntentForLatestVideo(Context context, ResultReceiver resultReceiver, String channelId, String pageToken) {
        Intent intent = new Intent(context, YTApiService.class);
        intent.putExtra(KEY_RESULT_RECEIVER, resultReceiver);
        intent.putExtra(KEY_CHANNEL_ID, channelId);
        intent.putExtra(KEY_PAGE_TOKEN, pageToken);
        intent.putExtra(KEY_API, API.LATEST_VIDEOS);
        return intent;
    }

    public static Intent makeIntentForVideos(Context context, ResultReceiver resultReceiver, String videoIds) {
        Intent intent = new Intent(context, YTApiService.class);
        intent.putExtra(KEY_RESULT_RECEIVER, resultReceiver);
        intent.putExtra(KEY_VIDEO_IDS, videoIds);
        intent.putExtra(KEY_API, API.VIDEOS);
        return intent;
    }

    public static final String KEY_RESULT_DATA = "KEY_RESULT_DATA";
    public static final String KEY_API = "KEY_API";
    private static final String KEY_RESULT_RECEIVER = "KEY_RESULT_RECEIVER";
    private static final String KEY_CHANNEL_ID = "KEY_CHANNEL_ID";
    private static final String KEY_CHANNEL_IDS = "KEY_CHANNEL_IDS";
    private static final String KEY_PLAYLIST_ID = "KEY_PLAYLIST_ID";
    private static final String KEY_PAGE_TOKEN = "KEY_PAGE_TOKEN";
    private static final String KEY_VIDEO_IDS = "KEY_VIDEO_IDS";

    public enum API {
        CHANNEL_INFO_LIST,
        PLAYLISTS,
        PLAYLIST_ITEMS,
        VIDEOS,
        LATEST_VIDEOS
    }

    public YTApiService() {
        super("YTApiService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        ResultReceiver resultReceiver = (ResultReceiver) intent.getExtras().get(KEY_RESULT_RECEIVER);
        API api = (API) intent.getExtras().getSerializable(KEY_API);

        if (api == API.CHANNEL_INFO_LIST) {
            String channelIds = intent.getStringExtra(KEY_CHANNEL_IDS);
            requestChannelInfoList(channelIds, resultReceiver);
        } else if (api == API.PLAYLISTS) {
            String channelId = intent.getStringExtra(KEY_CHANNEL_ID);
            String pageToken = intent.getStringExtra(KEY_PAGE_TOKEN);
            requestPlaylists(channelId, pageToken, resultReceiver);
        } else if (api == API.PLAYLIST_ITEMS) {
            String playlistId = intent.getStringExtra(KEY_PLAYLIST_ID);
            String pageToken = intent.getStringExtra(KEY_PAGE_TOKEN);
            requestPlaylistItems(playlistId, pageToken, resultReceiver);
        } else if (api == API.LATEST_VIDEOS) {
            String channelId = intent.getStringExtra(KEY_CHANNEL_ID);
            String pageToken = intent.getStringExtra(KEY_PAGE_TOKEN);
            requestLatestVideos(channelId, pageToken, resultReceiver);
        } else if (api == API.VIDEOS) {
            String videoIds = intent.getStringExtra(KEY_VIDEO_IDS);
            requestVideos(videoIds, resultReceiver);
        }
    }

    private void sendResult(API api, BaseResponse baseResponse, ResultReceiver resultReceiver) {
        if (resultReceiver != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(KEY_API, api);
            bundle.putParcelable(KEY_RESULT_DATA, baseResponse);
            resultReceiver.send(RESULT_OK, bundle);
        }
    }

    private void requestChannelInfoList(String channelIds, ResultReceiver resultReceiver) {
        Call<ChannelsResponse> call = RestClient.getInstance(YouTubeApi.DOMAIN).getApi(YouTubeApi.class).getChannels(Constants.YOUTUBE_API_KEY, channelIds);
        try {
            Response<ChannelsResponse> response = call.execute();
            if (response.isSuccessful()) {
                sendResult(API.CHANNEL_INFO_LIST, response.body(), resultReceiver);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void requestPlaylists(String channelId, String pageToken, ResultReceiver resultReceiver) {
        Call<PlaylistsResponse> call = RestClient.getInstance(YouTubeApi.DOMAIN).getApi(YouTubeApi.class).getPlaylists(Constants.YOUTUBE_API_KEY, channelId, pageToken, 50);
        try {
            Response<PlaylistsResponse> response = call.execute();
            if (response.isSuccessful()) {
                sendResult(API.PLAYLISTS, response.body(), resultReceiver);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void requestPlaylistItems(String playlistId, String pageToken, ResultReceiver resultReceiver) {
        Call<PlaylistItemsResponse> call = RestClient.getInstance(YouTubeApi.DOMAIN).getApi(YouTubeApi.class).getPlaylistItems(Constants.YOUTUBE_API_KEY, playlistId, pageToken, 50);
        try {
            Response<PlaylistItemsResponse> response = call.execute();
            if (response.isSuccessful()) {
                sendResult(API.PLAYLIST_ITEMS, response.body(), resultReceiver);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void requestLatestVideos(String channelId, String pageToken, ResultReceiver resultReceiver) {
        Call<SearchResponse> call = RestClient.getInstance(YouTubeApi.DOMAIN).getApi(YouTubeApi.class).getSearch(Constants.YOUTUBE_API_KEY, channelId, pageToken, "video", "", 50);
        try {
            Response<SearchResponse> response = call.execute();
            if (response.isSuccessful()) {
                sendResult(API.LATEST_VIDEOS, response.body(), resultReceiver);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void requestVideos(String videoIds, ResultReceiver resultReceiver) {
        Call<VideosResponse> call = RestClient.getInstance(YouTubeApi.DOMAIN).getApi(YouTubeApi.class).getVideos(Constants.YOUTUBE_API_KEY, videoIds);
        try {
            Response<VideosResponse> response = call.execute();
            if (response.isSuccessful()) {
                sendResult(API.VIDEOS, response.body(), resultReceiver);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
