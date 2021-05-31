package com.example.lopsotaylienlac.adapter;

import android.content.Context;
import android.content.SharedPreferences;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TimetableAdapter extends RecyclerView.Adapter<TimetableAdapter.TimetableViewHolder> {
    List<Timetable> listTimetable = new ArrayList<>();
    private Context context;
    //Convert list to HashSet to add array to sharedpreference
    private Set<String> set = new HashSet<>();

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
            holder.sharedPreferences = context.getSharedPreferences("timetable",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = holder.sharedPreferences.edit();
            String timetableId= String.valueOf(timetable.getTimetableID());
            /**
             * when user checked, it will add timetableid to Hashset
             * if Hashset has the timetableId, which has checked it will remove
             * add the list to sharedpreference to TimetableManagementFragment use to Save any changes
             */
            if(!set.contains(timetableId)){
                set.add(timetableId);//add to SetString to putStringSet
            }
            else
                set.remove(timetableId);
            editor.putStringSet("timetableID",set);
            editor.apply();
            editor.commit();
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
        private SharedPreferences sharedPreferences;
        public TimetableViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDateSchedule = (TextView) itemView.findViewById(R.id.txtDateSchedule);
            chkStudy =(CheckBox) itemView.findViewById(R.id.chkStudy);
        }
    }
}
