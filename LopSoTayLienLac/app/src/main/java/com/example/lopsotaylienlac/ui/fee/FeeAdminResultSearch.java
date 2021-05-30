package com.example.lopsotaylienlac.ui.fee;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lopsotaylienlac.R;
import com.example.lopsotaylienlac.adapter.SubClassStudentAdapter;
import com.example.lopsotaylienlac.apis.UserApi;
import com.example.lopsotaylienlac.beans.Student;
import com.example.lopsotaylienlac.beans.Subjectofstudent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeeAdminResultSearch extends Fragment {
    private ImageView btnSearchStudent, btnSaveFee;
    private EditText edtStudentID;
    private TextView txtClassname, txtStudentName;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private SharedPreferences sharedPreferences;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_class_per_student, container, false);

        btnSearchStudent = (ImageView) root.findViewById(R.id.btnSearchStudent);
        txtStudentName = (TextView)root.findViewById(R.id.txtNameStudent);
        txtClassname = (TextView)root.findViewById(R.id.txtNameClass);
        edtStudentID = (EditText)root.findViewById(R.id.edtStudentID);
        recyclerView = (RecyclerView)root.findViewById(R.id.rcvClassSub);
        btnSaveFee = (ImageView)root.findViewById(R.id.btnSaveFee);

        Context context = this.getContext();

        //search Student by StudentID in Edit text
        btnSearchStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edtStudentID.getText().toString();
                //get Student Info
                loadStudentInfor(context, id);
            }
        });

        btnSaveFee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnFeeadminFragment();
            }
        });

        return root;
    }

    private void returnFeeadminFragment() {
        sharedPreferences = this.getActivity().getSharedPreferences("listIsPaid", Context.MODE_PRIVATE);
        Set<String> set = new HashSet<String>();
        List<String> lstSubClassID = new ArrayList<>();
        set = sharedPreferences.getStringSet("subClassID", null);
        int stID = sharedPreferences.getInt("studentID", -1);

        for (Object ob: set)
            lstSubClassID.add((String) ob);


        for (String subClassID: lstSubClassID){
            UserApi.apiService.updateIsPaid(stID, subClassID.trim()).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    System.out.println("Success");
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    System.out.println("Fail");
                }
            });
        }
        NavHostFragment.findNavController(FeeAdminResultSearch.this).navigate(R.id.fragment_admin_fee);
    }

    private void loadStudentInfor(Context context, String id) {

        UserApi.apiService.getStudentByID(id).enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
              //check con bi loi
                if(response.body() == null){
                    openNullDialog();}
                else{
                txtStudentName.setText(response.body().getStudentName());
                txtClassname.setText(response.body().getClassname());
                loadSearchResult(context, id);
                Toast.makeText(getContext(), R.string.noti_load, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                openNullDialog();
                Toast.makeText(getContext(), R.string.noti_load_fail, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void openNullDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.dialog_search_confirm, null);
        Button btnOK = view.findViewById(R.id.btnSOK);

        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setView(view);
        alertDialog.show();

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

    private void loadSearchResult(Context context, String id) {

        int uid = Integer.parseInt(id);
        UserApi.apiService.getAllSubclassByStudentID(uid).enqueue(new Callback<ArrayList<Subjectofstudent>>() {
            @Override
            public void onResponse(Call<ArrayList<Subjectofstudent>> call, Response<ArrayList<Subjectofstudent>> response) {
                adapter = new SubClassStudentAdapter(response.body(), context, uid);
                layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(layoutManager);
                Toast.makeText(getContext(), R.string.noti_load, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ArrayList<Subjectofstudent>> call, Throwable t) {
                Toast.makeText(getContext(), R.string.noti_load_fail, Toast.LENGTH_LONG).show();
            }
        });

    }
}
