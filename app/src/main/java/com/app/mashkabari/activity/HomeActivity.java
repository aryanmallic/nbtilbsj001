package com.app.mashkabari.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.app.mashkabari.R;
import com.app.mashkabari.adapter.CategoriesParentAdapter;
import com.app.mashkabari.adapter.DepartmentAdapter;
import com.app.mashkabari.model.modelCategories.ModelCategories;
import com.app.mashkabari.model.modelDepartment.ModelDepartment;
import com.app.mashkabari.utility.Constants;
import com.app.mashkabari.web.api.ApiClient;
import com.app.mashkabari.web.api.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = HomeActivity.class.getSimpleName();
    private Context mContext;

    private CoordinatorLayout clParent;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private RecyclerView rvDepartment;

    private DepartmentAdapter departmentAdapter;
    private ArrayList<ModelDepartment> list;
    private DepartmentAdapter.SetOnDepartmentClickListner setOnDepartmentClickListner;

    @Override
    protected int defineLayoutResource() {
        return R.layout.activity_home;
    }

    @Override
    protected void initializeComponents() {
        clParent = findViewById(R.id.activity_home_clParent);
        toolbar = findViewById(R.id.activity_home_toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        rvDepartment = findViewById(R.id.activity_home_rvDepartment);
    }

    @Override
    protected void initializeComponentsBehaviour() {
        mContext = getApplicationContext();
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        int rid = getResources().getIdentifier("user", "mipmap", getPackageName());
        toolbar.setNavigationIcon(rid);

        navigationView.setNavigationItemSelectedListener(this);

        list = new ArrayList<>();
        setOnDepartmentClickListner=new DepartmentAdapter.SetOnDepartmentClickListner() {
            @Override
            public void getId(int id, int pos) {
                Intent intent=new Intent(mContext,CategoriesActivity.class);
                intent.putExtra(Constants.ID,id);
                startActivity(intent);
            }
        };
        departmentAdapter = new DepartmentAdapter(mContext, list,setOnDepartmentClickListner);
        rvDepartment.setLayoutManager(new LinearLayoutManager(mContext));
        rvDepartment.setAdapter(departmentAdapter);


        showMyLoader(this);
        getDepartmentFromServer();
    }

    @Override
    public void onBackPressed() {
        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.categories, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_cart) {
            startActivity(new Intent(mContext,CartActivity.class));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            startActivity(new Intent(mContext,ProfileActivity.class));
        } else if (id == R.id.nav_cart) {
            startActivity(new Intent(mContext,CartActivity.class));
        } else if (id == R.id.nav_order_history) {
            startActivity(new Intent(mContext,OrderHistoryActivity.class));
        } else if (id == R.id.nav_setting) {

        }

        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void getDepartmentFromServer() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<ArrayList<ModelDepartment>> call = apiInterface.getDepartment("json", Constants.TOKEN);

        call.enqueue(new Callback<ArrayList<ModelDepartment>>() {
            @Override
            public void onResponse(Call<ArrayList<ModelDepartment>> call, Response<ArrayList<ModelDepartment>> response) {
                hideMyLoader();
                if (response.isSuccessful()) {
                    list = response.body();
                    if (list.size() > 0) {
                        departmentAdapter.updateList(list);
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
            public void onFailure(Call<ArrayList<ModelDepartment>> call, Throwable t) {
                hideMyLoader();
                defaultToast(mContext, "Network Error");
            }
        });
    }
}
