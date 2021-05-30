package com.example.lopsotaylienlac.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lopsotaylienlac.R;
import com.example.lopsotaylienlac.beans.Subjectofstudent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SubClassStudentAdapter extends RecyclerView.Adapter<SubClassStudentAdapter.SubClassStudentViewHolder> {

    private List<Subjectofstudent> lstSubofStudent = new ArrayList<>();
    private int id;
    private List<String> lstIsPaid = new ArrayList<String>();
    private  Set<String> set = new HashSet<>();
    private Context context;

    public SubClassStudentAdapter(List<Subjectofstudent> lstSubofStudent, Context context, int id) {
        this.lstSubofStudent = lstSubofStudent;
        this.id = id;
        this.context = context;
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

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(isLongClick)
                    return;
                else{
                    holder.sharedPreferences = context.getSharedPreferences("listIsPaid", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = holder.sharedPreferences.edit();
                    String sid = itemSubStu.getSubjectID().toString().trim();

                    if (!lstIsPaid.contains(sid))
                        {   lstIsPaid.add(sid);
                            set.add(sid);
                        }

                    editor.putStringSet("subClassID",set);
                    editor.putInt("studentID", id);
                    editor.apply();
                    editor.commit();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return lstSubofStudent.size();
    }

    public class SubClassStudentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private TextView txtClassName, txtClassID, txtNumCre;
        private CheckBox chkisPaid;
        private ItemClickListener itemClickListener;
        private LinearLayout item;
        private SharedPreferences sharedPreferences;

        public SubClassStudentViewHolder(@NonNull View itemView) {
            super(itemView);

            txtClassName = (TextView) itemView.findViewById(R.id.txtClassName);
            txtClassID = (TextView) itemView.findViewById(R.id.txtClassId);
            txtNumCre = (TextView) itemView.findViewById(R.id.numCre);
            chkisPaid = (CheckBox) itemView.findViewById(R.id.chkPaid);
            
            chkisPaid.setOnClickListener(this);
            chkisPaid.setOnLongClickListener(this);
        }
        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }
}
