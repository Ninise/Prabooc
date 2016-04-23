package com.ninise.prabooc.mvp.network;


import android.content.Context;

import com.github.pwittchen.reactivenetwork.library.ConnectivityStatus;
import com.github.pwittchen.reactivenetwork.library.ReactiveNetwork;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NetworkConnection {

    public static Observable<ConnectivityStatus> getConnectivityStatus(final Context context) {
        return new ReactiveNetwork().observeConnectivity(context)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
