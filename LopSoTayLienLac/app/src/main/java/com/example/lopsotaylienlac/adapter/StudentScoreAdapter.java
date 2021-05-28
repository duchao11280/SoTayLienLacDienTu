package com.example.lopsotaylienlac.adapter;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lopsotaylienlac.R;
import com.example.lopsotaylienlac.beans.Subjectofstudent;

import java.util.ArrayList;
import java.util.List;

public class StudentScoreAdapter extends RecyclerView.Adapter<StudentScoreAdapter.StudentScoreViewHolder>{

    private List<Subjectofstudent> mlstSubStudent = new ArrayList<>();

    public StudentScoreAdapter(List<Subjectofstudent> mlstSubStudent) {
        this.mlstSubStudent = mlstSubStudent;
        notifyDataSetChanged();

    }
    /**
     * tao view layout
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public StudentScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student_check_score,parent,false);

        return new StudentScoreViewHolder(view);
    }
    /**
     * set du lieu len view
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull StudentScoreViewHolder holder, int position) {
        Subjectofstudent subjectofstudent = mlstSubStudent.get(position);
        if(subjectofstudent ==null)
            return;
        holder.txtMonhocScore.setText( subjectofstudent.getSubjectName());
        holder.txtDiemScore.setText(""+((subjectofstudent.getScoreMidTerm() + subjectofstudent.getScoreFinalTerm())/2));
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
    public class StudentScoreViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener
    {
        private TextView txtMonhocScore,txtDiemScore;
        ConstraintLayout item_row_score;

        public StudentScoreViewHolder(@NonNull View itemView) {
            super(itemView);
            //Anh xa view thong qua find
            txtMonhocScore = (TextView) itemView.findViewById(R.id.txtMonhocScore);
            txtDiemScore = (TextView) itemView.findViewById(R.id.txtDiemScore);
            item_row_score = itemView.findViewById(R.id.item_student_score_row);
            item_row_score.setOnCreateContextMenuListener(this);

        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(this.getAdapterPosition(),001,0,"Xem Chi Tiet");

        }
    }
}
