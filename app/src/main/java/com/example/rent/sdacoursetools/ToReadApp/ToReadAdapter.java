package com.example.rent.sdacoursetools.ToReadApp;

import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.rent.sdacoursetools.R;

/**
 * Created by RENT on 2017-03-02.
 */

public class ToReadAdapter extends PagerAdapter {


    private int[] drawable;

        public ToReadAdapter (int[] drawables) {
        this.drawable=drawables;
    }



    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        return super.instantiateItem(container, position);

        LayoutInflater layoutInflater = LayoutInflater.from(container.getContext());
        View view = layoutInflater.inflate(R.layout.single_doread_layout, container, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.single_doread_image);

        return view;
    }

    @Override
    public int getCount() {
        return drawable.length;
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
