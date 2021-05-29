package com.example.lopsotaylienlac.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lopsotaylienlac.R;
import com.example.lopsotaylienlac.beans.Timetable;

import java.util.ArrayList;
import java.util.List;


public class TimetableAdapter extends RecyclerView.Adapter<TimetableAdapter.TimetableViewHolder> {
    List<Timetable> listTimetable = new ArrayList<>();
    Context context;

    public TimetableAdapter(List<Timetable> listTimetable, Context context) {
        this.listTimetable = listTimetable;
        this.context = context;
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

        holder.txtDateSchedule.setText(""+timetable.getDate());
        holder.chkStudy.setChecked(timetable.isOff());
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
        }
    }
}
