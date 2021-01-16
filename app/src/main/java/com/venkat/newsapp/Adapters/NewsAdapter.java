package com.venkat.newsapp.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.venkat.newsapp.Model.Article;
import com.venkat.newsapp.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private static final String TAG = "NewsAdapter";
    Context context;
    List<Article> list;
    OnItemClick onItemClick;


    public NewsAdapter(Context context,List<Article> list, OnItemClick onItemClick) {
        this.context = context;
        this.list = list;
        this.onItemClick = onItemClick;
    }

    public void setList(List<Article> list) {
        this.list = list;
    }

    public interface OnItemClick {
        void onClick(Article position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.news_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getUrlToImage()).placeholder(R.drawable.placeholder).into(holder.image);
        Log.d(TAG, "onBindViewHolder: "+list.get(position).getUrlToImage());
        holder.title.setText(list.get(position).getTitle());
        holder.time_stamp.setText(list.get(position).getPublishedAt());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onClick(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title, time_stamp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            time_stamp = itemView.findViewById(R.id.time_stamp);

        }
    }
}
