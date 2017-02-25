package com.example.rent.sdacoursetools.TODOActivity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.rent.sdacoursetools.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RENT on 2017-02-22.
 */

public class TODOListAdapter extends RecyclerView.Adapter<TODOListAdapter.MyViewHolder> {

    private List<TODOListItem> items = new ArrayList<>();   //ten typ danych musi byc parcelizowany zeby podczas obrotu go nie tracic
    private OnItemCheckStateChanged checkedListener;

    public List<TODOListItem> getItems() {      //do parcealizacji
        return items;
    }

    public void setItems(List<TODOListItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void setCheckedListener(OnItemCheckStateChanged checkedListener) {
        this.checkedListener = checkedListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final TODOListItem listItem = items.get(position);
        holder.checkBox.setChecked(listItem.isChecked());         //bo recycler uzywa kilka razy widokow zeby zaoszczedzic...
        holder.checkBox.setOnCheckedChangeListener(null);       //zeby recyclerView nie kasowalo zaznaczen
        holder.textView.setText(listItem.getText());

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                listItem.setChecked(isChecked);
                if (checkedListener!=null) {
                    checkedListener.onItemCheckStateChanged(getCheckedItemsCount());
                }
            }
        });
    }

    public int getCheckedItemsCount(){
        int count = 0;
        for (TODOListItem item : items){
            if (item.isChecked()) {
                count++;
            }
        }
        return count;
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(String item) {
        items.add(new TODOListItem(item));
        notifyDataSetChanged();         // komunikacja adapter-widok
    }


    public void deleteAllCheckItems() {

        List<TODOListItem> newItems = new ArrayList<>(); //robimy kopię listy, żeby się nie posypało

        for (int i = 0; i < items.size(); i++) {
            if (!items.get(i).isChecked()) {
                newItems.add(items.get(i));
            }
        }

        items = newItems;
        notifyDataSetChanged();
        if (checkedListener!=null) {
            checkedListener.onItemCheckStateChanged(getCheckedItemsCount());
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        CheckBox checkBox;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_text);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
        }
    }

    public void deselectAllItems() {
        for (TODOListItem item :items) {
            item.setChecked(false);
        }
        notifyDataSetChanged();
    }
}
