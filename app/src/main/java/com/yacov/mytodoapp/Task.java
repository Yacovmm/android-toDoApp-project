package com.yacov.mytodoapp;

/**
 * Created by YacovR on 22-Nov-17.
 */

public class Task {

    String date, name;

    public Task() {
    }

    public Task(String date, String name) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }
}
