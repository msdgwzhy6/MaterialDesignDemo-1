package com.example.ryandu.materialdesigndemo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private CoordinatorLayout coordinatorLayout;
    private Toolbar toolbar;
    private FloatingActionButton floatingActionButton;
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    private Articles[] articles = {
            new Articles("算法-总章", "http://upload-images.jianshu.io/upload_images/6373593-c479283cf1334781.png"),
            new Articles("Activity启动模式(launchMode)详解", "http://upload-images.jianshu.io/upload_images/6373593-0050947459f5aeb8.png"),
            new Articles("插入排序", "http://upload-images.jianshu.io/upload_images/6373593-bddc6d205c1962bc.jpeg"),
            new Articles("Material Design之Snackbar", "http://upload-images.jianshu.io/upload_images/6373593-37b0c3cb82598e03.png"),
            new Articles("Material Design你真的了解吗？", "http://upload-images.jianshu.io/upload_images/6373593-29b2f104a9d0da2a.png"),
            new Articles("Material Design之Motion", "http://upload-images.jianshu.io/upload_images/6373593-6410a49c4ebf212b.png"),
            new Articles("Toolbar快速实现，让你一分钟上手", "http://upload-images.jianshu.io/upload_images/6373593-7871d294e2e6b645.png"),
            new Articles("Android Service(服务)详解", "http://upload-images.jianshu.io/upload_images/6373593-41f1fdcf345bc6d9.png"),
            new Articles("Git命令大集结", "http://upload-images.jianshu.io/upload_images/6373593-3cc4867c72907f38.png")
    };

    private List<Articles> articlesList = new ArrayList<>();
    private ArticlesAdapter adapter;

    public static void actionStart(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initArticles();
        initView();
    }


    private void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.more);
        }
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new ArticlesAdapter(articlesList);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                rrefreshArticles();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        switch (item.getItemId()) {
            case android.R.id.home:
            case R.id.more:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.about:
                intent.setData(Uri.parse("https://duyangs.github.io"));
                startActivity(intent);
                break;
            case R.id.github:
                intent.setData(Uri.parse("https://github.com/duyangs/MaterialDesignDemo"));
                startActivity(intent);
                break;

        }

        return true;
    }

    private void initArticles() {
        articlesList.clear();
        for (int i = 0; i < 40; i++) {
            Random random = new Random();
            int index = random.nextInt(articles.length);
            articlesList.add(articles[index]);
        }
    }

    private void rrefreshArticles() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initArticles();
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }
}
