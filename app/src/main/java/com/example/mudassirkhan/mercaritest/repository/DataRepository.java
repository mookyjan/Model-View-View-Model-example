package com.example.mudassirkhan.mercaritest.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.mudassirkhan.mercaritest.MercariApp;
import com.example.mudassirkhan.mercaritest.api.ErrorHandlingAdapter;
import com.example.mudassirkhan.mercaritest.api.LoadDataAssets;
import com.example.mudassirkhan.mercaritest.api.MercariServiceProvider;
import com.example.mudassirkhan.mercaritest.api.DataApi.DataServiceApi;
import com.example.mudassirkhan.mercaritest.model.DataResponse;
import java.io.IOException;
import retrofit2.Response;

/**
 * Created by Mudassir Khan on 2/20/2018.
 */

public class DataRepository {

    //instance of api service
    private DataServiceApi mDataServiceApi;
    //instance of Repository
    private static volatile DataRepository mDataRepository;

    /**
     * Repository Constructor
     */
    private DataRepository() {

        mDataServiceApi = MercariServiceProvider.get().getAllData();
    }

    /**
     * Create an instance on Repository Class
     * @return
     */
    public static DataRepository get() {
        DataRepository instance = mDataRepository;
        if (instance == null) {
            synchronized (DataRepository.class) {
                instance = mDataRepository;
                if (instance == null) {
                    instance = mDataRepository = new DataRepository();
                }
            }
        }

        return instance;
    }

    /**
     * Method to load data from assets
     * @param strFileName
     * @return
     */
    public LiveData<DataResponse> loadDataFromAssets(String strFileName){

        final MutableLiveData<DataResponse> data1=new MutableLiveData<>();
        LoadDataAssets loadDataAssets=new LoadDataAssets();
        DataResponse dataResponse =loadDataAssets.getJsonResponse(MercariApp.getsContext(),strFileName);
        data1.postValue(dataResponse);
        return data1;
    }

    /**
     * Load data from Server Url
     * @param url
     * @return
     */
    public LiveData<DataResponse> getAllData(String url) {


        final MutableLiveData<DataResponse> data = new MutableLiveData<>();

        mDataServiceApi.getAllData(url).enqueue(new ErrorHandlingAdapter.MyCallback<DataResponse>() {
            @Override
            public void success(Response<DataResponse> response2) {
                Log.d("responseR",response2.body()+"");
                data.postValue(response2.body());
            }

            @Override
            public void unauthenticated(Response<?> response) {

            }

            @Override
            public void clientError(Response<?> response) {

            }

            @Override
            public void serverError(Response<?> response) {

            }

            @Override
            public void networkError(IOException e) {

            }

            @Override
            public void unexpectedError(Throwable t) {

            }

            @Override
            public void failure(DataResponse response) {
//                data.postValue(response);
            }
        });

        return data;

    }
}
