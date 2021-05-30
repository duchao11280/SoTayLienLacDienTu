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
    //list of notification
    private List<Announcement> listAnnoucement = new ArrayList<>();
    private NavController navController;//use to change fragment
    private Context context;
    private int role, uid;
    private String type;

    //constructor
    public AnnoucementAdapter(List<Announcement> listAnnoucement, NavController navController, Context context, int role, int uid, String type) {
        this.listAnnoucement = listAnnoucement;
        this.navController = navController;
        this.context = context;
        this.role = role;
        this.uid = uid;
        this.type = type;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AnouncementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //get view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
        return  new AnouncementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnouncementViewHolder holder, int position) {
        //get data 1 item
        Announcement itemAnnou = listAnnoucement.get(position);

        //check null
        if(itemAnnou== null)
            return;

        //set Tile color (204,204,204) if isRead true (0,0,0) if isRead false
        if(itemAnnou.isRead()==true)
        {holder.txtTitleNoti.setTextColor(Color.rgb(204,204,204));}//set mã màu rgb
        else
            holder.txtTitleNoti.setTextColor(Color.rgb(0,0,0));//set mã màu rgb

        //set data for views
        holder.txtTitleNoti.setText(itemAnnou.getTitle());
        holder.txtContentNoti.setText(itemAnnou.getAnnounContent());
        holder.txtDateSendnoti.setText(DateFormat.format("yyyy-MM-dd",itemAnnou.getDateSend()).toString());

        //get announID
        int announID = itemAnnou.getAnnounID();

        //overide clickItem, mở đến fragment Detail Notification
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                //khong thực hiện LongClick
                if(isLongClick)
                    return;
                else
                {
                    //createSharePrefrences
                    holder.sharedPreferences = context.getSharedPreferences("notificationDetail", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = holder.sharedPreferences.edit();//tạo editor
                    editor.putInt("announID", announID);
                    editor.putInt("role", role);
                    editor.putInt("uid", uid);
                    editor.putString("type", type);//acces type
                    editor.apply();
                    editor.commit();

                    //change to Notification Detail Fragment
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

        public AnouncementViewHolder(@NonNull View itemView) {
            super(itemView);
            //get view
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
