package com.example.rent.sdacoursetools.TODOActivity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rent.sdacoursetools.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RENT on 2017-02-22.
 */

public class TODOListAdapter extends RecyclerView.Adapter<TODOListAdapter.MyViewHolder>{

    List<String> items = new ArrayList<>();



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem (String item){
        items.add(item);
        notifyDataSetChanged();         // komunikacja adapter-widok
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_text);

        }
    }

}
