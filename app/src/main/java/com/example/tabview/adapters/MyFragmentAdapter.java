package com.example.tabview.adapters;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.tabview.fragments.CreateEntryFragment;
import com.example.tabview.fragments.ViewFragment;

public class MyFragmentAdapter extends FragmentPagerAdapter {
    public MyFragmentAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new CreateEntryFragment();
            case 1: return new ViewFragment();
        }
        return new CreateEntryFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0: return "Create Entry";
            case 1: return "View";
        }
        return "Create Entry";
    }
}
