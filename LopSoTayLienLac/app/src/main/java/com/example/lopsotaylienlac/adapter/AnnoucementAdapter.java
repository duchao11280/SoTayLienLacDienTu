package com.example.lopsotaylienlac.adapter;

import android.graphics.Color;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lopsotaylienlac.R;
import com.example.lopsotaylienlac.beans.Announcement;

import java.util.ArrayList;
import java.util.List;

public class AnnoucementAdapter extends RecyclerView.Adapter<AnnoucementAdapter.AnouncementViewHolder> {

    private List<Announcement> listAnnoucement = new ArrayList<>();
    Fragment fragment;

    public AnnoucementAdapter(List<Announcement> listAnnoucement, Fragment fragment) {
        this.listAnnoucement= listAnnoucement;
        this.fragment = fragment;
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

        //set read or didnt read noti
        if(itemAnnou.isRead()==true)
        {holder.txtTitleNoti.setTextColor(Color.rgb(204,204,204));}
        else
            holder.txtTitleNoti.setTextColor(Color.rgb(0,0,0));

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

        TextView txtTitleNoti, txtContentNoti, txtDateSendnoti, txtAnnouID;
        LinearLayout item_noti;
        //getView
        public AnouncementViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitleNoti = (TextView)itemView.findViewById(R.id.txtTitleNotification);
            txtContentNoti =(TextView) itemView.findViewById(R.id.txtContentNotification);
            txtDateSendnoti = (TextView) itemView.findViewById(R.id.txtdateSendNotification);
            item_noti = (LinearLayout) itemView.findViewById(R.id.item_noti);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavHostFragment.findNavController(fragment).navigate(R.id.fragment_detail_notification);
                }
            });



        }
    }
}
