package com.mytracker.familygpstracker.Fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mytracker.familygpstracker.Activities.AlertCenterActivity;
import com.mytracker.familygpstracker.Activities.MainActivity;
import com.mytracker.familygpstracker.Activities.SendAlertActivity;
import com.mytracker.familygpstracker.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmergencyFragment extends RootFragment
{
   @BindView(R.id.alertCenterCardView) CardView cardViewAlertCenter;
   @BindView(R.id.sendAlertCardView) CardView cardViewSendAlert;
   @BindView(R.id.aboutDevId) CardView cardViewLogout;


   FirebaseAuth auth;
   FirebaseUser firebaseUser;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  =  inflater.inflate(R.layout.fragment_emergency, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

        auth = FirebaseAuth.getInstance();

        firebaseUser = auth.getCurrentUser();



        cardViewAlertCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getContext(), AlertCenterActivity.class);
                startActivity(intent);



            }
        });

        cardViewSendAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getContext(), SendAlertActivity.class);
                startActivity(intent);

            }
        });


        cardViewLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }
}
