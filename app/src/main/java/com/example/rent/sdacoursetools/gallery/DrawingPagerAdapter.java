package com.example.rent.sdacoursetools.gallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.rent.sdacoursetools.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by RENT on 2017-02-21.
 */

public class DrawingPagerAdapter extends PagerAdapter {


    private File[] files;

    public DrawingPagerAdapter (File[] files) {
            this.files=files;

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater layoutInflater = LayoutInflater.from(container.getContext());
        View view = layoutInflater.inflate(R.layout.single_page_item, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.drawing_image);

        try {
            FileInputStream fileInputStream = new FileInputStream(files[position]);
            Bitmap bitmap = BitmapFactory.decodeStream(fileInputStream);
            imageView.setImageBitmap(bitmap);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        container.addView(view);

        return view;
    }

        @Override
    public int getCount() {
        return files.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void deleteItem(int currentItem) {
        if (currentItem<files.length) {
            List<File> list = new ArrayList<>(Arrays.asList(files));
            list.get(currentItem).delete();
            list.remove(currentItem);

            File[] newFiles = new File[list.size()];
            list.toArray(newFiles);
            files = newFiles;
            notifyDataSetChanged();
        }

    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
