package dev.av.com.ridehailingappand.core.object;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by CodeSyaona on 27/07/2017.
 */

@Parcel
public class UserObject {

    @SerializedName("_id")
    private String userId;
    @SerializedName("password")
    private String userPassword;
    @SerializedName("mobile")
    private String userMobile;
    @SerializedName("name")
    private String userName;
    @SerializedName("email")
    private String userEmail;

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserName() {

        return userName;
    }

    public String getUserMobile() {

        return userMobile;
    }

    public String getUserPassword() {

        return userPassword;
    }

    public String getUserId() {

        return userId;
    }

}
