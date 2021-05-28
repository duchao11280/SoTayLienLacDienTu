package com.example.lopsotaylienlac.ui.sbclass;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lopsotaylienlac.R;
import com.example.lopsotaylienlac.adapter.StudentAdapter;
import com.example.lopsotaylienlac.apis.UserApi;
import com.example.lopsotaylienlac.beans.Student;
import com.example.lopsotaylienlac.beans.Subjectclass;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailSubjectFragment extends Fragment {
    SharedPreferences sharedPreferences;
    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;
    private LinearLayoutManager linearLayoutManager;
    private TextView txtTenHP,txtMaLopHP,txtSoTC,txtSoSV;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_class_detail, container, false);
        sharedPreferences = getContext().getSharedPreferences("subjectClass", Context.MODE_PRIVATE);
        String sbID = sharedPreferences.getString("subjectclassID","-1");
        recyclerView = root.findViewById(R.id.rcvListSt);
        //set layout cho recyclerview

        //Anh Xa
        txtTenHP=root.findViewById(R.id.txtTenHP);
        txtMaLopHP=root.findViewById(R.id.txtMaLopHP);
        txtSoTC=root.findViewById(R.id.txtSoTC);
        txtSoSV=root.findViewById(R.id.txtSoSV);
        System.out.println(sbID);
        getDetailClass(sbID);
        getListStudent(sbID);
        return root;
    }
    public void getDetailClass(String id){
        UserApi.apiService.getSubjectclassBySubjectID(id).enqueue(new Callback<ArrayList<Subjectclass>>() {
            @Override
            public void onResponse(Call<ArrayList<Subjectclass>> call, Response<ArrayList<Subjectclass>> response) {

                txtTenHP.setText(response.body().get(0).getSubjectName());
                txtMaLopHP.setText(response.body().get(0).getSubjectID());
                txtSoTC.setText(""+response.body().get(0).getCredit());
            }

            @Override
            public void onFailure(Call<ArrayList<Subjectclass>> call, Throwable t) {
                Toast.makeText(getContext(),"Error 404",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getListStudent(String id){
        UserApi.apiService.getListUserBySubjectID(id).enqueue(new Callback<ArrayList<Student>>() {
            @Override
            public void onResponse(Call<ArrayList<Student>> call, Response<ArrayList<Student>> response) {
                studentAdapter = new StudentAdapter(response.body(),getContext());
                recyclerView.setAdapter(studentAdapter);
                linearLayoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(linearLayoutManager);
                txtSoSV.setText(""+studentAdapter.getItemCount());
            }

            @Override
            public void onFailure(Call<ArrayList<Student>> call, Throwable t) {

            }
        });
    }
}
