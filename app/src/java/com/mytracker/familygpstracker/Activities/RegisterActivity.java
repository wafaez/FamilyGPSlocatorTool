package com.mytracker.familygpstracker.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;


import com.mytracker.familygpstracker.R;
import com.mytracker.familygpstracker.Utils.Utils;
import com.theartofdev.edmodo.cropper.CropImage;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.emailRegister)
    EditText editTextEmail;
    @BindView(R.id.passwordRegister) EditText editTextPassword;
    @BindView(R.id.nameRegister) EditText editTextName;

    @BindView(R.id.imageUpload) CircleImageView imageView;
    @BindView(R.id.toolbarRegister) Toolbar toolbar;



    Uri resultUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        setToolbar();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }





    }

    private void setToolbar()
    {
        toolbar.setTitle("Register");
        setSupportActionBar(toolbar);
    }


    public void pickPhoto(View v)
    {
        Intent pickPhotoIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhotoIntent, 12);
    }



    public void register(View v)
    {
        registerFinally();

    }


    public void registerFinally()
    {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String name = editTextName.getText().toString();

        if(email.isEmpty() || !Utils.isValid(email))
        {
            Toast.makeText(this,"Email is not valid",Toast.LENGTH_LONG).show();
            return;
        }

        if(password.isEmpty() || password.length()<6)
        {
            Toast.makeText(this,"Password must be 6 characters long.",Toast.LENGTH_LONG).show();
            return;
        }

        if(name.equals(""))
        {
            Toast.makeText(this,"Please input your name.",Toast.LENGTH_LONG).show();
            return;
        }

        if(resultUri==null)
        {
            Toast.makeText(this,"Please pick an image from gallery.",Toast.LENGTH_LONG).show();
            return;
        }

        Intent intent = new Intent(RegisterActivity.this,InviteCodeActivity.class);
        intent.putExtra("email",email);
        intent.putExtra("password",password);
        intent.putExtra("name",name);
        intent.putExtra("imageUri",resultUri.toString());
        startActivity(intent);
    }


    public void openLogin(View v)
    {
            Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
            startActivity(intent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode == 12 && resultCode==RESULT_OK && data!=null)
        {
            Uri uri = data.getData();
            if(uri!=null)
            {
                CropImage.activity(uri)
                        .start(this);
            }

        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                resultUri = result.getUri();
                if(resultUri!=null)
                {
                    imageView.setImageURI(resultUri);
                }


            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();

            }
        }

    }
}
