package com.damidev.dd.notregistred.login.dataaccess;

import io.reactivex.Flowable;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RegistrationRestApi {

    @FormUrlEncoded
    @POST("login")
    Flowable<Response<ServerRegResultDto>> registration(@Field("email") String email,
                                                        @Field("password") String password);
    @FormUrlEncoded
    @POST("login")
    Flowable<Response<ServerRegResultDto>> login(@Field("email") String email,
                                                 @Field("password") String password);

}
