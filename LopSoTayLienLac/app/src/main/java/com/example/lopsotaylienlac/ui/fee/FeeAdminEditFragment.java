package com.example.lopsotaylienlac.ui.fee;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.lopsotaylienlac.R;
import com.example.lopsotaylienlac.apis.UserApi;

import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeeAdminEditFragment extends Fragment {

    private TextView txtYear;
    private EditText edtFee;
    private Button btnEditFee;
    private ImageView btnReturn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //get view
        View root = inflater.inflate(R.layout.fragment_admin_edit_fee, container, false);

        //gte current Year to show on a textview
        Date currentTime = Calendar.getInstance().getTime();
        //format to get year
        String year = "Năm "+ DateFormat.format("yyyy",currentTime).toString()+":";

        //get veiw
        txtYear = (TextView) root.findViewById(R.id.txtYear);
        edtFee = (EditText) root.findViewById(R.id.edtFee);
        btnEditFee = (Button)root.findViewById(R.id.btnOK);
        btnReturn = (ImageView) root.findViewById(R.id.btnReturn);

        //set value for a text view
        txtYear.setText(year);

        //edit fee of current year
        btnEditFee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editFee();
            }
        });

        //return to previous fragment
        //feeAdminFragment
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnFeeAdminFragment();
            }
        });

        return  root;
    }

    private void returnFeeAdminFragment() {
        //returm FeeAdminFragment
        NavHostFragment.findNavController(FeeAdminEditFragment.this).navigate(R.id.fragment_admin_fee);
    }

    private void editFee() {

        //check null value input
        if(edtFee.getText().toString().length()==0) {
            edtFee.setError("Vui lòng điền số tiền");
            return;
        }

        //convert to int
        int fee =Integer.parseInt(edtFee.getText().toString());
        //call api to add new Fee of current year
        UserApi.apiService.addNewFee(fee).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                //thong bao them thanh cong
                //add success
                Toast.makeText(getContext(), R.string.noti_dathem, Toast.LENGTH_LONG).show();
                //set default value of textview
                edtFee.setText("");
                //returm FeeAdminFragment
                NavHostFragment.findNavController(FeeAdminEditFragment.this).navigate(R.id.fragment_admin_fee);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                //thong bao them that bai
                //add fail
                Toast.makeText(getContext(), R.string.noti_themthatbai, Toast.LENGTH_LONG).show();
            }
        });
    }
}
