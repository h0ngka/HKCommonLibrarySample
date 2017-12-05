package com.hongka.hkcommonlibrarysample.designpattern.factorymethodpattern;

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

// URL : http://jdm.kr/blog/180
// 설명 : 팩토리 메소드 패턴을 사용하는 경우 직접 객체를 생성해 사용하는 것을 방지하고 서브 클래스에 위임함으로써 보다 효율적인 코드 제어를 할 수 있고 의존성을 제거함.
// 결과적으로 결합도 또한 낮출 수 있음.
public class FactoryMethodPatternActivity extends AppCompatActivity {

    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, FactoryMethodPatternActivity.class);
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

        StringBuilder sb = new StringBuilder();


        RobotFactory rf = new SuperRobotFactory();
        Robot r = rf.createRobot("super");
        Robot r2 = rf.createRobot("power");

        sb.append(r.getName() + "\n");
        sb.append(r2.getName() + "\n");

        RobotFactory mrf = new ModifiedSuperRobotFactory();
        Robot r3 = mrf.createRobot("com.hongka.hkcommonlibrarysample.designpattern.factorymethodpattern.SuperRobot");
        Robot r4 = mrf.createRobot("com.hongka.hkcommonlibrarysample.designpattern.factorymethodpattern.PowerRobot");

        sb.append(r3.getName() + "\n");
        sb.append(r4.getName() + "\n");

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
