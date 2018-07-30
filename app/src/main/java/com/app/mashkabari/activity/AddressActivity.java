package com.app.mashkabari.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.app.mashkabari.R;

public class AddressActivity extends BaseActivity {

    private Context mContext;
    private TextView tvSave,tvSkip;
    private EditText etLineOne, etLineTwo, etDistrict, etPin,etLandmark;

    @Override
    protected int defineLayoutResource() {
        return R.layout.activity_address;
    }

    @Override
    protected void initializeComponents() {
        etLineOne = findViewById(R.id.activity_address_etLineOne);
        etLineTwo = findViewById(R.id.activity_address_etLineTwo);
        etDistrict = findViewById(R.id.activity_address_etDistrict);
        etPin = findViewById(R.id.activity_address_etPin);
        etLandmark = findViewById(R.id.activity_address_etLandmark);
        tvSave = findViewById(R.id.activity_login_tvSave);
        tvSkip=findViewById(R.id.activity_login_tvSkip);

    }

    @Override
    protected void initializeComponentsBehaviour() {
        mContext = getApplicationContext();
        tvSave.setOnClickListener(this);
        tvSkip.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.activity_login_tvSkip:
                finish();
                startActivity(new Intent(mContext, HomeActivity.class));
                break;
            case R.id.activity_login_tvSave:
                saveAddressToServer();
                break;
            default:
                break;
        }
    }

    private void saveAddressToServer() {

    }
}
