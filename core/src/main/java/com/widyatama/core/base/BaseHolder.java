package com.widyatama.core.base;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseHolder<M> extends RecyclerView.ViewHolder {

    public BaseHolder(View itemView){
        super(itemView);
    }

    public abstract void setContent(M model);
}
