package com.venkat.newsapp.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.venkat.newsapp.Adapters.NewsAdapter;
import com.venkat.newsapp.Model.Article;
import com.venkat.newsapp.Model.News;
import com.venkat.newsapp.Model.Source;
import com.venkat.newsapp.R;
import com.venkat.newsapp.Utils.Constants;
import com.venkat.newsapp.ViewModel.NewsViewModel;

import java.util.ArrayList;
import java.util.List;


public class NewsFragment extends Fragment {
    Context context;
    View view;
    private static final String TAG = "NewsFragment";
    List<Article> list;

    NewsViewModel viewModel;
    RecyclerView recyclerView;
    NewsAdapter adapter;
    public NewsFragment() {
        // Required empty public constructor
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.context = getContext();
        view = inflater.inflate(R.layout.fragment_news, container, false);
        viewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        recyclerView = view.findViewById(R.id.news_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        list = new ArrayList<>();
        adapter = new NewsAdapter(context, list, new NewsAdapter.OnItemClick() {
            @Override
            public void onClick(Article position) {
                Intent intent = new Intent(context,NewsDetailsActivity.class);
                intent.putExtra(Constants.ARTICLES, (Parcelable) position);
                context.startActivity(intent);
            }
        });
        Source source = getArguments().getParcelable(Constants.SOURCE);
        Log.d(TAG, "onCreateView: "+new Gson().toJson(source));
        viewModel.loadArticles(source.getId()).observe(getActivity(), new Observer<News>() {
            @Override
            public void onChanged(News news) {
                adapter.setList(news.getArticles());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
        
        return view;
    }
}