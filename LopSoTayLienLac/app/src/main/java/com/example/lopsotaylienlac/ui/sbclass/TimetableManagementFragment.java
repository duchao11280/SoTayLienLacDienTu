package com.example.lopsotaylienlac.ui.sbclass;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lopsotaylienlac.R;
import com.example.lopsotaylienlac.adapter.TimetableAdapter;
import com.example.lopsotaylienlac.apis.UserApi;
import com.example.lopsotaylienlac.beans.Timetable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimetableManagementFragment extends Fragment {
    private RecyclerView recyclerView;
    private SharedPreferences sharedPreferencesforload, sharedPreferencesforupdate;
    private TimetableAdapter timetableAdapter;
    private LinearLayoutManager layoutManager;
    ImageView imgSave;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_edit_schedule, container, false);
        sharedPreferencesforload = getContext().getSharedPreferences("subjectClass", Context.MODE_PRIVATE);
        sharedPreferencesforupdate = getContext().getSharedPreferences("timetable", Context.MODE_PRIVATE);
        String sbID = sharedPreferencesforload.getString("subjectclassID","-1");
        recyclerView = root.findViewById(R.id.rcvTimetable);
        layoutManager = new LinearLayoutManager(getContext());
        loadTimetable(sbID);

        imgSave = root.findViewById(R.id.imgSave);
        imgSave.setOnClickListener(v -> {
            saveChange();
        });
        return root;
    }

    private void saveChange() {
        Set<String> set =new HashSet<>();
        set = sharedPreferencesforupdate.getStringSet("timetableID",null);
        String ttbID="";
        for (String timetableID : set){
            ttbID += timetableID;
            ttbID +=",";
        }
        UserApi.apiService.updateIsOff(ttbID).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getContext(),"Saved Success",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getContext(),"Saved Failure",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void loadTimetable(String id){
        UserApi.apiService.getTimetableBySubjectId(id).enqueue(new Callback<ArrayList<Timetable>>() {
            @Override
            public void onResponse(Call<ArrayList<Timetable>> call, Response<ArrayList<Timetable>> response) {
                timetableAdapter = new TimetableAdapter(response.body(),getContext());
                System.out.println(response.body());
                recyclerView.setAdapter(timetableAdapter);
                recyclerView.setLayoutManager(layoutManager);
            }

            @Override
            public void onFailure(Call<ArrayList<Timetable>> call, Throwable t) {
                Toast.makeText(getContext(),"Error 404",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
