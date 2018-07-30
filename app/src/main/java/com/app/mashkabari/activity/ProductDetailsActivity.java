package com.app.mashkabari.activity;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.mashkabari.R;
import com.app.mashkabari.model.modelCategories.ModelCategories;
import com.app.mashkabari.model.modlDetails.ModelDetails;
import com.app.mashkabari.model.modlDetails.ModelDetailsUnitPrice;
import com.app.mashkabari.utility.Constants;
import com.app.mashkabari.web.api.ApiClient;
import com.app.mashkabari.web.api.ApiInterface;
import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends BaseActivity {
    private static final String TAG = ProductDetailsActivity.class.getSimpleName();
    private Context mContext;

    private CoordinatorLayout clParent;
    private TextView tvRate, tvTotalPrice, tvCount;
    private Toolbar toolbar;
    private ImageView ivProduct;
    private Button btConfirmOrder;
    private Spinner spUnit;
    private int categoryId;
    private ImageButton ibAdd, ibMinus;
    private int count = 1;
    private ModelDetailsUnitPrice unitPrice;

    @Override
    protected int defineLayoutResource() {
        return R.layout.activity_product_details;
    }

    @Override
    protected void initializeComponents() {
        clParent = findViewById(R.id.activity_product_details_clParent);
        toolbar = findViewById(R.id.activity_product_details_toolbar);
        tvRate = findViewById(R.id.activity_product_details_tvRate);
        tvTotalPrice = findViewById(R.id.activity_product_details_tvTotalPrice);
        ivProduct = findViewById(R.id.activity_product_details_ivProduct);
        spUnit = findViewById(R.id.activity_product_details_spUnit);
        btConfirmOrder = findViewById(R.id.activity_product_details_btConfirmOrder);
        ibAdd = findViewById(R.id.activity_product_details_ibAdd);
        ibMinus = findViewById(R.id.activity_product_details_ibMinus);
        tvCount = findViewById(R.id.activity_product_details_tvCount);

    }

    @Override
    protected void initializeComponentsBehaviour() {
        mContext = getApplicationContext();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle("");

        if (getIntent().getExtras() != null) {
            categoryId = getIntent().getExtras().getInt(Constants.ID);

            getDetailsFromServer();
        }

        tvCount.setText(String.valueOf(count));

        /*Glide.with(mContext)
                .load("http://server.myspace-shack.com/d23/b70df537-f1e5-4437-9bef-807772c6f7df.jpg")
                .into(ivProduct);*/


        /*SpannableStringBuilder builder = new SpannableStringBuilder();

        SpannableString str1 = new SpannableString("Rate: ");
        str1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimaryText)), 0, str1.length(), 0);
        builder.append(str1);

        SpannableString str2 = new SpannableString("Rs 40 / kg");
        str2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorGreen)), 0, str2.length(), 0);
        builder.append(str2);
        tvRate.setText(builder, TextView.BufferType.SPANNABLE);*/


        ////////////////////////////
        /*SpannableStringBuilder builder2 = new SpannableStringBuilder();

        SpannableString str3 = new SpannableString("Total: ");
        str3.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimaryText)), 0, str3.length(), 0);
        builder2.append(str3);

        SpannableString str4 = new SpannableString("Rs 80");
        str4.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorGreen)), 0, str4.length(), 0);
        builder2.append(str4);
        tvTotalPrice.setText(builder2, TextView.BufferType.SPANNABLE);*/


        spUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                unitPrice = (ModelDetailsUnitPrice) adapterView.getItemAtPosition(position);
                setPriceWithUnit(unitPrice);
                setTotalPrice(count);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btConfirmOrder.setOnClickListener(this);

        ibAdd.setOnClickListener(this);
        ibMinus.setOnClickListener(this);
    }

    private void setPriceWithUnit(ModelDetailsUnitPrice unitPrice) {
        SpannableStringBuilder builder = new SpannableStringBuilder();

        SpannableString str1 = new SpannableString("Rate: ");
        str1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimaryText)), 0, str1.length(), 0);
        builder.append(str1);

        String price = unitPrice.getUnitCurrency() + " " + unitPrice.getUnitPrice() + " / " + unitPrice.getUnit();

        SpannableString str2 = new SpannableString(price);
        str2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorGreen)), 0, str2.length(), 0);
        builder.append(str2);
        tvRate.setText(builder, TextView.BufferType.SPANNABLE);
    }


    private void setTotalPrice(int count){
        if (unitPrice!=null) {
            SpannableStringBuilder builder2 = new SpannableStringBuilder();

            SpannableString str3 = new SpannableString("Total: ");
            str3.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimaryText)), 0, str3.length(), 0);
            builder2.append(str3);

            double total=count*Double.valueOf(unitPrice.getUnitPrice());
            String totalPrice=unitPrice.getUnitCurrency() + " " + String.format("%.2f", total);

            SpannableString str4 = new SpannableString(totalPrice);
            str4.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorGreen)), 0, str4.length(), 0);
            builder2.append(str4);
            tvTotalPrice.setText(builder2, TextView.BufferType.SPANNABLE);
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.activity_product_details_ibAdd:
                if (Integer.parseInt(tvCount.getText().toString()) >= 1) {
                    count = Integer.parseInt(tvCount.getText().toString()) + 1;
                    setTotalPrice(count);
                    tvCount.setText(String.valueOf(count));
                }
                break;
            case R.id.activity_product_details_ibMinus:
                if (Integer.parseInt(tvCount.getText().toString()) != 1) {
                    count = Integer.parseInt(tvCount.getText().toString()) - 1;
                    setTotalPrice(count);
                    tvCount.setText(String.valueOf(count));
                }
                break;
            case R.id.activity_product_details_btConfirmOrder:
                Toast.makeText(mContext, "Coming Soon :)", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    private void getDetailsFromServer() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<ModelDetails> call = apiInterface.getDetails(categoryId, "json", Constants.TOKEN);

        call.enqueue(new Callback<ModelDetails>() {
            @Override
            public void onResponse(Call<ModelDetails> call, Response<ModelDetails> response) {
                if (response.isSuccessful()) {
                    ModelDetails modelDetails = response.body();
                    if (modelDetails.getStatus()) {
                        toolbar.setTitle(modelDetails.getProduct().getName());
                        if (modelDetails.getProduct().getImages().size() > 0) {
                            Glide.with(mContext)
                                    .load(modelDetails.getProduct().getImages().get(0).getImageUrl())
                                    .into(ivProduct);
                        }

                        if (modelDetails.getProduct().getUnitPrices().size() > 0) {
                            ArrayAdapter<ModelDetailsUnitPrice> unitPriceAdapter = new ArrayAdapter<>(ProductDetailsActivity.this, android.R.layout.simple_spinner_item, modelDetails.getProduct().getUnitPrices());
                            unitPriceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spUnit.setAdapter(unitPriceAdapter);

                        }
                    } else {

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
            public void onFailure(Call<ModelDetails> call, Throwable t) {
                hideMyLoader();
                defaultToast(mContext, "Network Error");
            }
        });
    }
}
