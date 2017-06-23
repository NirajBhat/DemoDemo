package com.nexttools.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
public class PrimaryFragment extends Fragment {
    RecyclerView mRecyclerView;
    ArrayList<Tabs> list = NlpConstants.tabsArrayList;
    List<Cards>  cardsList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_primary,container,false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_Prim);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            Tabs tabs = (Tabs) iterator.next();
            if(tabs.getTitle().equalsIgnoreCase("Primary")){
                cardsList = tabs.getCardInfo();
            }
        }
        SubjectsDataAdapter adapter = new SubjectsDataAdapter(getContext(), cardsList);
        mRecyclerView.setAdapter(adapter);
        return view;
    }
}
