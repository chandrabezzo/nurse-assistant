package com.widyatama.core.listener;

import android.view.View;

public interface OnItemClickListener {
    void onItemClick(View itemView, Integer position);

    boolean onItemLongClick(View itemView, Integer position);
}
