package com.example.android_foodbot.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_foodbot.Model.Request;
import com.example.android_foodbot.R;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    Context context;
    ArrayList<Request> requests;

    public  MenuAdapter(Context c, ArrayList<Request> r ){
        context = c;
        requests = r;
    }
    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MenuViewHolder(LayoutInflater.from(context).inflate(R.layout.order_layout, viewGroup, false  ));
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder menuViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return requests.size();
    }

    class  MenuViewHolder extends RecyclerView.ViewHolder{

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
