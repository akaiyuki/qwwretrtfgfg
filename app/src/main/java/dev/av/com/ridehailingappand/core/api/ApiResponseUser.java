package dev.av.com.ridehailingappand.core.api;

import com.google.gson.annotations.SerializedName;

import dev.av.com.ridehailingappand.core.object.UserObject;

/**
 * Created by CodeSyaona on 27/07/2017.
 */

public class ApiResponseUser {

    @SerializedName("msg")
    private String message;
    @SerializedName("data")
    private UserObject data;
    @SerializedName("code")
    private int status;

    public UserObject getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

}
