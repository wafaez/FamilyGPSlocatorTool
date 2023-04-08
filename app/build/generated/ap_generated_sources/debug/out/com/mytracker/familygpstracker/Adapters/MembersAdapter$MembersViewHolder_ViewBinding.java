// Generated code from Butter Knife. Do not modify!
package com.mytracker.familygpstracker.Adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mytracker.familygpstracker.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MembersAdapter$MembersViewHolder_ViewBinding implements Unbinder {
  private MembersAdapter.MembersViewHolder target;

  @UiThread
  public MembersAdapter$MembersViewHolder_ViewBinding(MembersAdapter.MembersViewHolder target,
      View source) {
    this.target = target;

    target.name_txt = Utils.findRequiredViewAsType(source, R.id.item_title, "field 'name_txt'", TextView.class);
    target.i1 = Utils.findRequiredViewAsType(source, R.id.item_image, "field 'i1'", ImageView.class);
    target.circleImageView = Utils.findRequiredViewAsType(source, R.id.item_imageprofile, "field 'circleImageView'", CircleImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MembersAdapter.MembersViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.name_txt = null;
    target.i1 = null;
    target.circleImageView = null;
  }
}
