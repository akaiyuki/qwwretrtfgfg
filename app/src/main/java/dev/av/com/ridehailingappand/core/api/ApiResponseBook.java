package dev.av.com.ridehailingappand.core.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CodeSyaona on 04/08/2017.
 */

public class ApiResponseBook {

    @SerializedName("msg")
    private String message;
    @SerializedName("code")
    private String code;

    public String getCode() {
        return code;
    }

    public String getMessage() {

        return message;
    }
}
