

package com.example.lopsotaylienlac.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lopsotaylienlac.R;
import com.example.lopsotaylienlac.adapter.AnnoucementAdapter;
import com.example.lopsotaylienlac.apis.UserApi;
import com.example.lopsotaylienlac.beans.Announcement;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationAdminFragment extends Fragment {

    private TextView txtNotiNull;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_admin_notification, container, false);


        System.out.println("Admin Here");
        txtNotiNull = (TextView)root.findViewById(R.id.txtNotiNull);
        //recyclerView
        recyclerView = (RecyclerView)root.findViewById(R.id.rcvNoti);
        showNotification();
        //end recyclerView

        return root;
    }

    private void showNotification() {
        List<Announcement> list = new ArrayList<>();
        UserApi.apiService.getAllAnnouncement().enqueue(new Callback<ArrayList<Announcement>>() {
            @Override
            public void onResponse(Call<ArrayList<Announcement>> call, Response<ArrayList<Announcement>> response) {

                //set visible or invisible for textview
                if (response.body()==null)
                    txtNotiNull.setVisibility(View.VISIBLE);
                else  txtNotiNull.setVisibility(View.INVISIBLE);
                //
                //setdata
                adapter = new AnnoucementAdapter(response.body());
                //set Layout Management
                layoutManager= new LinearLayoutManager(getContext());
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(layoutManager);
            }

            @Override
            public void onFailure(Call<ArrayList<Announcement>> call, Throwable t) {
                System.out.println("Fail");
            }
        });

    }
}
