package dev.av.com.ridehailingappand.core;

import android.app.Application;

/**
 * Created by CodeSyaona on 27/07/2017.
 */

public class AppController extends Application {

    private static AppController mInstance;
    public static synchronized AppController getInstance() {
        return mInstance;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        RSharedPreferences.init(mInstance);

    }


}
