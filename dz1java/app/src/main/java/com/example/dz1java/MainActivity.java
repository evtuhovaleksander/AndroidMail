package com.example.dz1java;

import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity implements NumListDelegate {
    public  static final String DATA_KEY = "upperBound";
    private static final String FRAGMENT_KEY = "fragmentKey";
    private static final String RECYCLE_FRAGMENT_TAG = "recycle_fragmentTag";
    private static final String ITEM_FRAGMENT_TAG = "item_fragmentTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            RecycleViewFragment recycleViewFragment = new RecycleViewFragment();
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
        Integer curVal = position;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ItemFragment itemFragment = new ItemFragment();
        itemFragment.curVal = curVal;
        fragmentTransaction.replace(R.id.fragment_container,itemFragment, ITEM_FRAGMENT_TAG);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public Boolean isVertical() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }
}