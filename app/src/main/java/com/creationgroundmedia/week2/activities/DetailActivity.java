package com.creationgroundmedia.week2.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.creationgroundmedia.week2.R;
import com.creationgroundmedia.week2.models.Article;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    private static final String ARTICLE = "article";

    public static void start(Context context, Article article) {
        Intent intent = new Intent(context, DetailActivity.class);
        Bundle extras = new Bundle();
        extras.putParcelable(ARTICLE, article);
        intent.putExtras(extras);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        final Article article = getIntent().getParcelableExtra(ARTICLE);

        TextView tvHeadline = (TextView) findViewById(R.id.tvHeadline);
        ImageView ivThumb = (ImageView) findViewById(R.id.ivThumb);
        TextView tvSnippet = (TextView) findViewById(R.id.tvSnippet);
        Button btViewArticle = (Button) findViewById(R.id.btViewArticle);

        if (TextUtils.isEmpty(article.getImageUrl())) {
            ivThumb.setVisibility(View.GONE);
        } else {
            ivThumb.setVisibility(View.VISIBLE);
            Picasso.with(this).load(article.getImageUrl()).into(ivThumb);
        }

        tvHeadline.setText(article.getHeadline());
        tvSnippet.setText(article.getSnippet());

        btViewArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(article.getWebUrl()));
                startActivity(intent);
            }
        });
    }
}
