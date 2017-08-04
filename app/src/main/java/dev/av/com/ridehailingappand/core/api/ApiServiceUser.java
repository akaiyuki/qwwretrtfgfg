package dev.av.com.ridehailingappand.core.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by CodeSyaona on 27/07/2017.
 */

public interface ApiServiceUser {

    @POST("login")
    @FormUrlEncoded
    Call<ApiResponseUser> userLogin(@Field("email") String email, @Field("password") String password);

    @POST("user/addRequest")
    @FormUrlEncoded
    Call<ApiResponseBook> addRequest(@Field("username") String userName, @Field("mobile") String mobile,
                                     @Field("from_location") String location, @Field("destination") String destination);

}
