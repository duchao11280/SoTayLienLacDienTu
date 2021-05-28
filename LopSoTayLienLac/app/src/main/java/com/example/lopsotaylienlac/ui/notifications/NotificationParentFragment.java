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

public class NotificationParentFragment extends Fragment {


    private TextView txtNotiNull;
    private ImageView imageView;
    private RecyclerView recyclerView;

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private int UID;
    private int role;

    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_parent_notification, container, false);

        //get UID from share
        sharedPreferences = this.getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("userID","-1");
        role = sharedPreferences.getInt("rolw", -1);
        System.out.println(id);
        UID = Integer.parseInt(id);


        txtNotiNull = (TextView)root.findViewById(R.id.txtNotiNull);
        imageView = (ImageView)root.findViewById(R.id.imgPopUp);

        //recyclerView
        recyclerView = (RecyclerView)root.findViewById(R.id.rcvNoti);
        showNotification(UID);
        //end recyclerView


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUpMenu(UID);
            }
        });

        return root;
    }


    private void showNotification(int UID) {

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
                adapter = new AnnoucementAdapter(response.body(), NotificationParentFragment.this);
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


    @SuppressLint("RestrictedApi")
    private void showPopUpMenu(int UID) {
        PopupMenu popup = new PopupMenu(getContext(), this.imageView);
        popup.inflate(R.menu.menu_notification_popup);

        Menu menu = popup.getMenu();

        // Register Menu Item Click event.
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return menuItemClicked(item, UID);
            }
        });

        // Show the PopupMenu.
        popup.show();
    }

    private boolean menuItemClicked(MenuItem item, int UID) {
        switch (item.getItemId()) {
            case R.id.mark_as_read:
                UserApi.apiService.markAllAnnounReaderByStudentID(UID).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        System.out.println("Success");
                        //refresh RecyclerView
                        showNotification(UID);
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
        return true;
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
                alertDialog.dismiss();
                //refresh Recycler View
                showNotification(UID);
                Toast.makeText(getContext(), "Đã xóa", Toast.LENGTH_LONG).show();
            }
        });
    }
}
