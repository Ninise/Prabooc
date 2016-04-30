package com.ninise.prabooc.mvp.presenter.home;

import android.view.MenuItem;

import com.ninise.prabooc.R;
import com.ninise.prabooc.mvp.network.facebook.SessionState;


public class HomePresenter implements IHomePresenter {

    private IHomeView mView;

    public HomePresenter(IHomeView view) {
        this.mView = view;
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
            case R.id.menuAbout:
                return true;
            case R.id.menuSignOut:
                SessionState.signOut();
                mView.exit();
                return true;
        }

        return false;
    }
}
