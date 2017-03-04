package com.damidev.dd.main.account.profileedit.platform;

import android.support.annotation.WorkerThread;
import android.util.Log;

import com.damidev.dd.shared.Events.ErrorEvent;
import com.damidev.dd.shared.Events.ServerEvent;
import com.damidev.dd.shared.dataaccess.DamiRestApi;
import com.damidev.dd.shared.dataaccess.ServerRegResultDto;
import com.damidev.dd.shared.rest.platform.BusProvider;
import com.squareup.otto.Produce;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ProfileCommunicator {
    private static  final String TAG = "Communicator";
    private static final String SERVER_URL = "http://androidtest.dev.damidev.com/api/";

    @WorkerThread
    public void updateUserProfile(String token, HashMap hashMap){

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SERVER_URL)
                .build();

        DamiRestApi service = retrofit.create(DamiRestApi.class);

        Call<ServerRegResultDto> call = service.updateUserProfile(token, hashMap);

        call.enqueue(new Callback<ServerRegResultDto>() {
            @Override
            public void onResponse(Call<ServerRegResultDto> call, Response<ServerRegResultDto> response) {
                // response.isSuccessful() is true if the response code is 2xx
                ServerRegResultDto data;
                data = response.body();

                if(data != null && data.getResponseCode() == 1) {
                    BusProvider.getInstance().post(new ServerEvent(data));
                    Log.e(TAG,"Success");
                }

                /*BusProvider.getInstance().post(new ErrorEvent(-2, "server not responding"));
                Log.e(TAG,"Failure");*/
            }

            @Override
            public void onFailure(Call<ServerRegResultDto> call, Throwable t) {
                // handle execution failures like no internet connectivity

                BusProvider.getInstance().post(new ErrorEvent(-2,t.getMessage()));
            }
        });
    }

    @Produce
    public ServerEvent produceServerEvent(ServerRegResultDto serverMapResponseDto) {
        return new ServerEvent(serverMapResponseDto);
    }

    @Produce
    public ErrorEvent produceErrorEvent(int errorCode, String errorMsg) {
        return new ErrorEvent(errorCode, errorMsg);
    }
}
