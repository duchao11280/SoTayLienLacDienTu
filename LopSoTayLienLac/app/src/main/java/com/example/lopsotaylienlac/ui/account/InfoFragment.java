package com.example.lopsotaylienlac.ui.account;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lopsotaylienlac.R;

public class InfoFragment extends Fragment {

    ImageView btnEditInfo;
    TextView txtName;
    TextView txtDob;
    TextView txtPhonenumber;
    TextView txtAddress;
    TextView txtClass;
    TextView txtTitleClass;
    TextView txtUserFullname;
    TextView txtRole;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_account_info, container, false);

        //Initial view with elements
        txtName = root.findViewById(R.id.txtFullName);
        txtDob = root.findViewById(R.id.txtDob);
        txtPhonenumber = root.findViewById(R.id.txtPhonenumber);
        txtAddress = root.findViewById(R.id.txtAddress);
        txtClass = root.findViewById(R.id.txtUserClass);
        txtTitleClass = root.findViewById(R.id.txtTitleClass);
        txtUserFullname = root.findViewById(R.id.txtName);
        txtRole = root.findViewById(R.id.txtRole);

        //Set text for view
        sharedPreferences = this.getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        txtName.setText(sharedPreferences.getString("userFullName",""));
        txtDob.setText(sharedPreferences.getString("dob",""));
        txtPhonenumber.setText(sharedPreferences.getString("phonenumber",""));
        txtAddress.setText(sharedPreferences.getString("address",""));
        txtClass.setText(sharedPreferences.getString("class",""));
        txtUserFullname.setText(sharedPreferences.getString("userFullName","Người dùng"));
        btnEditInfo = root.findViewById(R.id.btnEditInfo);
        int role = sharedPreferences.getInt("role",-1);
        switch (role){
            case 0:
                txtRole.setText("Admin");
                btnEditInfo.setVisibility(View.INVISIBLE);
                break;
            case 1:
                txtRole.setText("Sinh viên");
                break;
            case 2:
                txtRole.setText("Phụ huynh");
                btnEditInfo.setVisibility(View.INVISIBLE);
                txtTitleClass.setVisibility(View.INVISIBLE);
                break;
            default:
                break;
        }

        //Do button click change info
        btnEditInfo.setOnClickListener(v -> {
            NavHostFragment.findNavController(InfoFragment.this).navigate(R.id.fragment_account_edit_info);
        });

        return root;
    }
}