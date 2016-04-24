package com.ninise.prabooc.mvp.network;

import com.ninise.prabooc.BuildConfig;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class NetworkConnectionTest {

    @Test
    public void testGetConnectivityStatus() throws Exception {
        Assertions.assertThat(
                NetworkConnection.getConnectivityStatus(
                        new ShadowActivity().getApplicationContext()))
                .isNotNull();
    }
}