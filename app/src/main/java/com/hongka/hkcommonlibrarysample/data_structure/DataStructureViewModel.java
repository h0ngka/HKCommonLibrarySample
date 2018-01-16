package com.hongka.hkcommonlibrarysample.data_structure;

import android.content.Context;
import android.databinding.BaseObservable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jusung.kim@sk.com on 2017/12/18
 */

public class DataStructureViewModel extends BaseObservable {
    private Context mContext;

    public DataStructureViewModel(Context context) {
        mContext = context;
    }

    public void onClick(View view) {
        String tag = (String) view.getTag();

        if (tag.contains("HashMap")) {
            executeHashMap();
        }
    }

    // https://m.blog.naver.com/gi_balja/221163462106
    private void executeHashMap() {
        HashMap<String, String> studunt = new HashMap<>();
        studunt.put("First Name", "주성");
        studunt.put("Last Name", "김");
        studunt.put("Skills", "Android, Java, ASP, MS-SQL");
        studunt.put("Contury", "Korea");
        studunt.put("City", "Seoul");


//        if (studunt.containsKey("Skills")) {
//            Toast.makeText(mContext, "HashMap contains key Skills", Toast.LENGTH_SHORT).show();
//        }


        // 모든 value를 보고 싶을때
//        Toast.makeText(mContext,studunt.values().toString() ,Toast.LENGTH_SHORT).show();

        StringBuilder sb = new StringBuilder();
        for (String key : studunt.keySet()) {
            sb.append(key + " : " + studunt.get(key) + "\n");
        }
        Toast.makeText(mContext,sb.toString() ,Toast.LENGTH_SHORT).show();


        List list = new ArrayList();
        list.add(studunt);

        for (int i=0; i<list.size(); i++) {
            Object data = list.get(i);
            if (data instanceof HashMap) {
                for (String key : studunt.keySet()) {
                    Log.d("DataStructure",key + " : " + studunt.get(key) + "\n");
                }
            }
        }
    }
}
