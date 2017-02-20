package com.example.rent.sdacoursetools.reflexGame;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.TextView;


/**
 * Created by RENT on 2017-02-18.
 */

public class IntroAnimator {

    private TextView textView;

    public IntroAnimator(TextView textView) {
        this.textView = textView;
    }

    public void showIntro(Runnable animationEnds) {
        animateCounter(textView, animationEnds,3) ;
        }

    private void animateCounter(final TextView text3, final Runnable animationEnds, final int counter) {
        String text = counter == 0 ? "Start" : String.valueOf(counter);

        text3.setText(text);
        text3.setAlpha(1);
        text3.setScaleX(1);
        text3.setScaleY(1);
        text3.animate().alpha(0).scaleX(3).scaleY(3).setDuration(1000).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (counter > 0) {
                    animateCounter(text3, animationEnds, counter - 1);
                } else {
                    animationEnds.run();
                }
            }
        }).start();

    }
}


