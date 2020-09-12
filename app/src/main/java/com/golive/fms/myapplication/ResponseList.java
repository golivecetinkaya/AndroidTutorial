package com.golive.fms.myapplication;

import com.google.gson.annotations.SerializedName;

public class ResponseList {

    @SerializedName("name")
    String name = "";

    @SerializedName("capital")
    String capital = "";

    @SerializedName("flag")
    String flag = "";

    @SerializedName("population")
    int population;
}
