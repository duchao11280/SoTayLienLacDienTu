

package com.example.lopsotaylienlac.ui.notifications;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
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

public class NotificationManagementAdminFragment extends Fragment {
    private TextView txtNotiNull;
    private ImageView btnAddNotification;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private SharedPreferences sharedPreferences;

    private int role, UID;

    final String man = "Management";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //get View
        View root = inflater.inflate(R.layout.fragment_admin_management_notification, container, false);

        //use sharePreferences to get data
        sharedPreferences = this.getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("userID","-1");// user id
        role = sharedPreferences.getInt("role", -1);// role
        UID = Integer.parseInt(id);

        //use to change fragment
        NavController navController = NavHostFragment.findNavController(NotificationManagementAdminFragment.this);
        Context context = this.getContext();

        //get view
        txtNotiNull = (TextView)root.findViewById(R.id.txtNotiNull);
        btnAddNotification = (ImageView)root.findViewById(R.id.btnAddNotification);
        //recyclerView
        recyclerView = (RecyclerView)root.findViewById(R.id.rcvNoti);
        //show all notification
        showNotification(navController, context, role, UID);
        //end recyclerView

        //addNotification
        btnAddNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add a notification
                openAdminAddNotificationFragment();
            }
        });
        //end add notification

        return root;
    }

    private void openAdminAddNotificationFragment() {
        //change to NotificationAdminAddFragment
        NavHostFragment.findNavController(NotificationManagementAdminFragment.this).navigate(R.id.fragment_admin_add_notification);
    }

    private void showNotification(NavController navController, Context context, int role, int UID) {
        //call api to get all notification
        UserApi.apiService.getAllAnnouncement().enqueue(new Callback<ArrayList<Announcement>>() {
            @Override
            public void onResponse(Call<ArrayList<Announcement>> call, Response<ArrayList<Announcement>> response) {
                //set visible or invisible for textview
                //invisible "Bạn không có thông báo nào" if you dont have any notification
                if (response.body()==null)
                    txtNotiNull.setVisibility(View.VISIBLE);
                else  txtNotiNull.setVisibility(View.INVISIBLE);
                //
                //setdata
                adapter = new AnnoucementAdapter(response.body(), navController, context, role, UID, man);
                //set Layout Management
                layoutManager= new LinearLayoutManager(getContext());
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(layoutManager);
            }

            @Override
            public void onFailure(Call<ArrayList<Announcement>> call, Throwable t) {
                //call fail
                System.out.println("Fail");
            }
        });

    }
}
