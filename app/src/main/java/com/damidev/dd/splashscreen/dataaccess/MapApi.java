package com.damidev.dd.splashscreen.dataaccess;

import retrofit2.Call;
import retrofit2.http.POST;


public interface MapApi {

    @POST("getPointsOnMap")
    Call<ServerMapResponseDto> getPointsOnMap();

}
