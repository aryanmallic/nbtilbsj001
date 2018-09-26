package com.app.mashkabari.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.mashkabari.R;
import com.app.mashkabari.adapter.CartAdapter;
import com.app.mashkabari.model.ModelCart;

import java.util.ArrayList;

public class CartActivity extends BaseActivity {

    private Context mContext;
    private RecyclerView rvCart;
    private CartAdapter.CartAdapterClickListner listner;
    private ArrayList<ModelCart> list;

    @Override
    protected int defineLayoutResource() {
        return R.layout.activity_cart;
    }

    @Override
    protected void initializeComponents() {
        rvCart =findViewById(R.id.activity_cart_rvCart);
    }

    @Override
    protected void initializeComponentsBehaviour() {
        mContext = getApplicationContext();

        getSupportActionBar().setTitle("Cart");

        list=new ArrayList<>();
        list.add(new ModelCart(4,1));
        list.add(new ModelCart(5,1));
        list.add(new ModelCart(6,1));

        listner=new CartAdapter.CartAdapterClickListner() {
            @Override
            public void getCartId(int position) {

            }
        };
        rvCart.setLayoutManager(new LinearLayoutManager(mContext));
        CartAdapter cartAdapter=new CartAdapter(mContext,list,listner);
        rvCart.setAdapter(cartAdapter);

    }
}
