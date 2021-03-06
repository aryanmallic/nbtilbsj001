package com.app.mashkabari.activity;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.app.mashkabari.R;

public class ProfileActivity extends BaseActivity {

    private Context mContext;
    private Toolbar toolbar;

    @Override
    protected int defineLayoutResource() {
        return R.layout.activity_profile;
    }

    @Override
    protected void initializeComponents() {
        toolbar=findViewById(R.id.activity_profile_toolbar);
    }

    @Override
    protected void initializeComponentsBehaviour() {
        mContext = getApplicationContext();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) // Press Back Icon
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
