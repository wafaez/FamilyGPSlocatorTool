// Generated code from Butter Knife. Do not modify!
package com.mytracker.familygpstracker.Fragments;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.cardview.widget.CardView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mytracker.familygpstracker.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EmergencyFragment_ViewBinding implements Unbinder {
  private EmergencyFragment target;

  @UiThread
  public EmergencyFragment_ViewBinding(EmergencyFragment target, View source) {
    this.target = target;

    target.cardViewAlertCenter = Utils.findRequiredViewAsType(source, R.id.alertCenterCardView, "field 'cardViewAlertCenter'", CardView.class);
    target.cardViewSendAlert = Utils.findRequiredViewAsType(source, R.id.sendAlertCardView, "field 'cardViewSendAlert'", CardView.class);
    target.cardViewLogout = Utils.findRequiredViewAsType(source, R.id.aboutDevId, "field 'cardViewLogout'", CardView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EmergencyFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.cardViewAlertCenter = null;
    target.cardViewSendAlert = null;
    target.cardViewLogout = null;
  }
}
