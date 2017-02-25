package com.example.rent.sdacoursetools.Quiz;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.rent.sdacoursetools.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by RENT on 2017-02-25.
 */

public class QuizActivity extends AppCompatActivity{

    private TextView answer_1_textView;
    private TextView answer_2_textView;
    private TextView answer_3_textView;
    private TextView answer_4_textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity_main);


        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        ValueAnimator valueAnimator= ObjectAnimator.ofInt(0,100);
        valueAnimator.setDuration(12000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progressBar.setProgress((Integer) animation.getAnimatedValue());
            }
        });
        valueAnimator.start();

//        ObjectAnimator.ofInt(progressBar, "progres",0,100);

        TextView questionTextView = (TextView) findViewById(R.id.question_text_view);

        answer_1_textView = (TextView) findViewById(R.id.first);
        answer_2_textView = (TextView) findViewById(R.id.second);
        answer_3_textView = (TextView) findViewById(R.id.third);
        answer_4_textView = (TextView) findViewById(R.id.fourth);

        String json = loadQuizJason();



    }

    private String loadQuizJason() {
        StringBuilder buf = new StringBuilder();
        InputStream json = null;
        BufferedReader in = null;
        String str;
        try {
            json = getAssets().open("assets/quiz_data.json");
            in = new BufferedReader(new InputStreamReader(json, "UTF-8"));

            while((str = in.readLine())!= null ) {
                buf.append(str);}
            in.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
       return buf.toString();
    }





//    public void answerClick(View view){
//        switch view.getId(): {
//            case answer_1_textView {
//                break;
//            }};
//
//    }
}
