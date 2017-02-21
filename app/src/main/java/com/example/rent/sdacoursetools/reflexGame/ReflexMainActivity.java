package com.example.rent.sdacoursetools.reflexGame;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rent.sdacoursetools.R;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ReflexMainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Integer> images = Arrays.asList(R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four, R.drawable.five);
    private Random random = new Random();
    private ImageView imageViewLeft;
    private ImageView imageViewRight;
    private Button firstPlayerButton;
    private Button secondPlayerButton;
    private Button startButton;
    private CountDownTimer countDownTimer;
    private boolean isRunning = false;
    private Drawable defaultBackground1;
    private Drawable defaultBackground2;
    private IntroAnimator introAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reflex_activity_main);

        imageViewLeft = (ImageView) findViewById(R.id.image_view_left);
        imageViewRight = (ImageView) findViewById(R.id.image_view_right);

        startButton = (Button) findViewById(R.id.startButton);
        final TextView text3 = (TextView) findViewById(R.id.text3);
        introAnimator = new IntroAnimator(text3);

//        Button exitButton = (Button) findViewById(R.id.exitButton);

        firstPlayerButton = (Button) findViewById(R.id.buttonUser1);
        secondPlayerButton = (Button) findViewById(R.id.buttonUser2);
        firstPlayerButton.setOnClickListener(ReflexMainActivity.this);
        secondPlayerButton.setOnClickListener(this);

        defaultBackground1 = firstPlayerButton.getBackground();
        defaultBackground2 = secondPlayerButton.getBackground();

        countDownTimer = new CountDownTimer(20000, 2000) {
            @Override
            public void onTick(long millisUntilFinished) {

                int firstImage = images.get(random.nextInt(images.size()));
                int secondImage = images.get(random.nextInt(images.size()));

                imageViewLeft.setImageResource(firstImage);
                imageViewRight.setImageResource(secondImage);

                imageViewLeft.setTag(firstImage);
                imageViewRight.setTag(secondImage);

            }

            @Override
            public void onFinish() {
                startButton.setVisibility(View.VISIBLE);
                isRunning = false;
            }
        };

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                introAnimator.showIntro(new Runnable() {
                    @Override
                    public void run() {

                        isRunning = true;
                        imageViewLeft.setVisibility(View.VISIBLE);
                        imageViewRight.setVisibility(View.VISIBLE);
                        firstPlayerButton.setBackground(defaultBackground1);
                        secondPlayerButton.setBackground(defaultBackground2);
                        countDownTimer.start();
                    }
                });

                startButton.setVisibility(View.GONE);

            }
        });

    }


    @Override
    public void onClick(View view) {
        if (!isRunning) {
            return;
        }
        if (imageViewLeft.getTag().equals(imageViewRight.getTag())) {
            if (view.getId() == firstPlayerButton.getId()) {
                Toast.makeText(this, "Player ONE WINS!!", Toast.LENGTH_SHORT).show();
                firstPlayerButton.setBackgroundColor(Color.GREEN);
                secondPlayerButton.setBackgroundColor(Color.RED);
            } else {
                Toast.makeText(this, "Player TWO WINS!!", Toast.LENGTH_SHORT).show();
                secondPlayerButton.setBackgroundColor(Color.GREEN);
                firstPlayerButton.setBackgroundColor(Color.RED);
            }
        } else {
            if (view.getId() == firstPlayerButton.getId()) {
                Toast.makeText(this, "Player TWO WINS!!", Toast.LENGTH_SHORT).show();
                secondPlayerButton.setBackgroundColor(Color.GREEN);
                firstPlayerButton.setBackgroundColor(Color.RED);
            } else {
                Toast.makeText(this, "Player ONE WINS!!", Toast.LENGTH_SHORT).show();
                firstPlayerButton.setBackgroundColor(Color.GREEN);
                secondPlayerButton.setBackgroundColor(Color.RED);
            }
        }
        imageViewLeft.setVisibility(View.GONE);
        imageViewRight.setVisibility(View.GONE);
        startButton.setVisibility(View.VISIBLE);
        countDownTimer.cancel();
        isRunning = false;


    }


//    public void exitActivity(View view) {
//        finish();
//        System.exit(0);
//    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }}





