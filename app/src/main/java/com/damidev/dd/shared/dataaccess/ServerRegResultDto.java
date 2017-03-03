package com.damidev.dd.shared.dataaccess;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ServerRegResultDto implements Serializable {

    public ServerRegResultDto(String responseCodeText, int responseCode, ServerRegChildResponseDto childResponse) {
        this.responseCodeText = responseCodeText;
        this.responseCode = responseCode;
        this.childResponse = childResponse;
    }

    @SerializedName("responseCodeText")
    private String responseCodeText;

    @SerializedName("responseCode")
    private int responseCode = 1;

    @SerializedName("response")
    private ServerRegChildResponseDto childResponse;

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

    public ServerRegChildResponseDto getChildResponse() {
        return childResponse;
    }

    public void setChildResponse(ServerRegChildResponseDto childResponse) {
        this.childResponse = childResponse;
    }
}
