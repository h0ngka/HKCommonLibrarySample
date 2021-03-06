package com.hongka.hkcommonlibrarysample.navigation;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.hongka.hkcommonlibrarysample.R;
import com.hongka.hkcommonlibrarysample.common.ViewModelHolder;
import com.hongka.hkcommonlibrarysample.databinding.ActivityNavigationBinding;
import com.hongka.hkcommonlibrarysample.utils.ActivityUtils;

/**
 * Created by jusung.kim@sk.com on 2017/09/15
 */

public class NavigationActivity extends AppCompatActivity {
    private final static String TAG = NavigationActivity.class.getSimpleName();
    private final static String NAVIGATION_VIEWMODEL_TAG = "NAVIGATION_VIEWMODEL_TAG";
    private NavigationViewModel mViewModel;

    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, NavigationActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate()");

        ActivityNavigationBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_navigation);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.title_navigation);

        NavigationFragment navigationFragment = findOrCreateViewFragment();
        mViewModel = findOrCreateViewModel(NavigationActivity.this);
        navigationFragment.setViewModel(mViewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause()");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e(TAG, "onNewIntent");
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

    @NonNull
    private NavigationFragment findOrCreateViewFragment() {
        NavigationFragment navigationFragment = (NavigationFragment) getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (navigationFragment == null) {
            navigationFragment = NavigationFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), navigationFragment, R.id.content_frame);
        }
        return navigationFragment;
    }

    private NavigationViewModel findOrCreateViewModel(Context context) {
        @SuppressWarnings("unchecked")
        ViewModelHolder<NavigationViewModel> retainedViewModel =
                (ViewModelHolder<NavigationViewModel>) getSupportFragmentManager().findFragmentByTag(NAVIGATION_VIEWMODEL_TAG);

        if (retainedViewModel != null && retainedViewModel.getViewmodel() != null) {
            return retainedViewModel.getViewmodel();
        } else {
            NavigationViewModel viewModel = new NavigationViewModel(context);
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), ViewModelHolder.createContainer(viewModel), NAVIGATION_VIEWMODEL_TAG);

            return viewModel;
        }
    }
}
