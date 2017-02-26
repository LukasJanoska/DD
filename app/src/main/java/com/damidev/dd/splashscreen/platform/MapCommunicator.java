package com.damidev.dd.splashscreen.platform;

import android.content.Context;
import android.support.annotation.WorkerThread;
import android.util.Log;

import com.damidev.dd.splashscreen.Events.ErrorEvent;
import com.damidev.dd.splashscreen.Events.ServerEvent;
import com.damidev.dd.splashscreen.dataaccess.MapApi;
import com.damidev.dd.splashscreen.dataaccess.ServerMapResponseDto;
import com.squareup.otto.Produce;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MapCommunicator {
    private static  final String TAG = "Communicator";
    private static final String SERVER_URL = "http://androidtest.dev.damidev.com/api/";

    @WorkerThread
    public void getMapPoints(final Context context){

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SERVER_URL)
                .build();

        MapApi service = retrofit.create(MapApi.class);

        Call<ServerMapResponseDto> call = service.getPointsOnMap();

        call.enqueue(new Callback<ServerMapResponseDto>() {
            @Override
            public void onResponse(Call<ServerMapResponseDto> call, Response<ServerMapResponseDto> response) {
                // response.isSuccessful() is true if the response code is 2xx
                ServerMapResponseDto data;
                data = response.body();

                if(data != null && data.getResponseCode() == 1) {
                    BusProvider.getInstance().post(new ServerEvent(data));
                    Log.e(TAG,"Success");
                }

                /*BusProvider.getInstance().post(new ErrorEvent(-2, "server not responding"));
                Log.e(TAG,"Failure");*/
            }

            @Override
            public void onFailure(Call<ServerMapResponseDto> call, Throwable t) {
                // handle execution failures like no internet connectivity

                BusProvider.getInstance().post(new ErrorEvent(-2,t.getMessage()));
            }
        });
    }

    @Produce
    public ServerEvent produceServerEvent(ServerMapResponseDto serverMapResponseDto) {
        return new ServerEvent(serverMapResponseDto);
    }

    @Produce
    public ErrorEvent produceErrorEvent(int errorCode, String errorMsg) {
        return new ErrorEvent(errorCode, errorMsg);
    }
}
