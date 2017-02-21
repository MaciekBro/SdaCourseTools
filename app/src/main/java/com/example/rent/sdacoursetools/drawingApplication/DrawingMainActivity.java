package com.example.rent.sdacoursetools.drawingApplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
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
import android.widget.Toast;

import com.example.rent.sdacoursetools.R;
import com.example.rent.sdacoursetools.gallery.GalleryMainActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DrawingMainActivity extends AppCompatActivity {

    public static final String DRAWING_GALLERY_DIR = "drawing_gallery";
    private DrawerLayout drawerLayout;
    private SimpleDrawingView simpleDrawingView;
    private ActionBarDrawerToggle drawerToggle;
    private String timeStamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawing_activity_main);
        getSupportActionBar().setTitle("Rysowajka");


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
        getMenuInflater().inflate(R.menu.drawing_menu, menu);
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
        switch (item.getItemId()) {
            case R.id.clear: {

                simpleDrawingView.clear();
                break;
            }

            case R.id.save: {
                String timeStamp1 = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                Toast.makeText(this, "Zapisano jako: my_drawing" + timeStamp1 + ".png", Toast.LENGTH_SHORT).show();
                saveDrawingToFile();
                break;
            }

            case R.id.drawing_gallery: {
                Intent intent = new Intent(this, GalleryMainActivity.class);
                startActivity(intent);
                break;
            }

//            case android.R.id.home: {
//                onBackPressed();
//                break;
//            }
        }


        return super.onOptionsItemSelected(item);
    }

    private void saveDrawingToFile() {
        File drawingFile = new File(getDrawingGalleryDirectory(), createFileName());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(drawingFile);
            Bitmap bitmap = convertViewtoBitmap(simpleDrawingView);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);  //metoda zapisze bitmape do jakiegos pliku
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    private String createFileName() {
        timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return "my_drawing" + timeStamp + ".png";
    }

    private File getDrawingGalleryDirectory() {
        return getExternalFilesDir(DRAWING_GALLERY_DIR);
    }

    private Bitmap convertViewtoBitmap(View view) {          //konwertowanie widoku do bitmapy!!
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);      //ustawiamy biale tło
        view.draw(canvas);
        return bitmap;
    }



}
