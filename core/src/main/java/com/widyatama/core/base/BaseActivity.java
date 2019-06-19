package com.widyatama.core.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.widyatama.core.BuildConfig;
import com.widyatama.core.R;
import com.widyatama.core.util.LocaleUtil;

import org.jetbrains.annotations.NotNull;

public abstract class BaseActivity extends AppCompatActivity implements BaseActivityContract, BaseFragment.Callback {

    public ActionBar mActionBar;
    public Bundle dataReceived;
    Context mContext;
    boolean isAnimEnabled = true;
    AlertDialog mProgressDialog;
    public Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        mContext = this;
        dataReceived = getIntent().getExtras();

        mActionBar = getSupportActionBar();

        toolbar = findViewById(R.id.toolbar);
        if (toolbar != null){
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNavigationClick();
                }
            });
        }

        if (getIntent() != null){
            dataReceived = getIntent().getExtras();
        }

        onInitializedView(savedInstanceState);
    }

    public void launchActivityClearAllStack(Class classActivity){
        Intent intent = new Intent(this, classActivity);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_from_right, R.anim.scale_out);
        finish();
    }

    public void launchActivity(Class<?> classActivity, boolean isFinish, Bundle data){
        Intent intent = new Intent(this, classActivity.getClass());
        intent.putExtras(data);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_from_right, R.anim.scale_out);
        finish();
    }

    public void launchFragment(int view, Class<?> classFragment){
        Fragment fragment = new Fragment();

        try {
            fragment = (Fragment) classFragment.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(view, fragment);

        transaction.commit();
    }

    @Override
    public Context getContext() {
        return mContext;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleUtil.onAttach(newBase));
    }

    protected abstract void onInitializedView(Bundle savedInstanceState);

    public void onNavigationClick(){
        onClickBack();
    }

    @TargetApi(Build.VERSION_CODES.M)
    void requestPermissionsSafely(Integer requestCode, String... permissions){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            requestPermissions(permissions, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    boolean hasPermission(String permission){
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(@NotNull String TAG) {

    }

    protected abstract Integer setLayout();

    @Override
    public void showProgressDialog(@NotNull String message) {
        AlertDialog.Builder progressDialog = new AlertDialog.Builder(this);
        View layout = LayoutInflater.from(this).inflate(R.layout.progress_dialog, null);
        AppCompatTextView text = layout.findViewById(R.id.tv_loading);
        text.setText(message);
        progressDialog.setView(layout);

        mProgressDialog = progressDialog.create();
        mProgressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        mProgressDialog.dismiss();
    }

    public Context getmContext() {
        return mContext;
    }

    @Override
    public void displayHome() {
        if (mActionBar != null){
            mActionBar.setHomeButtonEnabled(true);
            mActionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
            mActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void setActionBarTitle(@NotNull String title) {
        if (mActionBar != null){
            mActionBar.setTitle(title);
        }
    }

    @Override
    public void onClickBack() {
        finish();
        if (isAnimEnabled || Build.VERSION.SDK_INT < 21){
            overridePendingTransition(R.anim.scale_in, R.anim.slide_out_to_right);
        }
    }

    @Override
    public void handleError(int caseError, @NotNull String message) {
        String error;
        switch (caseError){
            case 1 : {
                error = getString(R.string.service_not_found);
                break;
            }
            case 2 : {
                error = getString(R.string.network_not_stable);
                break;
            }
            case 3 : {
                error = getString(R.string.server_error);
                break;
            }
            case 4 : {
                error = getString(R.string.service_not_connected);
                break;
            }
            case 5 : {
                error = getString(R.string.some_error);
                break;
            }
            case 6 : {
                error = message;
                break;
            }
        }

        if (BuildConfig.DEBUG){
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (isAnimEnabled || Build.VERSION.SDK_INT < 21){
            overridePendingTransition(R.anim.scale_in, R.anim.slide_out_to_right);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onNavigationClick();
        }

        return true;
    }
}
