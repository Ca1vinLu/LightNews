package com.example.lyz.appexercise.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.lyz.appexercise.Bean.NewsBean;
import com.example.lyz.appexercise.R;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LYZ on 2017/5/15 0015.
 */

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<NewsBean> dataList;
    private Context context;

    private Random random = new Random();

    public NewsAdapter(Context context, List<NewsBean> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public int getItemViewType(int position) {
        if (dataList == null || dataList.size() == 0)
            return 1;
        else return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(context).inflate(R.layout.news_item, parent, false);
            return new NewsViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.empty_view, parent, false);
            return new EmpytViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 0) {
            NewsViewHolder viewHolder = (NewsViewHolder) holder;
            NewsBean newsBean = dataList.get(position);
            viewHolder.author.setText(newsBean.getAuthor_name());
            viewHolder.name.setText(newsBean.getTitle());
            viewHolder.time.setText(newsBean.getDate().substring(newsBean.getDate().lastIndexOf(" ")));
            if (newsBean.getThumbnail_pic_s() != null) {
                viewHolder.pic1.setVisibility(View.VISIBLE);
                Glide.with(context).load(newsBean.getThumbnail_pic_s()).diskCacheStrategy(DiskCacheStrategy.RESULT).placeholder(R.drawable.default_image).error(R.drawable.default_image).into(viewHolder.pic1);
            } else viewHolder.pic1.setVisibility(View.INVISIBLE);
            if (newsBean.getThumbnail_pic_s02() != null) {
                viewHolder.pic2.setVisibility(View.VISIBLE);
                Glide.with(context).load(newsBean.getThumbnail_pic_s02()).diskCacheStrategy(DiskCacheStrategy.RESULT).placeholder(R.drawable.default_image).error(R.drawable.default_image).into(viewHolder.pic2);
            } else
                viewHolder.pic2.setVisibility(View.INVISIBLE);
            if (newsBean.getThumbnail_pic_s03() != null) {
                viewHolder.pic3.setVisibility(View.VISIBLE);
                Glide.with(context).load(newsBean.getThumbnail_pic_s03()).diskCacheStrategy(DiskCacheStrategy.RESULT).placeholder(R.drawable.default_image).error(R.drawable.default_image).into(viewHolder.pic3);
            } else
                viewHolder.pic3.setVisibility(View.INVISIBLE);

            random.setSeed(System.currentTimeMillis());
            viewHolder.commentNum.setText(String.valueOf(random.nextInt(100)));
            viewHolder.likeNum.setText(String.valueOf(random.nextInt(200)));

        }


    }

    @Override
    public int getItemCount() {
        return dataList == null || dataList.size() == 0 ? 1 : dataList.size();
    }


    static class NewsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.pic1)
        ImageView pic1;
        @BindView(R.id.pic2)
        ImageView pic2;
        @BindView(R.id.pic3)
        ImageView pic3;
        @BindView(R.id.author)
        TextView author;
        @BindView(R.id.comment_num)
        TextView commentNum;
        @BindView(R.id.like_num)
        TextView likeNum;

        NewsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class EmpytViewHolder extends RecyclerView.ViewHolder {
        EmpytViewHolder(View view) {
            super(view);
        }
    }
}
