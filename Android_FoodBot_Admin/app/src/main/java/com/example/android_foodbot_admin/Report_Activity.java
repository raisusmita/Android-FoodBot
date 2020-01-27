package com.example.android_foodbot_admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_foodbot_admin.Common.Common;
import com.example.android_foodbot_admin.Model.Order;
import com.example.android_foodbot_admin.Model.Report;
import com.example.android_foodbot_admin.Model.Request;
import com.example.android_foodbot_admin.ViewHolder.OrderViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.text.BreakIterator;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Report_Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<Report> orderStatus = new ArrayList<>();


    FirebaseRecyclerAdapter<Request, ReportViewHolder> adapter;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference requestsReference;

    MaterialSpinner spinner;

    TextView txtTotalPrice = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_);
        LinearLayout LinearLayoutView = new LinearLayout(this);
        TextView DisplayStringArray = new TextView(this);
        DisplayStringArray.setTextSize(25);
        LinearLayoutView.addView(DisplayStringArray);

        //Firebase
        firebaseDatabase = FirebaseDatabase.getInstance();
        requestsReference = firebaseDatabase.getReference("Requests");

        //Init
        recyclerView = (RecyclerView)findViewById(R.id.listOrdersReport);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        txtTotalPrice = (TextView)findViewById(R.id.totalReport);



        loadOrders();
    }
    private void loadOrders() {
        final int[] total = {0};


        adapter = new FirebaseRecyclerAdapter<Request, ReportViewHolder>(

                Request.class,
                R.layout.report_layout,
                ReportViewHolder.class,
                requestsReference.orderByChild("status").equalTo("3")

        ) {
            @Override
            protected void populateViewHolder( ReportViewHolder viewHolder, Request model, final int position) {



                viewHolder.txtOrderId.setText(adapter.getRef(position).getKey());
                viewHolder.txtOrderStatus.setText(Common.convertCodeToStatus(model.getStatus()));
                viewHolder.txtOrderPhone.setText(model.getPhone());
                viewHolder.txtOrderTotal.setText(model.getTotal());
//

//        Calculate total price

                for (int i =0; i<1; i++) {
                    total[0] += (Float.parseFloat(model.getTotal()));

                }
//                Locale locale = new Locale("en", "NP");
//                NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
                DecimalFormat fmt = (DecimalFormat) NumberFormat.getInstance();
                fmt.setGroupingUsed(true);
                fmt.setMinimumFractionDigits(2);
                fmt.setMaximumFractionDigits(2);
                String ok = String.valueOf(total[0]);
//                Toast.makeText(Report_Activity.this, ""+ok, Toast.LENGTH_SHORT).show();

                txtTotalPrice.setText(fmt.format(total[0]));

            }


        };




        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

}
