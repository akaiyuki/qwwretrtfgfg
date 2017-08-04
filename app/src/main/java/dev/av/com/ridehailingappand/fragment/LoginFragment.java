package dev.av.com.ridehailingappand.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.av.com.ridehailingappand.R;
import dev.av.com.ridehailingappand.core.AppController;
import dev.av.com.ridehailingappand.core.RSharedPreferences;
import dev.av.com.ridehailingappand.core.api.ApiResponseUser;
import dev.av.com.ridehailingappand.core.api.RestClient;
import dev.av.com.ridehailingappand.core.object.PreferenceObject;
import dev.av.com.ridehailingappand.fragment.MainFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    @BindView(R.id.editEmail)
    EditText editEmail;
    @BindView(R.id.editPassword)
    EditText editPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this,view);


        return view;
    }

    @OnClick(R.id.btnLogin)
    public void onClickLogin(){
        requestApiLogin(editEmail.getText().toString(), editPassword.getText().toString());
    }

    private void requestApiLogin(String email, String password){

        RestClient restClient = new RestClient(RestClient.userApiService);
        Call<ApiResponseUser> call = restClient.getApiServiceUser().userLogin(email,password);
        call.enqueue(new Callback<ApiResponseUser>() {
            @Override
            public void onResponse(Call<ApiResponseUser> call, Response<ApiResponseUser> response) {

                if (response.isSuccessful()){

                    RSharedPreferences.setSomeStringValue(AppController.getInstance(), PreferenceObject.userName, response.body().getData().getUserName());
                    RSharedPreferences.setSomeStringValue(AppController.getInstance(), PreferenceObject.userId, response.body().getData().getUserId());
                    RSharedPreferences.setSomeStringValue(AppController.getInstance(), PreferenceObject.userMobile, response.body().getData().getUserMobile());
                    RSharedPreferences.setSomeStringValue(AppController.getInstance(), PreferenceObject.userEmail, response.body().getData().getUserEmail());


                    startActivity(new Intent(getActivity(), MainFragment.class));
                    getActivity().finish();
                }

            }

            @Override
            public void onFailure(Call<ApiResponseUser> call, Throwable t) {

            }
        });

    }

}
