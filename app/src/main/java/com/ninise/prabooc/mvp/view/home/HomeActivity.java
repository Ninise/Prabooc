package com.ninise.prabooc.mvp.view.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.jakewharton.rxbinding.support.design.widget.RxNavigationView;
import com.jakewharton.rxbinding.support.v7.widget.RxToolbar;
import com.ninise.prabooc.R;
import com.ninise.prabooc.mvp.presenter.home.HomePresenter;
import com.ninise.prabooc.mvp.presenter.home.IHomeView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends MvpActivity<IHomeView, HomePresenter> implements IHomeView {

    @Bind(R.id.homeToolbar) Toolbar mHomeToolbar;
    @Bind(R.id.homeDrawer) DrawerLayout mDrawerLayout;
    @Bind(R.id.homeNavigationView) NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        ButterKnife.bind(this);

        setSupportActionBar(mHomeToolbar);
        RxToolbar.itemClicks(mHomeToolbar).subscribe(presenter::menuSelected);
        RxNavigationView.itemSelections(mNavigationView).subscribe(menuItem -> {
            mDrawerLayout.closeDrawers();
            presenter.menuSelected(menuItem);
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout, mHomeToolbar, R.string.openDrawer,
                R.string.closeDrawer);

        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();
    }

    @NonNull
    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }

    @Override
    public void exit() {
        finish();
    }
}
