package com.example.rent.sdacoursetools.Quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rent.sdacoursetools.R;

import org.w3c.dom.Text;

public class QuizSummary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_summary);

        TextView resultOk = (TextView) findViewById(R.id.result_ok);
        TextView resultNotOk = (TextView) findViewById(R.id.result_not_ok);
        resultOk.setText("Poprawnych odpowiedzi: " + getIntent().getIntExtra(QuizActivity.CORRECT_ANSWERS, 0));
        resultNotOk.setText("Blednych odpowiedzi: " + getIntent().getIntExtra(QuizActivity.INCORRECT_ANSWERS, 0));

        Button button = (Button) findViewById(R.id.try_again_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizSummary.this,QuizActivity.class);
                startActivity(intent);
            }
        });
    }
}
