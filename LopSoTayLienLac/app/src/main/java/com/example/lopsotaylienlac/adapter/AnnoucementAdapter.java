package com.example.lopsotaylienlac.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lopsotaylienlac.R;
import com.example.lopsotaylienlac.apis.UserApi;
import com.example.lopsotaylienlac.beans.Announcement;

import java.util.ArrayList;
import java.util.List;

public class AnnoucementAdapter extends RecyclerView.Adapter<AnnoucementAdapter.AnouncementViewHolder> {

    private List<Announcement> listAnnoucement = new ArrayList<>();
    private NavController navController;
    private Context context;
    private int role, uid;

    public AnnoucementAdapter(List<Announcement> listAnnoucement, NavController navController, Context context, int role, int uid) {
        this.listAnnoucement = listAnnoucement;
        this.navController = navController;
        this.context = context;
        this.role = role;
        this.uid = uid;
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

        int announID = itemAnnou.getAnnounID();

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(isLongClick)
                    return;
                else
                {
                    holder.sharedPreferences = context.getSharedPreferences("notificationDetail", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = holder.sharedPreferences.edit();
                    editor.putInt("announID", announID);
                    editor.putInt("role", role);
                    editor.putInt("uid", uid);
                    editor.apply();
                    editor.commit();
                    navController.navigate(R.id.fragment_detail_notification);
                }
            }
        });

    }
    //return amount of item
    @Override
    public int getItemCount() {
        return listAnnoucement.size();
    }

    public class AnouncementViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private ItemClickListener itemClickListener;
        TextView txtTitleNoti, txtContentNoti, txtDateSendnoti, txtAnnouID;
        LinearLayout item_noti;
        SharedPreferences sharedPreferences;
        //getView
        public AnouncementViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitleNoti = (TextView)itemView.findViewById(R.id.txtTitleNotification);
            txtContentNoti =(TextView) itemView.findViewById(R.id.txtContentNotification);
            txtDateSendnoti = (TextView) itemView.findViewById(R.id.txtdateSendNotification);
            item_noti = (LinearLayout) itemView.findViewById(R.id.item_noti);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }
        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }
}
