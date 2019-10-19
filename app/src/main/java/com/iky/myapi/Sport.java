package com.iky.myapi;

import com.google.gson.annotations.SerializedName;

public class Sport {
    //@serializedName digunakan untuk mengambil data JSON menggunakan GSON
    @SerializedName("idPlayer") private String id_player;
    @SerializedName("strNationality") private String nationality;
    @SerializedName("strPlayer") private String name;
    @SerializedName("dateBorn") private String birthDate;
    @SerializedName("strBirthLocation") private String birthPlace;
    @SerializedName("strDescriptionEN") private String description;
    @SerializedName("strThumb") private String imagePath;

    public Sport(String id_player, String nationality, String name, String birthDate, String birthPlace, String description, String imagePath) {
        this.id_player = id_player;
        this.nationality = nationality;
        this.name = name;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
        this.description = description;
        this.imagePath = imagePath;

    }

    public String getId_player() {
        return id_player;
    }

    public String getNationality() {
        return nationality;
    }

    public String getName() {
        return name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }
}
