package com.example.rent.sdacoursetools.gallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.rent.sdacoursetools.R;
import com.example.rent.sdacoursetools.drawingApplication.DrawingMainActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by RENT on 2017-02-21.
 */

public class GalleryMainActivity extends AppCompatActivity {

    private File[] files;
    private File dir;
    private DrawingPagerAdapter pagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_activity_main);


        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dir = getExternalFilesDir(DrawingMainActivity.DRAWING_GALLERY_DIR);
        files = dir.listFiles();
        pagerAdapter = new DrawingPagerAdapter(files);
        viewPager.setAdapter(pagerAdapter);

        getSupportActionBar().setTitle("Liczba plików galerii: " + files.length);

//        getSupportActionBar().setTitle("Gallery"); //w manifescie to ustalimy



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.gallery_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.remove_image) {
            pagerAdapter.deleteItem(viewPager.getCurrentItem());
        }

        return super.onOptionsItemSelected(item);
    }
}
