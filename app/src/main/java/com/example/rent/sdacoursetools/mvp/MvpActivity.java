package com.example.rent.sdacoursetools.mvp;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rent.sdacoursetools.R;

import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusAppCompatActivity;

@RequiresPresenter(MvpPresenter.class)
public class MvpActivity extends NucleusAppCompatActivity<MvpPresenter> {

    private static final String RESULT_KEY = "result key";
    private TextView resultTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);

        resultTextView = (TextView) findViewById(R.id.result_text_view);
        Button startTaskButton = (Button) findViewById(R.id.start_task_button);
        startTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().executeLongRunningTask();
            }
        });
    }

    public void setTextOnUiThread (final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                resultTextView.setText(text);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(RESULT_KEY,resultTextView.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        resultTextView.setText(savedInstanceState.getString(RESULT_KEY));
    }

}
