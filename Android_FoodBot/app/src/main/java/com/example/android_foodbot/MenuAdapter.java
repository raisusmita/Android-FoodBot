package com.example.android_foodbot;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android_foodbot.Model.Category;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MenuAdapter  {

    private RecyclerView recyclerView;

//    private Context mContext;
//    private List<Category> menuList;
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        public TextView menu_name1, menu_price1;
//        public ImageView menu_image1;
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            menu_name1 = (TextView) itemView.findViewById(R.id.menu_name1);
//            menu_price1 = (TextView) itemView.findViewById(R.id.menu_price1);
//            menu_image1 = (ImageView) itemView.findViewById(R.id.menu_image1);
//        }
//
//
//    }
//
//    public MenuAdapter(Context mContext, List<Category> albumList) {
//        this.mContext = mContext;
//        this.menuList = menuList;
//    }
//
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View itemView = LayoutInflater.from(viewGroup.getContext())
//                .inflate(R.layout.menu_item, viewGroup, false);
//
//        return new MyViewHolder(itemView);
//
//
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
//
//        Category category = menuList.get(i);
//        myViewHolder.menu_name1.setText(category.getName1());
//        myViewHolder.menu_price1.setText(category.getPrice1());
//
//        // loading album cover using Glide library
//
//        Glide.with(mContext).load(category.getImage1()).into(myViewHolder.menu_image1);
//
//
//
//    }

//    @Override
//    public int getItemCount() {
//        return menuList.size();
//    }


}
