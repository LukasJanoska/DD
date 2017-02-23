package com.damidev.dd.splashscreen.dataaccess;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;


public class ServerMapChildResponseDto implements Serializable{

    @SerializedName("id")
    private int id;

    @SerializedName("lat")
    private double lat;

    @SerializedName("lng")
    private double lng;

    @SerializedName("desc")
    private String desc;

    @SerializedName("photo")
    private ArrayList<String> photos;

    public ServerMapChildResponseDto(int id, double lat, double lng, String desc, ArrayList<String> photos) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.desc = desc;
        this.photos = photos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ArrayList<String> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<String> photos) {
        this.photos = photos;
    }
}