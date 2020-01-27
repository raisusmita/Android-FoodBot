package com.example.android_foodbot.ViewHolder;


import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.android_foodbot.Common.Common;
import com.example.android_foodbot.Interface.ItemClickListener;
import com.example.android_foodbot.Model.Order;
import com.example.android_foodbot.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class  CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener{

    public TextView txt_cart_name, txt_price;
    public ImageView img_cart_count;

    private ItemClickListener itemClickListener;

    public void setText_cart_name(TextView txt_cart_name){
        this.txt_cart_name = txt_cart_name;
    }
    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        txt_cart_name = (TextView)itemView.findViewById(R.id.cart_item_name);
        txt_price = (TextView)itemView.findViewById(R.id.cart_item_price);
        img_cart_count = (ImageView) itemView.findViewById(R.id.cart_item_count);
        itemView.setOnCreateContextMenuListener(this);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Select Action");
        menu.add(0,0,getAdapterPosition(), Common.DELETE);
    }
}
public class CartAdapter extends RecyclerView.Adapter<CartViewHolder>{

    private List<Order> listData = new ArrayList<>();
    private Context context;

    public CartAdapter(List<Order> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.cart_layout, viewGroup, false);
        return new CartViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder cartViewHolder, int i) {
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(""+listData.get(i).getQuantity(), Color.RED);
        cartViewHolder.img_cart_count.setImageDrawable(drawable);

//        Locale locale = new Locale("en", "US");
//        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

//        DecimalFormat fmt = (DecimalFormat) NumberFormat.getInstance();
//        fmt.setGroupingUsed(true);
//        fmt.setPositivePrefix("Rs. ");
//        fmt.setNegativePrefix("Rs. -");
//        fmt.setMinimumFractionDigits(2);
//        fmt.setMaximumFractionDigits(2);

        float price = (Float.parseFloat(listData.get(i).getPrice()))*(Float.parseFloat(listData.get(i).getQuantity()));
        String priceString = String.valueOf(price);
        cartViewHolder.txt_price.setText( priceString);

        cartViewHolder.txt_cart_name.setText(listData.get(i).getProductName());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
