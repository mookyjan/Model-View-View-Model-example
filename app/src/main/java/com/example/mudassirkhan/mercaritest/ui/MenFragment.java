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
public class MenFragment extends Fragment {

    private static final String MEN_TAG=MenFragment.class.getSimpleName();
    private View mProgressView;
    private View menDataFormView;
    private View mContainerLayout;
    //declare recyclerViewAdapter
    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView recyclerView;
    private DataViewModel mDataViewModel;
    String urlMen="https://raw.githubusercontent.com/m-rec/f278b935bf419a41b5d1a2f0a8cbbf5ed590bae8/master/app/src/main/assets/men.json?token=AZ9zQ0abGxoz7zP5xAKmsavCeJIMg9Qdks5amCg7wA%3D%3D";
    public MenFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mLayoutView;
        //inflate the layout
        mLayoutView=inflater.inflate(R.layout.fragment_men, container, false);
        //get the menLayout from layout
        menDataFormView = mLayoutView.findViewById(R.id.menLayout);
        //get the progressView id
        mProgressView = mLayoutView.findViewById(R.id.menData_progress);
        //recyclerView
        recyclerView=(RecyclerView)mLayoutView.findViewById(R.id.menRecyclerview);

        //call the initViewModel
        initViewModel();
        //call the method
        getData();
        //return the inflated layout
        return mLayoutView ;

    }

    public void getData(){
        //check for network connectivity
        if (ConnectivityUtils.isConnected(getActivity())) {
            showProgress(true);
            //call the getData method
            mDataViewModel.getAllData(urlMen);
           // mDataViewModel.getDataFromAssets("men.json");
            //observe the viewModel
            observeViewModel();
        }
        else {
            showNetworkError();
        }
    }

    //method to initialize the ViewModel
    private void initViewModel() {
        //get the ViewModel from ViewModelProviders
        mDataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
    }

    //method to observe the data from View
    private void observeViewModel() {
        // Update the list when the data changes
        mDataViewModel.getObservableDataResponse().observe(this, new Observer<DataResponse>() {
            @Override
            public void onChanged(@Nullable DataResponse dataResponse) {
                if (dataResponse != null) {
                     showProgress(false);
                    if (dataResponse.getResult().equalsIgnoreCase("ok")) {
                        Log.d(MEN_TAG,dataResponse+"");
                        //call the populate RecyclerView method to show data in it
                        populateRecyclerView(dataResponse.getData());

                    } else {
                        Log.d(MEN_TAG,dataResponse.toString());
//                       the error will be handle here when proper api
                    }
                }
            }
        });
    }

    /**
     * Populate the RecyclerView
     * @param powderModelClassList
     */
    public void populateRecyclerView(List<DataItem> powderModelClassList){
        //init the recyclerView Adapter
        recyclerViewAdapter = new RecyclerViewAdapter(powderModelClassList);
        //set the layout Manager
        RecyclerView.LayoutManager mLayoutManager=new GridLayoutManager(getActivity().getApplicationContext(),2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //set the separation  line between items
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        //set the adapter for recyclerView
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


    private void showNetworkError() {
        PromptProvider.get()
                .provide(SnackbarPrompt.class)
                .showNetworkError(new SnackbarParams.Builder(getActivity())
                        .rootView(menDataFormView)
                        .listener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                        .build());
    }

}
