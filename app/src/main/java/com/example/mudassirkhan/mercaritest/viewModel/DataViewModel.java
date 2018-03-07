package com.example.mudassirkhan.mercaritest.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.mudassirkhan.mercaritest.model.DataResponse;
import com.example.mudassirkhan.mercaritest.repository.DataRepository;

/**
 * Created by Mudassir Khan on 2/21/2018.
 */

public class DataViewModel extends AndroidViewModel{

    private LiveData<DataResponse> mObservableAllDataResponse = new MutableLiveData<>();

    //constructor of ViewModel
    public DataViewModel(@NonNull Application application) {

        super(application);
    }

    /**
     * Expose the LiveData DataResponse query so the UI can observe it.
     */
    public void getAllData(String url) {

        mObservableAllDataResponse =  DataRepository.get().getAllData(url);
    }

    /**
     *  this method is used to load data from assets folder instead of server url
     */
    public void getDataFromAssets(String fileName){
        //call the method from Repository
        mObservableAllDataResponse= DataRepository.get().loadDataFromAssets(fileName);
    }

    /**
     * Expose the LiveData DataResponse query so the UI can observe it.
     */
    public LiveData<DataResponse> getObservableDataResponse() {

        return mObservableAllDataResponse;
    }

}
