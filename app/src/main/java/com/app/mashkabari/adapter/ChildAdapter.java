package com.app.mashkabari.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.mashkabari.R;
import com.app.mashkabari.activity.ProductDetailsActivity;
import com.app.mashkabari.model.ModelChild;
import com.app.mashkabari.model.modelCategories.ModelCategoriesProduct;
import com.app.mashkabari.utility.Constants;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<ModelChild> child;
    ArrayList<ModelCategoriesProduct> products;
    Activity mActivity;

    /*public ChildAdapter(Activity mActivity, ArrayList<ModelChild> child) {
        this.mActivity = mActivity;
        this.child = child;
    }*/

    public ChildAdapter(Activity mActivity, ArrayList<ModelCategoriesProduct> products) {
        this.mActivity = mActivity;
        this.products = products;
    }

    /*public ChildAdapter(Context mContext, ArrayList<ModelChild> child) {
        this.mContext = mContext;
        this.child = child;
    }*/

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_child, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        if(products.get(position).getImages().size()>0) {
            Glide.with(mActivity)
                    .load(products.get(position).getImages().get(0).getImageUrl())
                    .into(holder.ivImage);
        }
        if(products.get(position).getUnitPrices().size()>0){
            String price=products.get(position).getUnitPrices().get(0).getUnitCurrency()+
                    products.get(position).getUnitPrices().get(0).getUnitPrice()+"/"+
                    products.get(position).getUnitPrices().get(0).getUnit();

            holder.tvPrice.setText(price);
        }

        holder.ivName.setText(products.get(position).getName());

        holder.ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                /*mContext.startActivity(new Intent(mContext, ProductDetailsActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));*/

                Intent intent=new Intent(mActivity, ProductDetailsActivity.class);
                intent.putExtra(Constants.ID,products.get(position).getId());
                mActivity.startActivity(intent);
                        //.putExtra("id", String.valueOf(latestList.get(position).getId())));

                //Toast.makeText(mActivity,""+child.get(position).getChild(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        //return products.size();
        return 2;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivImage;
        private TextView ivName, tvPrice;

        public MyViewHolder(View itemView) {
            super(itemView);

            ivImage = itemView.findViewById(R.id.ivImage);
            ivName=itemView.findViewById(R.id.ivName);
            tvPrice =itemView.findViewById(R.id.tvPrice);
            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mActivity,""+child.get(getAdapterPosition()).getChild(),Toast.LENGTH_SHORT).show();
                }
            });*/
        }
    }
}
