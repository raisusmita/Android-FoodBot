package com.example.android_foodbot.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android_foodbot.Interface.ItemClickListener;
import com.example.android_foodbot.R;

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtOrderId, txtOrderStatus, txtOrderPhone, txtOrderTotal,txtOrderFood;

    private ItemClickListener itemClickListener;

    public OrderViewHolder(@NonNull View itemView) {
        super(itemView);

        txtOrderId = (TextView)itemView.findViewById(R.id.order_id);
        txtOrderStatus = (TextView)itemView.findViewById(R.id.order_status);
        txtOrderPhone = (TextView)itemView.findViewById(R.id.order_phone);
        txtOrderTotal= (TextView)itemView.findViewById(R.id.order_total);
//        txtOrderFood= (TextView)itemView.findViewById(R.id.order_food);



        itemView.setOnClickListener(this);



    }
//    @NonNull
//    @Override
//    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
//        View v= LayoutInflater.from( mContext ).inflate( R.layout.activity_order_status,parent,false );
//        return new OrderViewHolder( v );
//    }


    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;


    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), false);



    }

}


