package com.example.rent.sdacoursetools;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rent.sdacoursetools.KolkoIKrzyzyk.KolkoIKrzyzykActivity;
import com.example.rent.sdacoursetools.Quiz.QuizActivity;
import com.example.rent.sdacoursetools.TODOActivity.TODOActivity;
import com.example.rent.sdacoursetools.drawingApplication.DrawingMainActivity;
import com.example.rent.sdacoursetools.reflexGame.ReflexMainActivity;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    public static final String NOTE_KEY = "note_key";
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;  //hamburger zamiast strzaleczki!
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        final EditText notesEditText = (EditText) findViewById(R.id.my_note_edittext);
        notesEditText.setText(readText());

        Button saveButton = (Button) findViewById(R.id.save_note);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveText(notesEditText.getText().toString());
                Toast.makeText(MainActivity.this, "Zapisano notatke", Toast.LENGTH_SHORT).show();
            }
        });


        TextView todoListTextView = (TextView) findViewById(R.id.todo_list);
        todoListTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TODOActivity.class);
                startActivity(intent);
            }
        });

        TextView kolkokrzyzykTextView = (TextView) findViewById(R.id.kolkokrzyzyk_textview);
        kolkokrzyzykTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, KolkoIKrzyzykActivity.class);
                startActivity(intent);
            }
        });

        TextView milionTextView = (TextView) findViewById(R.id.milion_textView);
        milionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(intent);

            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private String readText() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        return sharedPreferences.getString(NOTE_KEY, "");
    }

    private void saveText(String text) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.edit().putString(NOTE_KEY, text).apply();
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

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
