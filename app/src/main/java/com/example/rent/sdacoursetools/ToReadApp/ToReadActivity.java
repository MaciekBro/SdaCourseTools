package com.example.rent.sdacoursetools.ToReadApp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.rent.sdacoursetools.R;

import java.util.Arrays;
import java.util.List;


/**
 * Created by RENT on 2017-03-02.
 */

public class ToReadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_read_layout);

        int[] imageResourceId = {R.drawable.clean_code, R.drawable.effective_java};

        ViewPager viewPager = (ViewPager) findViewById(R.id.to_read_view_pager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);  //do zapamietywania stanow po wyjsciu!

        Book book1 = new Book(1,R.drawable.clean_code, "Clean Code");
        book1.setRead(sharedPreferences.getBoolean(String.valueOf(book1.getId()),false));  //przy starcie pobiera zapamietany isRead!
        Book book2 = new Book(2,R.drawable.effective_java, "Effective Java");
        book2.setRead(sharedPreferences.getBoolean(String.valueOf(book2.getId()),false));
        Book book3 = new Book(3,R.drawable.design_patterns, "Head First Design Patterns");
        book3.setRead(sharedPreferences.getBoolean(String.valueOf(book3.getId()),false));

        List<Book>list =Arrays.asList(book1, book2, book3);
        ToReadAdapter toReadAdapter = new ToReadAdapter(list,sharedPreferences);

        viewPager.setAdapter(toReadAdapter);


//        viewPager = (ViewPager) findViewById(R.id.view_pager);
//        dir = getExternalFilesDir(DrawingMainActivity.DRAWING_GALLERY_DIR);
//        files = dir.listFiles();
//        pagerAdapter = new DrawingPagerAdapter(files);
//        viewPager.setAdapter(pagerAdapter);

    }
}
