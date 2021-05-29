package com.example.lopsotaylienlac.ui.notifications;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lopsotaylienlac.R;
import com.example.lopsotaylienlac.adapter.AnnoucementAdapter;
import com.example.lopsotaylienlac.apis.UserApi;
import com.example.lopsotaylienlac.beans.Announcement;
import com.example.lopsotaylienlac.ui.sbclass.ManagementClassFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationFragment extends Fragment {


    private TextView txtNotiNull;
    private ImageView imageView;
    private RecyclerView recyclerView;

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private SharedPreferences sharedPreferences;

    private int UID;

    private int role;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notification, container, false);

        NavController navController = NavHostFragment.findNavController(NotificationFragment.this);
        Context context = this.getContext();

        //getUID from share
        sharedPreferences = this.getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("userID","-1");
        role = sharedPreferences.getInt("role", -1);
        UID = Integer.parseInt(id);

        txtNotiNull = (TextView) root.findViewById(R.id.txtNotiNull);
        imageView = (ImageView) root.findViewById(R.id.imgPopUp);

        //recyclerView
        recyclerView = (RecyclerView) root.findViewById(R.id.rcvNoti);
        showNotification(Integer.parseInt(id), role, navController, context);
        //end recyclerView

        if(role == 0)
            imageView.setVisibility(View.INVISIBLE);
        else
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUpMenu(UID, role);
            }
        });

        return root;
    }


    private void showNotification(int UID, int role, NavController navController, Context context) {
        if(role == 0)
            showAdminNotification(UID, navController, context);
        else if(role == 1)
            showStudentNotification(UID, navController, context);
        else showParentNotification(UID, navController, context);
    }

    private void showParentNotification(int uid, NavController navController, Context context) {
        List<Announcement> list = new ArrayList<>();
        UserApi.apiService.getAnnounByParentID(UID).enqueue(new Callback<ArrayList<Announcement>>() {
            @Override
            public void onResponse(Call<ArrayList<Announcement>> call, Response<ArrayList<Announcement>> response) {

                //set visible or invisible for textview
                if (response.body()==null)
                    txtNotiNull.setVisibility(View.VISIBLE);
                else  txtNotiNull.setVisibility(View.INVISIBLE);
                //
                //setdata
                adapter = new AnnoucementAdapter(response.body(),navController, context, role, UID);
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

    private void showAdminNotification(int UID, NavController navController, Context context) {
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
                adapter = new AnnoucementAdapter(response.body(), navController, context, role, UID);
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

    private void showStudentNotification(int UID, NavController navController, Context context){
        List<Announcement> list = new ArrayList<>();
        UserApi.apiService.getAnnounByStudentID(UID).enqueue(new Callback<ArrayList<Announcement>>() {
            @Override
            public void onResponse(Call<ArrayList<Announcement>> call, Response<ArrayList<Announcement>> response) {

                //set visible or invisible for textview
                if(response.body()==null)
                    txtNotiNull.setVisibility(View.VISIBLE);
                else    txtNotiNull.setVisibility(View.INVISIBLE);
                //
                //setdata
                adapter = new AnnoucementAdapter(response.body(), navController, context, role, UID);
                adapter.notifyDataSetChanged();
                //set Layout Management
                layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);

                System.out.println("Success");
            }

            @Override
            public void onFailure(Call<ArrayList<Announcement>> call, Throwable t) {
                System.out.println("Fail");

            }
        });
    }

    @SuppressLint("RestrictedApi")
    private void showPopUpMenu(int UID, int role) {
        PopupMenu popup = new PopupMenu(getContext(), this.imageView);
        popup.inflate(R.menu.menu_notification_popup);

        Menu menu = popup.getMenu();

        // Register Menu Item Click event.
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return menuItemClicked(item, UID, role);
            }
        });

        // Show the PopupMenu.
        popup.show();
    }

    private boolean menuItemClicked(MenuItem item, int uid, int role) {
        if(role == 1)
            menuItemClickedStudent(item);
        else menuItemClickedParent(item);
        return true;
    }

    private void menuItemClickedParent(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mark_as_read:
                UserApi.apiService.markAllAnnounReaderByParentID(UID).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        NavHostFragment.findNavController(NotificationFragment.this).navigate(R.id.navigation_nofitication);
                        System.out.println("Success");
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        System.out.println("Fail");
                    }
                });
                break;
            case R.id.delete:
                openConfirmDialog();
                break;
        }
    }

    private void menuItemClickedStudent(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mark_as_read:
                UserApi.apiService.markAllAnnounReaderByStudentID(UID).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        NavHostFragment.findNavController(NotificationFragment.this).navigate(R.id.navigation_nofitication);
                        System.out.println("Success");
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        System.out.println("Fail");
                    }
                });
                break;
            case R.id.delete:
                openConfirmDialog();
                break;
        }
    }
    //END POPUPMENU

    private void openConfirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.dialog_confirm, null);
        Button btnYes = view.findViewById(R.id.btnYes);
        Button btnNo = view.findViewById(R.id.btnNo);

        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setView(view);
        alertDialog.show();

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserApi.apiService.deleteAllAnnounByStudentID(UID).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        System.out.println("Success");
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        System.out.println("Fail");
                    }
                });
                NavHostFragment.findNavController(NotificationFragment.this).navigate(R.id.navigation_nofitication);
                alertDialog.dismiss();
                Toast.makeText(getContext(), "Đã xóa", Toast.LENGTH_LONG).show();
            }
        });
    }
}
