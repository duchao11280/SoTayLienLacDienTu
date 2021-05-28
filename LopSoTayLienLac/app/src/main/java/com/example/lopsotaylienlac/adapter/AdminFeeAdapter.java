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


//public class AnnoucementAdapter extends RecyclerView.Adapter<AnnoucementAdapter.AnouncementViewHolder>
public class AdminFeeAdapter extends RecyclerView.Adapter<AdminFeeAdapter.AdminFeeViewHolder> {

    private List<Fee> lstFee = new ArrayList<>();

    public AdminFeeAdapter(List<Fee> lstFee) {
        this.lstFee = lstFee;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdminFeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fee, parent, false);
        return new AdminFeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminFeeViewHolder holder, int position) {
        Fee feeItem = lstFee.get(position);
        if(feeItem == null)
            return;


        String dateUpload = DateFormat.format("yyyy-MM-dd",feeItem.getDateUpload()).toString();
        System.out.println(dateUpload);

        String year = "Học phí năm "+ feeItem.getYear()+":";
        holder.txtTilte.setText(year);
        holder.txtFee.setText(feeItem.getMoney()+" đ/tc");
        holder.txtDate.setText(DateFormat.format("yyyy-MM-dd",feeItem.getDateUpload()).toString());

    }

    @Override
    public int getItemCount() {
        return lstFee.size();
    }

    public class AdminFeeViewHolder extends RecyclerView.ViewHolder{
        private TextView txtTilte, txtFee, txtDate;
        public AdminFeeViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTilte = (TextView)itemView.findViewById(R.id.txt_year_fee);
            txtFee = (TextView)itemView.findViewById(R.id.fee);
            txtDate = (TextView)itemView.findViewById(R.id.txt_date);

        }
    }
}
