package com.example.android_foodbot_admin.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_foodbot_admin.Common.Common;
import com.example.android_foodbot_admin.Interface.ItemClickListener;
import com.example.android_foodbot_admin.R;

public class MenuViewHolder extends RecyclerView.ViewHolder implements
        View.OnClickListener,
        View.OnCreateContextMenuListener
{
    public TextView txtMenuName1;
    public ImageView imageView1;

    private ItemClickListener itemClickListener;

    public MenuViewHolder(@NonNull View itemView) {
        super(itemView);

        txtMenuName1 = (TextView)itemView.findViewById(R.id.menu_name1);
        imageView1 = (ImageView)itemView.findViewById(R.id.menu_image1);

        itemView.setOnCreateContextMenuListener(this);
        itemView.setOnClickListener(this);
    }


    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(),false);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Select the action");
        menu.add(0,0,getAdapterPosition(), Common.UPDATE);
        menu.add(0,1,getAdapterPosition(), Common.DELETE);


    }
}

