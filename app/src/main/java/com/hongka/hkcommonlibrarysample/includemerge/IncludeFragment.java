package com.hongka.hkcommonlibrarysample.includemerge;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hongka.hkcommonlibrarysample.R;
import com.hongka.hkcommonlibrarysample.databinding.FragmentIncludeBinding;

/**
 * Created by jusung.kim@sk.com on 2017/10/24
 */

public class IncludeFragment extends Fragment {
    public static IncludeFragment newInstance() {
        return new IncludeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentIncludeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_include, container, false);
        View view = binding.getRoot();

        View include01 = view.findViewById(R.id.include_layout);
        View include02 = view.findViewById(R.id.include_layout2);
        View include03 = view.findViewById(R.id.include_layout3);

        TextView tv1 = (TextView) include01.findViewById(R.id.txt_startTime);
        TextView tv2 = (TextView) include01.findViewById(R.id.txt_endTime);
        TextView tv3 = (TextView) include02.findViewById(R.id.txt_startTime);
        TextView tv4 = (TextView) include02.findViewById(R.id.txt_endTime);
        TextView tv5 = (TextView) include03.findViewById(R.id.txt_startTime);
        TextView tv6 = (TextView) include03.findViewById(R.id.txt_endTime);

        return view;
    }
}
