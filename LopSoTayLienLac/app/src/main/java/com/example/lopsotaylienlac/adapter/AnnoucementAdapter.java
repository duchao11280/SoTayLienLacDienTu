package com.example.lopsotaylienlac.adapter;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lopsotaylienlac.R;
import com.example.lopsotaylienlac.beans.Announcement;

import java.util.ArrayList;
import java.util.List;

public class AnnoucementAdapter extends RecyclerView.Adapter<AnnoucementAdapter.AnouncementViewHolder> {

    private List<Announcement> listAnnoucement = new ArrayList<>();
    public AnnoucementAdapter(List<Announcement> listAnnoucement) {
        this.listAnnoucement= listAnnoucement;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AnouncementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
        return  new AnouncementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnouncementViewHolder holder, int position) {

        Announcement itemAnnou = listAnnoucement.get(position);
        if(itemAnnou== null)
            return;

        holder.txtTitleNoti.setText(itemAnnou.getTitle());
        holder.txtContentNoti.setText(itemAnnou.getAnnounContent());
        holder.txtDateSendnoti.setText(DateFormat.format("yyyy-MM-dd",itemAnnou.getDateSend()).toString());

    }

    //return amount of item
    @Override
    public int getItemCount() {
        return listAnnoucement.size();
    }

    public class AnouncementViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitleNoti, txtContentNoti, txtDateSendnoti;
        //getView
        public AnouncementViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitleNoti = (TextView)itemView.findViewById(R.id.txtTitleNotification);
            txtContentNoti =(TextView) itemView.findViewById(R.id.txtContentNotification);
            txtDateSendnoti = (TextView) itemView.findViewById(R.id.txtdateSendNotification);
        }
    }
}
