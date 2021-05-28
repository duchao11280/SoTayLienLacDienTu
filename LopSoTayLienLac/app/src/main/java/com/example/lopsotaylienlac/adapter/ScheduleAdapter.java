package com.example.lopsotaylienlac.adapter;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lopsotaylienlac.R;
import com.example.lopsotaylienlac.beans.Schedule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder> {

    List<Schedule> scheduleList = new ArrayList<>();

    public ScheduleAdapter(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }
    /**
     * tao view layout
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_schedule,parent,false);
        return new ScheduleViewHolder(view);
    }
    /**
     * set du lieu len view
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        Schedule schedule = scheduleList.get(position);
        String thu;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(schedule.getDate().getTime());
        if(calendar.get(Calendar.DAY_OF_WEEK)==1)
            thu="Chủ Nhật";
        else
            thu = "Thứ: "+ (calendar.get(Calendar.DAY_OF_WEEK) );
        String currDate = DateFormat.format("dd/MM/yyyy",calendar).toString();
        holder.txtThu.setText(thu);
        holder.txtDateSche.setText(""+currDate);
        holder.txtTietGio.setText("Tiết "+schedule.getTietbd()+"-"+schedule.getTietkt()+", Từ "+
                schedule.getTmStart()+" đến "+schedule.getTmEnd());
        holder.txtLopvaTC.setText(schedule.getSubjectName()+"("+schedule.getCredit()+" tín chỉ)");
        holder.txtPhong.setText("Phòng:" +schedule.getRoom());
        holder.txtTenGV.setText("Giảng viên: "+schedule.getTeachername());
    }

    @Override
    public int getItemCount() {
        if(scheduleList.size()!=0)
            return scheduleList.size();
        return 0;
    }

    public class ScheduleViewHolder extends RecyclerView.ViewHolder{
        TextView txtThu,txtDateSche,txtTietGio,txtLopvaTC,txtPhong,txtTenGV;
        public ScheduleViewHolder(@NonNull View itemView) {
            super(itemView);
            txtThu = (TextView) itemView.findViewById(R.id.txtThu);
            txtDateSche = (TextView) itemView.findViewById(R.id.txtDateSche);
            txtTietGio = (TextView) itemView.findViewById(R.id.txtTietGio);
            txtLopvaTC = (TextView) itemView.findViewById(R.id.txtLopvaTC);
            txtPhong = (TextView) itemView.findViewById(R.id.txtPhong);
            txtTenGV = (TextView) itemView.findViewById(R.id.txtTenGV);
        }
    }
}
