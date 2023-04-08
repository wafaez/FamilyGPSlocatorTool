package com.mytracker.familygpstracker.Adapters;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import android.view.ViewGroup;


import com.mytracker.familygpstracker.Fragments.EmergencyFragment;
import com.mytracker.familygpstracker.Fragments.HomeFragment;
import com.mytracker.familygpstracker.Fragments.InviteFriendsFragment;
import com.mytracker.familygpstracker.Fragments.JoinFragment;
import com.mytracker.familygpstracker.Fragments.MyCircleFragment;

import java.util.ArrayList;


public class MyPagerAdapter extends FragmentPagerAdapter
{

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private Fragment currentFragment;
    private Context mCxt;

    public MyPagerAdapter(FragmentManager fm, Context context) {
        super(fm);

        mCxt = context;

        fragments.clear();

        HomeFragment homeFragmentFragment = new HomeFragment();
        homeFragmentFragment.setTitle("Home");

        fragments.add(homeFragmentFragment);

        JoinFragment joinFragment = new JoinFragment();
        joinFragment.setTitle("Join Circle");

        fragments.add(joinFragment);


        MyCircleFragment myCircleFragment = new MyCircleFragment();
        myCircleFragment.setTitle("My Circle");
        fragments.add(myCircleFragment);

        InviteFriendsFragment inviteFriendsFragment = new InviteFriendsFragment();
        inviteFriendsFragment.setTitle("Invite Friends");

        fragments.add(inviteFriendsFragment);

        EmergencyFragment emergencyFragment = new EmergencyFragment();
        emergencyFragment.setTitle("Emergency Alerts");
        fragments.add(emergencyFragment);


    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (getCurrentFragment() != object) {
            currentFragment = ((Fragment) object);
        }
        super.setPrimaryItem(container, position, object);

    }


    public Fragment getCurrentFragment() {
        return currentFragment;
    }


}
