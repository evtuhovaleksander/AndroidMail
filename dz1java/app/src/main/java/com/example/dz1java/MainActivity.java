package com.example.dz1java;

import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity implements NumListDelegate {
    public  final String DATA_KEY = "upperBound";
    private final String FRAGMENT_KEY = "fragmentKey";
    private final String RECYCLE_FRAGMENT_TAG = "recycle_fragmentTag";
    private final String ITEM_FRAGMENT_TAG = "item_fragmentTag";
    RecycleViewFragment recycleViewFragment;
    Integer upperBound = 5;
    NumDataSource dataSource = new NumDataSource();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycleViewFragment = new RecycleViewFragment();
        if (savedInstanceState != null) {
            upperBound = savedInstanceState.getInt(DATA_KEY, 5);
        }
        dataSource.generateData(upperBound);
        recycleViewFragment.dataSource = dataSource;
        recycleViewFragment.mainActivity = this;
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.fragment_container, recycleViewFragment, RECYCLE_FRAGMENT_TAG).
                    commit();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void listItemPressed(int position) {
        //System.out.println(dataSource.numArray.get(position));
        Integer curVal = recycleViewFragment.dataSource.numArray.get(position);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ItemFragment itemFragment = new ItemFragment();
        itemFragment.curVal = curVal;
        fragmentTransaction.replace(R.id.fragment_container,itemFragment, ITEM_FRAGMENT_TAG);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public Boolean isVerticalScreenOrientation(){
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(DATA_KEY, recycleViewFragment.dataSource.numArray.size());
    }
}