package com.example.lopsotaylienlac.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lopsotaylienlac.R;
import com.example.lopsotaylienlac.beans.Subjectclass;

import java.util.ArrayList;
import java.util.List;

public class SubjectClassApdapter extends RecyclerView.Adapter<SubjectClassApdapter.SubjectClassViewHolder> {

    private List<Subjectclass> lstSubject = new ArrayList<>();

    public void setData(List<Subjectclass> list){
        this.lstSubject = list;
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
    public class SubjectClassViewHolder extends RecyclerView.ViewHolder
    {
        private TextView txtMaLop,txtTenLop;

        public SubjectClassViewHolder(@NonNull View itemView) {
            super(itemView);
            //Anh xa view thong qua find
            txtMaLop = (TextView) itemView.findViewById(R.id.txtMaLop);
            txtTenLop = (TextView) itemView.findViewById(R.id.txtTenLop);
        }
    }
}
