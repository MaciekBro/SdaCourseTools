package com.example.rent.sdacoursetools.ToReadApp;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.example.rent.sdacoursetools.R;

import java.util.List;

/**
 * Created by RENT on 2017-03-02.
 */

public class ToReadAdapter extends PagerAdapter {


    private List<Book> books;

    private SharedPreferences sharedPreferences;  //zapisuja dane do pliku xml, przez co beda one dostepne po zresetowaniu aplikacji

    public ToReadAdapter(List<Book> books, SharedPreferences sharedPreferences) {
        this.books = books;
        this.sharedPreferences = sharedPreferences;
    }


    @Override
    public CharSequence getPageTitle(int position) {            //!!!!!!!!!!!!!!!!!!!!! zeby dodac tytul do taba
        return books.get(position).getTitle();
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
//        return super.instantiateItem(container, position);

        LayoutInflater layoutInflater = LayoutInflater.from(container.getContext());
        View pageLayout = layoutInflater.inflate(R.layout.single_doread_layout, container, false);

        ImageView imageView = (ImageView) pageLayout.findViewById(R.id.single_doread_image);
        imageView.setImageResource(books.get(position).getImageResourceId());

        CheckBox checkBox = (CheckBox) pageLayout.findViewById(R.id.checkbox);
        checkBox.setOnCheckedChangeListener(null); //zeby nie wyslal blednego powiadomienia przy odswiezeniu
        checkBox.setChecked(books.get(position).isRead());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                books.get(position).setRead(isChecked);//nie odznacza sie jak dalej przewiniemy

                sharedPreferences.edit().putBoolean(String.valueOf(books.get(position).getId()),books.get(position).isRead()).apply(); //zapamietuje zazanczenie po wyjsciu z programu
            }
        });

        container.addView(pageLayout);
        return pageLayout;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);

    }
}
