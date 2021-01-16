package com.venkat.newsapp.DataSource;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;
import com.venkat.newsapp.Model.News;
import com.venkat.newsapp.Model.SourceNews;
import com.venkat.newsapp.Utils.Constants;
import com.venkat.newsapp.Utils.InstanceCreator;
import com.venkat.newsapp.Utils.NewsService;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {
    private static final String TAG = "NewsRepository";
    NewsService service;
    public NewsRepository(Application application) {
        service = InstanceCreator.getInstance().getExploreService();
    }

    public LiveData<News> getSourceNews(String news_id) {
        final MutableLiveData<News> ml = new MutableLiveData<>();
        ///
        service.loadSourceNews(news_id, Constants.API_KEY).enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.body().getStatus().equals("ok")){
                    ml.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Log.d(TAG, "onFailure: loading source news.."+t.getMessage());
            }
        });
        return ml;
    }

    public LiveData<SourceNews> getListOfSources() {
        final MutableLiveData<SourceNews> ml = new MutableLiveData<>();
        service.loadAllNewsSource().enqueue(new Callback<SourceNews>() {
            @Override
            public void onResponse(Call<SourceNews> call, Response<SourceNews> response) {
                if (response.body().getStatus().equals("ok")){
                    ml.setValue(response.body());
                    Log.d(TAG, "onResponse: "+new Gson().toJson(response.body()));
                }
            }

            @Override
            public void onFailure(Call<SourceNews> call, Throwable t) {
                Log.d(TAG, "onFailure: Error on list loading..."+t.getMessage());
            }
        });
        return ml;
    }

}
