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
        //get view from layout
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

        //return to FeeAdminFragment
        btnSaveFee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChange();
            }
        });

        return root;
    }

    private void saveChange() {
        //use sharePreferneces to get data
        sharedPreferences = this.getActivity().getSharedPreferences("listIsPaid", Context.MODE_PRIVATE);

        //createSet to get value
        Set<String> set = new HashSet<String>();
        List<String> lstSubClassID = new ArrayList<>();//list subclass ID
        set = sharedPreferences.getStringSet("subClassID", null);//setstring subclass id
        int stID = sharedPreferences.getInt("studentID", -1); //student id

        //set value for list
        for (Object ob: set)
            lstSubClassID.add((String) ob);

        //call api update isPaid for each subClassID in listsubClassID
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
        //return to FeeAdminFragment
        NavHostFragment.findNavController(FeeAdminResultSearch.this).navigate(R.id.fragment_admin_fee);
    }

    private void loadStudentInfor(Context context, String id) {
        // load student info
        //call api to get student info
        UserApi.apiService.getStudentByID(id).enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                //set value for view
                txtStudentName.setText(response.body().getStudentName());//student namw
                txtClassname.setText(response.body().getClassname());//class name
                loadSearchResult(context, id);//load list subclass of student

                //call success
                Toast.makeText(getContext(), R.string.noti_load, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                //open notify that device can not find selected student
                openNullDialog();
                //call fail
                Toast.makeText(getContext(), R.string.noti_load_fail, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void openNullDialog() {
        //dialog notify null return
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

        //load list subclass of student
        //up to student id
        //convert to int
        int uid = Integer.parseInt(id);
        UserApi.apiService.getAllSubclassByStudentID(uid).enqueue(new Callback<ArrayList<Subjectofstudent>>() {
            @Override
            public void onResponse(Call<ArrayList<Subjectofstudent>> call, Response<ArrayList<Subjectofstudent>> response) {
                adapter = new SubClassStudentAdapter(response.body(), context, uid);//create adapter
                layoutManager = new LinearLayoutManager(getContext());//create layout manager

                recyclerView.setAdapter(adapter);//set adapter
                recyclerView.setLayoutManager(layoutManager);//set layout manager

                //call success
                Toast.makeText(getContext(), R.string.noti_load, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ArrayList<Subjectofstudent>> call, Throwable t) {
                //call fail
                Toast.makeText(getContext(), R.string.noti_load_fail, Toast.LENGTH_LONG).show();
            }
        });

    }
}
