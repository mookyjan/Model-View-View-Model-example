package com.example.mudassirkhan.mercaritest.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;

import com.example.mudassirkhan.mercaritest.R;
import com.example.mudassirkhan.mercaritest.ui.AllFragment;
import com.example.mudassirkhan.mercaritest.ui.MenFragment;
import com.example.mudassirkhan.mercaritest.ui.WomenFragment;

/**
 * Created by Mudassir Khan on 2/19/2018.
 */

public class PagerAdapter extends FragmentStatePagerAdapter{

    //define a variable for the numbers of tabs
    int numTabs;

    //constructor of PagerAdapter
    public PagerAdapter(FragmentManager fragmentManager,int numTabs){
        super(fragmentManager);
        this.numTabs=numTabs;
    }
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
            MenFragment menFragment=new MenFragment();
            return menFragment;
            case 1:
                AllFragment allFragment=new AllFragment();
                return allFragment;
            case 2:
                WomenFragment womenFragment=new WomenFragment();
                return womenFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numTabs;
    }

}
