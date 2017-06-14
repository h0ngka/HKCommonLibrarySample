package com.hongka.hkcommonlibrarysample.youtube;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hongka.hkcommonlibrary.retrofit.RestClient;
import com.hongka.hkcommonlibrary.retrofit.api.YouTubeApi;
import com.hongka.hkcommonlibrary.retrofit.model.youtube.PlaylistItem;
import com.hongka.hkcommonlibrary.retrofit.model.youtube.PlaylistItemsResponse;
import com.hongka.hkcommonlibrary.retrofit.model.youtube.Search;
import com.hongka.hkcommonlibrary.retrofit.model.youtube.SearchResponse;
import com.hongka.hkcommonlibrary.retrofit.model.youtube.Snippet;
import com.hongka.hkcommonlibrarysample.R;
import com.hongka.hkcommonlibrarysample.common.Constants;
import com.hongka.hkcommonlibrarysample.databinding.FragmentVideoListBinding;
import com.hongka.hkcommonlibrarysample.databinding.RecyclerViewYtVideoItemBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jusung.kim@sk.com on 2017/05/24
 */

public class YTVideoListFragment extends Fragment {
    private FragmentVideoListBinding mBinding;
    private List<PlaylistItem> mPlaylistItemList;
    private List<Search> mSearchList;
    private String mPageToken;

    public static YTVideoListFragment newInstance() {
        YTVideoListFragment fragment = new YTVideoListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_video_list, container, false);
        return mBinding.getRoot();
    }

    public void requestPlaylistItems(String playlistId, String pageToken) {
        Call<PlaylistItemsResponse> call = RestClient.getInstance(YouTubeApi.DOMAIN).getApi(YouTubeApi.class).getPlaylistItems(Constants.YOUTUBE_API_KEY, playlistId, pageToken, 50);
        call.enqueue(new Callback<PlaylistItemsResponse>() {
            @Override
            public void onResponse(Call<PlaylistItemsResponse> call, Response<PlaylistItemsResponse> response) {
                if (response.isSuccessful()) {
                    mPageToken = response.body().pageInfo.nextPageToken;
                    mPlaylistItemList = response.body().items;
                    mBinding.recyclerView.setAdapter(new RecyclerViewAdapter());
                }
            }

            @Override
            public void onFailure(Call<PlaylistItemsResponse> call, Throwable t) {

            }
        });
    }

    public void requestLatestVideo(String channelId, String pageToken) {
        Call<SearchResponse> call = RestClient.getInstance(YouTubeApi.DOMAIN).getApi(YouTubeApi.class).getSearch(Constants.YOUTUBE_API_KEY, channelId, pageToken, "video", "", 50);
        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if (response.isSuccessful()) {
                    mPageToken = response.body().pageInfo.nextPageToken;
                    mSearchList = response.body().items;
                    mBinding.recyclerView.setAdapter(new RecyclerViewAdapter());
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {

            }
        });
    }

    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.BindingHolder> {
        private boolean mIsSearch;

        public RecyclerViewAdapter() {
            if (mSearchList != null && mSearchList.size() > 0) {
                mIsSearch = true;
            }
        }

        @Override
        public YTVideoListFragment.RecyclerViewAdapter.BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_yt_video_item, parent, false);
            return new YTVideoListFragment.RecyclerViewAdapter.BindingHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerViewAdapter.BindingHolder holder, int position) {
            String videoId = null;
            Snippet snippet = null;
            if (mIsSearch) {
                Search search = mSearchList.get(position);
                if (search != null) {
                    videoId = search.id.videoId;
                    snippet = search.snippet;
                }
            } else {
                PlaylistItem playlistItem = mPlaylistItemList.get(position);
                if (playlistItem != null) {
                    videoId = playlistItem.snippet.resourceId.videoId;
                    snippet = playlistItem.snippet;
                }
            }

            holder.mBinding.setVideoId(videoId);
            holder.mBinding.setSnippet(snippet);
            holder.mBinding.setPresenter(new Presenter());
            holder.mBinding.executePendingBindings();
        }

        @Override
        public int getItemCount() {
            if (mIsSearch) {
                if (mSearchList != null) {
                    return mSearchList.size();
                }
            } else {
                if (mPlaylistItemList != null) {
                    return mPlaylistItemList.size();
                }
            }
            return 0;
        }

        public class BindingHolder extends RecyclerView.ViewHolder {
            private RecyclerViewYtVideoItemBinding mBinding;

            public BindingHolder(View itemView) {
                super(itemView);
                mBinding = DataBindingUtil.bind(itemView);
            }
        }
    }

    public class Presenter {
        public void onClick(View view, String videoId, Snippet snippet) {
            startActivity(YTVideoDetailActivity.makeIntent(view.getContext(), videoId));
        }
    }
}
