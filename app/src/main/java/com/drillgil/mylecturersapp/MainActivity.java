package com.drillgil.mylecturersapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.drillgil.mylecturersapp.adapters.MyRecyclerViewAdapter;
import com.drillgil.mylecturersapp.models.LecturerModel;
import com.drillgil.mylecturersapp.viewmodels.MyListViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private MyListViewModel myListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        myListViewModel = new ViewModelProvider(this).get(MyListViewModel.class);

        SetupSearchView();
        ConfigureRecyclerView();
        ObserveAnyChange();
        SearchApi();
    }

    private void SetupSearchView() {
        final SearchView searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                FilteredList(newText);
                return false;
            }
        });
    }

    private void FilteredList(String text) {
        List<LecturerModel> filteredList = new ArrayList<>();
        myListViewModel.getLecturers().observe(this, lecturerModels -> {
            if (lecturerModels != null)
                for (LecturerModel lecturerModel : lecturerModels)
                    if (lecturerModel.getLecturerName().toLowerCase().contains(text.toLowerCase()))
                        filteredList.add(lecturerModel);

            if (filteredList.isEmpty())
                Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
            else
                myRecyclerViewAdapter.setFilteredList(filteredList);

        });
    }

    private void ConfigureRecyclerView() {
        myRecyclerViewAdapter = new MyRecyclerViewAdapter();
        recyclerView.setAdapter(myRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void ObserveAnyChange() {
        myListViewModel.getLecturers().observe(this, lecturerModels -> {
            if (lecturerModels != null)
                myRecyclerViewAdapter.setLecturers(lecturerModels);
        });
        myListViewModel.getLanguages().observe(this, languageModels -> {
            if (languageModels != null)
                myRecyclerViewAdapter.setLanguages(languageModels);
        });
    }

    private void SearchApi() {
        myListViewModel.SearchApi();
    }
}