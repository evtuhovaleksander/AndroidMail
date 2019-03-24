package com.example.dz1java;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.provider.Contacts.SettingsColumns.KEY;


public class RecycleViewFragment extends Fragment {

    public MainActivity mainActivity;
    //public Integer upperBound = 5;
    NumDataSource dataSource;// = new NumDataSource();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recycle_view_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        int gridCount = 1;
        if (mainActivity.isVerticalScreenOrientation()) {
            gridCount = 3;
        } else {
            gridCount = 4;
        }



            //dataSource.generateData(upperBound);




        final RecyclerView recyclerView = getView().findViewById(R.id.list_view);
        recyclerView.setLayoutManager(new GridLayoutManager(mainActivity, gridCount));
        recyclerView.setAdapter(new ListAdapter(dataSource, mainActivity));

        Button addButton = getView().findViewById(R.id.add_num_but);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataSource.addNumber();
                recyclerView.getAdapter().notifyDataSetChanged();
            }
        });

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(mainActivity.DATA_KEY, dataSource.numArray.size());
        super.onSaveInstanceState(outState);
    }


    class ListItemViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ListItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
        }
    }

    class ListAdapter extends RecyclerView.Adapter<ListItemViewHolder> {
        NumDataSource dataSource;
        NumListDelegate delegate;

        public ListAdapter(NumDataSource dataSource, NumListDelegate delegate) {
            this.dataSource = dataSource;
            this.delegate = delegate;
        }

        @NonNull
        @Override
        public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflatter = LayoutInflater.from(viewGroup.getContext());
            View view = inflatter.inflate(R.layout.num_constr_list_item,viewGroup, false);
            return new ListItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ListItemViewHolder listItemViewHolder, final int i) {
            int val = dataSource.numArray.get(i);
            if(val % 2 == 0) {
                listItemViewHolder.textView.setBackgroundColor(Color.RED);
            } else {
                listItemViewHolder.textView.setBackgroundColor(Color.BLUE);
            }
            listItemViewHolder.textView.setText(dataSource.numArray.get(i).toString());

            listItemViewHolder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    delegate.listItemPressed(i);
                }
            });
        }

        @Override
        public int getItemCount() {
            return dataSource.numArray.size();
        }
    }
}
