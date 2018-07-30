package com.app.mashkabari.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.mashkabari.R;
import com.app.mashkabari.model.modelDepartment.ModelDepartment;

import java.util.ArrayList;

public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<ModelDepartment> list;
    private SetOnDepartmentClickListner setOnDepartmentClickListner;

    public DepartmentAdapter(Context mContext, ArrayList<ModelDepartment> list,SetOnDepartmentClickListner setOnDepartmentClickListner) {
        this.mContext = mContext;
        this.list = list;
        this.setOnDepartmentClickListner=setOnDepartmentClickListner;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_department, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvName.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.adapter_department_tvName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setOnDepartmentClickListner.getId(list.get(getAdapterPosition()).getId(),
                            getAdapterPosition());
                }
            });
        }
    }

    public interface SetOnDepartmentClickListner{
        public void getId(int id,int pos);
    }


    public void updateList(ArrayList<ModelDepartment> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
