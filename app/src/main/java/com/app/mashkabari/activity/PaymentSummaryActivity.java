package com.app.mashkabari.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.app.mashkabari.R;

public class PaymentSummaryActivity extends BaseActivity {


    @Override
    protected int defineLayoutResource() {
        return R.layout.activity_payment_summary;
    }

    @Override
    protected void initializeComponents() {

    }

    @Override
    protected void initializeComponentsBehaviour() {
getSupportActionBar().setTitle("Check out");
    }
}
