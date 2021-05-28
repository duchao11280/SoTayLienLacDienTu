package com.example.lopsotaylienlac.ui.fee;

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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeeAdminFragment extends Fragment {

    private ImageView btnSearch, btnEdit;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_admin_fee, container, false);

        btnEdit =(ImageView) root.findViewById(R.id.btnEdit);
        btnSearch = (ImageView)root.findViewById(R.id.btnSearch);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(FeeAdminFragment.this).navigate(R.id.fragment_admin_edit_fee);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStudentFeeSearchFragment();
            }
        });

        recyclerView = (RecyclerView)root.findViewById(R.id.rcvFee);
        showFeeList();
        return root;
    }

    private void openStudentFeeSearchFragment() {
        NavHostFragment.findNavController(FeeAdminFragment.this).navigate(R.id.fragment_class_per_student);
    }

    private void showFeeList() {
        UserApi.apiService.getAllFee().enqueue(new Callback<ArrayList<Fee>>() {
            @Override
            public void onResponse(Call<ArrayList<Fee>> call, Response<ArrayList<Fee>> response) {
                adapter = new AdminFeeAdapter(response.body());
                layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(layoutManager);

                System.out.println(response.body().get(1).getYear());

                Toast.makeText(getContext(), R.string.noti_load, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ArrayList<Fee>> call, Throwable t) {
                Toast.makeText(getContext(), R.string.noti_load_fail, Toast.LENGTH_LONG).show();
            }
        });
    }
}
