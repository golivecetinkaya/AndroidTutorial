package com.golive.fms.myapplication;

public class NotModel {

    private String not;
    private String date;
    private int image;

    public NotModel(String not, String date, int image) {
        this.not = not;
        this.date = date;
        this.image = image;
    }

    public String getNot() {
        return not;
    }

    public String getDate() {
        return date;
    }

    public int getImage() {
        return image;
    }
}
