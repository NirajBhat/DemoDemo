package com.nexttools.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.nexttools.fragments.ChemistryFragment;
import com.nexttools.fragments.EnglishFragment;
import com.nexttools.fragments.MathsFragment;
import com.nexttools.fragments.PrimaryFragment;
import com.nexttools.fragments.SocialFragment;

import java.util.ArrayList;

/**
 * Created by next on 28/4/17.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    int tabCount;

    public PagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount= tabCount;

    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
               return new MathsFragment();
            case 1:
                return new ChemistryFragment();
            case 2:
                return new SocialFragment();
            case 3:
                return new EnglishFragment();
            case 4:
                return new PrimaryFragment();
            default:
            return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }

}
