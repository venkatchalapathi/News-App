package com.venkat.newsapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.venkat.newsapp.Model.Source;
import com.venkat.newsapp.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class NewsSourceAdapter extends RecyclerView.Adapter<NewsSourceAdapter.ViewHolder> {
    List<Source> list;
    onItemClick onItemClick;

    public NewsSourceAdapter(List<Source> list,onItemClick onItemClick) {
        this.list = list;
        this.onItemClick = onItemClick;
    }

    public void setList(List<Source> list) {
        this.list = list;
    }

    public interface onItemClick{
        void onClick(View view,int position,List<Source> lis);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.source_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.source_text.setText(list.get(position).getName());
        holder.description.setText(list.get(position).getDescription());
        holder.source.setText(list.get(position).getUrl());
    }

    @Override
    public int getItemCount() {
        return list != null ? 10 : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView source_text;
        TextView description,source;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            source_text = itemView.findViewById(R.id.source_text);
            cardView = itemView.findViewById(R.id.cardview);
            description = itemView.findViewById(R.id.textView2);
            source = itemView.findViewById(R.id.source);
            cardView.setOnClickListener(this);
            source.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClick.onClick(view,getAdapterPosition(),list);
        }
    }
}
