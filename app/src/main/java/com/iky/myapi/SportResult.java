package com.iky.myapi;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SportResult {
    //sport diambil dari nama kelas di file object yaitu Sport jika nama beda harus di serializedName
    @SerializedName(value = "player",alternate = {"players"})
    private ArrayList<Sport> sports;
    public SportResult(ArrayList<Sport> sports) {
        this.sports = sports;
    }

    public ArrayList<Sport> getSports() {
        return sports;
    }
}
