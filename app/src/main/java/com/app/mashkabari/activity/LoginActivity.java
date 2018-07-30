package com.app.mashkabari.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.design.widget.CoordinatorLayout;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.app.mashkabari.R;
import com.app.mashkabari.utility.Constants;
import com.app.mashkabari.utility.HelperMethods;
import com.app.mashkabari.utility.SharedPref;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends BaseActivity {

    private static final String TAG = LoginActivity.class.getName();
    private Context mContext;
    private TextView tvGetStarted, tvVerify, tvOuterGetStarted;
    private EditText etMobile;
    private ViewSwitcher vsEditText;
    private Animation slideFromRight, slideToLeft, slideFromLeft, slideToRight;
    private LinearLayout llBack;
    private CoordinatorLayout clParent;


    @Override
    protected int defineLayoutResource() {
        return R.layout.activity_login;
    }

    @Override
    protected void initializeComponents() {
        tvOuterGetStarted = findViewById(R.id.activity_login_tvOuterGetStarted);
        tvGetStarted = findViewById(R.id.activity_login_tvGetStarted);
        tvVerify = findViewById(R.id.activity_login_tvVerify);
        vsEditText = findViewById(R.id.activity_login_vsEditText);
        llBack = findViewById(R.id.activity_login_llBack);
        clParent = findViewById(R.id.activity_login_clParent);

        etMobile = findViewById(R.id.activity_login_etMobile);
    }

    @Override
    protected void initializeComponentsBehaviour() {
        mContext = getApplicationContext();

        slideFromRight = AnimationUtils.loadAnimation(mContext, R.anim.slide_from_right);
        slideToLeft = AnimationUtils.loadAnimation(mContext, R.anim.slide_to_left);

        slideFromLeft = AnimationUtils.loadAnimation(mContext, R.anim.slide_from_left);
        slideToRight = AnimationUtils.loadAnimation(mContext, R.anim.slide_to_right);

        PackageInfo info;
        try {
            info = getPackageManager().getPackageInfo("com.app.mashkabari", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.d("hashkey", something);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }

        tvVerify.setOnClickListener(this);
        tvGetStarted.setOnClickListener(this);
        llBack.setOnClickListener(this);


        tvOuterGetStarted.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()) {
            case R.id.activity_login_tvOuterGetStarted:
                if (isReadSmsPermissionGranted()) {
                    initFackbookSMSKit();
                }

                break;

            case R.id.activity_login_llBack:
                vsEditText.setInAnimation(slideFromLeft);
                vsEditText.setOutAnimation(slideToRight);
                vsEditText.showNext();
                break;

            case R.id.activity_login_tvVerify:
                break;

            case R.id.activity_login_tvGetStarted:
                vsEditText.setInAnimation(slideFromRight);
                vsEditText.setOutAnimation(slideToLeft);
                vsEditText.showPrevious();

                break;
            default:
                break;
        }
    }

    private void initFackbookSMSKit() {
        final Intent intent = new Intent(this, AccountKitActivity.class);
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(
                        LoginType.PHONE,
                        AccountKitActivity.ResponseType.TOKEN); // or .ResponseType.TOKEN

        configurationBuilder.setReadPhoneStateEnabled(true);
        configurationBuilder.setReceiveSMS(true);

        // ... perform additional configuration ...
        intent.putExtra(
                AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                configurationBuilder.build());
        startActivityForResult(intent, Constants.FACEBOOK_LOGIN_REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.FACEBOOK_LOGIN_REQUEST_CODE) {
            // confirm that this response matches your request
            AccountKitLoginResult loginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            String toastMessage = "";
            if (loginResult.getError() != null) {
                toastMessage = loginResult.getError().getErrorType().getMessage();
            } else if (loginResult.wasCancelled()) {
                toastMessage = "Login Cancelled";
            } else {
                if (loginResult.getAccessToken() != null) {
                    toastMessage = "Success:" + loginResult.getAccessToken().getAccountId();
                    String token = loginResult.getAccessToken().getToken();
                    getAccount(token);
                }
            }

            //Toast.makeText(mContext, toastMessage, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPref pref=new SharedPref(mContext);
        if(pref.getLoginStatus()){
            startActivity(new Intent(LoginActivity.this,AddressActivity.class));
            finish();
        }
    }

    private void getAccount(String token) {
        AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
            @Override
            public void onSuccess(final Account account) {
                // Get Account Kit ID
                final String accountKitId = account.getId();

                // Get phone number
                final PhoneNumber phoneNumber = account.getPhoneNumber();
                final String phoneNumberString = phoneNumber.toString().trim();
                final String phoneNumberWithoutCode = phoneNumberString.substring(phoneNumberString.length() - 10, phoneNumberString.length());

                new SharedPref(mContext).setPhoneNum(phoneNumberWithoutCode);
                Log.d(TAG, "phoneNumberWithoutCode: " + phoneNumberWithoutCode);

                // Surface the result to your user in an appropriate way.
                // Toast.makeText(HomeActivity.this, phoneNumberWithoutCode, Toast.LENGTH_LONG).show();
                if (HelperMethods.isOnline(LoginActivity.this)) {
                    if (!TextUtils.isEmpty(phoneNumberString) && !TextUtils.isEmpty(accountKitId)) {

                        new SharedPref(mContext).setLoginStatus(true);
                        Intent intent = new Intent(mContext, AddressActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();



                        //callLoginWS(phoneNumberWithoutCode, token);
                        //apiInterface = APIClient.getClient().create(APIInterface.class);
                        //loginRetrofit2Api(phoneNumberWithoutCode, token);
                    }
                } else {
                    showSnack("No Internet", clParent);
                }
            }

            @Override
            public void onError(final AccountKitError error) {
                Log.d(TAG, "AccountKit" + error.toString());
                // Handle Error
            }
        });



    }
}
