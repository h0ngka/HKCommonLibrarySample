package com.hongka.hkcommonlibrarysample.rxjava;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.hongka.hkcommonlibrarysample.R;

import io.reactivex.Observable;

/**
 * Created by jusung.kim@sk.com on 2018/02/08
 */

public class RxJavaActivity extends AppCompatActivity {

    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, RxJavaActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);

        emit();
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

    private void emit() {
//        Observable.just("Hello", "RxJava 2!!").subscribe(System.out::println);
        Observable.just("Hello", "RxJava 2!!").subscribe(it -> System.out.println(it));
    }
}
