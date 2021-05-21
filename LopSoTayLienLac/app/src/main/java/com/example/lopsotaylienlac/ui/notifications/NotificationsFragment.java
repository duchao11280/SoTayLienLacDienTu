package com.example.lopsotaylienlac.ui.notifications;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lopsotaylienlac.R;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;

    private ImageView imageView;
    private RecyclerView recyclerView;
    private TextView textView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_student_notification, container, false);

        textView = (TextView)root.findViewById(R.id.txtNoneLoad);
        imageView = (ImageView)root.findViewById(R.id.imgPopUp);
        recyclerView = (RecyclerView)root.findViewById(R.id.rcvNoti);


        //enter NumofItem
//        setText(1);




        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUpMenu();
            }
        });

        return root;
    }


    private void setText(int numItem){
        if (numItem == 0) {
            textView.setVisibility(View.INVISIBLE);
        }
        else return;
    }



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

    private void openConfirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.dialog_confirm,null);
        Button btnYes = view.findViewById(R.id.btnYes);
        Button btnNo = view.findViewById(R.id.btnNo);

        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setView(view);

        alertDialog.show();
    }
}