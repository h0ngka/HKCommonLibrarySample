package com.hongka.hkcommonlibrarysample.designpattern.abstractfactorypattern;

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

// URL : http://jdm.kr/blog/192
// 설명 : 추상 팩토리 패턴은 많은 수의 연관된 서브 클래스를 특정 그룹으로 묶어 한번에 교체할 수 있도록 만든 디자인 패턴입니다.
public class AbstractFactoryPatternActivity extends AppCompatActivity {

    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, AbstractFactoryPatternActivity.class);
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


        MachineFactory machineFactoryA = new MachineFactoryA();
        machineFactoryA.getMachineA().process();
        machineFactoryA.getMachineB().process();

        MachineFactory machineFactoryB = new MachineFactoryB();
        machineFactoryB.getMachineA().process();
        machineFactoryB.getMachineB().process();
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
