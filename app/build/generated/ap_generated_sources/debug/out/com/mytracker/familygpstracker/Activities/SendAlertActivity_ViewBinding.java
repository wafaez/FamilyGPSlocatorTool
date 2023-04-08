// Generated code from Butter Knife. Do not modify!
package com.mytracker.familygpstracker.Activities;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mytracker.familygpstracker.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SendAlertActivity_ViewBinding implements Unbinder {
  private SendAlertActivity target;

  @UiThread
  public SendAlertActivity_ViewBinding(SendAlertActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SendAlertActivity_ViewBinding(SendAlertActivity target, View source) {
    this.target = target;

    target.t1_CounterTxt = Utils.findRequiredViewAsType(source, R.id.textView9, "field 't1_CounterTxt'", TextView.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SendAlertActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.t1_CounterTxt = null;
    target.toolbar = null;
  }
}
