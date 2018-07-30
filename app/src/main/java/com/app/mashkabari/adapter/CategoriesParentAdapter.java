package com.app.mashkabari.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.mashkabari.R;
import com.app.mashkabari.activity.HomeActivity;
import com.app.mashkabari.model.modelCategories.ModelCategoriesHomelist;

import java.util.ArrayList;

public class CategoriesParentAdapter extends RecyclerView.Adapter<CategoriesParentAdapter.MyViewHolder>{

    private Context mContext;
    private Activity mActivity;

    ArrayList<ModelCategoriesHomelist> homelist;

    public CategoriesParentAdapter(Activity mActivity, ArrayList<ModelCategoriesHomelist> homelist) {
        this.mActivity = mActivity;
        this.homelist = homelist;
    }

    /*public CategoriesParentAdapter(Context mContext, ArrayList<ModelHomelist> homelist) {
        this.mContext = mContext;
        this.homelist = homelist;
    }*/

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_categories_parent,parent,false);
        MyViewHolder viewHolder=new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvChildTitle.setText(homelist.get(position).getTitle());
        if(homelist.get(position).getProducts().size()>0){
            ChildAdapter adapter=new ChildAdapter(mActivity,homelist.get(position).getProducts());
            holder.rvChildImages.setAdapter(adapter);
        }
    }

    @Override
    public int getItemCount() {
        return homelist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvChildTitle;
        private RecyclerView rvChildImages;
        public MyViewHolder(View itemView) {
            super(itemView);

            tvChildTitle=itemView.findViewById(R.id.tvChildTitle);
            rvChildImages=itemView.findViewById(R.id.rvChildImages);
            rvChildImages.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        }
    }
}
