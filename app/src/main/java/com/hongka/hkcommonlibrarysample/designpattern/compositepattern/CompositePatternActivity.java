package com.hongka.hkcommonlibrarysample.designpattern.compositepattern;

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
// URL : http://jdm.kr/blog/228
// 설명 : Composite Pattern은 간단하게 말해 단일 객체든 객체들의 집합이든 같은 방법으로 취급하는 것입니다.
// 다시 말해, 개별적인 객체들과 객체들의 집합간의 처리 방법의 차이가 없을 경우 사용하면 됩니다.
// 여기서 컴포지트의 의미는 일부 또는 그룹을 표현하는 객체들을 트리 구조로 구성한다는 겁니다.
public class CompositePatternActivity extends AppCompatActivity {
    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, CompositePatternActivity.class);
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
        logTextView.setText("이해 할려면 소스 분석을 하시오!");

        Directory dir1 = new Directory("디렉토리1");
        dir1.add(new File("파일1"));
        dir1.add(new File("파일2"));
        dir1.add(new Directory("디렉토리2"));

        Directory dir3 = new Directory("디렉토리3");
        dir3.add(dir1);
        dir3.getName();
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
