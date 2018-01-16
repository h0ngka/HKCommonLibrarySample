package com.hongka.hkcommonlibrarysample.design_pattern.strategypattern;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.hongka.hkcommonlibrarysample.R;
import com.hongka.hkcommonlibrarysample.design_pattern.strategypattern.programmer.AdvancedWebProgrammer;
import com.hongka.hkcommonlibrarysample.design_pattern.strategypattern.programmer.AmateurProgrammer;
import com.hongka.hkcommonlibrarysample.design_pattern.strategypattern.programmer.Programmer;
import com.hongka.hkcommonlibrarysample.design_pattern.strategypattern.programmer.WebProgrammer;
import com.hongka.hkcommonlibrarysample.design_pattern.strategypattern.skill.AdvancedWebServerSide;
import com.hongka.hkcommonlibrarysample.design_pattern.strategypattern.skill.WebClientSide;

/**
 * Created by jusung.kim@sk.com on 2017/12/04
 */

// URL : http://jdm.kr/blog/54
// 설명 : 알고리즘 캡슐화, 상속보다는 구성(Composition)을 이용
public class StrategyPatternActivity extends AppCompatActivity {
    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, StrategyPatternActivity.class);
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

        Programmer wp = new WebProgrammer(); // 일반 웹 프로그래머
        Programmer awp = new AdvancedWebProgrammer(); // 고급 웹 프로그래머
        Programmer ap = new AmateurProgrammer(); // 아마추어 웹 프로그래머

        Programmer p = new Programmer() {
            @Override
            public String getAllSkill() {
                String display = getClientProgramming() + ", " + getServerProgramming();
                return display;
            }
        };

        // 다이나믹하게 전략을 수립해보자.
        // 다이나믹 프로그래머는 일반 웹 클라이언트 사이드/고급 웹 서버 사이드를 가진다.
        p.setClientSide(new WebClientSide());
        p.setServerSide(new AdvancedWebServerSide());

        System.out.println("[WebProgrammer]\n" + wp.getAllSkill());
        System.out.println("[AdvancedWebProgrammer]\n" + awp.getAllSkill());
        System.out.println("[AmateurProgrammer]\n" + ap.getAllSkill());
        System.out.println("[DynamicProgrammer]\n" + p.getAllSkill());

        sb.append("[WebProgrammer]\n" + wp.getAllSkill() + "\n\n");
        sb.append("[AdvancedWebProgrammer]\n" + awp.getAllSkill() + "\n\n");
        sb.append("[AmateurProgrammer]\n" + ap.getAllSkill() + "\n\n");
        sb.append("[DynamicProgrammer]\n" + p.getAllSkill());

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
