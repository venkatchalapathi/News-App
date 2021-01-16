package com.venkat.newsapp.Utils;

import com.venkat.newsapp.Model.News;
import com.venkat.newsapp.Model.SourceNews;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsService {

    @GET("v2/sources?apiKey=46e6762e17304585a041315517ff0dbf")
    Call<SourceNews> loadAllNewsSource();

    @GET("v2/top-headlines")
    Call<News> loadSourceNews(@Query("sources") String responseType,
                              @Query("apiKey") String searchText);
}
