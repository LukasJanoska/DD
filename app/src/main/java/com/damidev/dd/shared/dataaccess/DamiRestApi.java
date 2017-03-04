package com.damidev.dd.shared.dataaccess;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DamiRestApi {

    @FormUrlEncoded
    @POST("register")
    Flowable<Response<ServerRegResultDto>> registration(@Field("email") String email,
                                                        @Field("password") String password);
    @FormUrlEncoded
    @POST("login")
    Flowable<Response<ServerRegResultDto>> login(@Field("email") String email,
                                                 @Field("password") String password);

    @FormUrlEncoded
    @POST("updateAccount")
    Call<ServerRegResultDto> updateUserProfile(@Field("token") String token, @FieldMap Map<String, String> fields );

    @FormUrlEncoded
    @POST("getContacts")
    Call<ServerContactsResultDto> getContacts(@Field("token") String token );

    @FormUrlEncoded
    @POST("addContact")
    Call<ServerNewContactResultDto> addContact(@Field("token") String token, @FieldMap Map<String, String> fields );

    @FormUrlEncoded
    @POST("deleteContact")
    Call<BaseResponseDto> deleteContact(@Field("token") String token, @Field("id") int id );

}
