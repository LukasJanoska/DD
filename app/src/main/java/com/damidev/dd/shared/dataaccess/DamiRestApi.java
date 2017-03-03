package com.damidev.dd.shared.dataaccess;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DamiRestApi {

    @FormUrlEncoded
    @POST("login")
    Flowable<Response<ServerRegResultDto>> registration(@Field("email") String email,
                                                        @Field("password") String password);
    @FormUrlEncoded
    @POST("login")
    Flowable<Response<ServerRegResultDto>> login(@Field("email") String email,
                                                 @Field("password") String password);

    @FormUrlEncoded
    @POST("updateAccount")
    Call<ServerRegResultDto> updateUserProfile(@Field("token") String token,
                                                                                        @Field("name") String name);

}
