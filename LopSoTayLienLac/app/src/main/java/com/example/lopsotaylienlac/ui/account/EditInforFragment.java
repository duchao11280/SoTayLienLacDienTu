package com.example.lopsotaylienlac.ui.account;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lopsotaylienlac.R;
import com.example.lopsotaylienlac.apis.UserApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditInforFragment extends Fragment {

    TextView txtUserFullname;
    TextView txtRole;
    EditText edtPhonenumber;
    EditText edtAddress;
    ImageView btnSaveInfo;

    SharedPreferences sharedPreferences;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_account_edit_infor, container, false);

        //Get info from sharedPreferences
        sharedPreferences = this.getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
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

        //Initial View with elements
        edtPhonenumber = root.findViewById(R.id.edtChangePhonenumber);
        edtAddress = root.findViewById(R.id.edtChangeAddress);
        edtAddress.setText(sharedPreferences.getString("address",""));
        edtPhonenumber.setText(sharedPreferences.getString("phonenumber",""));
        btnSaveInfo = root.findViewById(R.id.btnSaveInfor);

        //do click button save info
        btnSaveInfo.setOnClickListener(v -> {
            String userID = sharedPreferences.getString("userID","-1");
            String newPhone = edtPhonenumber.getText().toString();
            String newAddress = edtAddress.getText().toString();
            UserApi.apiService.updateInfo(userID,newPhone,newAddress).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Toast.makeText(getContext(),"Thay đổi thành công",Toast.LENGTH_LONG).show();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("address",newAddress);
                    editor.putString("phonenumber",newPhone);
                    editor.apply();
                    editor.commit();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(getContext(),"Change failed",Toast.LENGTH_LONG).show();
                }
            });
        });

        return root;
    }
}