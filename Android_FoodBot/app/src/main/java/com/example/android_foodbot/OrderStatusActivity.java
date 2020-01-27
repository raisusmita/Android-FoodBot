package com.example.android_foodbot;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_foodbot.AccountActivity.User;
import com.example.android_foodbot.Common.Common;
import com.example.android_foodbot.Model.Order;
import com.example.android_foodbot.Model.Request;
import com.example.android_foodbot.ViewHolder.OrderViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrderStatusActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Request, OrderViewHolder> adapter;

    FirebaseDatabase database;
    DatabaseReference requests;

//    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        LinearLayout LinearLayoutView = new LinearLayout(this);
        TextView DisplayStringArray = new TextView(this);
        DisplayStringArray.setTextSize(25);
        LinearLayoutView.addView(DisplayStringArray);
        //Firebase
        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Requests");

        recyclerView = (RecyclerView)findViewById(R.id.listOrders);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

//        if(getIntent() ==null)
//        {
//            //Getting current user details
//            String uid;
//            FirebaseAuth auth;
//            auth = FirebaseAuth.getInstance();
//            uid = auth.getCurrentUser().getUid();
//
//            FirebaseDatabase.getInstance().getReference().child("Users").child(uid).addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    User user = dataSnapshot.getValue(User.class);
////                if (user==null){
////                    Toast.makeText(OrderStatusActivity.this, "NUll user", Toast.LENGTH_SHORT).show();
////                }
////                else{
////                    Toast.makeText(OrderStatusActivity.this, "user is "+user.getPhone(), Toast.LENGTH_SHORT).show();
////                }
//
//                    loadOrders(user.getPhone());
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//                    throw databaseError.toException();
//
//                }
//            });
//        }
//        else
//        {
//            loadOrders(getIntent().getStringExtra("userPhone"));
//        }

    }




    private void loadOrders(String phone){
        adapter = new FirebaseRecyclerAdapter<Request, OrderViewHolder>(
                Request.class,
                R.layout.order_layout,
                OrderViewHolder.class,
                requests.orderByChild("name").equalTo(phone)) {
            @Override
            protected void populateViewHolder(OrderViewHolder viewHolder, Request model, int position) {
                viewHolder.txtOrderId.setText(adapter.getRef(position).getKey());
                viewHolder.txtOrderStatus.setText(Common.convertCodeToStatus(model.getStatus()));
                viewHolder.txtOrderPhone.setText(model.getPhone());
                viewHolder.txtOrderTotal.setText(model.getTotal());
            }
        };
        recyclerView.setAdapter(adapter);

//        if (recyclerView != null){
//            recyclerView.setHasFixedSize(true);
//            Toast.makeText(this, "Recycler View is not null ", Toast.LENGTH_SHORT).show();
//
//        }
//        if (recyclerView == null){
////            Log.v("TAG", "RECYCLERVIEW NULL");
//            Toast.makeText(this, "RECYCLERVIEW NULL", Toast.LENGTH_SHORT).show();
//
//        } else if (layoutManager == null){
////            Log.v("TAG", "LAYOUTMANAGER NULL");
//            Toast.makeText(this, "LAYOUTMANAGER NULL", Toast.LENGTH_SHORT).show();
//
//        } else if (adapter == null) {
////            Log.v("TAG", "mFIREADAPTER NULL");
//            Toast.makeText(this, "mFIREADAPTER NULL", Toast.LENGTH_SHORT).show();
//
//        }else if (adapter != null) {
////            Log.v("TAG", "mFIREADAPTER NULL");
//            Toast.makeText(this, "FIREADAPTER IS NOT NULL" +adapter.getItemCount(), Toast.LENGTH_SHORT).show();
//
//        }


    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        //Getting current user details
        String uid;
        FirebaseAuth auth;
        auth = FirebaseAuth.getInstance();
        uid = auth.getCurrentUser().getUid();

        FirebaseDatabase.getInstance().getReference().child("Users").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
//                if (user==null){
//                    Toast.makeText(OrderStatusActivity.this, "NUll user", Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    Toast.makeText(OrderStatusActivity.this, "user is "+user.getPhone(), Toast.LENGTH_SHORT).show();
//                }

                loadOrders(user.getPhone());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();

            }
        });
    }



}


