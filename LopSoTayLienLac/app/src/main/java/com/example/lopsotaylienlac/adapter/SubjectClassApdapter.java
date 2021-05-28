package com.example.lopsotaylienlac.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.constraintlayout.widget.ConstraintLayout;

import androidx.navigation.NavController;

import androidx.recyclerview.widget.RecyclerView;

import com.example.lopsotaylienlac.adapter.ItemClickListener;
import com.example.lopsotaylienlac.MainActivity;
import com.example.lopsotaylienlac.R;
import com.example.lopsotaylienlac.beans.Subjectclass;
import com.example.lopsotaylienlac.ui.dashboard.DashboardFragment;
import com.example.lopsotaylienlac.ui.sbclass.DetailSubjectFragment;
import com.example.lopsotaylienlac.ui.sbclass.ManagementClassFragment;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class SubjectClassApdapter extends RecyclerView.Adapter<SubjectClassApdapter.SubjectClassViewHolder> {

    private List<Subjectclass> lstSubject = new ArrayList<>();
    NavController navController;
    Context context;



    public SubjectClassApdapter(List<Subjectclass> lstSubject, NavController navController,Context context) {
        this.lstSubject = lstSubject;
        this.navController = navController;
        this.context=context;
    }


    /**
     * tao view layout
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public SubjectClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class,parent,false);
        return new SubjectClassViewHolder(view);
    }
    /**
     * set du lieu len view
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull SubjectClassViewHolder holder, int position) {
        Subjectclass sbclass = lstSubject.get(position);
        if(sbclass==null)
            return;
        holder.txtMaLop.setText(sbclass.getSubjectID());
        holder.txtTenLop.setText(sbclass.getSubjectName());
        holder.setItemClickListener(new ItemClickListener() {

            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(isLongClick)
                    return;
                else {

                    holder.sharedPreferences = context.getSharedPreferences("subjectClass", MODE_PRIVATE);
                    SharedPreferences.Editor editor = holder.sharedPreferences.edit();
                    editor.putString("subjectclassID",sbclass.getSubjectID());
                    editor.apply();
                    editor.commit();
                    navController.navigate(R.id.fragment_class_detail);

                }
            }
        });
    }
    /**
     * Tra ve so luong item trong list
     * @return
     */
    @Override
    public int getItemCount() {
        return lstSubject.size();
    }

    //Thiet ke view de truyen vao Adapter
    public class SubjectClassViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener
    {
        // Khai báo interface
        private ItemClickListener itemClickListener;
        private TextView txtMaLop,txtTenLop;
        ConstraintLayout itemClass;
        SharedPreferences sharedPreferences;
        public SubjectClassViewHolder(@NonNull View itemView) {
            super(itemView);
            //Anh xa view thong qua find
            txtMaLop = (TextView) itemView.findViewById(R.id.txtMaLop);
            txtTenLop = (TextView) itemView.findViewById(R.id.txtTenLop);
            itemClass = itemView.findViewById(R.id.itemClass);

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
}
