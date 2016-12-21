package com.zhaozhuan.mobile.tile.http;

import com.zhaozhuan.mobile.tile.entity.HttpResult;
import com.zhaozhuan.mobile.tile.entity.User;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ricky.ye on 12/20/16.
 */

public interface UserService {
    @GET("getSms")
    Observable<HttpResult<Boolean>> getSms(@Query("phone") String phone);

    @GET("login")
    Observable<HttpResult<User>> login(@Query("phone") String phone, @Query("sms") String sms);
}
