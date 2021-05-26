package com.example.lopsotaylienlac.ui.dashboard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;


import com.example.lopsotaylienlac.R;
import com.example.lopsotaylienlac.ui.account.AccountFragment;


import static android.content.Context.MODE_PRIVATE;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private SharedPreferences sharedPreferences;
    private TextView itdbClass;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sharedPreferences = this.getActivity().getSharedPreferences("dataLogin", MODE_PRIVATE);
        // lấy role khi nguoi dùng đăng nhập ( 0: admin, 1: student, 2: parent)
        int role = sharedPreferences.getInt("role",1);
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = null;
        switch (role){
            case 0:
                root = inflater.inflate(R.layout.fragment_admin_dashboard, container, false);
                itdbClass = root.findViewById(R.id.itdbClass);
                itdbClass.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NavHostFragment.findNavController(DashboardFragment.this).navigate(R.id.fragment_admin_management_allclass);
                        System.out.println("Click success");

                    }
                });
                break;
            case 1:
                root = inflater.inflate(R.layout.fragment_student_dashboard, container, false);
                break;
            case 2:
                root = inflater.inflate(R.layout.fragment_parent_dashboard, container, false);
                break;
            default:
                break;
        }


        return root;
    }
}