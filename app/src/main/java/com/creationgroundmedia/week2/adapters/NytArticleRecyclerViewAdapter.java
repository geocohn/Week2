package com.creationgroundmedia.week2.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.creationgroundmedia.week2.R;
import com.creationgroundmedia.week2.activities.DetailActivity;
import com.creationgroundmedia.week2.models.Article;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geo on 3/11/17.
 */

public class NytArticleRecyclerViewAdapter extends RecyclerView.Adapter<NytArticleRecyclerViewAdapter.ViewHolder> {
    private final static String LOG_TAG = NytArticleRecyclerViewAdapter.class.getSimpleName();
    private Context mContext;
    private List<Article> mArticles;

    public  NytArticleRecyclerViewAdapter(Context context, ArrayList<Article> articles) {
        mContext = context;
        mArticles = articles;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(mContext)
                .inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Article article = mArticles.get(position);

        Log.d(LOG_TAG, "imageUrl: " + article.getImageUrl() + ", headline: " + article.getHeadline());

        holder.ivThumb.setImageResource(0);
        if (!TextUtils.isEmpty(article.getImageUrl())) {
            Picasso.with(mContext).load(article.getImageUrl()).into(holder.ivThumb);
        }
        holder.tvHeadline.setText(article.getHeadline());

        holder.cvContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailActivity.start(mContext, article);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CardView cvContainer;
        ImageView ivThumb;
        TextView tvHeadline;

        public ViewHolder(View itemView) {
            super(itemView);

            cvContainer = (CardView) itemView.findViewById(R.id.cvContainer);
            ivThumb = (ImageView) itemView.findViewById(R.id.ivThumb);
            tvHeadline = (TextView) itemView.findViewById(R.id.tvHeadline);
        }
    }
}
