package com.hongka.hkcommonlibrarysample.youtube;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hongka.hkcommonlibrary.retrofit.RestClient;
import com.hongka.hkcommonlibrary.retrofit.api.YouTubeApi;
import com.hongka.hkcommonlibrary.retrofit.model.youtube.Playlist;
import com.hongka.hkcommonlibrary.retrofit.model.youtube.PlaylistsResponse;
import com.hongka.hkcommonlibrarysample.R;
import com.hongka.hkcommonlibrarysample.common.Constants;
import com.hongka.hkcommonlibrarysample.databinding.FragmentPlaylistListBinding;
import com.hongka.hkcommonlibrarysample.databinding.RecyclerViewYtPlaylistItemBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jusung.kim@sk.com on 2017/05/24
 */

public class YTPlaylistListFragment extends Fragment {
    private FragmentPlaylistListBinding mBinding;

    public static YTPlaylistListFragment newInstance() {
        YTPlaylistListFragment fragment = new YTPlaylistListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_playlist_list, container, false);
        return mBinding.getRoot();
    }

    public void requestPlaylistsData(String channelId, String pageToken) {
        if (TextUtils.isEmpty(pageToken)) {
            pageToken = "";
        }

        Call<PlaylistsResponse> call = RestClient.getInstance(YouTubeApi.DOMAIN).getApi(YouTubeApi.class)
                .getPlaylists(Constants.YOUTUBE_API_KEY, channelId, pageToken, 50);
        call.enqueue(new Callback<PlaylistsResponse>() {
            @Override
            public void onResponse(Call<PlaylistsResponse> call, Response<PlaylistsResponse> response) {
                if (response.isSuccessful()) {
                    List<Playlist> items = response.body().items;
                    mBinding.recyclerView.setAdapter(new RecyclerViewAdapter(items));
                }
            }

            @Override
            public void onFailure(Call<PlaylistsResponse> call, Throwable t) {

            }
        });
    }

    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.BindingHolder> {
        private List<Playlist> mPlaylistList;

        public RecyclerViewAdapter(List<Playlist> playlistList) {
            mPlaylistList = playlistList;
        }

        @Override
        public RecyclerViewAdapter.BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_yt_playlist_item, parent, false);
            return new RecyclerViewAdapter.BindingHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerViewAdapter.BindingHolder holder, int position) {
            Playlist playlist = mPlaylistList.get(position);
            holder.mBinding.setPlaylist(playlist);
            holder.mBinding.setPresenter(new Presenter());
            holder.mBinding.executePendingBindings();
        }

        @Override
        public int getItemCount() {
            if (mPlaylistList != null) {
                return mPlaylistList.size();
            }
            return 0;
        }

        public class BindingHolder extends RecyclerView.ViewHolder {
            private RecyclerViewYtPlaylistItemBinding mBinding;

            public BindingHolder(View itemView) {
                super(itemView);
                mBinding = DataBindingUtil.bind(itemView);
            }
        }
    }

    public class Presenter {
        public void onClick(View view, Playlist playlist) {
            startActivity(YTVideoListActivity.makeIntent(getContext(), playlist.id));
        }
    }
}
