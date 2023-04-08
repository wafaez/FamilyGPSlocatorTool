// Generated code from Butter Knife. Do not modify!
package com.mytracker.familygpstracker.Activities;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mytracker.familygpstracker.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AlertCenterActivity_ViewBinding implements Unbinder {
  private AlertCenterActivity target;

  @UiThread
  public AlertCenterActivity_ViewBinding(AlertCenterActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AlertCenterActivity_ViewBinding(AlertCenterActivity target, View source) {
    this.target = target;

    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.alertRecyclerView, "field 'recyclerView'", RecyclerView.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbarAlertCenter, "field 'toolbar'", Toolbar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AlertCenterActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerView = null;
    target.toolbar = null;
  }
}
