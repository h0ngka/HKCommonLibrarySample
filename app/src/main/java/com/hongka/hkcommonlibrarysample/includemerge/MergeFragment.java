package com.hongka.hkcommonlibrarysample.includemerge;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hongka.hkcommonlibrarysample.R;
import com.hongka.hkcommonlibrarysample.databinding.FragmentMergeBinding;

/**
 * Created by jusung.kim@sk.com on 2017/10/24
 */

public class MergeFragment extends Fragment {
    public static MergeFragment newInstance() {
        return new MergeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentMergeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_merge, container, false);
        View view = binding.getRoot();

        View include01 = view.findViewById(R.id.include_layout);
        View include02 = view.findViewById(R.id.include_layout2);
        View include03 = view.findViewById(R.id.include_layout3);

//        TextView tv1 = (TextView) include01.findViewById(R.id.txt_startTime);
//        TextView tv2 = (TextView) include02.findViewById(R.id.txt_startTime);
//        TextView tv3 = (TextView) include03.findViewById(R.id.txt_startTime);

        /*
            http://twinbraid.blogspot.kr/2015/02/merge-include.html

            include tag는 반복되는 layout을 사용할때
            merge tag는 각각의 xml에서 1개씩만 합칠때 사용함.
               - merge tag는 합쳐질때 해당 tag가 사라지면서 include tag의 id도 함께 사라짐.
            include tag의 id를 뽑아도 NULL 임.
            그래서 include하는 각각의 layout의 id값이 해당 xml에서 유일한 값이어야 함.
        */

        return view;
    }
}
