package com.example.rent.sdacoursetools.ToReadApp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.rent.sdacoursetools.R;

/**
 * Created by RENT on 2017-03-02.
 */

public class ToReadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_read_layout);

        ViewPager viewPager = (ViewPager) findViewById(R.id.to_read_view_pager);
        int[] drawables = {R.drawable.clean_code, R.drawable.effective_java};
        viewPager.setAdapter(new ToReadAdapter(drawables));

    }
}
