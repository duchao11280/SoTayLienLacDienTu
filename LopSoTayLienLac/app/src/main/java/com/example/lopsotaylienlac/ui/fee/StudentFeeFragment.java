package com.example.lopsotaylienlac.ui.fee;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lopsotaylienlac.R;
import com.example.lopsotaylienlac.adapter.AnnoucementAdapter;
import com.example.lopsotaylienlac.adapter.StudentFeeAdapter;
import com.example.lopsotaylienlac.adapter.SubjectClassApdapter;
import com.example.lopsotaylienlac.apis.UserApi;
import com.example.lopsotaylienlac.beans.Announcement;
import com.example.lopsotaylienlac.beans.Fee;
import com.example.lopsotaylienlac.beans.Subjectofstudent;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StudentFeeFragment extends Fragment {
    private RecyclerView recyclerView;
    private StudentFeeAdapter studentFeeAdapter;
    private LinearLayoutManager layoutManager;
    private TextView txtSubject;
    private TextView txtPrice;
    SharedPreferences sharedPreferences;
    private int feeofyear;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_student_fee, container, false);
        sharedPreferences = this.getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("userID","-1");
        recyclerView = (RecyclerView)root.findViewById(R.id.rcvStudentCheckFee);
        layoutManager = new LinearLayoutManager(getContext());
        UserApi.apiService.getAllSubclassByStudentID(Integer.parseInt(id)).enqueue(new Callback<ArrayList<Subjectofstudent>>() {
            @Override
            public void onResponse(Call<ArrayList<Subjectofstudent>> call, Response<ArrayList<Subjectofstudent>> responsemain) {
                UserApi.apiService.getAllFee().enqueue(new Callback<ArrayList<Fee>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Fee>> call, Response<ArrayList<Fee>> response) {
                        List<Fee> lst = new ArrayList<>();

                        lst =  response.body();
                        int lastindex = lst.size();
                        Fee lastfee = response.body().get(lastindex-1);
                        feeofyear = lastfee.getMoney();

                        studentFeeAdapter= new StudentFeeAdapter(responsemain.body(),feeofyear);
                        recyclerView.setAdapter(studentFeeAdapter);
                        recyclerView.setLayoutManager(layoutManager);
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Fee>> call, Throwable t) {

                    }
                });

            }

            @Override
            public void onFailure(Call<ArrayList<Subjectofstudent>> call, Throwable t) {
                Toast.makeText(getContext(),"Không có fee nào",Toast.LENGTH_LONG).show();
            }
        });

        return  root;
    }


}