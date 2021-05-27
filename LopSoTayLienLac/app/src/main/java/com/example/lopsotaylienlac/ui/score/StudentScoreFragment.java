package com.example.lopsotaylienlac.ui.score;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lopsotaylienlac.R;
import com.example.lopsotaylienlac.adapter.StudentFeeAdapter;
import com.example.lopsotaylienlac.adapter.StudentScoreAdapter;
import com.example.lopsotaylienlac.apis.UserApi;
import com.example.lopsotaylienlac.beans.Fee;
import com.example.lopsotaylienlac.beans.Subjectofstudent;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentScoreFragment extends Fragment {

    private RecyclerView recyclerView;
    private StudentScoreAdapter studentScoreAdapter;
    private LinearLayoutManager layoutManager;
    SharedPreferences sharedPreferences;
    private Button btnStudentFeeOK;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_student_check_score, container, false);
        sharedPreferences = this.getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("userID","-1");
        recyclerView = (RecyclerView)root.findViewById(R.id.rcvStudentCheckScore);
        layoutManager = new LinearLayoutManager(getContext());
        UserApi.apiService.getAllSubclassByStudentID(Integer.parseInt(id)).enqueue(new Callback<ArrayList<Subjectofstudent>>() {
            @Override
            public void onResponse(Call<ArrayList<Subjectofstudent>> call, Response<ArrayList<Subjectofstudent>> response) {


                studentScoreAdapter= new StudentScoreAdapter(response.body());
                recyclerView.setAdapter(studentScoreAdapter);
                recyclerView.setLayoutManager(layoutManager);
                /*UserApi.apiService.getAllFee().enqueue(new Callback<ArrayList<Fee>>() {
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
                });*/

            }

            @Override
            public void onFailure(Call<ArrayList<Subjectofstudent>> call, Throwable t) {

                openInfoDialog();
            }
        });

        return  root;
    }

    public void openInfoDialog(){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.dialog_student_check_fee,null);
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setView(view);
        alertDialog.show();


        /**
         * su kien nut ok
         */
        btnStudentFeeOK = view.findViewById(R.id.btnStudentFeeOK);
        btnStudentFeeOK.setOnClickListener(v -> {
            alertDialog.cancel();

        });


    }
}
