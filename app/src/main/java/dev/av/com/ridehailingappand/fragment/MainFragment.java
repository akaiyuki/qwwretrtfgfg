package dev.av.com.ridehailingappand.fragment;


import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import dev.av.com.ridehailingappand.R;
import dev.av.com.ridehailingappand.core.AppController;
import dev.av.com.ridehailingappand.core.RSharedPreferences;
import dev.av.com.ridehailingappand.core.api.ApiResponseBook;
import dev.av.com.ridehailingappand.core.api.RestClient;
import dev.av.com.ridehailingappand.core.object.PreferenceObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends FragmentActivity implements OnMapReadyCallback {

    private LatLng direction;
    private GoogleMap mMap;
    private String url;
    private TextView mTextTimer;
    private Button mButtonRequest;
    private EditText mTextLocation;
    private EditText mTextDestination;

    public static MainFragment INSTANCE = null;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        INSTANCE = this;

        mTextTimer = findViewById(R.id.txtTimer);
        mButtonRequest = findViewById(R.id.btnRequest);
        mTextLocation = findViewById(R.id.txtLocation);
        mTextDestination = findViewById(R.id.txtDestination);

        mTextTimer.setVisibility(View.GONE);

        mButtonRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (mTextLocation.getText().length() != 0 && mTextDestination.getText().length() != 0){

                    mTextTimer.setVisibility(View.VISIBLE);

                    String userName = RSharedPreferences.getSomeStringValue(AppController.getInstance(), PreferenceObject.userName);
                    String userMobile = RSharedPreferences.getSomeStringValue(AppController.getInstance(), PreferenceObject.userMobile);

                    requestApiAddRequest(userName,userMobile,mTextLocation.getText().toString(),mTextDestination.getText().toString());
                } else {

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(INSTANCE);
                    builder1.setMessage("Please input location and destination");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });


                    AlertDialog alert11 = builder1.create();
                    alert11.show();

                }

            }
        });



    }

    @Override
    public void onMapReady(GoogleMap map) {


        double latitude = 14.55828;
        double longitude = 121.0547;

        direction = new LatLng(latitude, longitude);

        mMap = map;

        map.moveCamera(CameraUpdateFactory.newLatLng(direction));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(latitude, longitude))
                .zoom(10)
                .build();

        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


    }

    private void requestApiAddRequest(String user, String mobile, String location, String destination){
        RestClient restClient = new RestClient(RestClient.userApiService);
        Call<ApiResponseBook> call = restClient.getApiServiceUser().addRequest(user, mobile, location, destination);
        call.enqueue(new Callback<ApiResponseBook>() {
            @Override
            public void onResponse(Call<ApiResponseBook> call, Response<ApiResponseBook> response) {
                if (response.isSuccessful()){
                    CountDownTimer countDownTimer = new CountDownTimer(10000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            mTextTimer.setText("" + millisUntilFinished / 1000);
                            mButtonRequest.setBackgroundColor(Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(getApplicationContext(),R.color.colorRed))));
                            mButtonRequest.setText("Cancel Request");
                        }

                        public void onFinish() {
                            mTextTimer.setText("done!");
                            mTextTimer.setVisibility(View.GONE);
                            mButtonRequest.setBackgroundColor(Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(getApplicationContext(),R.color.colorButtonBackground))));
                            mButtonRequest.setText("Request");

                        }
                    }.start();
                }
            }

            @Override
            public void onFailure(Call<ApiResponseBook> call, Throwable t) {

            }
        });
    }
}
