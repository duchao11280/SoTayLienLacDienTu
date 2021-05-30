package com.example.lopsotaylienlac;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.lopsotaylienlac.apis.UserApi;
import com.example.lopsotaylienlac.beans.Subjectclass;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_nofitication,R.id.navigation_account)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        NavigationUI.setupWithNavController(navView, navController);

        sharedPreferences = getSharedPreferences("subjectClass", MODE_PRIVATE);

        setSharePre();

    }

    private void setSharePre() {

        //gọi api lấy danh sách các lớp
        UserApi.apiService.getAllSubjectclass().enqueue(new Callback<ArrayList<Subjectclass>>() {
            @Override
            public void onResponse(Call<ArrayList<Subjectclass>> call, Response<ArrayList<Subjectclass>> response) {

                //tạo list lưu danh sách các lớp
                List<String> lst = new ArrayList<>();

                //lấy giá trị từ response
                for (Subjectclass ele: response.body())
                    lst.add(ele.getSubjectID());

                //tạo set và gán giá trị cho set
                Set<String> set = new HashSet<String>();
                set.addAll(lst);

                //tạo editor
                SharedPreferences.Editor editor = sharedPreferences.edit();

                //share allSubjectClassName để load vào Spinner ở FragmentAddNotification
                editor.putStringSet("allSubjectClassName", set);
                editor.commit();

            }

            @Override
            public void onFailure(Call<ArrayList<Subjectclass>> call, Throwable t) {
                System.out.println("Fail");
            }
        });

    }


}