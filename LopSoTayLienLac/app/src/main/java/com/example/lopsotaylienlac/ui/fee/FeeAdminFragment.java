package com.example.lopsotaylienlac.ui.fee;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lopsotaylienlac.R;
import com.example.lopsotaylienlac.adapter.AdminFeeAdapter;
import com.example.lopsotaylienlac.apis.UserApi;
import com.example.lopsotaylienlac.beans.Fee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeeAdminFragment extends Fragment {

    private ImageView btnSearch, btnEdit;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //get view
        View root = inflater.inflate(R.layout.fragment_admin_fee, container, false);

        btnEdit =(ImageView) root.findViewById(R.id.btnEdit);
        btnSearch = (ImageView)root.findViewById(R.id.btnSearch);
        recyclerView = (RecyclerView)root.findViewById(R.id.rcvFee);
        //change to edit fee fragment
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(FeeAdminFragment.this).navigate(R.id.fragment_admin_edit_fee);
            }
        });

        //change to Search Student Fragment
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStudentFeeSearchFragment();
            }
        });

        //show History Fee List
        showFeeList();
        return root;
    }

    private void openStudentFeeSearchFragment() {
        NavHostFragment.findNavController(FeeAdminFragment.this).navigate(R.id.fragment_class_per_student);
    }

    private void showFeeList() {
        //call api to get all fee history
        UserApi.apiService.getAllFee().enqueue(new Callback<ArrayList<Fee>>() {
            @Override
            public void onResponse(Call<ArrayList<Fee>> call, Response<ArrayList<Fee>> response) {

                //reverse list in response
//                Collections.reverse(response.body());
                //set data, adapter
                adapter = new AdminFeeAdapter(response.body());//adapter
                layoutManager = new LinearLayoutManager(getContext());//layout manager

                recyclerView.setAdapter(adapter);//set adapter
                recyclerView.setLayoutManager(layoutManager);//set layput manager

                //call success
                Toast.makeText(getContext(), R.string.noti_load, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ArrayList<Fee>> call, Throwable t) {
                //call fail
                Toast.makeText(getContext(), R.string.noti_load_fail, Toast.LENGTH_LONG).show();
            }
        });
    }
}
