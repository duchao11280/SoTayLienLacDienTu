package com.example.lopsotaylienlac.ui.sbclass;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lopsotaylienlac.R;
import com.example.lopsotaylienlac.adapter.SubjectClassApdapter;
import com.example.lopsotaylienlac.apis.UserApi;
import com.example.lopsotaylienlac.beans.Subjectclass;
import com.example.lopsotaylienlac.ui.dashboard.DashboardFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ManagementClassFragment extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    SubjectClassApdapter subjectClassApdapter;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_admin_management_allclass, container, false);
        recyclerView = (RecyclerView)root.findViewById(R.id.rcvAllSubjectClass);
        // tao navcontroller đẻ chuyển trang
        NavController navController = NavHostFragment.findNavController(ManagementClassFragment.this);
        Context context = this.getContext();
        /**
         * tao va set layout cho recylerview
         */
        layoutManager = new LinearLayoutManager(getContext());
        /**
         * show list subjectclass
         */
        UserApi.apiService.getAllSubjectclass().enqueue(new Callback<ArrayList<Subjectclass>>() {
            @Override
            public void onResponse(Call<ArrayList<Subjectclass>> call, Response<ArrayList<Subjectclass>> response) {
                subjectClassApdapter= new SubjectClassApdapter(response.body(),navController,context);
                recyclerView.setAdapter(subjectClassApdapter);
                recyclerView.setLayoutManager(layoutManager);
            }

            @Override
            public void onFailure(Call<ArrayList<Subjectclass>> call, Throwable t) {
                Toast.makeText(getContext(),"Không có lớp nào",Toast.LENGTH_LONG).show();

            }
        });
        return root;
    }

}