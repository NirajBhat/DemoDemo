package com.nexttools.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nexttools.R;
import com.nexttools.adapter.SubjectsDataAdapter;
import com.nexttools.constants.NlpConstants;
import com.nexttools.model.Cards;
import com.nexttools.model.Tabs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by next on 28/4/17.
 */
public class MathsFragment extends Fragment {
    RecyclerView mRecyclerView;
    ArrayList<Tabs> list = NlpConstants.tabsArrayList;
    List<Cards> cardsList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_maths,container,false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_Maths);
        //Setting of LinearLayoutManager to set the recylerview horizontally
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(manager);

        //iterating form the list matching with ingnore case
        Iterator iterator = list.iterator();
        while (iterator.hasNext())
        {
            Tabs tabs = (Tabs) iterator.next();
            if(tabs.getTitle().equalsIgnoreCase("Maths")){
                cardsList = tabs.getCardInfo();
                Log.i("TAG", "onCreateView Maths: "+cardsList);
            }

        }
        //Calling the adapter class to set the data to view
        SubjectsDataAdapter adapter = new SubjectsDataAdapter(getContext(), cardsList);
        mRecyclerView.setAdapter(adapter);
        return view;
    }
}
