package com.hongka.hkcommonlibrarysample.fragment;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.hongka.hkcommonlibrarysample.R;
import com.hongka.hkcommonlibrarysample.common.ViewModelHolder;
import com.hongka.hkcommonlibrarysample.databinding.ActivityFragmentMainBinding;
import com.hongka.hkcommonlibrarysample.navigation.NavigationViewModel;
import com.hongka.hkcommonlibrarysample.utils.ActivityUtils;

/**
 * Created by jusung.kim@sk.com on 2017/10/19
 */

public class FragmentMainActivity extends AppCompatActivity {
    private final static String FRAGMENT_MAIN_VIEWMODEL_TAG = "FRAGMENT_MAIN_VIEWMODEL_TAG";
    private FragmentMainViewModel mViewModel;

    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, FragmentMainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFragmentMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_fragment_main);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.title_fragment_test);
        mViewModel = findOrCreateViewModel(this);
        binding.setViewModel(mViewModel);
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

    private FragmentMainViewModel findOrCreateViewModel(Context context) {
        @SuppressWarnings("unchecked")
        ViewModelHolder<FragmentMainViewModel> retainedViewModel =
                (ViewModelHolder<FragmentMainViewModel>) getSupportFragmentManager().findFragmentByTag(FRAGMENT_MAIN_VIEWMODEL_TAG);

        if (retainedViewModel != null && retainedViewModel.getViewmodel() != null) {
            return retainedViewModel.getViewmodel();
        } else {
            FragmentMainViewModel viewModel = new FragmentMainViewModel(context);
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), ViewModelHolder.createContainer(viewModel), FRAGMENT_MAIN_VIEWMODEL_TAG);

            return viewModel;
        }
    }
}
