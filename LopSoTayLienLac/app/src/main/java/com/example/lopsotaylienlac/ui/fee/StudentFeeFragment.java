package com.example.lopsotaylienlac.ui.fee;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lopsotaylienlac.R;
import com.example.lopsotaylienlac.adapter.AnnoucementAdapter;
import com.example.lopsotaylienlac.adapter.StudentFeeAdapter;
import com.example.lopsotaylienlac.adapter.SubjectClassApdapter;
import com.example.lopsotaylienlac.apis.UserApi;
import com.example.lopsotaylienlac.beans.Announcement;
import com.example.lopsotaylienlac.beans.Subjectofstudent;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StudentFeeFragment extends Fragment {
//    private RecyclerView recyclerView;
//    private LinearLayoutManager layoutManager;
    private Subjectofstudent subjectofstudent;
    private SharedPreferences sharedPreferences;
    private StudentFeeAdapter studentFeeAdapter;
    private List<Subjectofstudent> mListSubStu;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private TextView txtSubject;
    private TextView txtPrice;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_student_fee, container, false);

        txtSubject = (TextView) root.findViewById(R.id.txtMonhoc) ;
        txtPrice = (TextView) root.findViewById(R.id.txtPrice) ;

        recyclerView = (RecyclerView)root.findViewById(R.id.rcvStudentCheckFee);
        showFee();



        return  root;
    }

    private void showFee() {

        List<Subjectofstudent> list = new ArrayList<>();
        sharedPreferences = this.getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("userID","-1");
        UserApi.apiService.getAllSubclassByStudentID(Integer.parseInt(id)).enqueue(new Callback<ArrayList<Subjectofstudent>>() {
            @Override
            public void onResponse(Call<ArrayList<Subjectofstudent>> call, Response<ArrayList<Subjectofstudent>> response) {

                Subjectofstudent s1 = response.body().get(0);
                System.out.println(s1);

                mListSubStu = response.body();
                System.out.println(mListSubStu.get(0).getSubjectName());
//                studentFeeAdapter.setData(mListSubStu);
//                recyclerView.setAdapter(studentFeeAdapter);



            }

            @Override
            public void onFailure(Call<ArrayList<Subjectofstudent>> call, Throwable t) {
                System.out.println("Fail");

            }
        });


    }
}