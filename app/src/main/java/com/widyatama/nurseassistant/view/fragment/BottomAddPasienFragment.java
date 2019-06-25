package com.widyatama.nurseassistant.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.widyatama.nurseassistant.R;
import com.widyatama.nurseassistant.data.model.Pasien;

import java.util.Arrays;
import java.util.List;

public class BottomAddPasienFragment extends BottomSheetDialogFragment implements BottomAddPasienViewContract, SheetCallback {

    private SheetCallback sheetCallback;
    private BottomAddPasienPresenter bottomAddPasienPresenter;

    public BottomAddPasienFragment(SheetCallback sheetCallback){
        this.sheetCallback = sheetCallback;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sheet_add_pasien, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initialView(view);
    }

    private void initialView(View view) {
        bottomAddPasienPresenter = new BottomAddPasienPresenter(getContext(), this);
        Spinner spinLantai = (Spinner) view.findViewById(R.id.spinLantai);
        Spinner spinKamar = (Spinner) view.findViewById(R.id.spinKamar);
        Spinner spinKasur = (Spinner) view.findViewById(R.id.spinKasur);
        TextView eTodo = (EditText) view.findViewById(R.id.todo);
        TextView eName = (EditText) view.findViewById(R.id.name);
        TextView eTime = (EditText) view.findViewById(R.id.time);
        ImageView btnAdd = (ImageView) view.findViewById(R.id.btnAdd);

        spinLantai.setAdapter(new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.list_lantai)));
        spinKamar.setAdapter(new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.list_kamar)));
        spinKasur.setAdapter(new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.list_kasur)));
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> todos = Arrays.asList(eTodo.getText().toString().split(","));
                Pasien pasien = new Pasien();
                pasien.setName(eName.getText().toString());
                pasien.setFloor(spinLantai.getSelectedItem().toString());
                pasien.setRoom(spinKamar.getSelectedItem().toString());
                pasien.setBed(spinKasur.getSelectedItem().toString());
                pasien.setTimeVisit(eTime.getText().toString());
                pasien.setTodoList(todos);
                bottomAddPasienPresenter.addPasien(pasien);
                sheetCallback.onFinish();


            }
        });
    }

    @Override
    public void showResult() {

    }

    @Override
    public void onDetachView() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onFinish() {

    }
}
