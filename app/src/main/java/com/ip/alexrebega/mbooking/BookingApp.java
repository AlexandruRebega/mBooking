package com.ip.alexrebega.mbooking;

import android.app.Application;
import android.content.Context;

public class BookingApp extends Application {
    private static final String TAG = "ApplicationStart";
    private static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
//        EventBus.builder().logNoSubscriberMessages(false).installDefaultEventBus();
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }

//    EventBus eventBus = EventBus.builder().
//            logNoSubscriberMessages(false).
//            sendNoSubscriberEvent(false).
//            installDefaultEventBus();
}
