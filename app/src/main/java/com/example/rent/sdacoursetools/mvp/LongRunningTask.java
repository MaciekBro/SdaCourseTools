package com.example.rent.sdacoursetools.mvp;

/**
 * Created by RENT on 2017-03-04.
 */

public class LongRunningTask {

    public String execute() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Udalo sie!";
    }
}
