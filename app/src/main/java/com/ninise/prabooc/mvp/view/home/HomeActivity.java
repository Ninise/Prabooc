package com.ninise.prabooc.mvp.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.jakewharton.rxbinding.support.design.widget.RxNavigationView;
import com.jakewharton.rxbinding.support.v7.widget.RxToolbar;
import com.ninise.prabooc.R;
import com.ninise.prabooc.mvp.network.facebook.SessionState;
import com.ninise.prabooc.mvp.presenter.home.HomePresenter;
import com.ninise.prabooc.mvp.presenter.home.IHomeView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements IHomeView {

    @Bind(R.id.homeToolbar) Toolbar mHomeToolbar;
    @Bind(R.id.homeDrawer) DrawerLayout mDrawerLayout;
    @Bind(R.id.homeNavigationView) NavigationView mNavigationView;

    private HomePresenter mPresenter;

    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        ButterKnife.bind(this);

        mPresenter = new HomePresenter(this);

        setSupportActionBar(mHomeToolbar);
        RxToolbar.itemClicks(mHomeToolbar).subscribe(mPresenter::menuSelected);
        RxNavigationView.itemSelections(mNavigationView).subscribe(menuItem -> {
            mDrawerLayout.closeDrawers();
            mPresenter.menuSelected(menuItem);
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout, mHomeToolbar, R.string.openDrawer,
                R.string.closeDrawer);

        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            SessionState.signOut();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, getString(R.string.back_pressed), Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
    }

    @Override
    public void switchToActivity(Class c) {
        startActivity(new Intent(this, c));
    }

    @Override
    public void exit() {
        finish();
    }
}
