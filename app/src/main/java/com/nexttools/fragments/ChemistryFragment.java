package com.nexttools.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nexttools.R;
import com.nexttools.activity.MainActivity;
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
public class ChemistryFragment extends Fragment {
    RecyclerView mRecyclerView;
    ArrayList<Tabs> cards = NlpConstants.tabsArrayList;
    List<Cards> cardLIst;
    private static final String TAG="";

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MainActivity mainActivity;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_chemistry,container,false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_Chem );

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);



            Iterator it = cards.iterator();
        while (it.hasNext())
        {
            Tabs object = (Tabs) it.next();
            if(object.getTitle().equalsIgnoreCase("chemistry"))
            {
                cardLIst = object.getCardInfo();
            }


        }
        SubjectsDataAdapter adapter = new SubjectsDataAdapter(getContext(), cardLIst);
        mRecyclerView.setAdapter(adapter);
        return view;
    }
}
