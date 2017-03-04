package com.example.rent.sdacoursetools.mvp;

import android.os.Handler;

import nucleus.presenter.Presenter;

/**
 * Created by RENT on 2017-03-04.
 */
public class MvpPresenter extends Presenter<MvpActivity>{

    private LongRunningTask longRunningTask = new LongRunningTask();
    private Handler handler;

    public void executeLongRunningTask(){
        new Thread() {
            @Override
            public void run() {
                final String result = longRunningTask.execute();
                getView().setTextOnUiThread(result);

            }
        }.start();
    }
}
