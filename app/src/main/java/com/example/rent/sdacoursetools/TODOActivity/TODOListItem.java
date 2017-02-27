package com.example.rent.sdacoursetools.TODOActivity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by RENT on 2017-02-23.
 */


//klasa potrzebna do zaznaczenia i usuniecia itemu listy
public class TODOListItem implements Parcelable {

    private String text;
    private boolean isChecked;

    public TODOListItem(String text) {
        this.text = text;
    }

    protected TODOListItem(Parcel in) {
        text = in.readString();
        isChecked = in.readByte() != 0;
    }

    public static final Creator<TODOListItem> CREATOR = new Creator<TODOListItem>() {
        @Override
        public TODOListItem createFromParcel(Parcel in) {
            return new TODOListItem(in);
        }

        @Override
        public TODOListItem[] newArray(int size) {
            return new TODOListItem[size];
        }
    };

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }


    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(text);
        out.writeByte((byte) (isChecked ? 1:0));

    }




}
