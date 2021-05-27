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
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lopsotaylienlac.R;
import com.example.lopsotaylienlac.adapter.AnnoucementAdapter;
import com.example.lopsotaylienlac.apis.UserApi;
import com.example.lopsotaylienlac.beans.Announcement;
import com.example.lopsotaylienlac.ui.account.AccountFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NotificationsFragment extends Fragment {
    private NotificationsViewModel notificationsViewModel;
    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_all_role_notification, container, false);


        sharedPreferences = this.getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        System.out.println(sharedPreferences.getString("userFullName","none"));
        int role = sharedPreferences.getInt("role", -1);

        System.out.println("Role: "+role);
        switch (role){
            case 0:
                showAdminNotification();
                break;
            case 1:
                showStudentNotification();
                break;
            case 2:
                showParentNotification();
                break;
            default:
                break;
        }

        System.out.println("Role: "+role);
        return  root;


    }

    private void showParentNotification() {
        NavHostFragment.findNavController(NotificationsFragment.this).navigate(R.id.fragment_parent_notification);
    }
    private void showAdminNotification() {
        System.out.println("Going to Admin View....");
        NavHostFragment.findNavController(NotificationsFragment.this).navigate(R.id.fragment_admin_notification);
    }
    private void showStudentNotification() {
        NavHostFragment.findNavController(NotificationsFragment.this).navigate(R.id.fragment_student_notification);
    }

}
