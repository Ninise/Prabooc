package com.ninise.prabooc.mvp.view.login;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.ninise.prabooc.R;
import com.ninise.prabooc.mvp.presenter.login.ILoginView;
import com.ninise.prabooc.mvp.presenter.login.LoginPresenter;
import com.ninise.prabooc.mvp.view.home.HomeActivity;

import butterknife.Bind;
import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    @Bind(R.id.loginToolbar) Toolbar mLoginToolbar;
    @Bind(R.id.loginFacebookBtn) LoginButton mLoginButton;
    @BindString(R.string.network_not_found) String mNetworkNotFound;
    @BindDrawable(R.mipmap.ic_launcher) Drawable mLoginDrawable;

    private CallbackManager mCallbackManager;
    private LoginPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        mCallbackManager= CallbackManager.Factory.create();

        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);

        mPresenter = new LoginPresenter(this);

        setSupportActionBar(mLoginToolbar);
        mLoginToolbar.setLogo(mLoginDrawable);


        mLoginButton.setOnClickListener(v -> mPresenter.checkNetwork(getApplicationContext()));

        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
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
