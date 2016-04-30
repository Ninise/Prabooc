package com.ninise.prabooc.mvp.view.about;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.jakewharton.rxbinding.support.v7.widget.RxToolbar;
import com.jakewharton.rxbinding.view.RxView;
import com.ninise.prabooc.R;

import butterknife.Bind;
import butterknife.BindDrawable;
import butterknife.ButterKnife;

public class AboutActivity extends AppCompatActivity {

    @Bind(R.id.aboutToolbar) Toolbar mAboutToolbar;
    @Bind(R.id.aboutOkButton) Button mOkButton;
    @BindDrawable(R.drawable.ic_action_navigation_arrow_back) Drawable mBackDrawable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity);

        ButterKnife.bind(this);

        setSupportActionBar(mAboutToolbar);
        mAboutToolbar.setNavigationIcon(mBackDrawable);
        RxToolbar.navigationClicks(mAboutToolbar).subscribe(aVoid -> onBackPressed());

        RxView.clicks(mOkButton).subscribe(aVoid -> onBackPressed());
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }
}
