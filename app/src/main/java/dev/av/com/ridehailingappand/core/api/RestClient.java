package dev.av.com.ridehailingappand.core.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dev.av.com.ridehailingappand.core.RConfiguration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by CodeSyaona on 27/07/2017.
 */

public class RestClient {


    private Retrofit retrofit;


    // Api Service Classes String
    public static String userApiService = "ApiServiceUser.class";



    // Api Selected Service
    private ApiServiceUser apiServiceUser;


    public RestClient(String className)
    {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(RConfiguration.liveUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        getApiRestService(className);

    }

//    public static String getAVConfiguration(){
//        if (AVConfiguration.Staging){
//            return AVConfiguration.testBaseUrl;
//        } else {
//            return AVConfiguration.liveBaseUrl;
//        }
//    }

    public ApiServiceUser getApiServiceUser(){ return apiServiceUser; }

    private void getApiRestService(String apiServiceSelected){
       if (apiServiceSelected.equalsIgnoreCase(userApiService)){
            apiServiceUser = retrofit.create(ApiServiceUser.class);
        }
    }



}
