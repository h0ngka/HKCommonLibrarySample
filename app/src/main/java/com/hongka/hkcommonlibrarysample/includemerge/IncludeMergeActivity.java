package com.hongka.hkcommonlibrarysample.includemerge;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.hongka.hkcommonlibrarysample.R;
import com.hongka.hkcommonlibrarysample.databinding.ActivityIncludeMergeBinding;

/**
 * Created by jusung.kim@sk.com on 2017/10/23
 */

public class IncludeMergeActivity extends AppCompatActivity {
    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, IncludeMergeActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityIncludeMergeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_include_merge);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.title_include_merge_test);

        binding.setViewModel(new IncludeMergeViewModel(this));
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
