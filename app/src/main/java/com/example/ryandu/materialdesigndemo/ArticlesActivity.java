package com.example.ryandu.materialdesigndemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ArticlesActivity extends AppCompatActivity {
    private static final String ARTICLES_NAME = "articlesName";
    private static final String ARTICLES_IMAGE = "articlesImage";

    public static void actionStart(Context context, String articlesName, String articlesImage) {
        Intent intent = new Intent(context, ArticlesActivity.class);
        intent.putExtra(ARTICLES_NAME, articlesName);
        intent.putExtra(ARTICLES_IMAGE, articlesImage);
        context.startActivity(intent);
    }

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private ImageView articlesImageView;
    private TextView articlesTextView;

    private String articlesName;
    private String articlesImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
        Intent intent = getIntent();
        articlesImage = intent.getStringExtra(ARTICLES_IMAGE);
        articlesName = intent.getStringExtra(ARTICLES_NAME);
        initView();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        articlesImageView = (ImageView) findViewById(R.id.articles_image);
        articlesTextView = (TextView) findViewById(R.id.articles_content);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(articlesName);
        Picasso.with(this).load(articlesImage).into(articlesImageView);
        String articlesContent = generateArticlesContent(articlesName);
        articlesTextView.setText(articlesContent);
    }

    private String generateArticlesContent(String articlesName) {
        StringBuilder articlesContent = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            articlesContent.append(articlesName);
        }
        return articlesContent.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
