package com.example.movieapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.movieapp.activity.DetailsActivity;
import com.example.movieapp.R;
import com.example.movieapp.model.BannerModel;
import java.util.ArrayList;

public class BannerAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<BannerModel> list;

    public BannerAdapter(Context context, ArrayList<BannerModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view= LayoutInflater.from(context).inflate(R.layout.layout_banner,container,false);
        ImageView imageView=view.findViewById(R.id.banner_iv);
        Glide.with(context).load(list.get(position).getImageUrl()).into(imageView);
        container.addView(view);

        imageView.setOnClickListener(v -> {
            Intent intent=new Intent(context, DetailsActivity.class);
            intent.putExtra("name",list.get(position).getMovieName());
            intent.putExtra("imageUrl",list.get(position).getImageUrl());
            context.startActivity(intent);
        });
        return view;
    }
}
