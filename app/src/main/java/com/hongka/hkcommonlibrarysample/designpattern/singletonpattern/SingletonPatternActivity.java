package com.hongka.hkcommonlibrarysample.designpattern.singletonpattern;

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
// 설명 : 싱글턴 패턴은 유일하게 사용해야 할 클래스가 필요 할 때에 유용하게 쓰이는 디자인 패턴입니다.
public class SingletonPatternActivity extends AppCompatActivity {
    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, SingletonPatternActivity.class);
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

        StringBuilder sb = new StringBuilder();


        Singleton s = Singleton.getInstance();
        sb.append(s.getMessage());

        logTextView.setText(sb.toString());
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
