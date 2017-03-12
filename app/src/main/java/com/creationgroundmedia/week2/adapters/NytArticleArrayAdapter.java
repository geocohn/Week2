package com.creationgroundmedia.week2.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.creationgroundmedia.week2.R;
import com.creationgroundmedia.week2.models.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by geo on 3/11/17.
 */

public class NytArticleArrayAdapter  extends ArrayAdapter<Article> {
    private final static String LOG_TAG = NytArticleArrayAdapter.class.getSimpleName();
    private final Context mContext;

    public NytArticleArrayAdapter(@NonNull Context context, @NonNull List<Article> articles) {
        super(context, 0, articles);
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Article article = getItem(position);

        Log.d(LOG_TAG, "getView(" + position + ", " + convertView + ", " + parent + ")");

         if (convertView == null) {
             LayoutInflater inflater = LayoutInflater.from(mContext);
             convertView = inflater.inflate(R.layout.list_item, parent, false);
         }

        Log.d(LOG_TAG, "imageUrl: " + article.getImageUrl() + ", headline: " + article.getHeadline());

        ImageView ivThumb = (ImageView) convertView.findViewById(R.id.ivThumb);
        TextView tvHeadline = (TextView) convertView.findViewById(R.id.tvHeadline);

        ivThumb.setImageResource(0);
        if (!TextUtils.isEmpty(article.getImageUrl())) {
            Picasso.with(mContext).load(article.getImageUrl()).into(ivThumb);
        }
        tvHeadline.setText(article.getHeadline());

        return convertView;
    }
}
