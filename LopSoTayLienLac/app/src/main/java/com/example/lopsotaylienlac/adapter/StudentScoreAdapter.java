package com.example.lopsotaylienlac.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lopsotaylienlac.BuildConfig;
import com.example.lopsotaylienlac.R;
import com.example.lopsotaylienlac.apis.UserApi;
import com.example.lopsotaylienlac.beans.Subjectofstudent;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static android.content.Context.MODE_PRIVATE;

public class StudentScoreAdapter extends RecyclerView.Adapter<StudentScoreAdapter.StudentScoreViewHolder>{

    private List<Subjectofstudent> mlstSubStudent = new ArrayList<>();
    Context context;
    SharedPreferences sharedPreferences;
    private Button btnScoreOK;

    public StudentScoreAdapter(List<Subjectofstudent> mlstSubStudent,Context context) {
        this.mlstSubStudent = mlstSubStudent;
        this.context = context;
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

        holder.setItemClickListener(new ItemClickListener() {

            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(isLongClick)
                    return;
                else {

                    holder.sharedPreferences = context.getSharedPreferences("dataLogin", MODE_PRIVATE);
                    int id = Integer.parseInt(holder.sharedPreferences.getString("userID","-1"));

                    openScoreDialog(position,id);
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
        if(mlstSubStudent != null){
            return mlstSubStudent.size();
        }
        return 0;
    }

    /**
     * Thiet ke view truyen vao adapter
     */
    public class StudentScoreViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener
    {
        private TextView txtMonhocScore,txtDiemScore;
//        ConstraintLayout item_row_score;
        private ItemClickListener itemClickListener;
        SharedPreferences sharedPreferences;

        public StudentScoreViewHolder(@NonNull View itemView) {
            super(itemView);
            //Anh xa view thong qua find
            txtMonhocScore = (TextView) itemView.findViewById(R.id.txtMonhocScore);
            txtDiemScore = (TextView) itemView.findViewById(R.id.txtDiemScore);
//            item_row_score = itemView.findViewById(R.id.item_student_score_row);
//            item_row_score.setOnCreateContextMenuListener(this);
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

    public void openScoreDialog(int x,int idd){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = li.inflate(R.layout.dialog_student_check_score,null);
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        UserApi.apiService.getAllSubclassByStudentID(idd).enqueue(new Callback<ArrayList<Subjectofstudent>>() {
            @Override
            public void onResponse(Call<ArrayList<Subjectofstudent>> call, Response<ArrayList<Subjectofstudent>> response) {
                /**
                 * anh xa du lieu tu view
                 */
                TextView txtTenMonHoc = view.findViewById(R.id.txtTitle);
                TextView txtDiemGiua = view.findViewById(R.id.txtDiemGiuaKi);
                TextView txtDiemCuoi = view.findViewById(R.id.txtDiemCuoiKi);
                TextView txtDiemTong = view.findViewById(R.id.txtDiemTongKet);

                String sjname = response.body().get(x).getSubjectName();
                float mid = response.body().get(x).getScoreMidTerm();
                float finalscore = response.body().get(x).getScoreFinalTerm();
                float tong = (mid+finalscore)/2;
                String txtDiemGiuaKi = String.valueOf(mid);
                String txtDiemCuoiKi = String.valueOf(finalscore);
                String txtDiemTongKet = String.valueOf(tong);


                txtDiemGiua.setText(txtDiemGiuaKi);
                txtDiemCuoi.setText(txtDiemCuoiKi);
                txtDiemTong.setText(txtDiemTongKet);
                txtTenMonHoc.setText(sjname);
            }

            @Override
            public void onFailure(Call<ArrayList<Subjectofstudent>> call, Throwable t) {

            }
        });

        alertDialog.setView(view);
        alertDialog.show();

        /**
         * su kien nut OK
         */
        btnScoreOK = view.findViewById(R.id.btnScoreOK);
        btnScoreOK.setOnClickListener(v -> {
            alertDialog.cancel();
        });
    }
}
