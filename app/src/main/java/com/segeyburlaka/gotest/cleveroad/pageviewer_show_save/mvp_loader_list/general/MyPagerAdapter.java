package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.general;

/**
 * Created by Operator on 28.02.2017.
 */

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.favorite.FavoriteFragment;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.search.SearchFragment;

/**
 * Created by Belal on 2/3/2016.
 */
//Extending FragmentStatePagerAdapter
public class MyPagerAdapter extends FragmentStatePagerAdapter {

    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] { "Search", "Favorite" };

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
              return   SearchFragment.newInstance(position + 1);

            case 1:
              return   FavoriteFragment.newInstance(position + 1);

            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}





