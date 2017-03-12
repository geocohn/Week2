package com.creationgroundmedia.week2.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.creationgroundmedia.week2.R;
import com.creationgroundmedia.week2.models.Article;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Article article = getIntent().getParcelableExtra("article");

        TextView tvHeadline = (TextView) findViewById(R.id.tvHeadline);
        ImageView ivThumb = (ImageView) findViewById(R.id.ivThumb);
        TextView tvSnippet = (TextView) findViewById(R.id.tvSnippet);

        if (TextUtils.isEmpty(article.getImageUrl())) {
            ivThumb.setVisibility(View.GONE);
        } else {
            ivThumb.setVisibility(View.VISIBLE);
            Picasso.with(this).load(article.getImageUrl()).into(ivThumb);
        }

        tvHeadline.setText(article.getHeadline());
        tvSnippet.setText(article.getSnippet());
    }
}
