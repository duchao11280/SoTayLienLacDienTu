package com.example.lopsotaylienlac.ui.notifications;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.lopsotaylienlac.R;
import com.example.lopsotaylienlac.apis.UserApi;
import com.example.lopsotaylienlac.beans.Subjectclass;
import com.example.lopsotaylienlac.beans.Subjectofstudent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationAdminAddFragment extends Fragment {
    private Spinner spnReceiver, spnClassReveive;
    private EditText edtTitle, edtContent;
    private Button btnSendNotification;

    private SharedPreferences sharedPreferences;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //get view
        View root = inflater.inflate(R.layout.fragment_admin_add_notification, container, false);

        //use sharepreferences to get data
        sharedPreferences = this.getActivity().getSharedPreferences("subjectClass", Context.MODE_PRIVATE);

        //get view
        spnReceiver = (Spinner)root.findViewById(R.id.spnReceiver);
        spnClassReveive = (Spinner)root.findViewById(R.id.spnClassReceive);
        edtTitle = (EditText) root.findViewById(R.id.edtTitleAddNoti);
        edtContent = (EditText)root.findViewById(R.id.edtContent);
        btnSendNotification = (Button) root.findViewById(R.id.btnSend);

        //button send notification
        btnSendNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification();
            }
        });

        //define value for spnReceiver
        initSpnReceiver();

        //define value for spnClassReceiver
        initSpnClassReceiver();

        return root;
    }

    private void initSpnReceiver()
    {
        //create adapter and set value for spnReceiver
        ArrayAdapter<CharSequence> adapterCl = ArrayAdapter.createFromResource(getContext(),R.array.spn_Receiver, android.R.layout.simple_spinner_item);
        adapterCl.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnReceiver.setAdapter(adapterCl);
    }

    private void  initSpnClassReceiver()
    {
        //create Set to get value
        Set<String> set = new HashSet<String>();
        set = sharedPreferences.getStringSet("allSubjectClassName", null);

        //create content for spnClassReceiver
        String[] initprio = {"Tất cả"};
        List listb = new ArrayList(Arrays.asList(initprio));
        for (Object b : set)
            listb.add(b);
        Object[] b = listb.toArray();

        //create adapter and set value
        ArrayAdapter lstPrio = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,b);
        lstPrio.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnClassReveive.setAdapter(lstPrio);
    }

    private void sendNotification() {

        //create sharePreferences to get date
        sharedPreferences = this.getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);

        //create value to call api
        String sender = sharedPreferences.getString("userFullName", "null");//người gửi
        String title = edtTitle.getText().toString().trim();//tiêu đề
        String content = edtContent.getText().toString().trim();//nội dung thông báo
        String roleString = spnReceiver.getSelectedItem().toString();//loại người nhận, học sinh hay phụ huynh
        int role;

        //set value up to spnItem selected
        if (roleString.equals("Học sinh"))
            role = 1;
        else role = 2;

        //check constraint
        //check, setError if title is empty
        if(title.length() == 0)
        {edtTitle.setError("Nhập tiêu đề thông báo"); return;}
        //check, setError if content is empty
        if(content.length() == 0)
        {
            edtContent.setError("Nhập nội dung thông báo");
            return;
        }

        //class receive notification
        String receive = spnClassReveive.getSelectedItem().toString();

        //set value for "Tất cả"
        if (receive.equals("Tất cả"))
            receive = "All";

        //call api to add new Notification
        UserApi.apiService.addNewAnnouncement(title,sender, content, role, receive ).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                //add success
                Toast.makeText(getContext(), R.string.noti_dathem, Toast.LENGTH_LONG).show();

                //return  fragment NotificationAdminManangementFragment
                NavHostFragment.findNavController(NotificationAdminAddFragment.this).navigate(R.id.fragment_admin_management_notification);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                //add fail
                Toast.makeText(getContext(), R.string.noti_themthatbai, Toast.LENGTH_LONG).show();
            }
        });
    }
}
