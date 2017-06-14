package com.hongka.hkcommonlibrarysample.exoplayer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Surface;
import android.view.View;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoRendererEventListener;
import com.hongka.hkcommonlibrarysample.R;

/**
 * Created by jusung.kim@sk.com on 2017/05/26
 */

public class TestExoPlayerActivity extends AppCompatActivity {

    private static final DefaultBandwidthMeter BANDWIDTH_METER = new DefaultBandwidthMeter();
    private static final String TAG = TestExoPlayerActivity.class.getSimpleName();

    private ExoEventListener mExoEventListener;
    private ExoVideoRenderEventListener mExoVideoRenderEventListener;
    private ExoAudioRendererEventListener mExoAudioRendererEventListener;

    private SimpleExoPlayerView mPlayerView;
    private SimpleExoPlayer mPlayer;
    private String mPlayUrl;

    private long mPlaybackPosition;
    private int mCurrentWindow;
    private boolean mPlayWhenReady = true;

    public static Intent makeIntent(Context context, String playUrl) {
        Intent intent = new Intent(context, TestExoPlayerActivity.class);
        intent.putExtra("play_url", playUrl);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo_player);

        mPlayUrl = getIntent().getStringExtra("play_url");

        mExoEventListener = new ExoEventListener();
        mExoVideoRenderEventListener = new ExoVideoRenderEventListener();
        mExoAudioRendererEventListener = new ExoAudioRendererEventListener();

        mPlayerView = (SimpleExoPlayerView) findViewById(R.id.video_view);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initializePlayer();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideSystemUi();
        if ((Util.SDK_INT <= 23 || mPlayer == null)) {
            initializePlayer();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    private void initializePlayer() {
        if (mPlayer == null) {
            mPlayer = ExoPlayerFactory.newSimpleInstance(
                    new DefaultRenderersFactory(this),
                    new DefaultTrackSelector(), new DefaultLoadControl());

            mPlayer.addListener(mExoEventListener);
            mPlayer.setVideoDebugListener(mExoVideoRenderEventListener);
            mPlayer.setAudioDebugListener(mExoAudioRendererEventListener);

            mPlayer.setPlayWhenReady(mPlayWhenReady);
            mPlayer.seekTo(mCurrentWindow, mPlaybackPosition);

            mPlayerView.setPlayer(mPlayer);
        }
//        Uri uri = Uri.parse(getString(R.string.media_url_mp4));
        Uri uri = Uri.parse(mPlayUrl);
        MediaSource mediaSource = buildMediaSource(uri);
        mPlayer.prepare(mediaSource, true, false);
    }

//    // DASH
//    private void initializePlayer() {
//        if (mPlayer == null) {
//            // a factory to create an AdaptiveVideoTrackSelection
//            TrackSelection.Factory adaptiveTrackSelectionFactory =
//                    new AdaptiveTrackSelection.Factory(BANDWIDTH_METER);
//
//            mPlayer = ExoPlayerFactory.newSimpleInstance(
//                    new DefaultRenderersFactory(this),
//                    new DefaultTrackSelector(adaptiveTrackSelectionFactory),
//                    new DefaultLoadControl());
//
//            mPlayer.addListener(mExoEventListener);
//            mPlayer.setVideoDebugListener(mExoVideoRenderEventListener);
//            mPlayer.setAudioDebugListener(mExoAudioRendererEventListener);
//
//            mPlayer.setPlayWhenReady(mPlayWhenReady);
//            mPlayer.seekTo(mCurrentWindow, mPlaybackPosition);
//
//            mPlayerView.setPlayer(mPlayer);
//        }
//        Uri uri = Uri.parse(getString(R.string.media_url_dash));
//        MediaSource mediaSource = buildMediaSource(uri);
//        mPlayer.prepare(mediaSource, true, false);
//    }


    private void releasePlayer() {
        if (mPlayer != null) {
            mPlaybackPosition = mPlayer.getCurrentPosition();
            mCurrentWindow = mPlayer.getCurrentWindowIndex();
            mPlayWhenReady = mPlayer.getPlayWhenReady();

            mPlayer.removeListener(mExoEventListener);
            mPlayer.setVideoListener(null);
            mPlayer.setVideoDebugListener(null);
            mPlayer.setAudioDebugListener(null);
            mPlayer.release();
            mPlayer = null;
        }
    }

    // http mp4
//    private MediaSource buildMediaSource(Uri uri) {
//        return new ExtractorMediaSource(uri,
//                new DefaultHttpDataSourceFactory("ua"),
//                new DefaultExtractorsFactory(), null, null);
//    }

    // http mp4, file mp4
    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource(uri,
                new DefaultDataSourceFactory(this, "ua"),
                new DefaultExtractorsFactory(), null, null);
    }


//    // ConcatenatingMediaSource
//    private MediaSource buildMediaSource(Uri uri) {
//        // these are reused for both media sources we create below
//        DefaultExtractorsFactory extractorsFactory =
//                new DefaultExtractorsFactory();
//        DefaultHttpDataSourceFactory dataSourceFactory =
//                new DefaultHttpDataSourceFactory( "user-agent");
//
//        ExtractorMediaSource videoSource =
//                new ExtractorMediaSource(uri, dataSourceFactory,
//                        extractorsFactory, null, null);
//
//        Uri audioUri = Uri.parse(getString(R.string.media_url_mp3));
//        ExtractorMediaSource audioSource =
//                new ExtractorMediaSource(audioUri, dataSourceFactory,
//                        extractorsFactory, null, null);
//
//        return new ConcatenatingMediaSource(audioSource, videoSource);
//    }

//    // Dash
//    private MediaSource buildMediaSource(Uri uri) {
//        DataSource.Factory dataSourceFactory =
//                new DefaultHttpDataSourceFactory("ua", BANDWIDTH_METER);
//        DashChunkSource.Factory dashChunkSourceFactory =
//                new DefaultDashChunkSource.Factory(dataSourceFactory);
//
//        return new DashMediaSource(uri, dataSourceFactory,
//                dashChunkSourceFactory, null, null);
//    }

    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        mPlayerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    private class ExoEventListener implements ExoPlayer.EventListener {
        // EventListener
        @Override
        public void onTimelineChanged(Timeline timeline, Object manifest) {

        }

        @Override
        public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
            Log.d(TAG, "trackGroups : " + trackGroups.toString()
                    + " trackSelections : " + trackSelections.toString());
        }

