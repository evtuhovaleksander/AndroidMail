package com.example.dz1java;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ItemFragment extends Fragment {

    public Integer curVal = 0;
    private Button fragment_button;
    private String STORED_NUM = "storedNum";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            curVal = savedInstanceState.getInt(STORED_NUM, 0);
        }
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.item_fragment, container, false);
    }

    @Override
    public void onStart() {

        fragment_button = getView().findViewById(R.id.item_fragment_button);
        fragment_button.setText(curVal.toString());
        if(curVal % 2 == 0) {
            fragment_button.setBackgroundColor(Color.RED);
        } else {
            fragment_button.setBackgroundColor(Color.BLUE);
        }
        super.onStart();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(STORED_NUM, curVal);
        super.onSaveInstanceState(outState);
    }
}
