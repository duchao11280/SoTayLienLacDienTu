package com.example.lopsotaylienlac.ui.account;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.lopsotaylienlac.LoginActivity;
import com.example.lopsotaylienlac.R;


public class AccountFragment extends Fragment {

    private SharedPreferences sharedPreferences;
    TextView txtUserFullname;
    TextView txtRole;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        sharedPreferences = this.getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        View root = inflater.inflate(R.layout.fragment_account, container, false);

        //Init View
        txtUserFullname = root.findViewById(R.id.txtName);
        txtRole = root.findViewById(R.id.txtRole);
        txtUserFullname.setText(sharedPreferences.getString("userFullName","Người dùng"));
        int role = sharedPreferences.getInt("role",-1);
        switch (role){
            case 0:
                txtRole.setText("Admin");
                break;
            case 1:
                txtRole.setText("Sinh viên");
                break;
            case 2:
                txtRole.setText("Phụ huynh");
                break;
            default:
                break;
        }


        LinearLayout btnInforFragment = root.findViewById(R.id.btnOpenInforFragment);

        //Open INFORMATION ACCOUNT
        btnInforFragment.setOnClickListener(v -> {
            NavHostFragment.findNavController(AccountFragment.this).navigate(R.id.fragment_account_info);
        });
        LinearLayout btnLogout = root.findViewById(R.id.btnLogout);

        //LOG OUT
        btnLogout.setOnClickListener(v -> {
            editor.putInt("role",-1);
            editor.putString("userID","");
            editor.putString("userFullName","");
            editor.putString("dob","");
            editor.putString("phonenumber","");
            editor.putString("address","");
            editor.putString("class","");
            editor.putBoolean("checkLogin",false);
            editor.apply();
            editor.commit();
            getActivity().finish();
            startActivity(new Intent(getContext(),LoginActivity.class));
        });
        return root;
    }
}
