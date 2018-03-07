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

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class AllFragment extends Fragment {

   private static final String ALL_TAG=AllFragment.class.getSimpleName();
    private View mProgressView;
    private View mAllDataFormView;
    private View mContainerLayout;
    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView recyclerView;
    private DataViewModel mDataViewModel;
    private String urlAll = "https://raw.githubusercontent.com/m-rec/f278b935bf419a41b5d1a2f0a8cbbf5ed590bae8/master/app/src/main/assets/all.json?token=AZ9zQ7hN7iWk6vj9htCl3X0RFpe8v2aVks5alusbwA%3D%3D";

    public AllFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mLayoutView;
        // Inflate the layout for this fragment
        mLayoutView = inflater.inflate(R.layout.fragment_all, container, false);
        mAllDataFormView = mLayoutView.findViewById(R.id.allDataLayout);
        mProgressView = mLayoutView.findViewById(R.id.allData_progress);
        recyclerView = (RecyclerView) mLayoutView.findViewById(R.id.allDataRecyclerview);
        //call the initViewModel
        initViewModel();
        //call the get Data
        getData();
        return mLayoutView;

    }

    //call the method to get Data
    public void getData() {
        if (ConnectivityUtils.isConnected(getActivity())) {
            showProgress(true);
            //get data from server
              mDataViewModel.getAllData(urlAll);
            //uncomment this to get data from assets
           // mDataViewModel.getDataFromAssets("all.json");
            observeViewModel();
        } else {
            //show network error
            showNetworkError();
        }
    }

    //init the ViewModel
    private void initViewModel() {
        //get the ViewModel from ViewModelProviders
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

                        Log.d(ALL_TAG, dataResponse + "");

                        populateRecyclerView(dataResponse.getData());

                    } else {
                        Log.d(ALL_TAG, dataResponse.toString());
                        //the error handling will be done here when proper API Data
                    }
                }
            }
        });
    }

    //method to populate the recyclerView
    public void populateRecyclerView(List<DataItem> powderModelClassList) {

        //init the recyclerView Adapter
        recyclerViewAdapter = new RecyclerViewAdapter(powderModelClassList);
        //set the layout Manager for recycler view with 2 elements in a row
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //set the decoration item line
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(recyclerViewAdapter);
        //notify the adapter after changes
        recyclerViewAdapter.notifyDataSetChanged();
    }

    /**
     * Shows the progress bar
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {

        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void showNetworkError() {
        PromptProvider.get()
                .provide(SnackbarPrompt.class)
                .showNetworkError(new SnackbarParams.Builder(getActivity())
                        .rootView(mAllDataFormView)
                        .listener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                        .build());
    }

}
