package com.example.lopsotaylienlac.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lopsotaylienlac.R;
import com.example.lopsotaylienlac.beans.Student;
import com.example.lopsotaylienlac.beans.Subjectclass;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Student> lstStudent = new ArrayList<>();
    Context context;

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
    }

    @Override
    public int getItemCount() {
        if(lstStudent!=null)
            return lstStudent.size();
        return 0;
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder{
        private TextView txtMSSV,txtHovaten;
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMSSV = (TextView) itemView.findViewById(R.id.txtMSSV);
            txtHovaten= (TextView) itemView.findViewById(R.id.txtHovaTen);

        }
    }
}
