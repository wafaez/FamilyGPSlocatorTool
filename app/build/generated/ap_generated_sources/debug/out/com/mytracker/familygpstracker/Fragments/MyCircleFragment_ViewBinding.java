// Generated code from Butter Knife. Do not modify!
package com.mytracker.familygpstracker.Fragments;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mytracker.familygpstracker.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyCircleFragment_ViewBinding implements Unbinder {
  private MyCircleFragment target;

  @UiThread
  public MyCircleFragment_ViewBinding(MyCircleFragment target, View source) {
    this.target = target;

    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recyclerview, "field 'recyclerView'", RecyclerView.class);
    target.textViewNoFound = Utils.findRequiredViewAsType(source, R.id.textViewNoFound, "field 'textViewNoFound'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyCircleFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerView = null;
    target.textViewNoFound = null;
  }
}
