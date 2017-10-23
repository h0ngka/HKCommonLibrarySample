package com.hongka.hkcommonlibrarysample.viewstub;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.Toast;

import com.hongka.hkcommonlibrarysample.R;
import com.hongka.hkcommonlibrarysample.databinding.ActivityNavigationBinding;
import com.hongka.hkcommonlibrarysample.databinding.ActivityViewStubBinding;

/**
 * Created by jusung.kim@sk.com on 2017/10/23
 */

public class ViewStubActivity extends AppCompatActivity {
    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, ViewStubActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActivityViewStubBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_view_stub);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.title_view_stub_test);

        binding.showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = null;
                if (!binding.stubImport.isInflated()) {
                    view = binding.stubImport.getViewStub().inflate();
                    view.findViewById(R.id.view_panel).setBackgroundColor(Color.RED);
                } else {
                    view = findViewById(R.id.panel_import);
                    view.setVisibility(View.VISIBLE);
                    view.findViewById(R.id.view_panel).setBackgroundColor(Color.BLUE);
                }
            }
        });

        binding.hideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.stubImport.isInflated()) {
                    View view = findViewById(R.id.panel_import);
                    view.setVisibility(View.GONE);
                }
            }
        });
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
