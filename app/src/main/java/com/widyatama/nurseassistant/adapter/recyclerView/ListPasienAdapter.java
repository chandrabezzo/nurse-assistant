package com.widyatama.nurseassistant.adapter.recyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.widyatama.nurseassistant.R;
import com.widyatama.nurseassistant.data.model.Pasien;

import java.util.List;

public class ListPasienAdapter extends RecyclerView.Adapter<ListPasienAdapter.MyViewHolder> {

    public interface OnItemClick{
        public void onItemClick(Pasien pasien);
    }

    private List<Pasien> pasienList;
    private Context context;
    private OnItemClick listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, floor, room, bed, time;
        public LinearLayout container;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            floor = (TextView) view.findViewById(R.id.floor);
            room = (TextView) view.findViewById(R.id.room);
            bed = (TextView) view.findViewById(R.id.bed);
            time = (TextView) view.findViewById(R.id.time);
            container = (LinearLayout) view.findViewById(R.id.container);

        }
    }


    public ListPasienAdapter(List<Pasien> pasienList, Context context) {
        this.pasienList = pasienList;
        this.context = context;
    }

    public void setItem(List<Pasien> pasien){
        this.pasienList = pasien;
    }

    public void setOnClick(OnItemClick onClick){
        this.listener = onClick;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_pasien, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Pasien pasien = pasienList.get(position);
        holder.name.setText(pasien.getName());
        holder.floor.setText(pasien.getFloor());
        holder.room.setText(pasien.getRoom());
        holder.bed.setText(pasien.getBed());
        holder.time.setText(pasien.getTimeVisit());
        for (String val : pasien.getTodoList()){
            TextView tv = text();
            tv.setText(val);
            holder.container.addView(tv);
        }
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(pasien);
            }
        });


    }

    @Override
    public int getItemCount() {
        return pasienList.size();
    }

    private TextView text(){
        TextView tv = new TextView(context);
        LayoutParams params = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        tv.setLayoutParams(params);
        tv.setTextColor(context.getResources().getColor(R.color.blueLight));
        return tv;

    }
}
