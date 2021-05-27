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

public class NotificationStudentFragment extends Fragment {


    private TextView txtNotiNull;
    private ImageView imageView;
    private RecyclerView recyclerView;

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_student_notification, container, false);

        txtNotiNull = (TextView) root.findViewById(R.id.txtNotiNull);
        imageView = (ImageView) root.findViewById(R.id.imgPopUp);

        //recyclerView
        recyclerView = (RecyclerView) root.findViewById(R.id.rcvNoti);
        showNotification();
        //end recyclerView


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUpMenu();
            }
        });

        return root;
    }


    private void showNotification() {

        List<Announcement> list = new ArrayList<>();
        sharedPreferences = this.getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("userID", "null");
        UserApi.apiService.getAnnounBySudentID(Integer.parseInt(id)).enqueue(new Callback<ArrayList<Announcement>>() {
            @Override
            public void onResponse(Call<ArrayList<Announcement>> call, Response<ArrayList<Announcement>> response) {

                //set visible or invisible for textview
                if (response.body() == null)
                    txtNotiNull.setVisibility(View.VISIBLE);
                else txtNotiNull.setVisibility(View.INVISIBLE);
                //
                //setdata
                adapter = new AnnoucementAdapter(response.body());
                //set Layout Management
                layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(layoutManager);
            }

            @Override
            public void onFailure(Call<ArrayList<Announcement>> call, Throwable t) {
                System.out.println("Fail");
            }
        });

    }


    //POPUP MENU
//    private void setText(int numItem){
//        if (numItem == 0) {
//            textView.setVisibility(View.INVISIBLE);
//        }
//        else return;
//    }
    @SuppressLint("RestrictedApi")
    private void showPopUpMenu() {
        PopupMenu popup = new PopupMenu(getContext(), this.imageView);
        popup.inflate(R.menu.menu_notification_popup);

        Menu menu = popup.getMenu();

        // Register Menu Item Click event.
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return menuItemClicked(item);
            }
        });

        // Show the PopupMenu.
        popup.show();
    }

    private boolean menuItemClicked(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mark_as_read:
                Toast.makeText(getContext(), "da doc!!!", Toast.LENGTH_SHORT).show();
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
    }
}
