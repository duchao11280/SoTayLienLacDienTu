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
    private EditText edtTitle, edtContent, edtSender;
    private Button btnSendNotification;

    private SharedPreferences sharedPreferences;

    private String[] listSubClassName;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        View root = inflater.inflate(R.layout.fragment_admin_add_notification, container, false);

        sharedPreferences = this.getActivity().getSharedPreferences("subjectClass", Context.MODE_PRIVATE);
        Set<String> set = new HashSet<String>();
        set = sharedPreferences.getStringSet("allSubjectClassName", null);

        if (set != null){System.out.println("InAdminAddFragment: "+set.size());}
        else
            System.out.println("Fail");

        String[] initprio = {"Tất cả"};
        List listb = new ArrayList(Arrays.asList(initprio));
        for (Object b : set)
            listb.add(b);
        Object[] b = listb.toArray();

        spnReceiver = (Spinner)root.findViewById(R.id.spnReceiver);
        spnClassReveive = (Spinner)root.findViewById(R.id.spnClassReceive);
        edtTitle = (EditText) root.findViewById(R.id.edtTitleAddNoti);
        edtContent = (EditText)root.findViewById(R.id.edtContent);
        btnSendNotification = (Button) root.findViewById(R.id.btnSend);

        btnSendNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification();
            }
        });

        ArrayAdapter lstPrio = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,b);
        lstPrio.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnClassReveive.setAdapter(lstPrio);



        //Set data for spinner Role Class Receiver
        ArrayAdapter<CharSequence> adapterCl = ArrayAdapter.createFromResource(getContext(),R.array.spn_Receiver, android.R.layout.simple_spinner_item);
        adapterCl.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnReceiver.setAdapter(adapterCl);

        return root;
    }



    private void sendNotification() {

        sharedPreferences = this.getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);


        String sender = sharedPreferences.getString("userFullName", "null");
        String title = edtTitle.getText().toString().trim();
        String content = edtContent.getText().toString().trim();

        String roleString = spnReceiver.getSelectedItem().toString();

        int role;
        if (roleString.equals(R.string.hocsinh))
            role = 1;
        else role = 2;


        String receive = spnClassReveive.getSelectedItem().toString();

        if (receive.equals("Tất cả"))
            receive = "All";



        UserApi.apiService.addNewAnnouncement(title,sender, content, role, receive ).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getContext(), R.string.noti_dathem, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getContext(), R.string.noti_themthatbai, Toast.LENGTH_LONG).show();
            }
        });
    }
}
