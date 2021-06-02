package com.example.lopsotaylienlac.ui.fee;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ParentFeeFragment extends Fragment {
    private RecyclerView recyclerView;
    private StudentFeeAdapter studentFeeAdapter;
    private LinearLayoutManager layoutManager;
    private TextView txtSubject;
    private TextView txtPrice;
    SharedPreferences sharedPreferences;
    private int feeofyear;
    private Button btnStudentFeeOK;
    private int studentID;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_parent_check_fee, container, false);
        //Lấy thông tin đăng nhập của phụ huynh
        sharedPreferences = this.getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("userID","-1");
        recyclerView = (RecyclerView)root.findViewById(R.id.rcvParentCheckFee);
        layoutManager = new LinearLayoutManager(getContext());

        getStudentId(Integer.parseInt(id));

        return  root;
    }
    /**
     * Dialog hiển thị khi không có môn học đóng học phí
     */
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

    /**
     * api lấy id của học sinh dựa vào id của phụ huynh
     * @param id
     */
    private void getStudentId(int id){
        UserApi.apiService.getStudentIdByParentId(id).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                studentID = response.body();
                //Lấy các môn học của học sinh
                getSubject(studentID);
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(getContext(),"Không có Student",Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * lấy môn học bằng id của học sinh
     * @param id
     */
    private void getSubject(int id){

        UserApi.apiService.getAllSubclassByStudentID(id).enqueue(new Callback<ArrayList<Subjectofstudent>>() {
            @Override
            public void onResponse(Call<ArrayList<Subjectofstudent>> call, Response<ArrayList<Subjectofstudent>> responsemain) {
                //api lấy danh sách học phí
                UserApi.apiService.getAllFee().enqueue(new Callback<ArrayList<Fee>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Fee>> call, Response<ArrayList<Fee>> response) {
                        List<Fee> lst = new ArrayList<>();
                        //Lấy học phí mới nhất
                        lst =  response.body();
                        int lastindex = lst.size();
                        Fee lastfee = response.body().get(lastindex-1);
                        feeofyear = lastfee.getMoney();
                        //Lấy danh sách học phí chưa đóng
                        List<Subjectofstudent> templst = new ArrayList<>();

                        for (Subjectofstudent s: responsemain.body()) {
                            if(s.isPaid()==false){
                                templst.add(s);
                            }
                        }

                        for (Subjectofstudent cc: templst) {
                            System.out.println(cc);
                        }
                        if(templst.size()==0){
                            openInfoDialog();
                            return;
                        }

                        //Set dữ liệu và hiển thị
                        studentFeeAdapter= new StudentFeeAdapter(templst,feeofyear);
                        recyclerView.setAdapter(studentFeeAdapter);
                        recyclerView.setLayoutManager(layoutManager);
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Fee>> call, Throwable t) {
                        System.out.println("No Fee");
                    }
                });

            }

            @Override
            public void onFailure(Call<ArrayList<Subjectofstudent>> call, Throwable t) {
                //Hiển thị dialog không có môn học đóng học phí
                openInfoDialog();
            }
        });

    }


}