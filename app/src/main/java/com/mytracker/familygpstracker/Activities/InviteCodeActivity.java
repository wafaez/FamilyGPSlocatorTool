package com.mytracker.familygpstracker.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mytracker.familygpstracker.Models.CreateUser;
import com.mytracker.familygpstracker.R;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InviteCodeActivity extends AppCompatActivity {


    @BindView(R.id.textView4) TextView textViewCode;
    @BindView(R.id.toolbarInviteCode) Toolbar toolbar;

    String name,email,password,code;
    Uri imageUri;

    DatabaseReference reference;
    FirebaseAuth auth;
    FirebaseUser user;
    ProgressDialog dialog;
    StorageReference firebaseStorageReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_code);
        ButterKnife.bind(this);
        setToolbar();
        dialog = new ProgressDialog(this);


        getData();
        generateCode();
        initFirebase();



    }




    private void setToolbar()
    {
        toolbar.setTitle("Invite Code");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    private void getData()
    {
        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        password = getIntent().getStringExtra("password");
        imageUri = Uri.parse(getIntent().getStringExtra("imageUri"));
    }

    public void generateCode()
    {
        Random rnd = new Random();
        int n = 100000 + rnd.nextInt(900000);


         code  = String.valueOf(n);
        textViewCode.setText(""+code);

    }

    private void initFirebase()
    {
        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("Users");
        firebaseStorageReference = FirebaseStorage.getInstance().getReference().child("Profile_images");
    }



    public void registerFirebase(View v)
    {
        register();

    }



    public void register() {

        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Creating new Profile. Please wait");
        dialog.setCancelable(false);
        dialog.show();

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            user = auth.getCurrentUser();
                            CreateUser createUser = new CreateUser(name, email, password, "na",
                                    code, user.getUid(), "false", "na", "na", "defaultimage");

                            reference.child(user.getUid()).setValue(createUser)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                StorageReference filePath = firebaseStorageReference.child(user.getUid() + ".jpg");
                                                filePath.putFile(imageUri)
                                                        .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                                                                if (task.isSuccessful()) {

                                                                    Task<Uri> result = task.getResult().getStorage().getDownloadUrl();
                                                                    result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                        @Override
                                                                        public void onSuccess(Uri uri) {

                                                                            reference.child(user.getUid()).child("profile_image").setValue(uri.toString())
                                                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                        @Override
                                                                                        public void onComplete(@NonNull Task<Void> task) {
                                                                                            if (task.isSuccessful()) {
                                                                                                dialog.dismiss();
                                                                                                Intent intent = new Intent(InviteCodeActivity.this, HomeScreenActivity.class);
                                                                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                                                                startActivity(intent);
                                                                                                finish();

                                                                                            }

                                                                                        }
                                                                                    });
                                                                        }
                                                                    });


                                                                } else {
                                                                    dialog.dismiss();
                                                                    Toast.makeText(getApplicationContext(), "Could not upload user image", Toast.LENGTH_SHORT).show();
                                                                }


                                                            }
                                                        });


                                            }
                                        }
                                    });
                        } else {
                            dialog.dismiss();
                            Toast.makeText(getApplicationContext(), "This email address is already registered. Please go back and change your email address", Toast.LENGTH_SHORT).show();
                        }

                    }
                });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

}
