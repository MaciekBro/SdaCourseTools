package com.example.rent.sdacoursetools.FortuneApp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Color;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.rent.sdacoursetools.R;
import com.squareup.seismic.ShakeDetector;

import java.util.Random;

/**
 * Created by RENT on 2017-03-04.
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class FortuneActivity extends AppCompatActivity implements ShakeDetector.Listener {

    private FrameLayout layer;
    private TextView fortuneTextView;
    private Animator showAnimation;
    private Animator hideAnimation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fortune_main_layout);

        layer = (FrameLayout) findViewById(R.id.fortuneContainer);
        FrameLayout parentLayout = (FrameLayout) findViewById(R.id.parent_layout);

        fortuneTextView = (TextView) findViewById(R.id.fortune);


        parentLayout.setOnTouchListener(new View.OnTouchListener() {

            private Animator hideAnimationTouch;
            private Animator showAnimationTouch;
            Random randomT =  new Random();
            String[] omensT = {"Grzesiek sika na siedząco", "Bartek żartuje z poważnych tematów", "Grzesiek jeździ na wakacje do Sosnowca", "Bartek lubi spędzać czas wolny w Radomiu", "Grzesiek nie czyta książek", "Bartek je mięso w piątki"};
            int randomOmensT = randomT.nextInt(omensT.length);

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (showAnimationTouch != null && showAnimationTouch.isRunning() || hideAnimationTouch != null && hideAnimationTouch.isRunning()) {
                    return false;
                }

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (layer.getVisibility() == View.INVISIBLE) {
                        showAnimationTouch = ViewAnimationUtils
                                .createCircularReveal(layer, (int) event.getX(), (int) event.getY(), 0, layer.getHeight() + 200);
                        layer.setVisibility(View.VISIBLE);
                        fortuneTextView.setText(omensT[randomOmensT]);
                        showAnimationTouch.start();
                    } else {
                        hideAnimationTouch = ViewAnimationUtils
                                .createCircularReveal(layer, (int) event.getX(), (int) event.getY(), layer.getHeight() + 200, 0);
                        hideAnimationTouch.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                layer.setVisibility(View.INVISIBLE);
                            }
                        });
                        hideAnimationTouch.start();

                    }
                }
                return true;
            }
        });


//        parentLayout.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
////                getResources().getDisplayMetrics().heightPixels;
//
//
//                if (layer.getVisibility() == View.INVISIBLE){
//                    Animator circularReveal = ViewAnimationUtils.createCircularReveal(layer,0,0,0,layer.getHeight()*2);
//                    layer.setVisibility(View.VISIBLE);
//                    circularReveal.start();
//                } else {
//                    Animator circularReveal = ViewAnimationUtils.createCircularReveal(layer,0,0,layer.getHeight()*2,0);
//                    circularReveal.addListener(new AnimatorListenerAdapter() {
//                        @Override
//                        public void onAnimationEnd(Animator animation) {
//                            layer.setVisibility(View.INVISIBLE);
//                        }
//                    });
//                    circularReveal.start();
//
//                }
//            }
//        });


        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        ShakeDetector sd = new ShakeDetector(this);
        sd.start(sensorManager);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home: {
//                onBackPressed();
//                break;
//            }
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void hearShake() {
        Random random =  new Random();

        int x = random.nextInt(getResources().getDisplayMetrics().widthPixels);
        int y = random.nextInt(getResources().getDisplayMetrics().heightPixels);

        String[] omens = {"Grzesiek sika na siedząco", "Bartek żartuje z poważnych tematów", "Grzesiek jeździ na wakacje do Sosnowca", "Bartek lubi spędzać czas wolny w Radomiu", "Grzesiek nie czyta książek", "Bartek je mięso w piątki"};
        int randomOmens = random.nextInt(omens.length);

//        int[] colors = {R.color.colorAccent};
//        int randomColor = random.nextInt(colors.length);

        int randomColor = Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255));

        if (showAnimation != null && showAnimation.isRunning() || hideAnimation != null && hideAnimation.isRunning()) {
            return;
        }

        if (layer.getVisibility() == View.INVISIBLE) {
            showAnimation = ViewAnimationUtils
                    .createCircularReveal(layer, x, y, 0, layer.getHeight() + 200);
            layer.setVisibility(View.VISIBLE);
            layer.setBackgroundColor(randomColor);
            fortuneTextView.setText(omens[randomOmens]);

            showAnimation.start();
        } else {
            hideAnimation = ViewAnimationUtils
                    .createCircularReveal(layer, x, y, layer.getHeight() + 200, 0);

            hideAnimation.addListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationEnd(Animator animation) {
                    layer.setVisibility(View.INVISIBLE);
                }

            });
            hideAnimation.start();
        }
    }
}