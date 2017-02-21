package com.example.rent.sdacoursetools;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rent.sdacoursetools.drawingApplication.DrawingMainActivity;
import com.example.rent.sdacoursetools.reflexGame.ReflexMainActivity;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;  //hamburger zamiast strzaleczki!

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //strzaleczka wracajaca do gory

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);

        TextView drawingTextView = (TextView) findViewById(R.id.drawing_app);
        drawingTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DrawingMainActivity.class);
                startActivity(intent);
            }
        });

        TextView reflexTextView = (TextView) findViewById(R.id.reflex_game);
        reflexTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(v.getContext(), ReflexMainActivity.class);
                startActivity(intent2);
            }
        });

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {   //wszystkie guziczki w gornym menu
        switch (item.getItemId()) {
            case android.R.id.home: {
                if (drawerLayout.isDrawerOpen(Gravity.START)) {
                    drawerLayout.closeDrawer(Gravity.START);
                } else {
                    drawerLayout.openDrawer(Gravity.START);
                }


//                Toast.makeText(DrawingMainActivity.this, "Wygrałeś milion złoty!", Toast.LENGTH_SHORT).show();
//                onBackPressed();   //dziala dokladnie jak defoultowa strzałka wstecz
                break;
            }

        }
        return super.onOptionsItemSelected(item);
    }
}
