package com.example.lopsotaylienlac.adapter;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lopsotaylienlac.R;
import com.example.lopsotaylienlac.apis.UserApi;
import com.example.lopsotaylienlac.beans.Timetable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TimetableAdapter extends RecyclerView.Adapter<TimetableAdapter.TimetableViewHolder> {
    List<Timetable> listTimetable = new ArrayList<>();
    Context context;

    public TimetableAdapter(List<Timetable> listTimetable, Context context) {
        this.listTimetable = listTimetable;
        this.context = context;
        notifyDataSetChanged();
    }

    public List<Timetable> getListTimetable() {
        return listTimetable;
    }

    /**
     * tao view layout
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public TimetableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_checkschedule,parent,false);
        return new TimetableViewHolder(view);
    }
    /**
     * set du lieu len view
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull TimetableViewHolder holder, int position) {
        Timetable timetable = listTimetable.get(position);
        if (timetable==null)
            return;
        boolean checked;
        if(timetable.isOff() == true)
            holder.chkStudy.setChecked(false);
        else
            holder.chkStudy.setChecked(true);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timetable.getDate().getTime());
        String date = DateFormat.format("dd/MM/yyyy",calendar).toString();
        holder.txtDateSchedule.setText(date);
        holder.chkStudy.setOnClickListener(v -> {
            UserApi.apiService.updateIsOff(timetable.getTimetableID()).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Toast.makeText(context,"Ngày "+date+"đã được thay đổi",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();
                }
            });
        });

    }

    @Override
    public int getItemCount() {
        if(listTimetable!=null)
            return listTimetable.size();
        return 0;
    }

    public class TimetableViewHolder extends RecyclerView.ViewHolder {

        private CheckBox chkStudy;
        private TextView txtDateSchedule;
        public TimetableViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDateSchedule = (TextView) itemView.findViewById(R.id.txtDateSchedule);
            chkStudy =(CheckBox) itemView.findViewById(R.id.chkStudy);
        }
    }
}
