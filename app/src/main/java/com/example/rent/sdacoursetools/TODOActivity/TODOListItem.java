package com.example.rent.sdacoursetools.TODOActivity;

/**
 * Created by RENT on 2017-02-23.
 */


//klasa potrzebna do zaznaczenia i usuniecia itemu listy
public class TODOListItem {

    private String text;
    private boolean isChecked;

    public TODOListItem(String text) {
        this.text = text;
    }

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
}
