package com.example.rent.sdacoursetools.drawingApplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.rent.sdacoursetools.R;

public class DrawingMainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private SimpleDrawingView simpleDrawingView;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawing_activity_main);





//        Button button = (Button) findViewById(R.id.clearButton);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                simpleDrawingView.clear();
//            }
//        });

        Button blueButton = (Button) findViewById(R.id.blueButton);
        Button redButton = (Button) findViewById(R.id.redButton);

        simpleDrawingView = (SimpleDrawingView) findViewById(R.id.simpleDrawingView);

        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                simpleDrawingView.setCurrentCollor(ContextCompat.getColor(DrawingMainActivity.this, R.color.blue));
            }
        });

        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleDrawingView.setCurrentCollor(ContextCompat.getColor(DrawingMainActivity.this, R.color.red));
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

//    @Override
//    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//        drawerToggle.syncState();
//
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.clear: {
                simpleDrawingView.clear();
                break;
            }

//            case android.R.id.home: {
//                onBackPressed();
//                break;
//            }
        }
        return super.onOptionsItemSelected(item);
    }
}
