package com.hongka.hkcommonlibrarysample.data_structure;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.hongka.hkcommonlibrarysample.R;
import com.hongka.hkcommonlibrarysample.databinding.ActivityDataStructureBinding;
import com.hongka.hkcommonlibrarysample.design_pattern.DesignPatternViewModel;

import java.io.DataInputStream;

/**
 * Created by jusung.kim@sk.com on 2017/12/18
 */

public class DataSturectureActivity extends AppCompatActivity {

    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, DataSturectureActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityDataStructureBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_structure);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.title_data_structure);

        binding.setViewModel(new DataStructureViewModel(this));
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
