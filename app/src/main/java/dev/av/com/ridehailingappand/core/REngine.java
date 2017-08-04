package dev.av.com.ridehailingappand.core;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by CodeSyaona on 27/07/2017.
 */

public class REngine {

    public static void switchFragment(BaseActivity baseActivity, Fragment fragment, int frame) {

        FragmentManager fm = baseActivity.getSupportFragmentManager();

        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(frame, fragment);
        transaction.addToBackStack(fragment.getClass().toString());
        transaction.commit();
    }

}
