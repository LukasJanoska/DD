package com.damidev.dd.shared.dataaccess;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ServerErrorResultDto implements Serializable {

    @SerializedName("responseCodeText")
    private String responseCodeText;

    @SerializedName("responseCode")
    private int responseCode = 1;

    public ServerErrorResultDto(String responseCodeText, int responseCode) {
        this.responseCodeText = responseCodeText;
        this.responseCode = responseCode;
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
}
