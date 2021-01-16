package com.venkat.newsapp.ViewModel;

import android.app.Application;


import com.venkat.newsapp.DataSource.NewsRepository;
import com.venkat.newsapp.Model.News;
import com.venkat.newsapp.Model.SourceNews;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class NewsViewModel extends AndroidViewModel {

    private NewsRepository mRepository;

    public NewsViewModel(Application application) {
        super(application);
        mRepository = new NewsRepository(application);
    }

    public LiveData<SourceNews> loadListOfNewsSources(){
        return mRepository.getListOfSources();
    }
    public LiveData<News> loadArticles(String news_id){
        return mRepository.getSourceNews(news_id);
    }

}