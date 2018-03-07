package com.example.mudassirkhan.mercaritest.api.DataApi;

import com.example.mudassirkhan.mercaritest.api.ErrorHandlingAdapter;

import retrofit2.http.GET;
import retrofit2.http.Url;

import com.example.mudassirkhan.mercaritest.model.DataResponse;


/**
 * Created by Mudassir Khan on 2/20/2018.
 */

public interface DataServiceApi{

    //method to get data from server
    @GET()
    ErrorHandlingAdapter.MyCall<DataResponse> getAllData(@Url String url);
}
