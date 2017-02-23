package com.damidev.dd.splashscreen.dataaccess;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;


public class ServerMapResponseDto implements Serializable{

    @SerializedName("responseCode")
    private int responseCode = 1;

    @SerializedName("responseCodeText")
    private String responseCodeText;

    @SerializedName("response")
    private ArrayList<ServerMapChildResponseDto> childResponse;

    public ServerMapResponseDto(String responseCodeText, int responseCode, ArrayList<ServerMapChildResponseDto> childResponse) {
        this.responseCodeText = responseCodeText;
        this.responseCode = responseCode;
        this.childResponse = childResponse;
    }

    public String getResponseCodeText() {
        return responseCodeText;
    }

    public void setResponseCodeText(String responseCodeText) {
        this.responseCodeText = responseCodeText;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public ArrayList<ServerMapChildResponseDto> getChildResponse() {
        return childResponse;
    }

    public void setChildResponse(ArrayList<ServerMapChildResponseDto> childResponse) {
        this.childResponse = childResponse;
    }
}