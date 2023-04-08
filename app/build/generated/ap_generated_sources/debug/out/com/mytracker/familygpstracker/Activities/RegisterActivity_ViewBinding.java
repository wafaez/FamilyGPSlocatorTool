// Generated code from Butter Knife. Do not modify!
package com.mytracker.familygpstracker.Activities;

import android.view.View;
import android.widget.EditText;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.Toolbar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mytracker.familygpstracker.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RegisterActivity_ViewBinding implements Unbinder {
  private RegisterActivity target;

  @UiThread
  public RegisterActivity_ViewBinding(RegisterActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RegisterActivity_ViewBinding(RegisterActivity target, View source) {
    this.target = target;

    target.editTextEmail = Utils.findRequiredViewAsType(source, R.id.emailRegister, "field 'editTextEmail'", EditText.class);
    target.editTextPassword = Utils.findRequiredViewAsType(source, R.id.passwordRegister, "field 'editTextPassword'", EditText.class);
    target.editTextName = Utils.findRequiredViewAsType(source, R.id.nameRegister, "field 'editTextName'", EditText.class);
    target.imageView = Utils.findRequiredViewAsType(source, R.id.imageUpload, "field 'imageView'", CircleImageView.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbarRegister, "field 'toolbar'", Toolbar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RegisterActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.editTextEmail = null;
    target.editTextPassword = null;
    target.editTextName = null;
    target.imageView = null;
    target.toolbar = null;
  }
}
