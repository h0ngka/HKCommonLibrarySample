package com.hongka.hkcommonlibrarysample;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.hongka.hkcommonlibrarysample.databinding.ActivityMainBinding;
import com.hongka.hkcommonlibrarysample.common.ViewModelHolder;
import com.hongka.hkcommonlibrarysample.utils.ActivityUtils;

public class MainActivity extends AppCompatActivity {

    private final static String MAIN_VIEWMODEL_TAG = "MAIN_VIEWMODEL_TAG";
    private MainViewModel mViewModel;

    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        MainFragment mainFragment = findOrCreateViewFragment();
        mViewModel = findOrCreateViewModel(MainActivity.this);
        mainFragment.setViewModel(mViewModel);
    }

    @NonNull
    private MainFragment findOrCreateViewFragment() {
        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (mainFragment == null) {
            mainFragment = MainFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mainFragment, R.id.content_frame);
        }
        return mainFragment;
    }

    private MainViewModel findOrCreateViewModel(Context context) {
        @SuppressWarnings("unchecked")
        ViewModelHolder<MainViewModel> retainedViewModel =
                (ViewModelHolder<MainViewModel>) getSupportFragmentManager().findFragmentByTag(MAIN_VIEWMODEL_TAG);

        if (retainedViewModel != null && retainedViewModel.getViewmodel() != null) {
            return retainedViewModel.getViewmodel();
        } else {
            MainViewModel viewModel = new MainViewModel(context);
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), ViewModelHolder.createContainer(viewModel), MAIN_VIEWMODEL_TAG);

            return viewModel;
        }
    }
}
