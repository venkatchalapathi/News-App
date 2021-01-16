package com.venkat.newsapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.venkat.newsapp.Model.Article;
import com.venkat.newsapp.R;
import com.venkat.newsapp.Utils.Constants;
import com.venkat.newsapp.Utils.InstanceCreator;

import java.lang.reflect.Type;

public class NewsDetailsActivity extends AppCompatActivity {
    ImageView imageView;
    TextView title,author,description,timestamp,source;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        webView = findViewById(R.id.webview);
        Article article = getIntent().getParcelableExtra(Constants.ARTICLES);
        imageView = findViewById(R.id.imageView);
        title = findViewById(R.id.title);
        author = findViewById(R.id.author);
        description = findViewById(R.id.description);
        timestamp = findViewById(R.id.timestamp);
        source = findViewById(R.id.source);

        Glide.with(this).load(article.getUrlToImage()).placeholder(R.drawable.placeholder).into(imageView);
        title.setText(article.getTitle());
        author.setText(article.getAuthor());
        description.setText(article.getContent());
        timestamp.setText(article.getPublishedAt());
        source.setText("Source");
        source.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.setVisibility(View.VISIBLE);
                webView = new WebView(NewsDetailsActivity.this);
                webView.loadUrl(article.getUrl());
                setContentView(webView);
            }
        });
    }
}