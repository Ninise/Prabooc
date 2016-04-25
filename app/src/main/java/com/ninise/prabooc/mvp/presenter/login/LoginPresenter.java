package com.ninise.prabooc.mvp.presenter.login;

import android.content.Context;

import com.github.pwittchen.reactivenetwork.library.ConnectivityStatus;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.ninise.prabooc.mvp.network.NetworkConnection;

public class LoginPresenter extends MvpBasePresenter<ILoginView> implements ILoginPresenter {

    @Override
    public void checkNetwork(Context context) {
        NetworkConnection.getConnectivityStatus(context)
                .subscribe(connectivityStatus -> {
                    if (!connectivityStatus.equals(ConnectivityStatus.WIFI_CONNECTED)) {
                        getView().networkNotFound();
                    }
                });
    }
}
