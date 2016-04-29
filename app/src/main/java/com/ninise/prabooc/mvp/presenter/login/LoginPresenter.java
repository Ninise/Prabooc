package com.ninise.prabooc.mvp.presenter.login;

import android.content.Context;

import com.github.pwittchen.reactivenetwork.library.ConnectivityStatus;
import com.ninise.prabooc.mvp.network.NetworkConnection;

public class LoginPresenter implements ILoginPresenter {

    private final ILoginView mView;

    public LoginPresenter(ILoginView view) {
        this.mView = view;
    }

    @Override
    public void checkNetwork(Context context) {
        NetworkConnection.getConnectivityStatus(context)
                .subscribe(connectivityStatus -> {
                    if (!connectivityStatus.equals(ConnectivityStatus.WIFI_CONNECTED)) {
                        mView.networkNotFound();
                    }
                });
    }
}
