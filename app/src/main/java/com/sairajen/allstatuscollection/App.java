package com.sairajen.allstatuscollection;

import android.app.Application;
import com.onesignal.OneSignal;

/**
 * @author Gmonetix
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();


    }
}
