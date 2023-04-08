// Generated code from Butter Knife. Do not modify!
package com.mytracker.familygpstracker.Activities;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.mytracker.familygpstracker.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeScreenActivity_ViewBinding implements Unbinder {
  private HomeScreenActivity target;

  @UiThread
  public HomeScreenActivity_ViewBinding(HomeScreenActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HomeScreenActivity_ViewBinding(HomeScreenActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.vp_horizontal_ntb, "field 'viewPager'", AHBottomNavigationViewPager.class);
    target.bottomNavigationView = Utils.findRequiredViewAsType(source, R.id.bottomNavigation, "field 'bottomNavigationView'", AHBottomNavigation.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeScreenActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.viewPager = null;
    target.bottomNavigationView = null;
  }
}