        @Override
        public void onLoadingChanged(boolean isLoading) {

        }

        @Override
        public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
            String stateString;
            switch (playbackState) {
                case ExoPlayer.STATE_IDLE:
                    stateString = "ExoPlayer.STATE_IDLE      -";
                    break;
                case ExoPlayer.STATE_BUFFERING:
                    stateString = "ExoPlayer.STATE_BUFFERING -";
                    break;
                case ExoPlayer.STATE_READY:
                    stateString = "ExoPlayer.STATE_READY     -";
                    break;
                case ExoPlayer.STATE_ENDED:
                    stateString = "ExoPlayer.STATE_ENDED     -";
                    break;
                default:
                    stateString = "UNKNOWN_STATE             -";
                    break;
            }
            Log.d(TAG, "changed state to " + stateString
                    + " playWhenReady: " + playWhenReady);
        }

        @Override
        public void onPlayerError(ExoPlaybackException error) {

        }

        @Override
        public void onPositionDiscontinuity() {

        }

        @Override
        public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

        }
    }

    private class ExoVideoRenderEventListener implements VideoRendererEventListener {

        @Override
        public void onVideoEnabled(DecoderCounters counters) {

        }

        @Override
        public void onVideoDecoderInitialized(String decoderName, long initializedTimestampMs, long initializationDurationMs) {

        }

        @Override
        public void onVideoInputFormatChanged(Format format) {

        }

        @Override
        public void onDroppedFrames(int count, long elapsedMs) {

        }

        @Override
        public void onVideoSizeChanged(int width, int height, int unappliedRotationDegrees, float pixelWidthHeightRatio) {

        }

        @Override
        public void onRenderedFirstFrame(Surface surface) {

        }

        @Override
        public void onVideoDisabled(DecoderCounters counters) {

        }
    }

    private class ExoAudioRendererEventListener implements AudioRendererEventListener {

        @Override
        public void onAudioEnabled(DecoderCounters counters) {

        }

        @Override
        public void onAudioSessionId(int audioSessionId) {

        }

        @Override
        public void onAudioDecoderInitialized(String decoderName, long initializedTimestampMs, long initializationDurationMs) {

        }

        @Override
        public void onAudioInputFormatChanged(Format format) {

        }

        @Override
        public void onAudioTrackUnderrun(int bufferSize, long bufferSizeMs, long elapsedSinceLastFeedMs) {

        }

        @Override
        public void onAudioDisabled(DecoderCounters counters) {

        }
    }
}
