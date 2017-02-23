package com.example.rent.sdacoursetools.KolkoIKrzyzyk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rent.sdacoursetools.R;

/**
 * Created by RENT on 2017-02-22.
 */

public class KolkoIKrzyzykActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kolokrzyzyk_activity_main);
    }

    int activePlayer=0;
    int[] gameStatus = {2,2,2,2,2,2,2,2,2};
    int[][] winStatus = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean isActive = true;

    public void iconShow(View view) {

        ImageView imageView = (ImageView) view;

        int gameMove = Integer.parseInt(imageView.getTag().toString());

        if (isActive){
        if (gameStatus[gameMove] == 2) {

            gameStatus[gameMove] = activePlayer;

            if (activePlayer == 0) {
                imageView.setTranslationY(-1000f);
                imageView.setImageResource(R.drawable.krzyzyk_icon);
                activePlayer = 1;

            } else {
                imageView.setTranslationY(-1000f);
                imageView.setImageResource(R.drawable.kolko_icon);
                activePlayer = 0;
                System.out.println(gameStatus);
            }

            imageView.animate().translationYBy(1000f).setDuration(1000);
        }}

        TextView winningText = (TextView) findViewById(R.id.winning_textview);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.relative_layout);
        for (int[] win: winStatus) {
            if (gameStatus[win[0]]==gameStatus[win[1]] && gameStatus[win[1]]==gameStatus[win[2]] && gameStatus[win[0]] != 2){
                String winner = "Player " + gameStatus[win[0]] + " won!";

                layout.setVisibility(View.VISIBLE);
                Toast.makeText(this, winner, Toast.LENGTH_SHORT).show();



                String activePlayer;

                if (gameStatus[win[0]]==1) {
                    activePlayer = "Zielony";
                } else {
                    activePlayer = "Czerwony";
                }
                winningText.setText(activePlayer+" wygrał mecz!");
                isActive=false;
            } else {
                boolean isDraw=true;
                for (int gamsestate: gameStatus){
                    if (gamsestate==2){
                        isDraw=false;
                    }
                }
                if (isDraw==true) {
                    layout.setVisibility(View.VISIBLE);
                    winningText.setText("Remis, zagrajcie jeszcze raz!");
                }
            }
        }

    }

    public void startAgain (View view) {

        for (int i=0; i<gameStatus.length;i++){
             gameStatus[i]=2;

        }

        GridLayout gridLayout = (GridLayout) findViewById(R.id.grid_layout);
        for ( int i = 0; i< gridLayout.getChildCount();i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

        isActive=true;
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.relative_layout);
        layout.setVisibility(View.GONE);
    }



}
