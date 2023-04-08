// Generated code from Butter Knife. Do not modify!
package com.mytracker.familygpstracker.Fragments;

import android.view.View;
import android.widget.Button;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.goodiebag.pinview.Pinview;
import com.mytracker.familygpstracker.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class JoinFragment_ViewBinding implements Unbinder {
  private JoinFragment target;

  @UiThread
  public JoinFragment_ViewBinding(JoinFragment target, View source) {
    this.target = target;

    target.pinView = Utils.findRequiredViewAsType(source, R.id.mypinview, "field 'pinView'", Pinview.class);
    target.joinButton = Utils.findRequiredViewAsType(source, R.id.joinBtn, "field 'joinButton'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    JoinFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.pinView = null;
    target.joinButton = null;
  }
}
