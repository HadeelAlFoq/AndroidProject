package com.example.hadeel.samples;

/**
 * Created by hadeel on 9/10/2017.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hadeel on 8/30/2017.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> mfragmentList=new ArrayList<>();
    private final List<String> mfragmentTitleList=new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }




    public android.support.v4.app.Fragment getItem(int position) {

        return mfragmentList.get(position);

    }

    @Override
    public int getCount() {
        return mfragmentList.size();
    }

    public CharSequence getPageTitle(int position){

        return mfragmentTitleList.get(position);
    }
    public void addFragment(Fragment fragment, String title){
        mfragmentList.add(fragment);
        mfragmentTitleList.add(title);
    }

}
