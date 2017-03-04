package com.example.rent.sdacoursetools.Quiz;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rent.sdacoursetools.MainActivity;
import com.example.rent.sdacoursetools.R;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by RENT on 2017-02-25.
 */

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView answer_1_textView;
    private TextView answer_2_textView;
    private TextView answer_3_textView;
    private TextView answer_4_textView;
    private SingleAnswer firstAnswer;
    private SingleAnswer secondAnswer;
    private SingleAnswer thirdAnswer;
    private SingleAnswer fourthAnswer;
    private QuizQuestion quizQuestion;
    private boolean isAnimationCancel; //bedziemy recznie anulowac animacje flaga

    private static final String INDEX_KEY = "index_key";
    private int currentQuestionIndex;
    private QuizContainer quizContainer;
    private ValueAnimator objectAnimator;

    public static final String CORRECT_ANSWERS = "correct answers";
    public static final String INCORRECT_ANSWERS = "incorrect answers";
    private int correctAnswers;
    private int incorrectanswers;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity_main);

        currentQuestionIndex = getIntent().getIntExtra(INDEX_KEY, 0); //cos co nam wystartuje intent to zwroci nam wartosc 0


        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        objectAnimator = ObjectAnimator.ofInt(0, 100);
        objectAnimator.setDuration(12000);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progressBar.setProgress((Integer) animation.getAnimatedValue());
            }
        });
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                if (!isAnimationCancel) {
//                    Toast.makeText(QuizActivity.this, "KOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOONIEC", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(QuizActivity.this, QuizActivity.class);
//                    intent.putExtra(INDEX_KEY, ++currentQuestionIndex);
//                    startActivity(intent);
                    Intent intent = new Intent(QuizActivity.this, QuizSummary.class);
                    startActivity(intent);
                }


            }
        });
        objectAnimator.start();

//        ObjectAnimator.ofInt(progressBar, "progres",0,100);

        TextView questionTextView = (TextView) findViewById(R.id.question_text_view);

        answer_1_textView = (TextView) findViewById(R.id.first);
        answer_2_textView = (TextView) findViewById(R.id.second);
        answer_3_textView = (TextView) findViewById(R.id.third);
        answer_4_textView = (TextView) findViewById(R.id.fourth);

        String json = loadQuizJason();
        quizContainer = new Gson().fromJson(json, QuizContainer.class);

        quizQuestion = quizContainer.getQuestions().get(currentQuestionIndex);

        questionTextView.setText(quizQuestion.getQuestion());

        firstAnswer = quizQuestion.getAnswers().get(0);
        secondAnswer = quizQuestion.getAnswers().get(1);
        thirdAnswer = quizQuestion.getAnswers().get(2);
        fourthAnswer = quizQuestion.getAnswers().get(3);

        answer_1_textView.setText(firstAnswer.getText());
        answer_2_textView.setText(secondAnswer.getText());
        answer_3_textView.setText(thirdAnswer.getText());
        answer_4_textView.setText(fourthAnswer.getText());

        answer_1_textView.setTag(firstAnswer.isCorrect());
        answer_2_textView.setTag(secondAnswer.isCorrect());
        answer_3_textView.setTag(thirdAnswer.isCorrect());
        answer_4_textView.setTag(fourthAnswer.isCorrect());

        answer_1_textView.setOnClickListener(this);
        answer_2_textView.setOnClickListener(this);
        answer_3_textView.setOnClickListener(this);
        answer_4_textView.setOnClickListener(this);

        playMusic("https://static.mezgrman.de/downloads/wwm/wechsel_nach_stufe_3.mp3"); //na początku pytania

        correctAnswers = getIntent().getIntExtra(CORRECT_ANSWERS,0);
        incorrectanswers = getIntent().getIntExtra(INCORRECT_ANSWERS,0);


    }

    String url;

    private void playMusic(String url) {
        this.url = url;

        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    boolean wasViewedClicked = false;

    @Override
    public void onClick(View v) {
        if (!wasViewedClicked) {
            if ((Boolean) v.getTag()) {
                Toast.makeText(this, "Odpowiedź poprawna!", Toast.LENGTH_SHORT).show();
                playMusic("https://static.mezgrman.de/downloads/wwm/richtig_stufe_1.mp3"); //correct answer
                ++correctAnswers;
            } else {
                Toast.makeText(this, "Niestety....zjebałeś", Toast.LENGTH_SHORT).show();
                playMusic("https://static.mezgrman.de/downloads/wwm/falsch.mp3"); //wrong answer
                ++incorrectanswers;
            }

            v.postDelayed(new Runnable() {

                @Override
                public void run() {
                    if (currentQuestionIndex < quizContainer.getQuestionsCount() - 1) {
                        Intent intent = new Intent(QuizActivity.this, QuizActivity.class);
                        intent.putExtra(INDEX_KEY, ++currentQuestionIndex);
                        intent.putExtra(CORRECT_ANSWERS, correctAnswers);
                        intent.putExtra(INCORRECT_ANSWERS,incorrectanswers);
                        startActivity(intent);
                    } else {
//                        Intent intent = new Intent(QuizActivity.this, QuizActivity.class);
//                        intent.putExtra(INDEX_KEY, 0);
//                        startActivity(intent);
                        Intent intent = new Intent(QuizActivity.this, QuizSummary.class);
                        intent.putExtra(CORRECT_ANSWERS, correctAnswers);
                        intent.putExtra(INCORRECT_ANSWERS,incorrectanswers);
                        startActivity(intent);
//                        Toast.makeText(QuizActivity.this, "Quiz ends", Toast.LENGTH_SHORT).show();


                    }
                    isAnimationCancel = true;

                }

            }, 3000);
            objectAnimator.removeAllUpdateListeners(); //aby pasek nie szedl dalej
            wasViewedClicked = true;
        }


//        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.milionerzy_wrong_answer);
//        mediaPlayer.start();


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent (QuizActivity.this, MainActivity.class);
        
        startActivity(intent);
    }

    private String loadQuizJason() {
        StringBuilder buf = new StringBuilder();
        InputStream json = null;
        BufferedReader in = null;
        String str;
        try {
            json = getAssets().open("quiz_data.json");
            in = new BufferedReader(new InputStreamReader(json, "UTF-8"));

            while ((str = in.readLine()) != null) {
                buf.append(str);
            }
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
