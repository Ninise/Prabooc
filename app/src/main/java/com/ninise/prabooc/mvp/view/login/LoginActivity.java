package com.ninise.prabooc.mvp.view.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.ninise.prabooc.R;
import com.ninise.prabooc.mvp.presenter.login.ILoginView;
import com.ninise.prabooc.mvp.presenter.login.LoginPresenter;

import butterknife.BindString;

public class LoginActivity extends MvpActivity<ILoginView, LoginPresenter> implements ILoginView {

    @BindString(R.string.access_denied) String mAccessDenied;
    @BindString(R.string.network_not_found) String mNetworkNotFound;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @NonNull
    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void accessDenied() {
        Toast.makeText(this, mAccessDenied, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void networkNotFound() {
        Toast.makeText(this, mNetworkNotFound, Toast.LENGTH_SHORT).show();
    }
}
