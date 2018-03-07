package com.example.mudassirkhan.mercaritest.ui;

import android.annotation.TargetApi;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mudassirkhan.mercaritest.R;
import com.example.mudassirkhan.mercaritest.Utils.ConnectivityUtils;
import com.example.mudassirkhan.mercaritest.adapter.RecyclerViewAdapter;
import com.example.mudassirkhan.mercaritest.model.DataItem;
import com.example.mudassirkhan.mercaritest.model.DataResponse;
import com.example.mudassirkhan.mercaritest.ui.ErrorPrompt.PromptProvider;
import com.example.mudassirkhan.mercaritest.ui.ErrorPrompt.snackbar.SnackbarParams;
import com.example.mudassirkhan.mercaritest.ui.ErrorPrompt.snackbar.SnackbarPrompt;
import com.example.mudassirkhan.mercaritest.viewModel.DataViewModel;
import java.util.List;


public class WomenFragment extends Fragment {

    private static final String WOMEN_TAG=WomenFragment.class.getSimpleName();
    private View mProgressView;
    private View womenDataFormView;
    private View mContainerLayout;
    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView recyclerView;
    private DataViewModel mDataViewModel;
    String urlWomen = "https://raw.githubusercontent.com/m-rec/f278b935bf419a41b5d1a2f0a8cbbf5ed590bae8/master/app/src/main/assets/women.json?token=AZ9zQwmA7DteiMHc4sBQ5NPHvdG2Bh7nks5amCqVwA%3D%3D";

    public WomenFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mLayoutView;
        mLayoutView = inflater.inflate(R.layout.fragment_women, container, false);
        womenDataFormView = mLayoutView.findViewById(R.id.womenDataLayout);
        mProgressView = mLayoutView.findViewById(R.id.womenData_progress);
        recyclerView = (RecyclerView) mLayoutView.findViewById(R.id.womenRecyclerview);

        //call the init ViewModel
        initViewModel();
        //call the method to get data
        getData();
        //return the view
        return mLayoutView;
    }

    public void getData() {
        //first check for internet connectivity
        if (ConnectivityUtils.isConnected(getActivity())) {
            //show the progress bar
            showProgress(true);
            //to get data from server
             mDataViewModel.getAllData(urlWomen);
            //to get data from json assets file
          //  mDataViewModel.getDataFromAssets("women.json");
            //observe the viewModel
            observeViewModel();
        } else {
            //show network error when not connect to internet
            showNetworkError();
        }
    }

    //init the viewModel
    private void initViewModel() {

        mDataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
    }

    private void observeViewModel() {
        // Update the list when the data changes
        mDataViewModel.getObservableDataResponse().observe(this, new Observer<DataResponse>() {
            @Override
            public void onChanged(@Nullable DataResponse dataResponse) {
                if (dataResponse != null) {
                    showProgress(false);
                    if (dataResponse.getResult().equalsIgnoreCase("ok")) {

                        Log.d(WOMEN_TAG, dataResponse + "");

                        populateRecyclerView(dataResponse.getData());

                    } else {
                        Log.d(WOMEN_TAG, dataResponse.toString());
                    }
                }
            }
        });
    }

    //populate the recyclerView
    public void populateRecyclerView(List<DataItem> powderModelClassList) {
        //init the recyclerView Adapter
        recyclerViewAdapter = new RecyclerViewAdapter(powderModelClassList);
        //set the layout with 2 elements in a row
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2);
        //set the layout manager
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //set the separation line
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        //set the adapter
        recyclerView.setAdapter(recyclerViewAdapter);
        //notify the adapter
        recyclerViewAdapter.notifyDataSetChanged();
    }


    /**
     * Shows the progress bar
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {

        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);

    }

    //method to show the network error
    private void showNetworkError() {

        PromptProvider.get().provide(SnackbarPrompt.class)
                .showNetworkError(new SnackbarParams.Builder(getActivity())
                        .rootView(womenDataFormView)
                        .listener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                        .build());
    }

}
