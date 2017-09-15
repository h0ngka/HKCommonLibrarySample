package com.hongka.hkcommonlibrarysample.navigation;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hongka.hkcommonlibrarysample.MainActivity;
import com.hongka.hkcommonlibrarysample.R;
import com.hongka.hkcommonlibrarysample.databinding.FragmentNavigationBinding;

/**
 * Created by jusung.kim@sk.com on 2017/09/15
 */

public class NavigationFragment extends Fragment {

    private FragmentNavigationBinding mViewDataBinding;
    private NavigationViewModel mViewModel;

    public static NavigationFragment newInstance() {
        return new NavigationFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_navigation, container, false);
        return mViewDataBinding.getRoot();
    }

    public void setViewModel(NavigationViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewDataBinding.setViewModel(mViewModel);
    }
}
