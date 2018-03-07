package com.example.mudassirkhan.mercaritest.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import com.example.mudassirkhan.mercaritest.MercariApp;

/**
 * Created by Mudassir Khan on 2/23/2018.
 */

public class ConnectivityUtils {

    private ConnectivityUtils() { }

    public static boolean isConnected(@NonNull Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public static boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                MercariApp.getsContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
