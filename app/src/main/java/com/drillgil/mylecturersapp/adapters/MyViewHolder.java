package com.drillgil.mylecturersapp.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.drillgil.mylecturersapp.R;


public class MyViewHolder extends RecyclerView.ViewHolder{

    TextView lecturerName;
    TextView languageName;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        lecturerName = itemView.findViewById(R.id.lecturer_name);
        languageName = itemView.findViewById(R.id.language_name);
    }
}
