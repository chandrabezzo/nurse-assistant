package com.widyatama.core.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public abstract class BaseDialogFragment extends DialogFragment implements BaseDialogContract {

    private BaseActivity baseActivity;
    private Bundle dataReceived;
    private View rootView;

    protected abstract void onViewInitialized(Bundle savedInstanceState);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(setLayout(), container, false);
        dataReceived = getArguments();
        onViewInitialized(savedInstanceState);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null){
            getDialog().setTitle(getArguments().getString("title"));
        }
        else {
            getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getContext() instanceof BaseActivity){
            BaseActivity mActivity = (BaseActivity) getContext();
            baseActivity = mActivity;
            mActivity.onFragmentAttached();
        }
    }

    @Override
    public void onDetach() {
        baseActivity = null;
        super.onDetach();
    }

    @Override
    public void dismissDialog(String tag) {
        dismiss();
        baseActivity.onFragmentDetached(tag);
    }

    abstract Integer setLayout();

    @Override
    public int show(FragmentTransaction transaction, String tag) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prevFragment = getFragmentManager().findFragmentByTag(tag);
        if (prevFragment != null) {
            ft.remove(prevFragment);
        }
        ft.addToBackStack(null);
        show(ft, tag);
        return super.show(transaction, tag);
    }

    @Override
    public void handleError(int caseError, String message){
        ((BaseActivity) baseActivity).handleError(caseError, message);
    }
}
