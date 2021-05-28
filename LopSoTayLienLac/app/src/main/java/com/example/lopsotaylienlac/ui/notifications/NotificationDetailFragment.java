package com.example.lopsotaylienlac.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lopsotaylienlac.R;

public class NotificationDetailFragment extends Fragment {

    private int role, announID;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_noti_detail, container, false);

        System.out.println("In Detail Fragment: "+role+announID);

        return  root;
    }

    public void getDate(int role, int uid) {
        this.role = role;
        this.announID = uid;
    }
}
