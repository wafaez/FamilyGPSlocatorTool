// Generated code from Butter Knife. Do not modify!
package com.mytracker.familygpstracker.Fragments;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mytracker.familygpstracker.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class InviteFriendsFragment_ViewBinding implements Unbinder {
  private InviteFriendsFragment target;

  @UiThread
  public InviteFriendsFragment_ViewBinding(InviteFriendsFragment target, View source) {
    this.target = target;

    target.textViewCode = Utils.findRequiredViewAsType(source, R.id.textView4, "field 'textViewCode'", TextView.class);
    target.shareButton = Utils.findRequiredViewAsType(source, R.id.shareButton, "field 'shareButton'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    InviteFriendsFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.textViewCode = null;
    target.shareButton = null;
  }
}
