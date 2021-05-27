package com.example.lopsotaylienlac.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lopsotaylienlac.R;
import com.example.lopsotaylienlac.beans.Subjectofstudent;

import java.util.ArrayList;
import java.util.List;

public class StudentFeeAdapter  extends RecyclerView.Adapter<StudentFeeAdapter.StudentFeeViewHolder> {

    private List<Subjectofstudent> mlstSubStudent = new ArrayList<>();
    private int feeofyear;

    public StudentFeeAdapter(List<Subjectofstudent> mlstSubStudent, int feeofyear) {
        this.mlstSubStudent = mlstSubStudent;
        this.feeofyear = feeofyear;
    }

    /**
     * tao view layout
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public StudentFeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student_check_fee,parent,false);

        return new StudentFeeViewHolder(view);

    }
    /**
     * set du lieu len view
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull StudentFeeViewHolder holder, int position) {
        Subjectofstudent subjectofstudent = mlstSubStudent.get(position);
        if(subjectofstudent ==null)
            return;
        holder.txtMonHoc.setText( subjectofstudent.getSubjectName());
        holder.txtPrice.setText(""+subjectofstudent.getCredit()*feeofyear);
    }
    /**
     * Tra ve so luong item trong list
     * @return
     */
    @Override
    public int getItemCount() {
        if(mlstSubStudent != null){
            return mlstSubStudent.size();
        }
        return 0;
    }

    /**
     * Thiet ke view truyen vao adapter
     */
    public class StudentFeeViewHolder extends RecyclerView.ViewHolder
    {
        private TextView txtMonHoc,txtPrice;
//        ConstraintLayout item_student_fee_row;

        public StudentFeeViewHolder(@NonNull View itemView) {
            super(itemView);
            //Anh xa view thong qua find
            txtMonHoc = (TextView) itemView.findViewById(R.id.txtMonhoc);
            txtPrice = (TextView) itemView.findViewById(R.id.txtPrice);
//            item_student_fee_row = itemView.findViewById(R.id.item_student_fee_row);

        }
    }
}
