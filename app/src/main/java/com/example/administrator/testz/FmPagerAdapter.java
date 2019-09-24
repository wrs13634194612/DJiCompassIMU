package com.example.administrator.testz;


import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * @desc:
 * @author: gjzhang
 * @createTime: 2015-8-13 上午9:24:55
 * @version: 1.0
 */
public class FmPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;

    public FmPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
        // TODO Auto-generated constructor stub
    }

    @Override
    public Fragment getItem(int arg0) {
        // TODO Auto-generated method stub
        return fragmentList.get(arg0);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return fragmentList.size();
    }

}
