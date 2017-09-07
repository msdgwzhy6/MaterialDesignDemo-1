package com.example.ryandu.materialdesigndemo;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * <p>Project:MaterialDesignDemo</p>
 * <p>Package:com.example.ryandu.materialdesigndemo</p>
 * <p>Description:</p>
 * <p>Company:</p>
 *
 * @author duyangs
 * @date 2017/09/07 0007
 */
public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {

    private Context context;
    private List<Articles> articlesList;

    public ArticlesAdapter(List<Articles> articlesList) {
        this.articlesList = articlesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.articles_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Articles articles = articlesList.get(position);
                ArticlesActivity.actionStart(context, articles.getName(), articles.getImageUrl());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Articles articles = articlesList.get(position);
        holder.articlesName.setText(articles.getName());
        Picasso.with(context).load(articles.getImageUrl()).into(holder.articlesImage);
    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView articlesImage;
        TextView articlesName;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            articlesImage = (ImageView) view.findViewById(R.id.articles_image);
            articlesName = (TextView) view.findViewById(R.id.articles_name);
        }
    }
}
