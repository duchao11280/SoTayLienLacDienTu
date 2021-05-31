package com.example.lopsotaylienlac.adapter;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lopsotaylienlac.R;
import com.example.lopsotaylienlac.beans.Fee;

import java.util.ArrayList;
import java.util.List;


public class AdminFeeAdapter extends RecyclerView.Adapter<AdminFeeAdapter.AdminFeeViewHolder> {

  //list fee history through years
    private List<Fee> lstFee = new ArrayList<>();

    //constructor
    public AdminFeeAdapter(List<Fee> lstFee) {
        this.lstFee = lstFee;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdminFeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //get view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fee, parent, false);
        return new AdminFeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminFeeViewHolder holder, int position) {
        //get data of an item
        Fee feeItem = lstFee.get(position);

        //check null
        if(feeItem == null)
            return;

        //format date string
        String dateUpload = DateFormat.format("dd/MM/yyyy",feeItem.getDateUpload()).toString();
        String year = "Học phí năm "+ feeItem.getYear()+":";
        String fee = feeItem.getMoney()+" đ/tc";

        //set value for views
        holder.txtTilte.setText(year);
        holder.txtFee.setText(fee);
        holder.txtDate.setText(dateUpload);
    }

    @Override
    public int getItemCount() {
        return lstFee.size();
    }

    public class AdminFeeViewHolder extends RecyclerView.ViewHolder{

        private TextView txtTilte, txtFee, txtDate;

        public AdminFeeViewHolder(@NonNull View itemView) {
            super(itemView);

        //get view
            txtTilte = (TextView)itemView.findViewById(R.id.txt_year_fee);
            txtFee = (TextView)itemView.findViewById(R.id.fee);
            txtDate = (TextView)itemView.findViewById(R.id.txt_date);
        }
    }
}
