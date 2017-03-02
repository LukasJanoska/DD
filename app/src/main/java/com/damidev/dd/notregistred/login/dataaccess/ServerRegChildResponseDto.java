package com.damidev.dd.notregistred.login.dataaccess;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ServerRegChildResponseDto implements Serializable{

    @SerializedName("id")
    int id;

    @SerializedName("rights")
    private String rights;

    @SerializedName("email")
    private String email;

    @SerializedName("name")
    String name;

    @SerializedName("lastname")
    String lastname;

    @SerializedName("password")
    String password;

    @SerializedName("phone")
    String phone;

    @SerializedName("description")
    String description;

    @SerializedName("token")
    private String token;

    @SerializedName("favorites")
    private ArrayList<Favorites> favorites;

    //photo

    public ServerRegChildResponseDto(int id, String rights, String email, String name, String lastname, String password, String phone, String description, String token, ArrayList<Favorites> favorites) {
        this.id = id;
        this.rights = rights;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.password = password;
        this.phone = phone;
        this.description = description;
        this.token = token;
        this.favorites = favorites;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Favorites> getFavorites() {
        return favorites;
    }

    public void setFavorites(ArrayList<Favorites> favorites) {
        this.favorites = favorites;
    }

    public class Favorites implements Serializable {

        @SerializedName("id")
        private int id;

        @SerializedName("lat")
        private double lat;

        @SerializedName("lng")
        private double lng;

        @SerializedName("title")
        private String title;

        @SerializedName("desc")
        private String desc;

        @SerializedName("photo")
        private ArrayList<String> photo;

        public Favorites(int id, double lat, double lng, String title, String desc, ArrayList<String> photo) {
            this.id = id;
            this.lat = lat;
            this.lng = lng;
            this.title = title;
            this.desc = desc;
            this.photo = photo;
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

        public void setLat(long lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(long lng) {
            this.lng = lng;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public ArrayList<String> getPhoto() {
            return photo;
        }

        public void setPhoto(ArrayList<String> photo) {
            this.photo = photo;
        }
    }

}