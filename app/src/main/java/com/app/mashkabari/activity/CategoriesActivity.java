package com.app.mashkabari.activity;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.app.mashkabari.R;
import com.app.mashkabari.adapter.CategoriesParentAdapter;
import com.app.mashkabari.model.modelCategories.ModelCategories;
import com.app.mashkabari.utility.Constants;
import com.app.mashkabari.web.api.ApiClient;
import com.app.mashkabari.web.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesActivity extends BaseActivity {

    private static final String TAG = CategoriesActivity.class.getName();
    private RecyclerView rvCategories;
    private Context mContext;
    private CoordinatorLayout clParent;
    private Toolbar toolbar;

    private int departmentId;

    @Override
    protected int defineLayoutResource() {
        return R.layout.activity_categories;
    }

    @Override
    protected void initializeComponents() {
        clParent = findViewById(R.id.activity_categories_clParent);
        toolbar = findViewById(R.id.activity_categories_toolbar);
        rvCategories = findViewById(R.id.activity_categories_rvCategories);
    }

    @Override
    protected void initializeComponentsBehaviour() {
        mContext = getApplicationContext();

        if (getIntent().getExtras()!=null) {
            departmentId=getIntent().getExtras().getInt(Constants.ID);
            //Toast.makeText(mContext, ""+departmentId, Toast.LENGTH_SHORT).show();
        }

        showMyLoader(this);
        getCategoriesFromServer();
    }


    private void getCategoriesFromServer() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<ModelCategories> call = apiInterface.getCategories(departmentId,"json", Constants.TOKEN);
        call.enqueue(new Callback<ModelCategories>() {
            @Override
            public void onResponse(Call<ModelCategories> call, Response<ModelCategories> response) {
                hideMyLoader();
                if (response.isSuccessful()) {
                    ModelCategories modelCategories = response.body();
                    if (modelCategories.getStatus()) {
                        CategoriesParentAdapter adapter = new CategoriesParentAdapter(CategoriesActivity.this,  modelCategories.getHomelist());
                        rvCategories.setLayoutManager(new LinearLayoutManager(mContext));
                        rvCategories.setAdapter(adapter);

                    } else {
                        ///false
                    }

                } else {
                    Log.d(TAG, "RESPONSE CODE" + response.code());
                    switch (response.code()) {
                        case 401:
                            showSnack(getString(R.string.network_authentication_failed), clParent);
                            break;
                        case 400:
                            showSnack(getString(R.string.network_bad_request), clParent);
                            break;
                        case 404:
                            showSnack(getString(R.string.network_error_try_again), clParent);
                            break;
                        case 500:
                            showSnack(getString(R.string.network_server_Error), clParent);
                            break;
                        default:
                            showSnack(getString(R.string.network_error_try_again), clParent);
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<ModelCategories> call, Throwable t) {
                hideMyLoader();
                defaultToast(mContext, "Network Error");
            }
        });
    }
}
