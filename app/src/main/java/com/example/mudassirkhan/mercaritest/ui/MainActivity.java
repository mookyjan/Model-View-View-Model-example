package com.example.mudassirkhan.mercaritest.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import com.example.mudassirkhan.mercaritest.R;
import com.example.mudassirkhan.mercaritest.adapter.PagerAdapter;


public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setUpViewPager();
    }

    public void initViews() {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.txtMenFragment)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.txtAllFragment)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.txtWomenFragment)));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

    }

    public void setUpViewPager() {

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        //set the current AllFragment by default
        mViewPager.setCurrentItem(1);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                mViewPager.setCurrentItem(tab.getPosition());

                if (tab.getPosition() == 0) {

                    mViewPager.setCurrentItem(0);

                } else if (tab.getPosition() == 1) {

                    mViewPager.setCurrentItem(1);

                } else if (tab.getPosition() == 2) {

                    mViewPager.setCurrentItem(2);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


}
