package com.example.movieapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.movieapp.R;
import com.example.movieapp.activity.DetailsActivity;
import com.example.movieapp.model.CategoryItem;
import java.util.ArrayList;

public class RVMainAdapter extends RecyclerView.Adapter<RVMainAdapter.AllCategoryViewHolder> {

    private ArrayList<CategoryItem> list = new ArrayList<>();
    private Context context;

    public RVMainAdapter(Context context) {
        this.context = context;
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public AllCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AllCategoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_main, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AllCategoryViewHolder holder, int position) {
        CategoryItem item = list.get(position);
        holder.tv_title.setText(item.getTitle());
        GlideApp.with(context).load(item.getThumb()).into(holder.imageView);

        holder.itemView.setOnClickListener(v ->
                context.startActivity(new Intent(context, DetailsActivity.class).putExtra("item", item)));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(ArrayList<CategoryItem> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    class AllCategoryViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_title;
        private ImageView imageView;

        public AllCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.itemMainRV_tv);
            imageView = itemView.findViewById(R.id.itemMainRV_iv);
        }
    }
}