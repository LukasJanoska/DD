package com.damidev.dd.main.account.contacts.platform;

import android.support.annotation.WorkerThread;
import android.util.Log;

import com.damidev.dd.shared.Events.ErrorEvent;
import com.damidev.dd.shared.Events.ServerEvent;
import com.damidev.dd.shared.dataaccess.BaseResponseDto;
import com.damidev.dd.shared.dataaccess.DamiRestApi;
import com.damidev.dd.shared.dataaccess.ServerContactsResultDto;
import com.damidev.dd.shared.dataaccess.ServerNewContactResultDto;
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


public class ContactsCommunicator {
    private static  final String TAG = "Communicator";
    private static final String SERVER_URL = "http://androidtest.dev.damidev.com/api/";

    @WorkerThread
    public void getAllContacts(String token){

        Retrofit retrofit = setServerComunication();
        DamiRestApi service = retrofit.create(DamiRestApi.class);

        Call<ServerContactsResultDto> call = service.getContacts(token);

        call.enqueue(new Callback<ServerContactsResultDto>() {
            @Override
            public void onResponse(Call<ServerContactsResultDto> call, Response<ServerContactsResultDto> response) {
                // response.isSuccessful() is true if the response code is 2xx
                ServerContactsResultDto data;
                data = response.body();

                if(data != null && data.getResponseCode() == 1) {
                    BusProvider.getInstance().post(new ServerEvent(data));
                    Log.e(TAG,"Success");
                }

                /*BusProvider.getInstance().post(new ErrorEvent(-2, "server not responding"));
                Log.e(TAG,"Failure");*/
            }

            @Override
            public void onFailure(Call<ServerContactsResultDto> call, Throwable t) {
                // handle execution failures like no internet connectivity

                BusProvider.getInstance().post(new ErrorEvent(-2,t.getMessage()));
            }
        });
    }

    @WorkerThread
    public void addContact(String token, HashMap hashMap){

        Retrofit retrofit = setServerComunication();
        DamiRestApi service = retrofit.create(DamiRestApi.class);

        Call<ServerNewContactResultDto> call = service.addContact(token, hashMap);

        call.enqueue(new Callback<ServerNewContactResultDto>() {
            @Override
            public void onResponse(Call<ServerNewContactResultDto> call, Response<ServerNewContactResultDto> response) {
                // response.isSuccessful() is true if the response code is 2xx
                ServerNewContactResultDto data;
                data = response.body();

                if(data != null && data.getResponseCode() == 1) {
                    BusProvider.getInstance().post(new ServerEvent(data));
                    Log.e(TAG,"Success");
                }
            }

            @Override
            public void onFailure(Call<ServerNewContactResultDto> call, Throwable t) {
                // handle execution failures like no internet connectivity

                BusProvider.getInstance().post(new ErrorEvent(-2,t.getMessage()));
            }
        });
    }

    @WorkerThread
    public void deleteContact(String token, int id){

        Retrofit retrofit = setServerComunication();
        DamiRestApi service = retrofit.create(DamiRestApi.class);

        Call<BaseResponseDto> call = service.deleteContact(token, id);

        call.enqueue(new Callback<BaseResponseDto>() {
            @Override
            public void onResponse(Call<BaseResponseDto> call, Response<BaseResponseDto> response) {
                // response.isSuccessful() is true if the response code is 2xx

                if(response != null && response.code() == 200) {
                    BusProvider.getInstance().post("success");
                    Log.e(TAG,"Success");
                }
            }

            @Override
            public void onFailure(Call<BaseResponseDto> call, Throwable t) {
                // handle execution failures like no internet connectivity

                BusProvider.getInstance().post(new ErrorEvent(-2,t.getMessage()));
            }
        });
    }

    private Retrofit setServerComunication() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SERVER_URL)
                .build();
        return retrofit;
    }

    @Produce
    public ServerEvent produceServerEvent(ServerContactsResultDto serverMapResponseDto) {
        return new ServerEvent(serverMapResponseDto);
    }

    @Produce
    public ErrorEvent produceErrorEvent(int errorCode, String errorMsg) {
        return new ErrorEvent(errorCode, errorMsg);
    }
}
