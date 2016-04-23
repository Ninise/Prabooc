package com.ninise.prabooc.mvp.presenter.login;


import android.content.Context;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

public interface ILoginPresenter extends MvpPresenter<ILoginView> {

    void checkNetwork(Context context);

}
