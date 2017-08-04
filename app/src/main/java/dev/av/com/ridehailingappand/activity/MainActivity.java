package dev.av.com.ridehailingappand.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dev.av.com.ridehailingappand.R;
import dev.av.com.ridehailingappand.core.BaseActivity;
import dev.av.com.ridehailingappand.core.REngine;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFrameLayout(R.id.framelayout_main);


    }
}
