package com.example.android_foodbot_admin;

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
import android.widget.Toast;

import com.example.android_foodbot_admin.Common.Common;
import com.example.android_foodbot_admin.Interface.ItemClickListener;
import com.example.android_foodbot_admin.Model.Request;
import com.example.android_foodbot_admin.R;
import com.example.android_foodbot_admin.Report_Activity;
//import com.example.android_foodbot_admin.ViewHolder.ReportViewHolder;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;


class ReportViewHolder extends RecyclerView.ViewHolder {

    public TextView txtOrderId, txtOrderStatus, txtOrderPhone, txtOrderTotal;
    TextView txtTotalPrice = null;


    public ReportViewHolder(@NonNull View itemView) {
        super(itemView);

        txtOrderId = (TextView) itemView.findViewById(R.id.order_id_report);
        txtOrderStatus = (TextView) itemView.findViewById(R.id.order_status_report);
        txtOrderPhone = (TextView) itemView.findViewById(R.id.order_phone_report);
        txtOrderTotal = (TextView) itemView.findViewById(R.id.order_total_report);


    }

}
public class ReportAdapter extends RecyclerView.Adapter<ReportViewHolder>{

    private List<Request> listData = new ArrayList<>();
    private Context context;

    public ReportAdapter(List<Request> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.report_layout, viewGroup, false);
        return new ReportViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ReportViewHolder cartViewHolder, int i) {
//        TextView txtTotalPrice = (TextView).findViewById(R.id.totalReport);

//        Locale locale = new Locale("en", "US");
//        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
//
//        Common.Total = (Integer.parseInt(listData.get(i).getTotal()));
//
//        int total = 0;
////                for (ReportViewHolder request:order)
////                String ok ="";
//        for (int x =0; x <listData.size(); x++) {
//            //                     request.txtOrderTotal.setText(model.getTotal());
//            total+= (Integer.parseInt(listData.get(i).getTotal()));
//
//        }
////                Locale locale = new Locale("en", "US");
////                NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
//        String ok = String.valueOf(total);
//        if (ok!=null) {
//            Toast.makeText(context, "" + ok, Toast.LENGTH_SHORT).show();
//        }
//        else
//        {
//            Toast.makeText(context, "Null", Toast.LENGTH_SHORT).show();
//        }
//        cartViewHolder.txtTotalPrice.setText(ok);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}
