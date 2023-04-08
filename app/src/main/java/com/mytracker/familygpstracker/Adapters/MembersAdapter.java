package com.mytracker.familygpstracker.Adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.mytracker.familygpstracker.Fragments.MyCircleFragment;
import com.mytracker.familygpstracker.Models.CreateUser;
import com.mytracker.familygpstracker.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


public class MembersAdapter extends RecyclerView.Adapter<MembersAdapter.MembersViewHolder>
{

    ArrayList<CreateUser> nameList;
    MyCircleFragment myCircleFragment;
    Context context;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference mReference,mJoinedRef;


    public MembersAdapter(ArrayList<CreateUser> nameList, MyCircleFragment myCircleFragment,Context context)
    {
        this.nameList = nameList;
        this.context=context;
        this.myCircleFragment = myCircleFragment;
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mReference = FirebaseDatabase.getInstance().getReference().child("Users").child(mUser.getUid()).child("CircleMembers");
        mJoinedRef = FirebaseDatabase.getInstance().getReference().child("Users");
    }

    @Override
    public int getItemCount()
    {
        return nameList.size();
    }

    @Override
    public MembersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout,parent,false);
        MembersViewHolder membersViewHolder = new MembersViewHolder(view);
        return membersViewHolder;
    }

    @Override
    public void onBindViewHolder(MembersViewHolder holder, int position) {

        CreateUser addCircle = nameList.get(position);
        holder.name_txt.setText(addCircle.name);
        Picasso.get().load(addCircle.profile_image).placeholder(R.drawable.defaultprofile).into(holder.circleImageView);

        if(addCircle.issharing.equals("false"))
        {
            holder.i1.setImageResource(R.drawable.redoffline);
        }
        else if(addCircle.issharing.equals("true"))
        {
            holder.i1.setImageResource(R.drawable.green);
        }

    }

    public  class MembersViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener,
            MenuItem.OnMenuItemClickListener

    {
        @BindView(R.id.item_title) TextView name_txt;
        @BindView(R.id.item_image) ImageView i1;
        @BindView(R.id.item_imageprofile) CircleImageView circleImageView;


        public MembersViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnCreateContextMenuListener(this);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    myCircleFragment.openLiveActivity(position,nameList);
                }
            });
        }



        @Override
        public boolean onMenuItemClick(MenuItem item) {

            final CreateUser addCircle = nameList.get(getAdapterPosition());

            mReference.child(addCircle.userid).removeValue()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    mJoinedRef.child(addCircle.userid).child("CircleMembers").child(mUser.getUid()).removeValue()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                        if(task.isSuccessful())
                                                        {
                                                            nameList.remove(addCircle);
                                                            notifyDataSetChanged();
                                                            notifyItemRemoved(getAdapterPosition());

                                                            Toast.makeText(context,"User removed from circle.",Toast.LENGTH_SHORT).show();

                                                        }
                                                }
                                            });
                                }
                        }
                    });


            return false;
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuItem myActionItem = menu.add("Remove");
            myActionItem.setOnMenuItemClickListener(this);
        }
    }


}
