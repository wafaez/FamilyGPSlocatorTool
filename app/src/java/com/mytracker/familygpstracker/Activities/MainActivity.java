package com.mytracker.familygpstracker.Activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mytracker.familygpstracker.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    ProgressDialog dialog;


      @BindView(R.id.emailLogin) EditText editTextEmail;
    @BindView(R.id.passwordLogin) EditText editTextPassword;


    String[] permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE,
    Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        if(user == null)
        {
            setContentView(R.layout.activity_main);
            ButterKnife.bind(this);
            dialog = new ProgressDialog(this);
            checkPermissions();


            SharedPreferences.Editor editor = getSharedPreferences("FirstTimeShared", MODE_PRIVATE).edit();
            editor.putBoolean("firstTimeOk", true);
            editor.apply();


        }
        else
        {
            Intent myIntent = new Intent(MainActivity.this, HomeScreenActivity.class);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(myIntent);
            finish();

        }
    }



    public void getStarted_click(View v)
    {
        Intent myintent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(myintent);

    }

    public void login(View v)
    {

        if(!editTextEmail.equals("") && !editTextPassword.equals("") && editTextPassword.length()>=6)
        {
            dialog.setMessage("Please wait!");
            dialog.show();


            auth.signInWithEmailAndPassword(editTextEmail.getText().toString(),editTextPassword.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                dialog.dismiss();

                                Intent intent = new Intent(MainActivity.this, HomeScreenActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();

                            }
                            else
                            {
                                Toast.makeText(MainActivity.this,"Incorrect email/password combination.",Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                            }
                        }
                    });
        }
        else
        {
            dialog.dismiss();
            Toast.makeText(this,"Password must be 6 characters long",Toast.LENGTH_LONG).show();
        }

    }


    public void forgotPassword(View v)
    {
        final EditText taskEditText = new EditText(this);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Forgot Password?")
                .setMessage("Enter your email address?")
                .setView(taskEditText)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String task = String.valueOf(taskEditText.getText());

                        if(task.equals(""))
                        {
                            Toast.makeText(getApplicationContext(),"Email cannot be empty",Toast.LENGTH_LONG).show();
                        }
                        else {
                            FirebaseAuth.getInstance().sendPasswordResetEmail(task)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(MainActivity.this,"Please check your email.",Toast.LENGTH_LONG).show();
                                            }
                                            else {
                                                Toast.makeText(MainActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                        }


                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }


    private boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 100);
            return false;
        }
        return true;
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 100) {
            for (int i = 0; i < permissions.length; i++) {
                //   String permission = permissions[i];
                int grantResult = grantResults[i];

                if (grantResult == PackageManager.PERMISSION_GRANTED) {

                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission
                                .READ_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
                    }
                }

            }

        }

    }

}
