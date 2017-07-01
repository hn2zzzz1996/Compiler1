package com.xpf.me.sms.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.LinkedList;

/**
 * Created by xgo on 10/30/15.
 */
public class FragmentAdapter extends FragmentPagerAdapter {
    private LinkedList<Fragment> fragments;

    public FragmentAdapter(FragmentManager fm, LinkedList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int n) {
        return this.fragments.get(n);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }
}

