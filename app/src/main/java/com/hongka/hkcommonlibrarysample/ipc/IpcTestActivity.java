package com.hongka.hkcommonlibrarysample.ipc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hongka.hkcommonlibrarysample.R;
import com.hongka.hkcommonlibrarysample.databinding.ActivityIpcTestBinding;

/**
 * Created by jusung.kim@sk.com on 2017/07/05
 */

public class IpcTestActivity extends AppCompatActivity {
    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, IpcTestActivity.class);
        return intent;
    }

    private boolean mBound = false;
    private Messenger mRemoteService = null;

    private ServiceConnection mRemoteConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mRemoteService = new Messenger(iBinder);
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mRemoteService = null;
            mBound = false;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityIpcTestBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_ipc_test);
        binding.setPresenter(new Presenter());
    }

    public class Presenter {
        public void onBindClick(View view) {
            Intent intent = new Intent(IpcTestActivity.this, WorkerThreadService.class);
            bindService(intent, mRemoteConnection, Context.BIND_AUTO_CREATE);

            Toast.makeText(view.getContext(), "바인드", Toast.LENGTH_SHORT).show();
        }

        public void onUnBindClick(View view) {
            if (mBound) {
                unbindService(mRemoteConnection);
                mBound = false;
            }
            Toast.makeText(view.getContext(), "언바인드", Toast.LENGTH_SHORT).show();
        }

        public void onSendClick(View view) {
            if (mBound) {
                try {
                    mRemoteService.send(Message.obtain(null, 2, 0, 0));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            Toast.makeText(view.getContext(), "전송", Toast.LENGTH_SHORT).show();
        }

        public void onSendClickTwoWay(View view) {
            if (mBound) {
                try {
                    Message msg = Message.obtain(null, 1, 0, 0);
                    msg.replyTo = new Messenger(new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            Log.d("SIM", "Message sent back - msg.what = " + msg.what);
                        }
                    });
                    mRemoteService.send(msg);
                } catch (RemoteException e) {
                    Log.e("SIM", e.getMessage());
                }
            }
            Toast.makeText(view.getContext(), "양방향 전송", Toast.LENGTH_SHORT).show();
        }
    }
}
