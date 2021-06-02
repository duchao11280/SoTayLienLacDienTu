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
        //Lấy thông tin đăng nhập của học sinh
        sharedPreferences = this.getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("userID","-1");
        recyclerView = (RecyclerView)root.findViewById(R.id.rcvStudentCheckScore);
        layoutManager = new LinearLayoutManager(getContext());
        //api lấy tất cả môn học của học sinh thông qua id
        UserApi.apiService.getAllSubclassByStudentID(Integer.parseInt(id)).enqueue(new Callback<ArrayList<Subjectofstudent>>() {
            @Override
            public void onResponse(Call<ArrayList<Subjectofstudent>> call, Response<ArrayList<Subjectofstudent>> response) {

                //set dữ liệu và hiện lên
                studentScoreAdapter= new StudentScoreAdapter(response.body(),getContext());
                recyclerView.setAdapter(studentScoreAdapter);
                recyclerView.setLayoutManager(layoutManager);

            }

            @Override
            public void onFailure(Call<ArrayList<Subjectofstudent>> call, Throwable t) {
                //Mở dialog không có môn học
                openInfoDialog();
            }
        });

        return  root;
    }

    /**
     * dialog hiển thị khi không có môn học
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
     * dialog hiển thị điểm chi tiết của từng môn học
     * @param x
     */
    public void openScoreDialog(int x){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.dialog_student_check_score,null);
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        sharedPreferences = this.getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("userID","-1");
        //api lấy tất cả môn học của học sinh bằng id của học sinh
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
                //Lấy môn học tại item được ấn
                String sjname = response.body().get(x).getSubjectName();
                float mid = response.body().get(x).getScoreMidTerm();
                float finalscore = response.body().get(x).getScoreFinalTerm();
                float tong = (mid+finalscore)/2;
                String txtDiemGiuaKi = String.valueOf(mid);
                String txtDiemCuoiKi = String.valueOf(finalscore);
                String txtDiemTongKet = String.valueOf(tong);

                //Set dữ liệu và hiển thị lên
                txtDiemGiua.setText(txtDiemGiuaKi);
                txtDiemCuoi.setText(txtDiemCuoiKi);
                txtDiemTong.setText(txtDiemTongKet);
                txtTenMonHoc.setText(sjname);
            }

            @Override
            public void onFailure(Call<ArrayList<Subjectofstudent>> call, Throwable t) {
                System.out.println("Error");
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

}
