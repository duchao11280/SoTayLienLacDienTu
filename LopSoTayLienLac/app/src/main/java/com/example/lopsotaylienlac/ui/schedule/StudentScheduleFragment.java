package com.example.lopsotaylienlac.ui.schedule;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lopsotaylienlac.R;
import com.example.lopsotaylienlac.adapter.ScheduleAdapter;
import com.example.lopsotaylienlac.adapter.SubjectClassApdapter;
import com.example.lopsotaylienlac.apis.UserApi;
import com.example.lopsotaylienlac.beans.Schedule;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class StudentScheduleFragment extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ScheduleAdapter scheduleAdapter;
    private SharedPreferences sharedPreferences;
    private Button btnXemLich;
    private int studentID;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_student_schedule, container, false);
        recyclerView = (RecyclerView)root.findViewById(R.id.rcvSchedule);
        btnXemLich = root.findViewById(R.id.btnXemLich);
        /**
         * tao va set layout cho recylerview
         */
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // lay student id tu sharedPreference khi da dang nhap
        sharedPreferences = this.getActivity().getSharedPreferences("dataLogin", MODE_PRIVATE);
        String id = sharedPreferences.getString("userID","-1");
        System.out.println(id);
        studentID = Integer.parseInt(id);
        /**
         * lay ngay hien tai
         */
        Calendar cal = Calendar.getInstance();
        String currDate = DateFormat.format("yyyy-MM-dd",cal).toString();
        /**
         * Lay thoi khoa bieu ngay hien tai
         */
        getSchedule(studentID,currDate);
        /**
         * Mo Datetimepicker khi click vao button
         */
        btnXemLich.setOnClickListener(v -> {
            openCalender();
        });
        return root;
    }

    private void openCalender() {
        //Initialize year, month, day
        Calendar cal = Calendar.getInstance();
        final int year = cal.get(Calendar.YEAR);
        final int month = cal.get(Calendar.MONTH);
        final int day = cal.get(Calendar.DAY_OF_MONTH);
        //Initialize Date Picker Dialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {


                        Calendar date = Calendar.getInstance();
                        date.set(year,month,day);
                        String sdate = DateFormat.format("yyyy-MM-dd",date).toString();
                        //Display Schedule
                        getSchedule(studentID,sdate);

                    }
                },year,month,day);
        //Show Date Picker Dialog

        datePickerDialog.show();
    }

    public void getSchedule(int id, String dtpk){
        UserApi.apiService.getScheduleByStudentIdandDate(id,dtpk).enqueue(new Callback<ArrayList<Schedule>>() {
            @Override
            public void onResponse(Call<ArrayList<Schedule>> call, Response<ArrayList<Schedule>> response) {
                recyclerView.setVisibility(View.VISIBLE);
                scheduleAdapter= new ScheduleAdapter(response.body());
                recyclerView.setAdapter(scheduleAdapter);

            }

            @Override
            public void onFailure(Call<ArrayList<Schedule>> call, Throwable t) {
                Toast.makeText(getContext(),"Không có TKB",Toast.LENGTH_LONG).show();
                recyclerView.setVisibility(View.INVISIBLE);
            }
        });
    }

}
