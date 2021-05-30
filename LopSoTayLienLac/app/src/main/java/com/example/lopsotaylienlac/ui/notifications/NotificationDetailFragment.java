package com.example.lopsotaylienlac.ui.notifications;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.lopsotaylienlac.R;
import com.example.lopsotaylienlac.apis.UserApi;
import com.example.lopsotaylienlac.beans.Announcement;
import com.example.lopsotaylienlac.ui.fee.FeeAdminEditFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationDetailFragment extends Fragment {
    private SharedPreferences sharedPreferences;

    private TextView txtTitle, txtSender, txtReceiver, txtDateSend, txtContent;
    private Button btnOK;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_noti_detail, container, false);
        sharedPreferences = getContext().getSharedPreferences("notificationDetail", Context.MODE_PRIVATE);

        int announID = sharedPreferences.getInt("announID", -1);
        int role = sharedPreferences.getInt("role", -1);
        int uid = sharedPreferences.getInt("uid", -1);

        txtTitle = (TextView) root.findViewById(R.id.txtTitleDe);
        txtSender = (TextView) root.findViewById(R.id.txtFrom);
        txtReceiver = (TextView) root.findViewById(R.id.txtTo);
        txtDateSend = (TextView) root.findViewById(R.id.txtDate);
        txtContent = (TextView) root.findViewById(R.id.txtContent);
        btnOK = (Button) root.findViewById(R.id.btnOK);

        showDetailNotification(announID);
        markAsRead(role, announID, uid);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnNotificationFragment();
            }
        });

        return  root;
    }

    private void markAsRead(int role, int announID, int uid) {
        if(role == 1)
            markAsReadStudent(announID, uid);
        else
            markAsReadParent(announID, uid);
    }

    private void markAsReadParent(int announID, int uid) {
        UserApi.apiService.markReadedParent(uid, announID).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println("Success");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println("Fail");
            }
        });
    }

    private void markAsReadStudent(int announID, int uid) {
        UserApi.apiService.markReadedStudent(uid, announID).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println("Success");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println("Fail");
            }
        });
    }

    private void returnNotificationFragment() {
        NavHostFragment.findNavController(NotificationDetailFragment.this).navigate(R.id.navigation_nofitication);
    }

    private void showDetailNotification(int announID) {
        UserApi.apiService.getDetailAnnounByAnnounID(announID).enqueue(new Callback<Announcement>() {
            @Override
            public void onResponse(Call<Announcement> call, Response<Announcement> response) {

                String dateSend =  DateFormat.format("yyyy-MM-dd",response.body().getDateSend()).toString();


                txtTitle.setText(response.body().getTitle());
                txtSender.setText("> from "+response.body().getSenderName());
                txtReceiver.setText("to "+response.body().getReceiverName());
                txtDateSend.setText(dateSend);
                txtContent.setText(response.body().getAnnounContent());

                System.out.println("Success");
            }

            @Override
            public void onFailure(Call<Announcement> call, Throwable t) {
                System.out.println("Fail get Announ");
            }
        });
    }
}
