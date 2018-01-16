package com.hongka.hkcommonlibrarysample.design_pattern.adapterpattern;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.hongka.hkcommonlibrarysample.R;
import com.hongka.hkcommonlibrarysample.design_pattern.adapterpattern.adapter.ALibAdapter;
import com.hongka.hkcommonlibrarysample.design_pattern.adapterpattern.adapter.MyLibAdapter;
import com.hongka.hkcommonlibrarysample.design_pattern.adapterpattern.engine.ALib;
import com.hongka.hkcommonlibrarysample.design_pattern.adapterpattern.engine.MyLib;
import com.hongka.hkcommonlibrarysample.design_pattern.adapterpattern.engine.PhotoEngine;

/**
 * Created by jusung.kim@sk.com on 2017/12/15
 */
// URL : http://jdm.kr/blog/11
// 설명 :
public class AdapterPatternActivity extends AppCompatActivity {
    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, AdapterPatternActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_design_pattern_result);

        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.title_adapter_pattern);

        TextView logTextView = (TextView) findViewById(R.id.log_text_view);

//        ALib alib = new ALib();
//        // 아래 라인이 해석이 안되면 Java Upcasting 공부를 하셔야합니다.
//        PhotoEngine lib = new ALibAdapter( alib );
        MyLib myLib = new MyLib();
        PhotoEngine lib = new MyLibAdapter(myLib);
        String[] photoList = { "abc.jpg" , "def.jpg" };
        lib.printPhotoList(photoList);
        lib.printPhoto("abc.jpg");
        lib.deletePhoto("abc.jpg");
        lib.addPhoto("aaa.jpg");





        logTextView.setText("System에 결과 출력!");
    }
}
