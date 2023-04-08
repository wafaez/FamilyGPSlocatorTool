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

public class InviteCodeActivity_ViewBinding implements Unbinder {
  private InviteCodeActivity target;

  @UiThread
  public InviteCodeActivity_ViewBinding(InviteCodeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public InviteCodeActivity_ViewBinding(InviteCodeActivity target, View source) {
    this.target = target;

    target.textViewCode = Utils.findRequiredViewAsType(source, R.id.textView4, "field 'textViewCode'", TextView.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbarInviteCode, "field 'toolbar'", Toolbar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    InviteCodeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.textViewCode = null;
    target.toolbar = null;
  }
}
