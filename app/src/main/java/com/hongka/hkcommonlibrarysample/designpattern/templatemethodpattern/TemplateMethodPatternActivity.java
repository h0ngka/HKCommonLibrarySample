package com.hongka.hkcommonlibrarysample.designpattern.templatemethodpattern;

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
 * Created by jusung.kim@sk.com on 2017/12/05
 */

// URL : http://jdm.kr/blog/116
// 설명 : 템플릿 메소드 패턴은 "알고리즘의 뼈대"를 맞추는 것에 있음. 즉, 전체적인 레이아웃을 통일 시키지만 상속받은 클래스로 하여금 어느정도 유연성을 주도록하는 디자인 패턴임.
public class TemplateMethodPatternActivity extends AppCompatActivity {
    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, TemplateMethodPatternActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_design_pattern_result);

        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.title_template_method_pattern);

        TextView logTextView = (TextView) findViewById(R.id.log_text_view);
        logTextView.setText("System에 결과 출력!");

        // 전사는 전투 준비를 위해서
        // 체력을 단련하고, 검을 닦고, 갑옷을 입습니다.
        Warrior warrior = new Warrior();
        warrior.readyToBattle();

        System.out.println("--------");

        // 궁사는 전투 준비를 위해서
        // 체력을 단련하고, 활을 손질하고, 화살을 준비합니다.
        Archer archer = new Archer();
        archer.readyToBattle();

        System.out.println("--------");

        // 마법사는 전투 준비를 위해서
        // 체력을 단련하고, 지팡이를 준비하고, 로브를 입습니다.
        Wizard wizard = new Wizard();
        wizard.readyToBattle();
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
