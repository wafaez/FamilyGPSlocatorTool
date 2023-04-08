package com.mytracker.familygpstracker.Activities;

import android.app.Notification;
import android.app.NotificationChannel;
import android.os.Build;
import androidx.core.app.NotificationManagerCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mytracker.familygpstracker.Adapters.HelpAlertsAdapter;
import com.mytracker.familygpstracker.Models.CreateUser;
import com.mytracker.familygpstracker.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlertCenterActivity extends AppCompatActivity {

    @BindView(R.id.alertRecyclerView) RecyclerView recyclerView;

    @BindView(R.id.toolbarAlertCenter) Toolbar toolbar;
    public Notification.Builder builder123;
    HelpAlertsAdapter recycleradapter;
    ArrayList<CreateUser> myList;
    FirebaseAuth auth;
    FirebaseUser user;
    String memberUserId;
    CreateUser createUser;
    DatabaseReference reference,userReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_center);
        ButterKnife.bind(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        toolbar.setTitle("Alert Center");
        setSupportActionBar(toolbar);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid()).child("HelpAlerts");
        userReference = FirebaseDatabase.getInstance().getReference().child("Users");
        myList = new ArrayList<>();

        recycleradapter = new HelpAlertsAdapter(myList,AlertCenterActivity.this);
        recyclerView.setAdapter(recycleradapter);


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }


        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                myList.clear();


                if(dataSnapshot.exists() && dataSnapshot.getChildrenCount()>0)
                {
                    for(DataSnapshot dss: dataSnapshot.getChildren())
                    {
                        memberUserId = dss.child("circlememberid").getValue().toString();

                        userReference.child(memberUserId).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                myList.clear();
                                createUser = dataSnapshot.getValue(CreateUser.class);
                                myList.add(createUser);
                                recycleradapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    }

                    showNotits(45002);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AlertCenterActivity.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void showNotits(int download_id) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("channelid2", String.valueOf(download_id), android.app.NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("This is description");
            android.app.NotificationManager notificationManager = (android.app.NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);

            builder123 = new Notification.Builder(getApplicationContext(), notificationChannel.getId());
            builder123.setSmallIcon(R.mipmap.ic_launcher)
                    .setContentText("ALERT!!! Someone may need your help. Check your alert center.")
                    .setWhen(System.currentTimeMillis())
                    .setContentTitle("Family Alert");



            NotificationManagerCompat nmc = NotificationManagerCompat.from(getApplicationContext());
            nmc.notify(download_id, builder123.build());
        } else {

            builder123 = new Notification.Builder(getApplicationContext());
            builder123.setSmallIcon(R.mipmap.ic_launcher)
                    .setWhen(System.currentTimeMillis())
                    .setContentText("ALERT!!! Someone may need your help. Check your alert center.")
                    .setContentTitle("Family Alert");

            NotificationManagerCompat nmc = NotificationManagerCompat.from(getApplicationContext());
            nmc.notify(download_id, builder123.build());
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}
