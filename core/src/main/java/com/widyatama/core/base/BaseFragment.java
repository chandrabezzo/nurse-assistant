package com.widyatama.core.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import com.widyatama.core.R;

import org.jetbrains.annotations.NotNull;

public abstract class BaseFragment extends Fragment implements BaseFragmentContract {
    BaseActivity baseActivity;
    AlertDialog mProgressDialog;
    Bundle dataReceived;
    View rootView;
    Context mContext;

    protected abstract void onViewInitialized(Bundle savedInstanceState);

    protected void showOptionMenu() {
        setHasOptionsMenu(true);
    }

    protected void hideOptionMenu() {
        setHasOptionsMenu(false);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(setLayout(), container, false);
        dataReceived = getArguments();
        mContext = getActivity();

        if (((BaseActivity) getActivity()).toolbar != null){
            ((BaseActivity) getActivity()).toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }

        onViewInitialized(savedInstanceState);
        return rootView;
    }

    public void launchActivity(Class<?> classActivity){
        Intent intent = new Intent(getActivity(), classActivity);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_in_from_right, R.anim.scale_out);
    }

    public void launchActivity(Class<?> classActivity, Bundle data){
        Intent intent = new Intent(getActivity(), classActivity);
        intent.putExtras(data);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_in_from_right, R.anim.scale_out);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (getContext() instanceof BaseActivity) {
            BaseActivity activity = ((BaseActivity) getActivity());
            this.baseActivity = activity;
            activity.onFragmentAttached();
        }
    }

    @Nullable
    @Override
    public Context getContext() {
        return this.mContext;
    }

    @Override
    public void onDetach() {
        baseActivity = null;
        super.onDetach();
    }

    protected abstract Integer setLayout();

    @Override
    public void showProgressDialog(@NotNull String message) {
        AlertDialog.Builder progressDialog = new AlertDialog.Builder(getContext());
        View layout = LayoutInflater.from(getContext()).inflate(R.layout.progress_dialog, null);
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

    void setActionBarTitle(String title) {
        ((BaseActivity) getActivity()).setActionBarTitle(title);
    }

    interface Callback {
        void onFragmentAttached();

        void onFragmentDetached(String TAG);
    }

    @Override
    public void onBackPressed() {
        ((BaseActivity) getActivity()).onNavigationClick();
    }

    @Override
    public void handleError(int caseError, @NotNull String message) {
        ((BaseActivity) getActivity()).handleError(caseError, message);
    }
}
