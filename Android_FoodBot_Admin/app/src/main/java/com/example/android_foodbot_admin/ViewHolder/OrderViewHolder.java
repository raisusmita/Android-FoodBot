package com.example.android_foodbot_admin.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android_foodbot_admin.Interface.ItemClickListener;
import com.example.android_foodbot_admin.R;

public class OrderViewHolder extends RecyclerView.ViewHolder{

    public TextView txtOrderId, txtOrderStatus, txtOrderPhone, txtOrderTotal,txtOrderFood;

//    private ItemClickListener itemClickListener;

    public Button btn_edit, btn_delete;

    public OrderViewHolder(@NonNull View itemView) {
        super(itemView);

        txtOrderId = (TextView)itemView.findViewById(R.id.order_id);
        txtOrderStatus = (TextView)itemView.findViewById(R.id.order_status);
        txtOrderPhone = (TextView)itemView.findViewById(R.id.order_phone);
        txtOrderTotal= (TextView)itemView.findViewById(R.id.order_total);

        btn_edit = (Button)itemView.findViewById(R.id.btn_edit);
        btn_delete = (Button)itemView.findViewById(R.id.btn_delete);

//        itemView.setOnClickListener(this);
//        itemView.setOnCreateContextMenuListener(this);
    }

//    public void setItemClickListener(ItemClickListener itemClickListener) {
//        this.itemClickListener = itemClickListener;
//
//
//    }
//
//    @Override
//    public void onClick(View v) {
//        itemClickListener.onClick(v, getAdapterPosition(), false);
//
//
//
//    }

//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        menu.setHeaderTitle("Select The Action");
//        menu.add(0,0, getAdapterPosition(), "Update");
//        menu.add(0,0, getAdapterPosition(), "Delete");
//
//    }

}
