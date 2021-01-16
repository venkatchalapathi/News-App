package com.venkat.newsapp.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import com.venkat.newsapp.Adapters.NewsSourceAdapter;
import com.venkat.newsapp.Model.Source;
import com.venkat.newsapp.Model.SourceNews;
import com.venkat.newsapp.R;
import com.venkat.newsapp.Utils.Constants;
import com.venkat.newsapp.Utils.InstanceCreator;
import com.venkat.newsapp.Utils.NewsService;
import com.venkat.newsapp.ViewModel.NewsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    NewsViewModel viewModel;
    RecyclerView recyclerView;
    NewsSourceAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.source_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Source> list = new ArrayList<>();
        adapter = new NewsSourceAdapter(list, new NewsSourceAdapter.onItemClick() {
            @Override
            public void onClick(View view, int position,List<Source> lis) {
                if (view.getId() == R.id.source){
                    // open webview
                    InstanceCreator.openBrowser(MainActivity.this,lis.get(position).getUrl());
                }else{
                    //open news source
                    Intent intent = new Intent(MainActivity.this,NewsSourceActivity.class);
                    intent.putExtra(Constants.POSITION,position);
                    intent.putParcelableArrayListExtra(Constants.SOURCE_DATA,(ArrayList<Source>)lis);
                    startActivity(intent);
                }
            }
        });

        viewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        viewModel.loadListOfNewsSources().observe(this, new Observer<SourceNews>() {
            @Override
            public void onChanged(SourceNews sourceNews) {
                adapter.setList(sourceNews.getSources());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }
}