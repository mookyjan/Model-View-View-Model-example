package com.example.mudassirkhan.mercaritest.api;

import com.example.mudassirkhan.mercaritest.api.DataApi.DataServiceApi;

/**
 * Created by Mudassir Khan on 2/20/2018.
 */

public interface IServiceProvider {

    //all the methods in the api service will be write here so this interface is responsible to contain all the methods
    DataServiceApi getAllData();
}
