package com.venkat.newsapp.Utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InstanceCreator {

    private static InstanceCreator instance = null;
    private Retrofit retrofit;

    private NewsService service;

    public InstanceCreator() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS).build();

        retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        service = retrofit.create(NewsService.class);
    }
    public NewsService getExploreService() {
        return service;
    }
    public static InstanceCreator getInstance() {
        if (instance == null) {
            instance = new InstanceCreator();
        }

        return instance;
    }
    public static void openBrowser(Context context, String url) {
        Log.d("URL->", "openBrowser: " + url);
        try {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            context.startActivity(i);
        } catch (Exception e) {

        }
    }

}