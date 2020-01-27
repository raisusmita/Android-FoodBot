package com.example.android_foodbot;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_foodbot.AccountActivity.User;
import com.example.android_foodbot.Common.Common;
import com.example.android_foodbot.Database.Database;
import com.example.android_foodbot.Model.Order;
import com.example.android_foodbot.Model.Request;
import com.example.android_foodbot.ViewHolder.CartAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CartActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference requests;

    TextView txtTotalPrice;
    Button btnPlaceOrder;

    List<Order> cart = new ArrayList<>();
    CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        //Firebase
        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Requests");

        //Init
        recyclerView = (RecyclerView)findViewById(R.id.listCart);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        txtTotalPrice = (TextView)findViewById(R.id.total);
        btnPlaceOrder = (Button)findViewById(R.id.btnPlaceOrder);

        //Button clicked to place the order
        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cart.size()>0)
                    showAlertDialog();
                else
                    Toast.makeText(CartActivity.this, "Your cart is empty !!!", Toast.LENGTH_SHORT).show();

            }
        });

        loadListFood();
    }



    private void showAlertDialog(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CartActivity.this);
        alertDialog.setTitle("One more step");
        alertDialog.setMessage("Enter your phone number: ");

        final EditText editPhone = new EditText(CartActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );

        editPhone.setLayoutParams(lp);
        alertDialog.setView(editPhone);
        alertDialog.setIcon(R.drawable.ic_shopping_cart_black_24dp);

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String uid;
                FirebaseAuth auth;
                auth = FirebaseAuth.getInstance();
                uid = auth.getCurrentUser().getUid();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                FirebaseDatabase.getInstance().getReference().child("Users").child(uid).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Date timenow = Calendar.getInstance().getTime();
                        User user = dataSnapshot.getValue(User.class);
                        Toast.makeText(CartActivity.this, "user" +user.getPhone(), Toast.LENGTH_SHORT).show();
                        Request request = new Request(

                            user.getName(),
//                            timenow.getTime().toString(),
                            editPhone.getText().toString(),
                            txtTotalPrice.getText().toString(),
                            cart



                            );

                        //Submit to Firebase
                        //Using System.CurrentMilli to key
                        requests.child(String.valueOf(System.currentTimeMillis()))
                                .setValue(request);

                        new Database(getBaseContext()).cleanCart();
                        Toast.makeText(CartActivity.this, "Thank You!! Your order has been placed", Toast.LENGTH_SHORT).show();
                        finish();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        throw databaseError.toException();

                    }
                });
            }
        });

        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.show();
    }

    private void loadListFood() {
        cart = new Database(this).getCarts();
        adapter = new CartAdapter(cart, this);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        //Calculate total price
        int total = 0;
        for(Order order:cart)
            total +=(Float.parseFloat(order.getPrice()))*(Float.parseFloat(order.getQuantity()));

//        Locale locale = new Locale("en", "US");
//        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        String totalCart = String.valueOf(total);
//        DecimalFormat fmt = (DecimalFormat) NumberFormat.getInstance();
//        fmt.setGroupingUsed(true);
//        fmt.setPositivePrefix("Rs. ");
//        fmt.setNegativePrefix("Rs. -");
//        fmt.setMinimumFractionDigits(2);
//        fmt.setMaximumFractionDigits(2);

        txtTotalPrice.setText(totalCart);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getTitle().equals(Common.DELETE))
            deleteCart(item.getOrder());
        return true;
    }

    private void deleteCart(int position) {
        //Removing item at List<Order> by position
        cart.remove(position);
        //Deleting all the old data from SQLite
        new Database(this).cleanCart();
        //Update new data from List<Order> to SQLite
        for (Order item:cart)
            new Database(this).addToCart(item);

        //Refreshing after delete
        loadListFood();
    }
}
