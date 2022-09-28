package com.drillgil.mylecturersapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.drillgil.mylecturersapp.R;
import com.drillgil.mylecturersapp.models.LanguageModel;
import com.drillgil.mylecturersapp.models.LecturerModel;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<LecturerModel> mLecturers;
    private List<LanguageModel> mLanguages;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder) holder).lecturerName.setText(mLecturers.get(position).getLecturerName());
        ((MyViewHolder) holder).languageName.setText(mLanguages.get(position).getLanguageName());
    }

    @Override
    public int getItemCount() {
        if (mLecturers != null) {
            return mLecturers.size();
        } else if (mLanguages != null) {
            return mLanguages.size();
        } else {
            return 0;
        }
    }

    public void setLecturers(List<LecturerModel> mLecturers) {
        this.mLecturers = mLecturers;
        notifyItemChanged(mLecturers.size());
    }

    public void setLanguages(List<LanguageModel> mLanguages) {
        this.mLanguages = mLanguages;
        notifyItemChanged(mLanguages.size());
    }

    public void setFilteredList(List<LecturerModel> filteredList) {
        this.mLecturers = filteredList;
        notifyDataSetChanged();
    }
}
