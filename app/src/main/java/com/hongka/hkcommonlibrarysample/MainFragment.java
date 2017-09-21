package com.hongka.hkcommonlibrarysample;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hongka.hkcommonlibrarysample.databinding.FragmentMainBinding;
import com.hongka.hkcommonlibrarysample.databinding.RecyclerViewMainItemBinding;
import com.hongka.hkcommonlibrarysample.model.MainItem;

/**
 * Created by jusung.kim@sk.com on 2017/09/18
 */

public class MainFragment extends Fragment {
    private FragmentMainBinding mViewDataBinding;
    private MainViewModel mViewModel;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        return mViewDataBinding.getRoot();
    }

    public void setViewModel(MainViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel.loadData();
        mViewDataBinding.recyclerView.setAdapter(new RecyclerViewAdapter(mViewModel));
    }

    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.BindingHolder> {
        private MainViewModel mViewModel;

        public RecyclerViewAdapter(MainViewModel viewModel) {
            mViewModel = viewModel;
        }

        @Override
        public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_main_item, parent, false);
            return new BindingHolder(view);
        }

        @Override
        public void onBindViewHolder(BindingHolder holder, int position) {
            MainItem mainItem = mViewModel.mMainItems.get(position);
            holder.mBinding.setMainItem(mainItem);
            holder.mBinding.setViewModel(mViewModel);
            holder.mBinding.executePendingBindings();
        }

        @Override
        public int getItemCount() {
            if (mViewModel.mMainItems != null) {
                return mViewModel.mMainItems.size();
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
}
