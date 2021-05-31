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

        //get notification detail by using sharePreferences
        sharedPreferences = getContext().getSharedPreferences("notificationDetail", Context.MODE_PRIVATE);

        //get value by using sharePre
        int announID = sharedPreferences.getInt("announID", -1);//id thông báo
        int role = sharedPreferences.getInt("role", -1);//quyền của người xem
        int uid = sharedPreferences.getInt("uid", -1);//id của người dùng
        String type = sharedPreferences.getString("type", "null");//loại truy cập management hay client

        //get View
        txtTitle = (TextView) root.findViewById(R.id.txtTitleDe);
        txtSender = (TextView) root.findViewById(R.id.txtFrom);
        txtReceiver = (TextView) root.findViewById(R.id.txtTo);
        txtDateSend = (TextView) root.findViewById(R.id.txtDate);
        txtContent = (TextView) root.findViewById(R.id.txtContent);
        btnOK = (Button) root.findViewById(R.id.btnOK);

        //show notification detail
        showDetailNotification(announID);

        //mark as read a notification
        markAsRead(role, announID, uid);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //return previous fragment
                returnNotificationFragment(type);
            }
        });
        return  root;
    }

    private void markAsRead(int role, int announID, int uid) {
        if(role == 1)
            //if you is a student, update idRead to table Student
            markAsReadStudent(announID, uid);
        else
        //if you is a parent, update isRead to table parent
            markAsReadParent(announID, uid);
    }

    private void markAsReadParent(int announID, int uid) {
        //call api mark as read parent, apiname has a mistake :)
        UserApi.apiService.markReadedParent(uid, announID).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                //success call api
                System.out.println("Success");
            }

            //fail call api
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println("Fail");
            }
        });
    }

    private void markAsReadStudent(int announID, int uid) {
        //call api mark as read by student
        UserApi.apiService.markReadedStudent(uid, announID).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                //call success
                System.out.println("Success");
            }

            //call fail
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println("Fail");
            }
        });
    }

    private void returnNotificationFragment(String type) {
        //return previous fragment
        if (type.equals("Management"))
            //if you management notification, return  fragment NotificationAdminManangementFragment
            NavHostFragment.findNavController(NotificationDetailFragment.this).navigate(R.id.fragment_admin_management_notification);
        else if(type.equals("Client"))
            //if you is a client, just read notification, return NotificationFragment
            NavHostFragment.findNavController(NotificationDetailFragment.this).navigate(R.id.navigation_nofitication);
    }

    private void showDetailNotification(int announID) {
        //call api get Notification detail
        UserApi.apiService.getDetailAnnounByAnnounID(announID).enqueue(new Callback<Announcement>() {
            @Override
            public void onResponse(Call<Announcement> call, Response<Announcement> response) {
                //format date
                String dateSend =  DateFormat.format("dd/MM/yyyy",response.body().getDateSend()).toString();

                //set value for view
                txtTitle.setText(response.body().getTitle());
                txtSender.setText("> from "+response.body().getSenderName());
                txtReceiver.setText("to "+response.body().getReceiverName());
                txtDateSend.setText(dateSend);
                txtContent.setText(response.body().getAnnounContent());
            }

            @Override
            public void onFailure(Call<Announcement> call, Throwable t) {
                //call fail
                System.out.println("Fail get Announ");
            }
        });
    }
}
