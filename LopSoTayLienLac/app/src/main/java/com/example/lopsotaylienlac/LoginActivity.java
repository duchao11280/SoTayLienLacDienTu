package com.example.lopsotaylienlac;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lopsotaylienlac.apis.UserApi;
import com.example.lopsotaylienlac.beans.Admin;
import com.example.lopsotaylienlac.beans.Parent;
import com.example.lopsotaylienlac.beans.Student;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Spinner rolespinner;
    Button btnSignin;
    EditText edtID;
    EditText edtPass;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView(){
        btnSignin = findViewById(R.id.btnSignin);
        edtID = findViewById(R.id.edtUsername);
        edtPass = findViewById(R.id.edtPassword);
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        if(sharedPreferences.getBoolean("checkLogin",false)==true){
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    doSignin();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //Set data for spinner Role
        rolespinner = findViewById(R.id.spnRole);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.role_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rolespinner.setAdapter(adapter);

    }

    private void doSignin() throws InterruptedException {
        String username = edtID.getText().toString().trim();
        String password = edtPass.getText().toString();
        if(username.length() == 0){
            edtID.setError("Bạn chưa nhập tài khoản!");
            return;
        }
        else{
            edtID.setError(null);
        }
        if(password.length() == 0){
            edtPass.setError("Bạn chưa nhập mật khẩu!");
            return;
        }
        else {
            edtPass.setError(null);
        }

        //Get User here...
        String role_string = rolespinner.getSelectedItem().toString();
        int role = -1;
        if(role_string.equals("Học sinh")) role = 1;//student
        if(role_string.equals("Phụ huynh")) role = 2;//parent
        if(role_string.equals("Admin")) role = 0;//admin

        switch (role){
            case 0:
                getAdmin(username, password);
                break;
            case 1:
                getStudent(username, password);
                break;
            case 2:
                getParent(username, password);
                break;
            default:
                break;
        }

    }

    private void getAdmin(String username, String password){
        UserApi.apiService.getAdminByAdminID(username).enqueue(new Callback<Admin>() {
            @Override
            public void onResponse(Call<Admin> call, Response<Admin> response) {
                System.out.println("Call Success");
                if(response.body()!=null){//Check exist user != null
                    if(!password.equals(response.body().getPassword())){
                        openLoginFailedDialog();
                        return;
                    }
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("role",0);
                    editor.putString("userID",String.valueOf(response.body().getAdminID()));
                    editor.putString("userFullName",response.body().getAdminName());
                    editor.putString("dob","");
                    editor.putString("phonenumber","");
                    editor.putString("address","");
                    editor.putString("class","");
                    editor.putBoolean("checkLogin",true);
                    editor.apply();
                    editor.commit();

                    // Do Successfully Signin!!
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    openLoginFailedDialog();
                }
            }

            @Override
            public void onFailure(Call<Admin> call, Throwable t) {
                openLoginFailedDialog();
            }
        });
    }

    private void getStudent(String username, String password){
        UserApi.apiService.getStudentByID(username).enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                System.out.println("Call Success");
                if(response.body()!=null){//Check exist user != null
                    if(!password.equals(response.body().getPassword())){
                        openLoginFailedDialog();
                        return;
                    }
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("role",1);
                    editor.putString("userID",String.valueOf(response.body().getStudentID()));
                    editor.putString("userFullName",response.body().getStudentName());
                    editor.putString("dob",String.valueOf(response.body().getDob()));
                    editor.putString("phonenumber",response.body().getPhonenumber());
                    editor.putString("address",response.body().getAddress());
                    editor.putString("class",response.body().getClassname());
                    editor.putBoolean("checkLogin",true);
                    editor.apply();
                    editor.commit();

                    // Do Successfully Signin!!
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    openLoginFailedDialog();
                }
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                System.out.println("Call Fail");
                openLoginFailedDialog();
            }
        });
    }

    private void getParent(String username, String password){
        UserApi.apiService.getParentByParentID(username).enqueue(new Callback<Parent>() {
            @Override
            public void onResponse(Call<Parent> call, Response<Parent> response) {
                if(response.body()!=null){//Check exist user != null
                    if(!password.equals(response.body().getPassword())){
                        openLoginFailedDialog();
                        return;
                    }
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("role",2);
                    editor.putString("userID",String.valueOf(response.body().getParentID()));
                    editor.putString("userFullName",response.body().getParentName());
                    editor.putString("dob",String.valueOf(response.body().getDob()));
                    editor.putString("phonenumber",response.body().getPhonenumber());
                    editor.putString("address",response.body().getAddress());
                    editor.putString("class","");
                    editor.putBoolean("checkLogin",true);
                    editor.apply();
                    editor.commit();

                    // Do Successfully Signin!!
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    openLoginFailedDialog();
                }
            }

            @Override
            public void onFailure(Call<Parent> call, Throwable t) {
                openLoginFailedDialog();
            }
        });
    }

    private void openLoginFailedDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_login_failed,null);
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setView(view);
        alertDialog.getWindow().setLayout(400,800);
        Button btnOk = view.findViewById(R.id.btnLoginFailed);
        btnOk.setOnClickListener(v -> {
            alertDialog.cancel();
        });
        alertDialog.show();
    }
}