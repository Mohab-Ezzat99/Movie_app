package com.example.movieapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.movieapp.databinding.ActivityDetailsBinding;
import com.example.movieapp.model.CategoryItem;

public class DetailsActivity extends AppCompatActivity {

    private ActivityDetailsBinding binding;
    private CategoryItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        item= (CategoryItem) getIntent().getSerializableExtra("item");
        if(item!=null){
            //movies
            binding.detailsFab.setVisibility(View.VISIBLE);
            Glide.with(this).load(item.getThumb()).into(binding.detailsIv);
            binding.detailsTvName.setText(item.getTitle());
            binding.detailsTvAuthor.setText(item.getSubtitle());
            binding.detailsTvDesc.setText(item.getDescription());
        }
        else {
            //banners
            binding.detailsFab.setVisibility(View.GONE);
            Glide.with(this).load(getIntent().getStringExtra("imageUrl")).into(binding.detailsIv);
            binding.detailsTvName.setText(getIntent().getStringExtra("name"));
        }

        binding.detailsFab.setOnClickListener(v -> {
            startActivity(new Intent(getBaseContext(),VideoPlayerActivity.class).putExtra("url",item.getSources().get(0)));
        });
    }
}