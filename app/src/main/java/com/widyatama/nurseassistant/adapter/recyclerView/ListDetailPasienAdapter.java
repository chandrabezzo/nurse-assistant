package com.widyatama.nurseassistant.adapter.recyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.widyatama.nurseassistant.R;

import java.util.List;

public class ListDetailPasienAdapter extends RecyclerView.Adapter<ListDetailPasienAdapter.MyViewHolder> {

    public interface OnItemClick{
        public void onItemClick(String pasien);
    }

    private List<String> todoList;
    private Context context;
    private OnItemClick listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView todo;
        public RelativeLayout container;

        public MyViewHolder(View view) {
            super(view);
            todo = (TextView) view.findViewById(R.id.todo);
            container = (RelativeLayout) view.findViewById(R.id.container);
        }
    }


    public ListDetailPasienAdapter(List<String> todoList, Context context) {
        this.todoList = todoList;
        this.context = context;
    }

    public void setItem(List<String> pasien){
        this.todoList = pasien;
    }

    public void setOnClick(OnItemClick onClick){
        this.listener = onClick;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_todo, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String pasien = todoList.get(position);
        holder.todo.setText(pasien);
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }
}
