package com.mytracker.familygpstracker.Callbacks;


import com.mytracker.familygpstracker.Fragments.RootFragment;

/**
 * Created by Farhan Ijaz on 6/6/14.
 */
public interface OnBackPressListener {

    public boolean onBackPressed();

    public RootFragment getFragment();

}
