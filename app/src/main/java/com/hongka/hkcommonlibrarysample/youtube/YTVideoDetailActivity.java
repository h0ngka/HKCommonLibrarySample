package com.hongka.hkcommonlibrarysample.youtube;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.hongka.hkcommonlibrary.retrofit.RestClient;
import com.hongka.hkcommonlibrary.retrofit.api.YouTubeApi;
import com.hongka.hkcommonlibrary.retrofit.model.youtube.Video;
import com.hongka.hkcommonlibrary.retrofit.model.youtube.VideosResponse;
import com.hongka.hkcommonlibrary.utils.DensityScaleUtil;
import com.hongka.hkcommonlibrarysample.R;
import com.hongka.hkcommonlibrarysample.common.Constants;
import com.hongka.hkcommonlibrarysample.databinding.ActivityYtVideoDetailBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jusung.kim@sk.com on 2017/05/25
 */

public class YTVideoDetailActivity extends YouTubeBaseActivity {

    private final int REQUEST_YOUTUBE_PLAYER_RECOVERY_DIALOG = 1;
    private final String TAG = YTVideoDetailActivity.class.getSimpleName();

    private ActivityYtVideoDetailBinding mBinding;
    private YouTubePlayer mYouTubePlayer;
    private String mVideoId;

    private int mPlayerCurrentTimeMillis;
    private boolean mIsFullScreen;
    private boolean mIsVideoPaused;


    public static Intent makeIntent(Context context, String videoId) {
        Intent intent = new Intent(context, YTVideoDetailActivity.class);
        intent.putExtra("video_id", videoId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_yt_video_detail);

        mVideoId = getIntent().getStringExtra("video_id");

        // 비율 조정
        int width = DensityScaleUtil.getDisplayWidth(this);
        int height = (width * 101) / 180;
        mBinding.youtubeView.setLayoutParams(new LinearLayout.LayoutParams(width, height));

        requestVideosData(mVideoId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        release();
    }


    @Override
    protected void onResume() {
        super.onResume();
        initialize();
    }

    @Override
    protected void onPause() {
        super.onPause();

        mIsVideoPaused = true;

        if (mIsFullScreen) {
            mYouTubePlayer.setFullscreen(false);
        } else {
            release();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mIsFullScreen) {
            mYouTubePlayer.setFullscreen(false);
        } else {
            super.onBackPressed();
        }
    }

    private void initialize() {
        try {
            if (mBinding.youtubeView != null) {
                mBinding.youtubeView.initialize(Constants.YOUTUBE_API_KEY, mOnInitializedListener);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (mBinding.youtubeView != null) {
                mBinding.youtubeView.initialize(Constants.YOUTUBE_API_KEY, mOnInitializedListener);
            }
        }
    }


    private void release() {
        try {
            if (mYouTubePlayer != null) {
                mPlayerCurrentTimeMillis = mYouTubePlayer.getCurrentTimeMillis();
                mYouTubePlayer.release();
                mYouTubePlayer = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private YouTubePlayer.OnInitializedListener mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
        @Override
        public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {

            mYouTubePlayer = player;
            mYouTubePlayer.setOnFullscreenListener(new YouTubePlayer.OnFullscreenListener() {

                @Override
                public void onFullscreen(boolean isFullScreen) {
                    mIsFullScreen = isFullScreen;
                }
            });

            if (!wasRestored) {
                // ++ YouTube Player 설정
                mYouTubePlayer.setPlayerStateChangeListener(mPlayerStateChangeListener);
                mYouTubePlayer.setPlaybackEventListener(mPlayerEventChangeListener);
            }

            if (mVideoId != null) {
                try {
                    boolean isAutoPlay = true; // 자동 재생
                    if (mPlayerCurrentTimeMillis > 0) {
                        if (isAutoPlay && !mIsVideoPaused) {
                            mYouTubePlayer.loadVideo(mVideoId, mPlayerCurrentTimeMillis);

                        } else {
                            mYouTubePlayer.cueVideo(mVideoId, mPlayerCurrentTimeMillis);
                        }
                    } else {
                        if (isAutoPlay && !mIsVideoPaused) {
                            mYouTubePlayer.loadVideo(mVideoId);
                        } else {
                            mYouTubePlayer.cueVideo(mVideoId);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
            if (errorReason.isUserRecoverableError()) {
                errorReason.getErrorDialog(YTVideoDetailActivity.this, REQUEST_YOUTUBE_PLAYER_RECOVERY_DIALOG).show();
            } else {
                String errorMessage = String.format(getString(R.string.error_player), errorReason.toString());
                Toast.makeText(YTVideoDetailActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        }
    };

    private YouTubePlayer.PlayerStateChangeListener mPlayerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onAdStarted() {
            Log.i(TAG, "onAdStarted");
        }

        @Override
        public void onLoading() {
            Log.i(TAG, "onLoading");
        }

        @Override
        public void onLoaded(String videoId) {
            Log.i(TAG, "onLoaded");
        }

        @Override
        public void onVideoStarted() {
            Log.i(TAG, "onVideoStarted");
            mIsVideoPaused = false;
        }

        @Override
        public void onVideoEnded() {
            Log.i(TAG, "onVideoEnded");
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {
            Log.e(TAG, "onError() : " + errorReason.toString());
        }
    };

    private YouTubePlayer.PlaybackEventListener mPlayerEventChangeListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {
            Log.i(TAG, "onPlaying");
        }

        @Override
        public void onPaused() {
            Log.i(TAG, "onPaused");
        }

        @Override
        public void onStopped() {
            Log.i(TAG, "onStopped");
        }

        @Override
        public void onBuffering(boolean isBuffering) {
            Log.i(TAG, "onBuffering : " + isBuffering);
        }

        @Override
        public void onSeekTo(int msec) {
            Log.i(TAG, "onSeekTo : " + msec);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_YOUTUBE_PLAYER_RECOVERY_DIALOG) {
            initialize();
        }
    }

    private void requestVideosData(String videoIds) {
        Call<VideosResponse> call = RestClient.getInstance(YouTubeApi.DOMAIN).getApi(YouTubeApi.class).getVideos(Constants.YOUTUBE_API_KEY, videoIds);
        call.enqueue(new Callback<VideosResponse>() {
            @Override
            public void onResponse(Call<VideosResponse> call, Response<VideosResponse> response) {
                if (response.isSuccessful()) {
                    VideosResponse videosResponse = response.body();

                    StringBuilder sb = new StringBuilder();
                    for (Video item : videosResponse.items) {
                        sb.append("[ 제목 ]\n").append(item.snippet.title).append("\n\n");
                        sb.append("[ 게시일 ]\n").append(item.snippet.publishedAt).append("\n\n");
                        sb.append("[ 설명 ]\n").append(item.snippet.description).append("\n\n");
                    }

                    mBinding.contentTextView.setText(sb.toString());
                }
            }

            @Override
            public void onFailure(Call<VideosResponse> call, Throwable t) {

            }
        });
    }
}
