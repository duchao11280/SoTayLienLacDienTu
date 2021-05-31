package com.example.lopsotaylienlac.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lopsotaylienlac.R;
import com.example.lopsotaylienlac.apis.UserApi;
import com.example.lopsotaylienlac.beans.Student;
import com.example.lopsotaylienlac.beans.Subjectclass;
import com.example.lopsotaylienlac.beans.Subjectofstudent;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Student> lstStudent = new ArrayList<>();
    Context context;
    private EditText edtMidGrades,edtFinalGrades;
    private Button btnFillOK;
    public StudentAdapter(List<Student> lstStudent, Context context) {
        this.lstStudent = lstStudent;
        this.context = context;
    }
    /**
     * tao view layout
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student,parent,false);
        return new StudentViewHolder(view);
    }
    /**
     * set du lieu len view
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = lstStudent.get(position);
        if(student==null)
            return;
        holder.txtMSSV.setText(""+student.getStudentID());
        holder.txtHovaten.setText(student.getStudentName());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(isLongClick)
                    return;
                else {
                    holder.sharedPreferences = context.getSharedPreferences("subjectClass",Context.MODE_PRIVATE);
                    String subjectID = holder.sharedPreferences.getString("subjectclassID","");
                    System.out.println("Position: "+position + "Student: "+student.getStudentID()+"SubjectID: "+subjectID);
                    openFillGradesDialog(student.getStudentID(),subjectID);
                }
            }
        });
    }

    private void openFillGradesDialog(int studentID, String subjectID) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = li.inflate(R.layout.dialog_fillgrades,null);
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        edtMidGrades = view.findViewById(R.id.edtMidGrades);
        edtFinalGrades = view.findViewById(R.id.edtFinalGrades);
        btnFillOK = view.findViewById(R.id.btnFillOK);
        UserApi.apiService.getDetailsSubjectclass(studentID,subjectID).enqueue(new Callback<Subjectofstudent>() {
            @Override
            public void onResponse(Call<Subjectofstudent> call, Response<Subjectofstudent> response) {
                edtMidGrades.setText(""+response.body().getScoreMidTerm());
                edtFinalGrades.setText(""+response.body().getScoreFinalTerm());
            }

            @Override
            public void onFailure(Call<Subjectofstudent> call, Throwable t) {
                Toast.makeText(context,"Error 404",Toast.LENGTH_SHORT).show();
            }
        });
        btnFillOK.setOnClickListener(v -> {
            float midGrades = Float.parseFloat(edtMidGrades.getText().toString());
            float finalGrades = Float.parseFloat(edtFinalGrades.getText().toString());

            if(checkPoint(midGrades) == false)
                edtMidGrades.setError("Nhập số điểm hợp lệ");
            else
                if(checkPoint(finalGrades) == false)
                    edtFinalGrades.setError("Nhập số điểm hợp lệ");
                else
                    UserApi.apiService.updateGrades(studentID,subjectID,midGrades,finalGrades).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            alertDialog.cancel();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(context,"Error 404", Toast.LENGTH_SHORT).show();
                        }
                    });

        });
        alertDialog.setView(view);
        alertDialog.show();
    }

    @Override
    public int getItemCount() {
        if(lstStudent!=null)
            return lstStudent.size();
        return 0;
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        private TextView txtMSSV,txtHovaten;
        private ItemClickListener itemClickListener;
        SharedPreferences sharedPreferences;
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMSSV = (TextView) itemView.findViewById(R.id.txtMSSV);
            txtHovaten= (TextView) itemView.findViewById(R.id.txtHovaTen);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }
        public void setItemClickListener(ItemClickListener itemClickListener)
        {
            this.itemClickListener = itemClickListener;
        }
        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),false);
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }

    private boolean checkPoint(float inp){
        if(inp>(float)10)
            return false;
        return true;
    }
}
