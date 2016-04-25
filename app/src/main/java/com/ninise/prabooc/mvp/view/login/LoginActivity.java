package com.ninise.prabooc.mvp.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.ninise.prabooc.R;
import com.ninise.prabooc.mvp.presenter.login.ILoginView;
import com.ninise.prabooc.mvp.presenter.login.LoginPresenter;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;

public class LoginActivity extends MvpActivity<ILoginView, LoginPresenter> implements ILoginView {

    @Bind(R.id.loginToolbar) Toolbar mLoginToolbar;
    @Bind(R.id.loginFacebookBtn) LoginButton mLoginButton;
    @BindString(R.string.access_denied) String mAccessDenied;
    @BindString(R.string.network_not_found) String mNetworkNotFound;

    private CallbackManager mCallbackManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        mCallbackManager= CallbackManager.Factory.create();

        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);

        setSupportActionBar(mLoginToolbar);


       mLoginButton.setOnClickListener(v -> presenter.checkNetwork(getApplicationContext()));

        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("das", "onSuccess: " + loginResult.toString());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @NonNull
    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void accessDenied() {
        Toast.makeText(getApplicationContext(), mAccessDenied, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void networkNotFound() {
        Toast.makeText(this, mNetworkNotFound, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }
}
