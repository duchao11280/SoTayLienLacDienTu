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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;

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

    /**
     * Initial View with elements
     */
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

    /**
     * do singin
     * @throws InterruptedException
     */
    private void doSignin() throws InterruptedException {
        String username = edtID.getText().toString().trim();
        String password = edtPass.getText().toString();
        String hassPass = getHashMd5(password);
        System.out.println(hassPass);
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
                getAdmin(username, hassPass);
                break;
            case 1:
                getStudent(username, hassPass);
                break;
            case 2:
                getParent(username, hassPass);
                break;
            default:
                break;
        }

    }

    /**
     * Hass password input by Md5
     * @param password password input
     * @return
     */
    public String getHashMd5(String password){
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(password.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Find Admin and Login
     * @param username Input adminID
     * @param password Input password
     */
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
                    //Put Login info into SharePreferences
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

    /**
     * Find Student and Login
     * @param username Input StudentID
     * @param password Input password
     */
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
                    //Put Login info into SharePreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("role",1);
                    editor.putString("userID",String.valueOf(response.body().getStudentID()));
                    editor.putString("userFullName",response.body().getStudentName());
                    editor.putString("dob", new SimpleDateFormat("dd/MM/yyyy").format(response.body().getDob()));
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

    /**
     * Find Parent and Login
     * @param username Input ParentID
     * @param password Input password
     */
    private void getParent(String username, String password){
        UserApi.apiService.getParentByParentID(username).enqueue(new Callback<Parent>() {
            @Override
            public void onResponse(Call<Parent> call, Response<Parent> response) {
                if(response.body()!=null){//Check exist user != null
                    if(!password.equals(response.body().getPassword())){
                        openLoginFailedDialog();
                        return;
                    }
                    //Put Login info into SharePreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("role",2);
                    editor.putString("userID",String.valueOf(response.body().getParentID()));
                    editor.putString("userFullName",response.body().getParentName());
                    editor.putString("dob", new SimpleDateFormat("dd/MM/yyyy").format(response.body().getDob()));
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

    /**
     * Open dialog when login failed
     */
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

    /**
     * Make app can't back previous activity
     */
    @Override
    public void onBackPressed(){
        Toast.makeText(this,"Không thể quay lại",Toast.LENGTH_LONG).show();
    }
}