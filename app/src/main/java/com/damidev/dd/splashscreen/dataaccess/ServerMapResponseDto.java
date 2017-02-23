package com.damidev.dd.splashscreen.dataaccess;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class ServerMapResponseDto implements Serializable{

    public ServerMapResponseDto(String responseCodeText, int responseCode) {
        this.responseCodeText = responseCodeText;
        this.responseCode = responseCode;
  //      this.childResponse = childResponse;
    }

    @SerializedName("responseCodeText")
    private String responseCodeText;

    @SerializedName("responseCode")
    private int responseCode = 1;

    /*@SerializedName("response")
    private ServerChildResponse childResponse;
*/
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

    /*public ServerChildResponse getChildResponse() {
        return childResponse;
    }

    public void setChildResponse(ServerChildResponse childResponse) {
        this.childResponse = childResponse;
    }*/
}