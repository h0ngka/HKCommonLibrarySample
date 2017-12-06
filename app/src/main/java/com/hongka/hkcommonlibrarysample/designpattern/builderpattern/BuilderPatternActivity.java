package com.hongka.hkcommonlibrarysample.designpattern.builderpattern;

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
 * Created by jusung.kim@sk.com on 2017/12/06
 */

// URL : http://jdm.kr/blog/217
// 설명 : 불필요한 생성자를 만들지 않고 객체를 만든다. 데이터의 순서에 상관 없이 객체를 만들어 낸다. 사용자가 봤을때 명시적이고 이해할 수 있어야 한다.
// 빌더 패턴은 생성자에 들어갈 매개 변수가 많든 적든 차례차례 매개 변수를 받아들이고 모든 매개 변수를 받은 뒤에 이 변수들을 통합해서 한번에 사용을 합니다.
public class BuilderPatternActivity extends AppCompatActivity {
    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, BuilderPatternActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_pattern_result);

        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.title_decorator_pattern);

        TextView logTextView = (TextView) findViewById(R.id.log_text_view);

        StringBuilder sb = new StringBuilder();

        PersonInfoBuilder personInfoBuilder = new PersonInfoBuilder();
        PersonInfo result = personInfoBuilder
                .setName("김주성")
                .setAge(39)
                .setFavoriteAnimal("강아지")
                .setFavoriteColor("Black")
                .setName("홍카")
                .setFavoriteNumber(7)
                .build();

        sb.append(result.getPersonInfo());

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
