package com.hongka.hkcommonlibrarysample.designpattern.observerpattern;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.hongka.hkcommonlibrarysample.R;

/**
 * Created by jusung.kim@sk.com on 2017/12/04
 */

// URL : http://jdm.kr/blog/32
// 설명 : 옵저버 패턴은 한 객체의 상태가 바뀌면 그 객체에 의존하는 다른 객체들에게 연락이 가고 자동으로 정보가 갱신되는 1:N 의 관계를 정의한다.
public class ObserverPatternActivity extends AppCompatActivity {
    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, ObserverPatternActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_design_pattern_result);

        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.title_strategy_pattern);

        TextView logTextView = (TextView) findViewById(R.id.log_text_view);
        logTextView.setText("콘솔로 확인하시오");


        Singer mcJDM = new Singer(); // 가수 탄생

        // 팬 생성
        SingerFan person1 = new SingerFan("일반인1");
        SingerFan person2 = new SingerFan("일반인2");

        // 가수를 보고 팬들이 달라 붙음.
        mcJDM.addFan(person1);
        mcJDM.addFan(person2);
        mcJDM.speak(); // 가수가 말을 합니다.

        // 팬 한명이 가수를 보기 싫어해서 탈퇴합니다.
        mcJDM.deleteFan(person2);

        // 새로운 팬이 되기로 결심합니다.
        SingerFan person3 = new SingerFan("일반인3");
        mcJDM.addFan(person3);

        mcJDM.speak(); // 가수가 말을 합니다.
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
