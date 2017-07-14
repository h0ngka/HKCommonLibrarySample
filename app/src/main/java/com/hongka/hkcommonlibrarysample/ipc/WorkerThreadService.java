package com.hongka.hkcommonlibrarysample.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by jusung.kim@sk.com on 2017/07/05
 */

public class WorkerThreadService extends Service {
    private WorkerThread mWorkerThread;
    private Messenger mWorkerMessenger;

    @Override
    public void onCreate() {
        super.onCreate();
        mWorkerThread = new WorkerThread();
        mWorkerThread.start();
    }

    private void onWorkerPrepared() {
        mWorkerMessenger = new Messenger(mWorkerThread.mWorkerHandler);
        synchronized (this) {
            notifyAll();
        }
    }

    public WorkerThreadService() {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        synchronized (this) {
            while (mWorkerMessenger == null) {
                try {
                    wait();
                } catch (InterruptedException e) {

                }
            }
        }
        return mWorkerMessenger.getBinder();
    }

    private class WorkerThread extends Thread {
        private Handler mWorkerHandler;

        // 서버에서는 해당 msg 가 왔을때 replyTo로 send해줌
        @Override
        public void run() {
            Looper.prepare();
            mWorkerHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);

                    switch (msg.what) {
                        case 1 :
                            Log.d("SIM", "Message receive 1");
                            try {
                                msg.replyTo.send(Message.obtain(null, msg.what, 0, 0));
                            } catch (RemoteException e) {
                                e.getMessage();
                            }
                            break;
                        case 2 :
                            Log.d("SIM", "Message receive 2");
                            break;
                    }
                }
            };
            onWorkerPrepared();
            Looper.loop();
        }

        public void quit() {
            mWorkerHandler.getLooper().quit();
        }
    }
}
