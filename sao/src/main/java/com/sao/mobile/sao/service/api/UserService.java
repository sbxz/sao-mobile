package com.sao.mobile.sao.service.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface UserService {
    @Headers("ZUMO-API-VERSION: 2.0.0")
    @GET("api/values")
    Call<Void> registerDevice();

    @Headers("ZUMO-API-VERSION: 2.0.0")
    @GET("api/values")
    Call<Void> retrieveUserInfo();

    @Headers("ZUMO-API-VERSION: 2.0.0")
    @GET("api/values")
    Call<Void> retrieveBars();
}
