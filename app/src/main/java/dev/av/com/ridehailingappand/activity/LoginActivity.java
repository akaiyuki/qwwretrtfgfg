package dev.av.com.ridehailingappand.activity;

import android.os.Bundle;

import dev.av.com.ridehailingappand.fragment.LoginFragment;
import dev.av.com.ridehailingappand.R;
import dev.av.com.ridehailingappand.core.BaseActivity;
import dev.av.com.ridehailingappand.core.REngine;

public class LoginActivity extends BaseActivity {

    public static LoginActivity INSTANCE = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setFrameLayout(R.id.framelayout_main);

        INSTANCE = this;
        REngine.switchFragment(this, new LoginFragment(), getFrameLayout());
    }
}
