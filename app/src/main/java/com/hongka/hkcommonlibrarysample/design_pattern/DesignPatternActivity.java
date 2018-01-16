package com.hongka.hkcommonlibrarysample.design_pattern;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.hongka.hkcommonlibrarysample.R;
import com.hongka.hkcommonlibrarysample.databinding.ActivityDesignPatternBinding;

/**
 * Created by jusung.kim@sk.com on 2017/12/01
 */

public class DesignPatternActivity extends AppCompatActivity {
    private final String TAG = DesignPatternActivity.class.getSimpleName();

    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, DesignPatternActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDesignPatternBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_design_pattern);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.title_design_pattern);

        binding.setViewModel(new DesignPatternViewModel(this));
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
}
