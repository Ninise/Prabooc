package com.ninise.prabooc.mvp.presenter.home;

import android.view.MenuItem;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.ninise.prabooc.R;


public class HomePresenter extends MvpBasePresenter<IHomeView> implements IHomePresenter {

    @Override
    public void attachView(IHomeView view) {

    }

    @Override
    public void detachView(boolean retainInstance) {

    }

    @Override
    public boolean menuSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuHome:
                return true;
            case R.id.menuFriends:
                return true;
            case R.id.menuSettings:
                return true;
            case R.id.menuSignOut:
                getView().exit();
                return true;
        }

        return false;
    }
}
