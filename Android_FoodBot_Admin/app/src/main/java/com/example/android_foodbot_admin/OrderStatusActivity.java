package com.example.android_foodbot_admin;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.android_foodbot_admin.Common.Common;
import com.example.android_foodbot_admin.Interface.ItemClickListener;
import com.example.android_foodbot_admin.Model.Request;
import com.example.android_foodbot_admin.ViewHolder.OrderViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jaredrummler.materialspinner.MaterialSpinner;

public class OrderStatusActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;


    FirebaseRecyclerAdapter<Request, OrderViewHolder> adapter;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference requestsReference;

    MaterialSpinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);


        //Firebase
        firebaseDatabase = FirebaseDatabase.getInstance();
        requestsReference = firebaseDatabase.getReference("Requests");

        //Init
        recyclerView = (RecyclerView)findViewById(R.id.listOrders);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadOrders();
    }

    private void loadOrders() {
        adapter = new FirebaseRecyclerAdapter<Request, OrderViewHolder>(
                Request.class,
                R.layout.order_layout,
                OrderViewHolder.class,
                requestsReference

        ) {
            @Override
            protected void populateViewHolder(OrderViewHolder viewHolder, Request model, final int position) {

                viewHolder.txtOrderId.setText(adapter.getRef(position).getKey());
                viewHolder.txtOrderStatus.setText(Common.convertCodeToStatus(model.getStatus()));
                viewHolder.txtOrderPhone.setText(model.getPhone());
                viewHolder.txtOrderTotal.setText(model.getTotal());

                viewHolder.btn_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showUpdateDialog(adapter.getRef(position).getKey(),
                                adapter.getItem(position));

                    }
                });

                viewHolder.btn_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteOrder(adapter.getRef(position).getKey());

                    }
                });

//                viewHolder.setItemClickListener(new ItemClickListener() {
//                    @Override
//                    public void onClick(View view, int position, boolean isLongClick) {
//
//                    }
//                });
            }

        };
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        if (item.getTitle().equals(Common.UPDATE))
//        {
//            showUpdateDialog(adapter.getRef(item.getOrder()).getKey(), adapter.getItem(item.getOrder()));
//
//        }
//        else if (item.getTitle().equals(Common.DELETE))
//        {
//            deleteOrder(adapter.getRef(item.getOrder()).getKey());
//
//        }
//        return super.onContextItemSelected(item);
//
//    }

    private void deleteOrder(String key) {
        requestsReference.child(key).removeValue();
        adapter.notifyDataSetChanged();  //Add to delete item size

    }

    private void showUpdateDialog(String key, final Request item) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(OrderStatusActivity.this);
        alertDialog.setTitle("Update Order");
        alertDialog.setMessage("Please choose status");

        LayoutInflater inflater = this.getLayoutInflater();
        final  View view = inflater.inflate(R.layout.update_order_layout, null);

        spinner = (MaterialSpinner)view.findViewById(R.id.statusSpinner);
        spinner.setItems("Placed", "Processing","Ready to pick", "Served");

        alertDialog.setView(view);

        final String localKey = key;
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                item.setStatus(String.valueOf(spinner.getSelectedIndex()));

                requestsReference.child(localKey).setValue(item);
                adapter.notifyDataSetChanged();  //Add to update item size
            }
        });

        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.show();


    }
}
