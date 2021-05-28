package com.example.lopsotaylienlac.ui.score;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
    private Button btnScoreOK;

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


                studentScoreAdapter= new StudentScoreAdapter(response.body(),getContext());
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

    public void openScoreDialog(int x){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.dialog_student_check_score,null);
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        sharedPreferences = this.getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("userID","-1");
        UserApi.apiService.getAllSubclassByStudentID(Integer.parseInt(id)).enqueue(new Callback<ArrayList<Subjectofstudent>>() {
            @Override
            public void onResponse(Call<ArrayList<Subjectofstudent>> call, Response<ArrayList<Subjectofstudent>> response) {
                /**
                 * anh xa du lieu tu view
                 */
                TextView txtTenMonHoc = view.findViewById(R.id.txtTitle);
                TextView txtDiemGiua = view.findViewById(R.id.txtDiemGiuaKi);
                TextView txtDiemCuoi = view.findViewById(R.id.txtDiemCuoiKi);
                TextView txtDiemTong = view.findViewById(R.id.txtDiemTongKet);

                String sjname = response.body().get(x).getSubjectName();
                float mid = response.body().get(x).getScoreMidTerm();
                float finalscore = response.body().get(x).getScoreFinalTerm();
                float tong = (mid+finalscore)/2;
                String txtDiemGiuaKi = String.valueOf(mid);
                String txtDiemCuoiKi = String.valueOf(finalscore);
                String txtDiemTongKet = String.valueOf(tong);


                txtDiemGiua.setText(txtDiemGiuaKi);
                txtDiemCuoi.setText(txtDiemCuoiKi);
                txtDiemTong.setText(txtDiemTongKet);
                txtTenMonHoc.setText(sjname);
            }

            @Override
            public void onFailure(Call<ArrayList<Subjectofstudent>> call, Throwable t) {

            }
        });

        alertDialog.setView(view);
        alertDialog.show();

        /**
         * su kien nut OK
         */
        btnScoreOK = view.findViewById(R.id.btnScoreOK);
        btnScoreOK.setOnClickListener(v -> {
            alertDialog.cancel();
        });
    }

/*
    *//**
     * tao context menu cho tung item trong recyclerview
     * @param item
     * @return
     *//*
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int x = item.getGroupId();
        switch (item.getItemId()){
            *//**
             * case 001 la nut xem chi tiet
             *//*
            case 001:
                System.out.println("Day la xem chi tiet");
                openScoreDialog(x);
                return true;

        }
        return super.onContextItemSelected(item);
    }*/
}
