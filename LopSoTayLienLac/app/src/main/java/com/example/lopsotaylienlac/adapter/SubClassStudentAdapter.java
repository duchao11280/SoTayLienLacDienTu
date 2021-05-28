package com.example.lopsotaylienlac.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lopsotaylienlac.R;
import com.example.lopsotaylienlac.apis.UserApi;
import com.example.lopsotaylienlac.beans.Subjectofstudent;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubClassStudentAdapter extends RecyclerView.Adapter<SubClassStudentAdapter.SubClassStudentViewHolder> {

    private List<Subjectofstudent> lstSubofStudent = new ArrayList<>();
    private int id;

    public SubClassStudentAdapter(List<Subjectofstudent> lstSubofStudent, int id) {
        this.lstSubofStudent = lstSubofStudent;
        this.id = id;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubClassStudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class_fee, parent, false);
        return new SubClassStudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubClassStudentViewHolder holder, int position) {

        Subjectofstudent itemSubStu = lstSubofStudent.get(position);

        if (itemSubStu == null)
            return;

        holder.txtClassName.setText(itemSubStu.getSubjectName());
        holder.txtClassID.setText(itemSubStu.getSubjectID());
        holder.txtNumCre.setText(itemSubStu.getCredit()+" TC");

        if(itemSubStu.isPaid() == true)
            holder.chkisPaid.setChecked(true);
        else
            holder.chkisPaid.setChecked(false);
        System.out.println("In ViewHolder");

    }

    @Override
    public int getItemCount() {
        return lstSubofStudent.size();
    }

    public class SubClassStudentViewHolder extends RecyclerView.ViewHolder{
        private TextView txtClassName, txtClassID, txtNumCre;
        private CheckBox chkisPaid;

        public SubClassStudentViewHolder(@NonNull View itemView) {
            super(itemView);

            txtClassName = (TextView) itemView.findViewById(R.id.txtClassName);
            txtClassID = (TextView) itemView.findViewById(R.id.txtClassId);
            txtNumCre = (TextView) itemView.findViewById(R.id.numCre);
            chkisPaid = (CheckBox) itemView.findViewById(R.id.chkPaid);

            chkisPaid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String subClassID = txtClassID.getText().toString();
                    UserApi.apiService.updateIsPaid(id, subClassID ).enqueue(new Callback<Void>() {
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
            });

        }
    }
}
