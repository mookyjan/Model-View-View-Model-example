package com.example.mudassirkhan.mercaritest;

import android.app.Application;
import android.content.Context;

/**
 * Created by Mudassir Khan on 2/23/2018.
 */

public class MercariApp extends Application {
    /**
     * Keeps a reference of the application context
     */
    private static Context sContext;
    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }

    public static Context getsContext(){
        return sContext;
    }

}
