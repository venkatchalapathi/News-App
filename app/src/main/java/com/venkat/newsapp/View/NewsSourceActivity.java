package com.venkat.newsapp.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.venkat.newsapp.Model.Source;
import com.venkat.newsapp.R;
import com.venkat.newsapp.Utils.Constants;

import java.util.ArrayList;


public class NewsSourceActivity extends AppCompatActivity {
    ArrayList<Source> list;
    int position;
    private static final String TAG = "NewsSourceActivity";
    Button nextBtn,previousBtn;
    NewsFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_source);
        nextBtn = findViewById(R.id.next_btn);
        previousBtn = findViewById(R.id.prevous_btn);
        list = new ArrayList<>();
        if (getIntent() != null){
            list = getIntent().getParcelableArrayListExtra(Constants.SOURCE_DATA);
            position = getIntent().getIntExtra(Constants.POSITION,0);
//            Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
        }
        if (position == 0){
            previousBtn.setAlpha(0.5f);
            previousBtn.setClickable(false);
        }else if (position == 9){
            nextBtn.setAlpha(0.5f);
            nextBtn.setClickable(false);
        }
        fragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.SOURCE,list.get(position));
        fragment.setArguments(bundle);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container,fragment);


        nextBtn.setOnClickListener(view -> {
            if (position == 9){
                nextBtn.setAlpha(0.5f);
                nextBtn.setClickable(false);
            }else{
                position++;
                previousBtn.setAlpha(1f);
                previousBtn.setClickable(true);
                fragment = new NewsFragment();
                Bundle bundle1 = new Bundle();
                bundle1.putParcelable(Constants.SOURCE,list.get(position));
                fragment.setArguments(bundle1);
                FragmentTransaction transaction1 = manager.beginTransaction();
                transaction1.replace(R.id.container,fragment);
                transaction1.commit();
            }
        });
        previousBtn.setOnClickListener(view -> {

            if (position == 1){
                previousBtn.setAlpha(0.5f);
                previousBtn.setClickable(false);
            }else{
                position--;
                nextBtn.setAlpha(1f);
                nextBtn.setClickable(true);
                fragment = new NewsFragment();
                Bundle bundle1 = new Bundle();
                bundle1.putParcelable(Constants.SOURCE,list.get(position));
                fragment.setArguments(bundle1);
                FragmentTransaction transaction1  = manager.beginTransaction();
                transaction1.replace(R.id.container,fragment);
                transaction1.commit();
            }
        });
        transaction.commit();


        Log.d(TAG, "onCreate: "+new Gson().toJson(getIntent().getParcelableArrayListExtra(Constants.SOURCE_DATA)));
    }

}