package com.example.mudassirkhan.mercaritest.api;

import com.example.mudassirkhan.mercaritest.api.DataApi.DataServiceApi;

/**
 * Created by Mudassir Khan on 2/20/2018.
 */

public class MercariServiceProvider  implements IServiceProvider{

    //instance of MercariServiceProvider
    private static volatile MercariServiceProvider retrofitService;
    //create an object of MercariServiceProvider
    public static MercariServiceProvider get() {
        if (retrofitService == null) {
            synchronized (MercariServiceProvider.class) {
                if (retrofitService == null) {
                    retrofitService = new MercariServiceProvider();
                }
            }
        }
        return retrofitService;
    }

    private MercariServiceProvider() {
    }


    //implement a data method
    @Override
    public DataServiceApi getAllData() {

        return RetrofitClient.getClient().create(DataServiceApi.class);
    }
}
