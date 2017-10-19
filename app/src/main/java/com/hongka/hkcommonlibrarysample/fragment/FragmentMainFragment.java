package com.hongka.hkcommonlibrarysample.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hongka.hkcommonlibrarysample.R;
import com.hongka.hkcommonlibrarysample.databinding.FragmentMainFragmentBinding;

/**
 * Created by jusung.kim@sk.com on 2017/10/19
 */

public class FragmentMainFragment extends Fragment {
    private FragmentMainFragmentBinding mViewDataBinding;
    private FragmentMainViewModel mViewModel;

    public static FragmentMainFragment newInstance() {
        return new FragmentMainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_fragment, container, false);
        return mViewDataBinding.getRoot();
    }

    public void setViewModel(FragmentMainViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewDataBinding.setViewModel(mViewModel);
    }
}
