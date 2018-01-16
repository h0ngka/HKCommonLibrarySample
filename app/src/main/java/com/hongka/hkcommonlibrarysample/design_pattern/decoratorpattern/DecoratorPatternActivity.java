package com.hongka.hkcommonlibrarysample.design_pattern.decoratorpattern;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.hongka.hkcommonlibrarysample.R;

/**
 * Created by jusung.kim@sk.com on 2017/11/10
 */

// URL : http://jdm.kr/blog/78
// 설명 : 유연하게 기능 확장. 대신 각각 데코레이터 클래스들이 어떤 기능을 수행하는지 알고 있어야 하고 자잘한 클래스들이 많이 생기는 것이 단점.
public class DecoratorPatternActivity extends AppCompatActivity {
    private final String TAG = DecoratorPatternActivity.class.getSimpleName();
    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, DecoratorPatternActivity.class);
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

        // 그냥 용감한 쿠키 만들기
        Cookie braveCookie = new BraveCookie();

        // 우유를 얹은 용감한 쿠키 만들기
        Cookie milkBraveCookie = new MilkTopping(braveCookie);

        // 그위에 초콜릿을 얹은 용감한 쿠키 만들기
        Cookie chocolateMilkBraveCookie = new ChocolateTopping(milkBraveCookie);

        // 그위에 우유를 한번 더 넣은 용감한 쿠키 만들기
        Cookie chocolateDoubleMilkBraveCookie = new MilkTopping(chocolateMilkBraveCookie);

        sb.append(chocolateDoubleMilkBraveCookie.getName()).append("\n\n");
        Log.e(TAG, chocolateDoubleMilkBraveCookie.getName());

        // 소다 쿠키 만들기
        Cookie SodaCookie = new Cookie() {
            @Override
            public String getName() {
                return "소다 쿠키";
            }
        };

        // 초콜릿을 두번 넣은 소다 쿠키 만들기
        SodaCookie = new ChocolateTopping(new ChocolateTopping(SodaCookie));

        sb.append(SodaCookie.getName());
        Log.e(TAG, SodaCookie.getName());

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
