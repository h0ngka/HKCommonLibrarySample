package com.hongka.hkcommonlibrarysample;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hongka.hkcommonlibrarysample.databinding.ActivityMainBinding;
import com.hongka.hkcommonlibrarysample.databinding.RecyclerViewMainItemBinding;
import com.hongka.hkcommonlibrarysample.model.MainItem;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();

    private MainViewModel mViewModel;

    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mViewModel = new MainViewModel(this);
        mViewModel.loadData();
        binding.recyclerView.setAdapter(new RecyclerViewAdapter(mViewModel));
    }

    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.BindingHolder> {
        private MainViewModel mViewModel;

        public RecyclerViewAdapter(MainViewModel viewModel) {
            mViewModel = viewModel;
        }

        @Override
        public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_main_item, parent, false);
            return new BindingHolder(view);
        }

        @Override
        public void onBindViewHolder(BindingHolder holder, int position) {
            MainItem mainItem = mViewModel.mMainItems.get(position);
            holder.mBinding.setMainItem(mainItem);
            holder.mBinding.setViewModel(mViewModel);
            holder.mBinding.executePendingBindings();
        }

        @Override
        public int getItemCount() {
            if (mViewModel.mMainItems != null) {
                return mViewModel.mMainItems.size();
            }
            return 0;
        }

        public class BindingHolder extends RecyclerView.ViewHolder {
            RecyclerViewMainItemBinding mBinding;

            public BindingHolder(View itemView) {
                super(itemView);
                mBinding = DataBindingUtil.bind(itemView);
            }
        }
    }
}
