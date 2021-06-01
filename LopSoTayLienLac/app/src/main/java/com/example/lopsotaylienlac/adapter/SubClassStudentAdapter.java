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

    //list contain list of subclass of a student
    private List<Subjectofstudent> lstSubofStudent = new ArrayList<>();
    private int id;
    private List<String> lstIsPaid = new ArrayList<String>();
    private Context context;
    private SharedPreferences sharedPreferences;
    private Set<String> set;
    private SharedPreferences.Editor editor;
    //constructor
    public SubClassStudentAdapter(List<Subjectofstudent> lstSubofStudent, Context context, int id) {
        this.lstSubofStudent = lstSubofStudent;
        this.id = id;
        this.context = context;
        set = new HashSet();
        sharedPreferences = context.getSharedPreferences("listIsPaid", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putStringSet("subClassID", set);
        editor.apply();
        editor.commit();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubClassStudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //get view for an item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class_fee, parent, false);
        return new SubClassStudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubClassStudentViewHolder holder, int position) {
        //get data of an item
        Subjectofstudent itemSubStu = lstSubofStudent.get(position);

        //check null
        if (itemSubStu == null)
            return;

        //set text for views
        holder.txtClassName.setText(itemSubStu.getSubjectName());
        holder.txtClassID.setText(itemSubStu.getSubjectID());
        holder.txtNumCre.setText(itemSubStu.getCredit()+" TC");

        //load check/uncheck up to this subclass id paid or/ isnt paid
        if(itemSubStu.isPaid() == true)
            holder.chkisPaid.setChecked(true);
        else
            holder.chkisPaid.setChecked(false);

        //overide item click
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                //dont long click
                if(isLongClick)
                    return;
                else{
                    //create shareprefernces
                    holder.sharedPreferences = context.getSharedPreferences("listIsPaid", Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = holder.sharedPreferences.edit();//editor
                    String sid = itemSubStu.getSubjectID().toString().trim();//id của môn học

                    //check
                    //if id is contain in list, or isnt
                    //add to list if isnt
                    if (!lstIsPaid.contains(sid))
                        {   lstIsPaid.add(sid);
                            set.add(sid);//add to SetString to putStringSet
                        }
                    else
                    {
                        //remove if id is contained in list
                        lstIsPaid.remove(sid);
                        set.remove(sid);
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
            //get view
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
