package com.ninise.prabooc.mvp.network.facebook;

import com.facebook.login.LoginManager;

public class SessionState {

    private SessionState() {
        throw new AssertionError();
    }

    public static void signOut() {
        LoginManager.getInstance().logOut();
    }

}
